package com.inedo.api;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

//gradle
//compile 'org.jboss.resteasy:resteasy-client:3.0.11.Final'
//compile 'org.jboss.resteasy:resteasy-jackson2-provider:3.0.11.Final'

public class BuildMasterClientBuilder {
	BuildMasterConfig config;
	
	//Fake Online REST API for Testing and Prototyping: http://jsonplaceholder.typicode.com/
	
	// Straight Java Example: http://stackoverflow.com/questions/221442/rest-clients-for-java
	
	// Look at mocking for testing api with a switch for testing against live buildmaster, could also look at ClientRequestFilters: http://docs.jboss.org/resteasy/docs/3.0.6.Final/userguide/html_single/index.html#Using_Path
	
//  for authentication idea
	// set engine code below	
	// and http://hc.apache.org/httpcomponents-client-ga/tutorial/html/
	// and http://docs.jboss.org/resteasy/docs/3.0.6.Final/userguide/html_single/index.html#Using_Path
		
	// See http://stackoverflow.com/questions/6929378/how-to-set-http-header-in-resteasy-client-framework
		
	
	
	public static BuildMasterClientRestEasy buildRestEasy(BuildMasterConfig config) {
		
		// Have posted question to:
		// http://sourceforge.net/p/resteasy/mailman/resteasy-users/?viewmonth=201504
		// https://java.net/projects/jersey/lists/users/archive
						
		ResteasyClient client = new ResteasyClientBuilder().build();

		BasicAuthentication filter = new BasicAuthentication(config.user, config.password);		
		client = client.register(filter);

		ResteasyWebTarget target = client.target(config.url);
        
        return target.proxy(BuildMasterClientRestEasy.class);
	}
}
