package com.inedo.api;

import java.util.Arrays;
import java.util.Map;

import org.apache.http.HttpHost;
//import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.BasicScheme;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.NTLMEngine;
import org.apache.http.impl.auth.NTLMScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import org.jboss.resteasy.util.BasicAuthHelper;


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

	public static BuildMasterClientApache buildApache(BuildMasterConfig config) {
		return new BuildMasterClientApache(config);
	}

}
