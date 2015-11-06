package role;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import msg.ServeiceInstanceInfo;
import msg.SubscriberInfo;
import msg.SubscriberMsg;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.AvroClientFactory;
import client.AvroClientPool;
import core.ZkClient;

/**
 * 服务订阅者
 * 
 * @author sea
 * @param <E>
 * 
 */
public class Subscriber extends Person
{

	private static final Logger logger = LoggerFactory.getLogger(Subscriber.class);

	// 此信息由订阅者特有信息
	private LinkedHashMap<String, ServeiceInstanceInfo> localMap = new LinkedHashMap<>();

	private Map<String, AvroClientPool<Object>> clientPoolMap = new HashMap<>();

	private GenericObjectPoolConfig cfg;

	public Subscriber(String serviceName, ZkClient zkClient) throws IOException, KeeperException, InterruptedException
	{
		super(serviceName, zkClient);
		serviceDefineInfo = zkClient.queryServiceDefineInfo(serviceName, new DefineWatcher());
		choseRoute(serviceDefineInfo);
		choseFailStrategy(serviceDefineInfo);
		if(serviceDefineInfo.getServicename()==null){
			throw new RuntimeException("服务未定义！请联系相关人员！");
		}else{
			logger.debug(serviceDefineInfo.toString());
		}
	}

	// 订阅服务
	public void subscribeService(String serverName, String owner, GenericObjectPoolConfig cfg) throws UnknownHostException, KeeperException, InterruptedException
	{
		this.cfg = cfg;
		SubscriberMsg subscriberMsg = new SubscriberMsg();
		subscriberMsg.setServiceName(serviceName);
		SubscriberInfo subscriberInfo = new SubscriberInfo();
		InetAddress addr = InetAddress.getLocalHost();
		subscriberInfo.setIp(addr.getHostAddress());
		subscriberInfo.setHostName(addr.getHostName());
		subscriberInfo.setDatetime(System.currentTimeMillis());
		subscriberInfo.setOwner(owner);
		subscriberInfo.setServerName(serverName);
		subscriberMsg.setSubscribeInfo(subscriberInfo);
		try
		{
			zkClient.subscriberService(subscriberMsg);
		} catch (Exception e)
		{
			logger.error("订阅" + serviceName + "服务失败!", e);
			throw e;
		}
		watch();
	}

	// 根据路由策略获取服务实例，并从连接池中获取一个连接
	// 如果获取连接失败，根据失败策略作出处理
	public Object choseClient()
	{
		if(localMap.isEmpty()){
			throw new RuntimeException("服务"+serviceName+"没有实例，请联系相关负责人！");
		}
		List<ServeiceInstanceInfo> instances = new ArrayList<ServeiceInstanceInfo>();
		Set<String> set = localMap.keySet();
		for (String string : set)
		{
			instances.add(localMap.get(string));
		}
		// 路由策略
		ServeiceInstanceInfo serveiceInstanceInfo = routeStrategy.getInstance(instances);
		AvroClientPool<Object> avroClientPool = clientPoolMap.get(serveiceInstanceInfo.toString());

		try
		{
			Object o = avroClientPool.borrowObject();
			return o;
		} catch (Exception e)
		{
			// 失败策略
			ServeiceInstanceInfo newServeiceInstanceInfo = failStrategy.failover(serviceDefineInfo, serveiceInstanceInfo, instances, e);
			avroClientPool = clientPoolMap.get(newServeiceInstanceInfo.toString());
			try
			{
				Object o = avroClientPool.borrowObject();
				return o;
			} catch (Exception e1)
			{
				throw new RuntimeException("调用服务" + serviceDefineInfo.getServicename() + "失败", e);
			}

		}
	}

	@Override
	protected void deal(WatchedEvent event)
	{
		if (event.getType() == EventType.NodeChildrenChanged)
		{
			try
			{
				zkClient.addWatcherRegisterMsgs(serviceName, watcher);
			} catch (KeeperException e)
			{
				logger.error(serviceName + " not exist!", e);
			} catch (InterruptedException e)
			{
				logger.error("zk事务异常", e);
			}
		} else
		{
			// 处理监听
			dealService();
		}
	}

	private void watch()
	{
		dealService();
	}

	private void dealService()
	{
		try
		{
			LinkedHashMap<String, ServeiceInstanceInfo> remoteMap = zkClient.queryServiceInstanceInfos(serviceName, watcher);
			for (String newkey : remoteMap.keySet())
			{
				if (!localMap.containsKey(newkey))// 新增的服务端
				{
					logger.debug("新增的实例：" + newkey);
					createClientPool(remoteMap.get(newkey));
				}
			}
			for (String newkey : localMap.keySet())
			{
				if (!remoteMap.containsKey(newkey))// 已下线的服务端
				{
					logger.debug("下线的实例：" + newkey);
					destoryClientPool(newkey);
				}
			}
			// 更新本地服务列表
			localMap = remoteMap;
		} catch (KeeperException e)
		{
			logger.error(serviceName + " not exist!", e);
		} catch (InterruptedException e)
		{
			logger.error("zk事务异常", e);
		}

	}

	private void createClientPool(ServeiceInstanceInfo serveiceInstanceInfo)
	{
		try
		{
			@SuppressWarnings("unchecked")
			Class<Object> target = (Class<Object>) Class.forName(serviceDefineInfo.getProxyClass());
			AvroClientFactory<Object> avroClientFactory = new AvroClientFactory<Object>(target, serveiceInstanceInfo.getIp(), serveiceInstanceInfo.getPort());
			AvroClientPool<Object> avroClientPool = new AvroClientPool<Object>(serveiceInstanceInfo.toString(), avroClientFactory, cfg);
			clientPoolMap.put(serveiceInstanceInfo.toString(), avroClientPool);
		} catch (Exception e)
		{
			logger.error("创建服务实例连接池失败：" + serveiceInstanceInfo.toString(), e);
		}
	}

	private void destoryClientPool(String key)
	{
		AvroClientPool<Object> avroClientPool = clientPoolMap.get(key);
		avroClientPool.destory();
		clientPoolMap.remove(key);
	}

}
