package com.inedo.api;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BuildMasterClientApache
{
	private BuildMasterConfig config;
	private HttpClient httpclient;
	
	public BuildMasterClientApache(BuildMasterConfig config) {
		this.config = config;
		
		RequestConfig requestConfig = RequestConfig.custom()
    	        .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM))
    	        .build();

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new NTCredentials(config.user, config.password, config.getHost(), config.domain));

        // Finally we instantiate the client. Client is a thread safe object and can be used by several threads at the same time. 
        // Client can be used for several request. The life span of the client must be equal to the life span of this EJB.
        httpclient = HttpClients.custom()
	        .setDefaultCredentialsProvider(credentialsProvider)
	        .setDefaultRequestConfig(requestConfig)
	        .build();

        		
	}
	
	/**
	 * Ensure can call BuildMaster api.  An exception will be thrown if cannot.  
	 * @throws IOException 
	 */
	public boolean checkConnection() throws IOException {
		String url = config.url + "/api/json/Applications_GetApplications?API_Key=" + config.apiKey + "&Application_Count=1";

		doGet(url, Application[].class);
		
		return true;
		
	}
	
	/**
	 *  Gets a list of all applications in the system.
	 *  @throws IOException 
	 */
	public Application[] getApplications() throws IOException {
		String url = config.url + "/api/json/Applications_GetApplications?API_Key=" + config.apiKey;
		
		return doGet(url, Application[].class);
	}
	
	private <T> T doGet(String url, Class<T> type) throws IOException {
		HttpGet httpget = new HttpGet(url);            
        HttpClientContext context = HttpClientContext.create();    
        
        System.out.println("Executing request " + httpget.getRequestLine());
        HttpResponse response = httpclient.execute(httpget, context);
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
            
        T myObject = new ObjectMapper().readValue(response.getEntity().getContent(), type);
        
        //System.out.println(EntityUtils.toString(response.getEntity()));
        EntityUtils.consume(response.getEntity());
        
        return myObject;
	}

}