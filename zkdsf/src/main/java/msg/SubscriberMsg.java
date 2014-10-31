package com.zkdsf.core;


public class SubscriberMsg
{

	private String serviceName;
	
	private SubscriberInfo subscribeInfo;

	public SubscriberInfo getSubscribeInfo()
	{
		return subscribeInfo;
	}

	public void setSubscribeInfo(SubscriberInfo subscribeInfo)
	{
		this.subscribeInfo = subscribeInfo;
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
