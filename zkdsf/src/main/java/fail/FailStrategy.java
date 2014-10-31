package fail;

import java.util.List;

import com.zkdsf.core.ServeiceInstanceInfo;
import com.zkdsf.core.ServiceDefineInfo;

public interface FailStrategy
{
	/**
	 * 初始化线程上下文
	 */
	public void initThreadContext();

	/**
	 * 失败转移
	 * 
	 * @param serviceDefine
	 *            服务定义
	 * @param failInstance
	 *            失败的服务实例
	 * @param onlineInstances
	 *            当前在线服务实例
	 * @return 返回转移服务实例
	 */
	public ServeiceInstanceInfo failover(ServiceDefineInfo serviceDefine, ServeiceInstanceInfo failInstance, List<ServeiceInstanceInfo> onlineInstances, Throwable exception);

	/**
	 * 清理线程上下文
	 */
	public void cleanThreadContext();
}
