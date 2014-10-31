package msg;

public class PublishMsg
{

	private PublisherInfo publisherInfo;

	private ServiceDefineInfo serviceDefineInfo;

	public PublishMsg(String owner, ServiceDefineInfo serviceDefineInfo)
	{
		publisherInfo = new PublisherInfo();
		publisherInfo.setOwner(owner);
		publisherInfo.setDatetime(System.currentTimeMillis());
		this.serviceDefineInfo = serviceDefineInfo;
	}

	public PublisherInfo getPublisherInfo()
	{
		return publisherInfo;
	}

	public void setPublisherInfo(PublisherInfo publisherInfo)
	{
		this.publisherInfo = publisherInfo;
	}

	public ServiceDefineInfo getServiceDefineInfo()
	{
		return serviceDefineInfo;
	}

	public void setServiceDefineInfo(ServiceDefineInfo serviceDefineInfo)
	{
		this.serviceDefineInfo = serviceDefineInfo;
	}

}
