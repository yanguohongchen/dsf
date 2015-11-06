package msg;

import java.util.Map;

/**
 * 服务定义
 * 
 * @author sea
 *
 */
public class ServiceDefineInfo
{

	private String servicename;

	private String description;
	
	private String proxyClass;

	private String protol;

	private String routestrage;

	private String failstrage;

	private int timeout;

	private String version;

	private Map<String, String> otherAttrs;

	public String getServicename()
	{
		return servicename;
	}

	public void setServicename(String servicename)
	{
		this.servicename = servicename;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getProtol()
	{
		return protol;
	}

	public void setProtol(String protol)
	{
		this.protol = protol;
	}

	public String getRoutestrage()
	{
		return routestrage;
	}

	public void setRoutestrage(String routestrage)
	{
		this.routestrage = routestrage;
	}

	public String getFailstrage()
	{
		return failstrage;
	}

	public void setFailstrage(String failstrage)
	{
		this.failstrage = failstrage;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public Map<String, String> getOtherAttrs()
	{
		return otherAttrs;
	}

	public void setOtherAttrs(Map<String, String> otherAttrs)
	{
		this.otherAttrs = otherAttrs;
	}

	public String getProxyClass()
	{
		return proxyClass;
	}

	public void setProxyClass(String proxyClass)
	{
		this.proxyClass = proxyClass;
	}
	
	@Override
	public String toString(){
		return servicename+":"+proxyClass+":"+routestrage;
	}
	

}
