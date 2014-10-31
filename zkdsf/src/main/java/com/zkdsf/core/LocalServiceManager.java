package com.zkdsf.core;

import java.util.List;
import java.util.Map;

import msg.ServeiceInstanceInfo;

public class LocalServiceManager
{
	
	private List<ServeiceInstanceInfo> serveiceInstanceInfos;
	private Map<String,Object> map;
	
	public LocalServiceManager(List<ServeiceInstanceInfo> serveiceInstanceInfos){
		this.serveiceInstanceInfos = serveiceInstanceInfos;
	}
	
	public void createPool(){
		
	}
	

}
