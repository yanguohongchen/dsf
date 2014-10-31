package route;

import java.util.List;

import com.zkdsf.core.ServeiceInstanceInfo;

/**
 * 路由策略
 * @author sean
 */
public interface RouteStrategy
{
	/**
	 * 读取服务实例
	 * @param service
	 * @return
	 */
	public ServeiceInstanceInfo getInstance(List<ServeiceInstanceInfo> instances);
}
