package role;

import java.io.IOException;

import msg.PublishMsg;
import msg.RegisterMsg;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.ZkClient;

public class Provider extends Person {

	private static final Logger logger = LoggerFactory
			.getLogger(Provider.class);

	public Provider(String serviceName, ZkClient zkClient) throws IOException,
			KeeperException, InterruptedException {
		super(serviceName, zkClient);
	}

	// 定义服务
	public void publishService(PublishMsg publishMsg) {
		try {
			zkClient.publishServiceDefine(publishMsg);
			logger.debug("发布服务"
					+ publishMsg.getServiceDefineInfo().getServicename()
					+ "成功！");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发布服务"
					+ publishMsg.getServiceDefineInfo().getServicename()
					+ "失败！", e);
		}
	}

	// 注册服务
	public void registerService(RegisterMsg registerMsg) throws Exception {
		try {
			zkClient.registerServer(registerMsg);
		} catch (Exception e) {
			logger.error("注册 "
					+ registerMsg.getServeiceInstanceInfo().getServiceName()
					+ " 服务失败", e);
			throw e;
		}
	}

	@Override
	protected void deal(WatchedEvent event) {
		//不需要信息
	}

}
