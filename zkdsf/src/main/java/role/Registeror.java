package role;

import java.io.IOException;

import msg.RegisterMsg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

import com.zkdsf.core.ZkClient;

public class Registeror extends Person
{

	public Registeror(String serviceName,ZkClient zkClient) throws IOException, KeeperException, InterruptedException
	{
		super(serviceName,zkClient);
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
		//暂时不需要信息
	}

}
