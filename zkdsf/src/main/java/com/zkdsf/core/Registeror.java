package com.zkdsf.core;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

public class Registeror extends Person
{

	public Registeror(ZkClient zkClient) throws IOException
	{
		super(zkClient);
	}

	// 注册服务
	public void registerService(RegisterMsg registerMsg)
	{
		try
		{
			zkClient.registerServer(registerMsg);
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



	@Override
	public void deal(WatchedEvent event)
	{
		
	}

}
