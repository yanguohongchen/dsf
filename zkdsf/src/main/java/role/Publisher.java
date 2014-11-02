package role;

import java.io.IOException;

import msg.PublishMsg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zkdsf.core.ZkClient;

/**
 * 服务发布者
 * 
 * @author sea
 *
 */
public class Publisher extends Person
{

	private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

	public Publisher(String serviceName, ZkClient zkClient) throws IOException, KeeperException, InterruptedException
	{
		super(serviceName);
		this.zkClient = zkClient;
	}

	// 定义服务
	public void publishService(PublishMsg publishMsg)
	{
		try
		{
			zkClient.publishServiceDefine(publishMsg);
			logger.debug("发布服务" + publishMsg.getServiceDefineInfo().getServicename() + "成功！");

		} catch (Exception e)
		{
			e.printStackTrace();
			logger.error("发布服务" + publishMsg.getServiceDefineInfo().getServicename() + "失败！", e);
		}
	}

	@Override
	public void deal(WatchedEvent event)
	{

	}

}
