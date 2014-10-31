package role;

import java.io.IOException;

import msg.PublishMsg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

import com.zkdsf.core.ZkClient;

/**
 * 服务发布者
 * 
 * @author sea
 *
 */
public class Publisher extends Person
{

	public Publisher(String serviceName,ZkClient zkClient) throws IOException, KeeperException, InterruptedException
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
