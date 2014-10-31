package route;

import java.util.List;
import java.util.Random;

import msg.ServeiceInstanceInfo;

/**
 * 带权重轮询的路由策略
 * @author sean
 */
public class WeightingRoundRobin implements RouteStrategy
{
	private Random random = new Random(System.currentTimeMillis());

	@Override
	public ServeiceInstanceInfo getInstance(List<ServeiceInstanceInfo> instances)
	{
		if (instances != null && !instances.isEmpty())
		{
			if (instances.size() == 1)
			{
				return instances.get(0);
			}
			else
			{
				// 放大权重值
				int factor = 1000;
				float probablity = 0;
				for (ServeiceInstanceInfo it : instances)
				{
					probablity += it.getWeightValue() * factor;
				}

				// 权重区间为0-maxRndNumber
				int maxRndNumber = (int) Math.ceil(probablity);
				// 生成随机数
				int rndNumber = this.random.nextInt(maxRndNumber);

				float currVal = 0;
				float lastVal = 0;
				for (ServeiceInstanceInfo it : instances)
				{
					currVal += it.getWeightValue() * factor;
					// 如果生成的随机数小于当前概率区间，则认为命中
					if (rndNumber >= lastVal && rndNumber < currVal)
					{
						return it;
					}
					lastVal = currVal;
				}
			}
		}
		return null;
	}
}
