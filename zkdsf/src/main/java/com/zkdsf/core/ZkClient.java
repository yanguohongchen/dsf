package com.zkdsf.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import msg.PublishMsg;
import msg.RegisterMsg;
import msg.ServeiceInstanceInfo;
import msg.ServiceDefineInfo;
import msg.SubscriberInfo;
import msg.SubscriberMsg;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.google.gson.Gson;

public class ZkClient
{

	private ZooKeeper zk;
	private Gson gson = new Gson();

	/**
	 * 连接到zkserver集群
	 * 
	 * @param hosts
	 *            zk列表(192.138.23.1:2181,123.55.45.15:2181)
	 * @param timeout
	 *            超时时间
	 * @throws IOException
	 */
	public void connect(String hosts, int timeout, Watcher watcher) throws IOException
	{
		zk = new ZooKeeper(hosts, timeout, watcher);
	}

	public ZkClient(String hosts, int timeout, Watcher watcher) throws IOException
	{
		this.connect(hosts, timeout, watcher);
	}
	
	public void close() throws InterruptedException{
		zk.close();
	}

	/**
	 * 发布定义
	 * 
	 * @param publishMsg
	 * @param watcher
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void publishServiceDefine(PublishMsg publishMsg) throws KeeperException, InterruptedException
	{
		ServiceDefineInfo serviceDefineInfo = publishMsg.getServiceDefineInfo();
		String serviceName = "/" + serviceDefineInfo.getServicename();
		String server = serviceName +"/server";
		String client = serviceName +"/client";
		zk.create(serviceName, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zk.setData(serviceName, gson.toJson(publishMsg).getBytes(), 0);
		zk.create(server, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zk.create(client, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	/**
	 * 注册服务
	 * 
	 * @param registerMsg
	 * @param watcher
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void registerServer(RegisterMsg registerMsg) throws KeeperException, InterruptedException
	{
		ServeiceInstanceInfo serveiceInstanceInfo = registerMsg.getServeiceInstanceInfo();
		String serverName = "/" + serveiceInstanceInfo.getServiceName() + "/server/" + serveiceInstanceInfo.toString();
		zk.create(serverName, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		zk.setData(serverName, gson.toJson(registerMsg).getBytes(), 1);
	}

	/**
	 * 
	 * 订阅服务
	 */
	public void subscriberService(SubscriberMsg subscriberMsg) throws KeeperException, InterruptedException
	{
		SubscriberInfo subscriberInfo = subscriberMsg.getSubscribeInfo();
		String clientName = "/" + subscriberMsg.getServiceName() + "/client/" + subscriberInfo.getServerName();
		zk.create(clientName, null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		zk.setData(clientName, gson.toJson(subscriberMsg).getBytes(), 1);

	}

	/**
	 * 获取服务注册所有信息
	 * 
	 * @param serviceName
	 * @param watcher
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public List<RegisterMsg> queryRegisterMsgs(String serviceName, Watcher watcher) throws KeeperException, InterruptedException
	{
		List<String> childs = zk.getChildren("/" + serviceName + "/server", watcher);
		List<RegisterMsg> registerMsgs = new ArrayList<RegisterMsg>();
		Stat stat = new Stat();
		for (String child : childs)
		{
			String childpath = "/" + serviceName + "/server/" + child;
			registerMsgs.add(gson.fromJson(new String(zk.getData(childpath, false, stat)), RegisterMsg.class));
		}
		return registerMsgs;
	}

	/**
	 * 获取服务列表
	 * 
	 * @param serviceName
	 * @param watcher
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public Map<String,ServeiceInstanceInfo> queryServiceInstanceInfos(String serviceName, Watcher watcher) throws KeeperException, InterruptedException
	{
		List<RegisterMsg> registerMsgs = queryRegisterMsgs(serviceName, watcher);
		Map<String,ServeiceInstanceInfo> map = new HashMap<String,ServeiceInstanceInfo>();
		for (RegisterMsg registerMsg : registerMsgs)
		{
			map.put(registerMsg.getServeiceInstanceInfo().toString(),registerMsg.getServeiceInstanceInfo());
		}
		return map;
	}

	/**
	 * 获取订阅者信息
	 * 
	 * @param serviceName
	 * @param watcher
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public List<SubscriberMsg> querySubscribers(String serviceName, Watcher watcher) throws KeeperException, InterruptedException
	{
		List<String> childs = zk.getChildren("/" + serviceName + "/client", watcher);
		List<SubscriberMsg> subscriberMsgs = new ArrayList<SubscriberMsg>();
		Stat stat = new Stat();
		for (String child : childs)
		{
			String childpath = "/" + serviceName + "/client/" + child;
			subscriberMsgs.add(gson.fromJson(new String(zk.getData(childpath, false, stat)), SubscriberMsg.class));
		}
		return subscriberMsgs;
	}
	
	/**
	 * 获取查询服务
	 * @param serviceName
	 * @param watcher
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public ServiceDefineInfo queryServiceDefineInfo(String serviceName,Watcher watcher) throws KeeperException, InterruptedException{
		Stat stat = new Stat();
		serviceName = "/"+serviceName;
		byte[] data = zk.getData(serviceName, watcher, stat);
		ServiceDefineInfo serviceDefineInfo = gson.fromJson(new String(data), ServiceDefineInfo.class);
		return serviceDefineInfo;
	}

}
