package route;

import java.util.List;

import msg.ServeiceInstanceInfo;

/**
 * 轮询的路由策略，所有在线服务实例拥有相同的权重
 * @author sean
 */
public class RoundRobin implements RouteStrategy
{
	private int index = 0;

	@Override
	public ServeiceInstanceInfo getInstance(List<ServeiceInstanceInfo> instances)
	{
		if (instances != null && !instances.isEmpty())
		{
			int size = instances.size();
			if (size > 0)
			{
				index++;
				if (index >= size)
				{
					index = 0;
				}
				return instances.get(index);
			}
		}
		return null;
	}
}
