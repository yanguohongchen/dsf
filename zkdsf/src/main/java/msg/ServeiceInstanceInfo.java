package msg;

/**
 * 服务实例信息
 * 
 * @author sea
 *
 */
public class ServeiceInstanceInfo
{

	private String serviceName;

	private String ip;

	private int port;

	private String state;

	private int weightValue;


	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public int getWeightValue()
	{
		return weightValue;
	}

	public void setWeightValue(int weightValue)
	{
		this.weightValue = weightValue;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}
	
	@Override
	public String toString(){
		return this.getIp()+":"+this.getPort();
	}

}
