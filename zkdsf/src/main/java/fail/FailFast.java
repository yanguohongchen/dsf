package fail;

import java.util.List;

import msg.ServeiceInstanceInfo;
import msg.ServiceDefineInfo;

/**
 * 快速失败，只发起一次调用，失败立即报错,通常用于非幂等性的写操作
 * @author sean
 */
public class FailFast implements FailStrategy
{

	@Override
	public void initThreadContext()
	{
	}

	@Override
	public void cleanThreadContext()
	{
	}

	@Override
	public ServeiceInstanceInfo failover(ServiceDefineInfo serviceDefine, ServeiceInstanceInfo failInstance, List<ServeiceInstanceInfo> onlineInstances, Throwable exception)
	{
		throw new RuntimeException("调用服务" + serviceDefine.getServicename() + "失败", exception);
	}

}
