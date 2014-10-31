package role;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import msg.ServeiceInstanceInfo;
import msg.SubscriberInfo;
import msg.SubscriberMsg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

import com.zkdsf.core.ZkClient;

/**
 * 服务订阅者
 * 
 * @author sea
 * 
 */
public class Subscriber extends Person
{
	//此信息由订阅者特有信息
	private Map<String, ServeiceInstanceInfo> map;
	public Subscriber(String serviceName, String serverName, String owner, ZkClient zkClient) throws IOException, KeeperException, InterruptedException
	{
		super(serviceName,zkClient);
		subscribeService(serverName, owner);
		watch();
	}

	// 订阅服务
	public void subscribeService(String serverName, String owner) throws UnknownHostException
	{
		SubscriberMsg subscriberMsg = new SubscriberMsg();
		subscriberMsg.setServiceName(serviceName);
		SubscriberInfo subscriberInfo = new SubscriberInfo();
		InetAddress addr = InetAddress.getLocalHost();
		subscriberInfo.setIp(addr.getHostAddress());
		subscriberInfo.setHostName(addr.getHostName());
		subscriberInfo.setDatetime(System.currentTimeMillis());
		subscriberInfo.setOwner(owner);
		subscriberInfo.setServerName(serverName);
		try
		{
			zkClient.subscriberService(subscriberMsg);
		} catch (KeeperException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据路由策略获取服务实例，并从连接池中获取一个连接
	//如果获取连接失败，根据失败策略作出处理
	public void getClient()
	{
		
	}

	@Override
	public void deal(WatchedEvent event)
	{
		// 处理监听
		try
		{
			dealService();
		} catch (KeeperException | InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void watch()
	{
		try
		{
			dealService();
		} catch (KeeperException | InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dealService() throws KeeperException, InterruptedException
	{
		Map<String, ServeiceInstanceInfo> newmap = zkClient.queryServiceInstanceInfos(serviceName, new ZkWatch());
		for (String newkey : newmap.keySet())
		{
			if (!map.containsKey(newkey))//新增的服务端
			{
				System.out.println("新增的服务："+newkey);
			}
		}
		for (String newkey : map.keySet())
		{
			if (!newmap.containsKey(newkey))//已下线的服务端
			{
				System.out.println("已经下线的服务端："+newkey);
			}
		}
		//更新在线服务列表
		map = newmap;
	}

}
