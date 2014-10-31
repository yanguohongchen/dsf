package com.zkdsf.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

/**
 * 服务订阅者
 * 
 * @author sea
 * 
 */
public class Subscriber extends Person
{


	private ServiceDefineInfo serviceDefineInfo;
	
	private Map<String, ServeiceInstanceInfo> map;

	public Subscriber(ServiceDefineInfo serviceDefineInfo, String serverName, String owner, ZkClient zkClient) throws IOException
	{
		super(zkClient);
		this.serviceDefineInfo = serviceDefineInfo;
		this.serviceName = serviceDefineInfo.getServicename();
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

	// 根据得到得服务列表创建连接池
	// 获取一个客户端链接
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
				
			}
		}
		for (String newkey : map.keySet())
		{
			if (!newmap.containsKey(newkey))//已下线的服务端
			{
				
			}
		}
		//更新在线服务列表
		map = newmap;
	}

}
