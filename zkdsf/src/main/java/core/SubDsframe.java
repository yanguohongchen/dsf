package core;

import java.io.IOException;

import msg.PublishMsg;
import msg.RegisterMsg;
import msg.ServeiceInstanceInfo;
import msg.ServiceDefineInfo;
import msg.ZkInfo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import role.Publisher;
import role.Registeror;
import role.Subscriber;

public class SubDsframe
{

	public void start(ZkInfo zkInfo) throws IOException
	{
		ZkClient zkClient = new ZkClient(zkInfo.getConnectAddr(), zkInfo.getTimeout(), new ZkWatch());
//		createService( zkInfo,  zkClient);
//		  registerServer(zkInfo, zkClient);
		  subscriber(zkClient);
		
	}

	private void subscriber(ZkClient zkClient) throws IOException
	{
		try
		{
			Subscriber subscriber = new Subscriber("test" ,zkClient);
			GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
			subscriber.subscribeService("testapp", "zhanghai", genericObjectPoolConfig);
			Object o = subscriber.choseClient();
			
			Thread.sleep(1000*60*60);
			
		} catch (KeeperException | InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void registerServer(ZkInfo zkInfo, ZkClient zkClient) throws Exception
	{
		try
		{
			Registeror registeror = new Registeror(zkInfo.getServiceName(),zkClient);
			RegisterMsg registerMsg = new RegisterMsg();
			ServeiceInstanceInfo serveiceInstanceInfo = new ServeiceInstanceInfo();
			serveiceInstanceInfo.setIp("1.1.1.12");
			serveiceInstanceInfo.setPort(80);
			serveiceInstanceInfo.setServiceName("test");
			serveiceInstanceInfo.setState("true");
			serveiceInstanceInfo.setWeightValue(20);
			registerMsg.setServeiceInstanceInfo(serveiceInstanceInfo);
			registeror.registerService(registerMsg);
			Thread.sleep(1000*60*2);
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

	private void createService(ZkInfo zkInfo, ZkClient zkClient) throws IOException
	{
		try
		{
			Publisher publisher = new Publisher(zkInfo.getServiceName(), zkClient);
			ServiceDefineInfo serviceDefineInfo = new ServiceDefineInfo();
			serviceDefineInfo.setDescription("测试服务");
			serviceDefineInfo.setFailstrage("fail");
			serviceDefineInfo.setProtol("frame");
			serviceDefineInfo.setProxyClass("com.baidu.test.class");
			serviceDefineInfo.setRoutestrage("round");
			serviceDefineInfo.setServicename(zkInfo.getServiceName());
			serviceDefineInfo.setTimeout(1000);
			serviceDefineInfo.setVersion("1");
			PublishMsg publishMsg = new PublishMsg("zhanghai", serviceDefineInfo);
			publisher.publishService(publishMsg);
		} catch (KeeperException | InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class ZkWatch implements Watcher
	{

		public void process(WatchedEvent event)
		{
			
		}

	}

	public static void main(String[] args)
	{
		ZkInfo zkInfo = new ZkInfo();
		zkInfo.setConnectAddr("127.0.0.1:2181");
		zkInfo.setTimeout(1000);
		zkInfo.setServiceName("test");
		SubDsframe dsframe = new SubDsframe();
		try
		{                   
			dsframe.start(zkInfo);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
