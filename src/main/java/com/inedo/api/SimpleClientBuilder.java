package com.inedo.api;

import jcifs.smb.NtlmPasswordAuthentication;

import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.BasicScheme;
import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.impl.client.BasicAuthCache;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class SimpleClientBuilder {
//	
//	compile 'org.jboss.resteasy:resteasy-client:3.0.11.Final'
//	compile 'org.jboss.resteasy:resteasy-jackson2-provider:3.0.11.Final'
	//testCompile 'org.mockito:mockito-core:2.0.5-beta'
	
	
	//Fake Online REST API for Testing and Prototyping: http://jsonplaceholder.typicode.com/
	
	// Straight Java Example: http://stackoverflow.com/questions/221442/rest-clients-for-java
	
	// Look at mocking for testing api with a switch for testing against live buildmaster, could also look at ClientRequestFilters: http://docs.jboss.org/resteasy/docs/3.0.6.Final/userguide/html_single/index.html#Using_Path
	
//  for authentication idea
	// set engine code below	
	// and http://hc.apache.org/httpcomponents-client-ga/tutorial/html/
	// and http://docs.jboss.org/resteasy/docs/3.0.6.Final/userguide/html_single/index.html#Using_Path
		
	// See http://stackoverflow.com/questions/6929378/how-to-set-http-header-in-resteasy-client-framework
		
	
	
	public static SimpleClient build() {
		
//		// Configure HttpClient to authenticate preemptively
//		// by prepopulating the authentication data cache.
//		 
//		// 1. Create AuthCache instance
//		AuthCache authCache = new BasicAuthCache();
//		 
//		// 2. Generate BASIC scheme object and add it to the local auth cache
//		AuthScheme basicAuth = new BasicScheme();
//		authCache.put(new HttpHost("sippycups.bluemonkeydiamond.com"), basicAuth);
//		 
//		// 3. Add AuthCache to the execution context
//		BasicHttpContext localContext = new BasicHttpContext();
//		localContext.setAttribute(ClientContext.AUTH_CACHE, authCache);
//		 
//		// 4. Create client executor and proxy
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient, localContext);
//		ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
		

		

        //groovy
//        http.client.credentialsProvider.setCredentials(AuthScope.ANY, new NTCredentials(config.user, config.password, config.host, config.domain));
//		http.client.params.setParameter(AuthPNames.TARGET_AUTH_PREF, [AuthPolicy.NTLM]);
		
		
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://jsonplaceholder.typicode.com");

        return target.proxy(SimpleClient.class);
	}

}
