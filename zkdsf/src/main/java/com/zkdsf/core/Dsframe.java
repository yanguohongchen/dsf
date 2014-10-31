package com.zkdsf.core;

import java.io.IOException;

import msg.ZkInfo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class Dsframe
{
	
	public void getZkClient(ZkInfo zkInfo) throws IOException
	{
//		ZkClient zkClient = new ZkClient(zkInfo.getConnectAddr(),zkInfo.getTimeout(),new ZkWatch());
//		
////		Publisher publisher = new Publisher(zkClient);
////		Registeror registeror = new Registeror(zkClient);
////		Subscriber subscriber = new Subscriber(zkClient);
	}
	
	class ZkWatch implements Watcher
	{

		public void process(WatchedEvent event)
		{
		}

	}
	
}
