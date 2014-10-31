package Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import msg.ServeiceInstanceInfo;
import msg.ServiceDefineInfo;
import route.RouteStrategy;
import fail.FailStrategy;


public class ServiceProxy implements InvocationHandler
{

	
	private ServiceDefineInfo serviceDefineInfo;
	private List<ServeiceInstanceInfo> instances;
	private RouteStrategy routeStrategy;
	private FailStrategy failStrategy;
	
	public ServiceProxy(ServiceDefineInfo serviceDefineInfo,List<ServeiceInstanceInfo> instances,RouteStrategy routeStrategy, FailStrategy failStrategy){
		this.serviceDefineInfo = serviceDefineInfo;
		this.instances= instances;
		this.routeStrategy = routeStrategy;
		this.failStrategy = failStrategy;
		
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		
		System.out.println("invoke");
		return null;
	}

	
}
