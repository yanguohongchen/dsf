package Service;

import java.lang.reflect.Proxy;
import java.util.List;

import msg.ServeiceInstanceInfo;
import msg.ServiceDefineInfo;
import route.RouteStrategy;
import fail.FailStrategy;

public class Service
{
	private Object p;

	public Service(ServiceDefineInfo serviceDefineInfo, List<ServeiceInstanceInfo> instances, RouteStrategy routeStrategy, FailStrategy failStrategy)
	{
		try
		{
			ServiceProxy serviceProxy = new ServiceProxy(serviceDefineInfo, instances, routeStrategy, failStrategy);
			//反射创建服务接口
			Object target = Class.forName(serviceDefineInfo.getProxyClass()).newInstance();
			//并在调用这接口之前做代理
			this.p = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), serviceProxy);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object returnProxy()
	{
		return p;
	}

}
