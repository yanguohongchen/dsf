package com.zkdsf.core;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

/**
 * 服务发布者
 * 
 * @author sea
 *
 */
public class Publisher extends Person
{

	public Publisher(ZkClient zkClient) throws IOException
	{
		super(zkClient);
	}

	// 定义服务
	public void publishService(PublishMsg publishMsg)
	{
		try
		{
			zkClient.publishServiceDefine(publishMsg);
		} catch (KeeperException e)
		{
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}
	}
	

	@Override
	public void deal(WatchedEvent event)
	{

	}


}
