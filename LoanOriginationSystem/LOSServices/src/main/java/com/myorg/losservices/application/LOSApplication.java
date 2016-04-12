package com.myorg.losservices.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.myorg.losservices.resource.LOSResources;

public class LOSApplication extends ResourceConfig {
	
	public LOSApplication() {
		super();
		register(LOSResources.class);
	}

}
