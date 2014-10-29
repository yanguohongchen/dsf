package com.zkdsf.core;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public abstract class Person
{

	protected ZkClient zkClient;
	
	protected String serviceName;

	public Person(ZkClient zkClient) throws IOException
	{
		this.zkClient = zkClient;

	}

	public ZkClient getZkClient()
	{
		return zkClient;
	}
	
	public abstract void deal(WatchedEvent event);
	
	
	
	protected class ZkWatch implements Watcher
	{

		public void process(WatchedEvent event)
		{
			deal(event);
		}

	}

}
