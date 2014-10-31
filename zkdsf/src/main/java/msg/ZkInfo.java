package msg;

public class ZkInfo
{

	
	private String serviceName;
	
	private String connectAddr;

	private int timeout;

	public String getConnectAddr()
	{
		return connectAddr;
	}

	public void setConnectAddr(String connectAddr)
	{
		this.connectAddr = connectAddr;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	
	
	
}
