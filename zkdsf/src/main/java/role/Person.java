package role;

import java.io.IOException;

import msg.ServiceDefineInfo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import route.RandomAccess;
import route.RoundRobin;
import route.RouteStrategy;
import route.WeightingRoundRobin;

import com.zkdsf.core.ZkClient;

import fail.FailStrategy;

public abstract class Person
{

	protected Watcher watcher = new ZkWatch();
	
	protected ZkClient zkClient;

	protected String serviceName;

	protected ServiceDefineInfo serviceDefineInfo;

	protected RouteStrategy routeStrategy;

	protected FailStrategy failStrategy;

	public Person(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public Person(String serviceName, ZkClient zkClient) throws IOException, KeeperException, InterruptedException
	{
		this.zkClient = zkClient;
		serviceDefineInfo = zkClient.queryServiceDefineInfo(serviceName, new DefineWatcher());
		this.serviceName = serviceName;

	}

	public ZkClient getZkClient()
	{
		return zkClient;
	}

	protected abstract void deal(WatchedEvent event);

	/**
	 * 更新定义信息
	 * 
	 * @author sea
	 *
	 */
	protected class DefineWatcher implements Watcher
	{
		public void process(WatchedEvent event)
		{
			try
			{
				serviceDefineInfo = zkClient.queryServiceDefineInfo(serviceName, new DefineWatcher());
				choseRoute(serviceDefineInfo);
				choseFailStrategy(serviceDefineInfo);
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

	private void choseRoute(ServiceDefineInfo serviceDefineInfo)
	{
		String route = serviceDefineInfo.getRoutestrage();
		if (route.equals("random"))
		{
			routeStrategy = new RandomAccess();
		} else if (route.equals("round"))
		{
			routeStrategy = new RoundRobin();
		} else if (route.equals("weightRound"))
		{
			routeStrategy = new WeightingRoundRobin();
		}

	}

	private void choseFailStrategy(ServiceDefineInfo serviceDefineInfo)
	{
		String failStrategy = serviceDefineInfo.getFailstrage();
		if (failStrategy.equals("failfast"))
		{
			
		} else if (failStrategy.equals("failover"))
		{
			
		}
	}

}
