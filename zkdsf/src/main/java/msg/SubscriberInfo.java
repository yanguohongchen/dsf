package msg;

public class SubscriberInfo {

	private String serverName;

	private String ip;
	
	private String owner;
	
	private long datetime;
	
	private String hostName;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServerName()
	{
		return serverName;
	}

	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}

	public String getOwner()
	{
		return owner;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public long getDatetime()
	{
		return datetime;
	}

	public void setDatetime(long datetime)
	{
		this.datetime = datetime;
	}

	public String getHostName()
	{
		return hostName;
	}

	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}
	
	

}
