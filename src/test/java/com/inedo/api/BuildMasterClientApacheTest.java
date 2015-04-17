package com.inedo.api;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.inedo.api.BuildMasterClientRestEasy;
import com.inedo.api.BuildMasterClientBuilder;
import com.inedo.api.Application;
import com.inedo.api.BuildMasterConfig;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;










import java.io.IOException;
import java.net.URLConnection;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class BuildMasterClientApacheTest {
	private BuildMasterConfig config;
	private BuildMasterClientApache buildMaster;
	private final boolean MOCK_REQUESTS = true;
	
	@Before
    public void before() throws JsonParseException, JsonMappingException, IOException {
		
		config = new BuildMasterConfig();
		
		config.url = "http://buildmaster";
		config.authentication = "ntlm";
		config.user = "as979c";
		config.password = "Tracey33";
		config.domain = "customstw";
		config.apiKey = "customs";
		config.logCalls = false;
		
		if (!MOCK_REQUESTS) {
			buildMaster = BuildMasterClientBuilder.buildApache(config);
			return;
		}
		
		buildMaster = mock(BuildMasterClientApache.class);
		
		when(buildMaster.getApplications()).thenReturn(new ObjectMapper().readValue(Application.EXAMPLE, Application[].class));
    }
	
	@Test
	public void checkConnection() throws IOException {
		// An exception will be thrown if fails
		buildMaster.checkConnection();
	}
	
	@Test
	public void getApplications() throws IOException  {
    	Application[] result = buildMaster.getApplications();
    	
        System.out.println(result[0].Application_Name);
	}
	
}
