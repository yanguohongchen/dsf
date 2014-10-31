package role;

import java.io.IOException;

import msg.ServiceDefineInfo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import com.zkdsf.core.ZkClient;

public abstract class Person
{

	protected ZkClient zkClient;
	
	protected String serviceName;
	
	protected ServiceDefineInfo serviceDefineInfo;
	

	public Person(String serviceName,ZkClient zkClient) throws IOException, KeeperException, InterruptedException
	{
		this.zkClient = zkClient;
		serviceDefineInfo = zkClient .queryServiceDefineInfo(serviceName,new DefineWatcher());
		this.serviceName = serviceName;

	}

	public ZkClient getZkClient()
	{
		return zkClient;
	}
	
	public abstract void deal(WatchedEvent event);
	
	
	/**
	 * 更新定义信息
	 * @author sea
	 *
	 */
	protected class DefineWatcher implements Watcher
	{
		public void process(WatchedEvent event)
		{
			try
			{
				serviceDefineInfo = zkClient .queryServiceDefineInfo(serviceName,new DefineWatcher());
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
	}
	
	
	protected class ZkWatch implements Watcher
	{

		public void process(WatchedEvent event)
		{
			deal(event);
		}

	}

}
