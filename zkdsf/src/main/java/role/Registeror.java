package role;

import java.io.IOException;

import msg.RegisterMsg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zkdsf.core.ZkClient;

public class Registeror extends Person
{

	private static final Logger logger = LoggerFactory.getLogger(Subscriber.class);

	public Registeror(String serviceName, ZkClient zkClient) throws IOException, KeeperException, InterruptedException
	{
		super(serviceName, zkClient);
	}

	// 注册服务
	public void registerService(RegisterMsg registerMsg) throws Exception
	{
		try
		{
			zkClient.registerServer(registerMsg);
		} catch (Exception e)
		{
			logger.error("注册 " + registerMsg.getServeiceInstanceInfo().getServiceName() + " 服务失败", e);
			throw e;
		}
	}

	@Override
	public void deal(WatchedEvent event)
	{
		// 暂时不需要信息
	}

}
