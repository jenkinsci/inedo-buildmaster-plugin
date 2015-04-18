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
import com.inedo.api.Release.ReleasesExtended;

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
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class BuildMasterClientApacheTest {
	private final String testApplicationId = "36";
	private String testReleaseNumber;
	
	private BuildMasterConfig config;
	private BuildMasterClientApache buildmaster;
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
			buildmaster = BuildMasterClientBuilder.buildApache(config);
			testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
			return;
		}
		
		buildmaster = mock(BuildMasterClientApache.class);
		testReleaseNumber = "1.3";
		
		when(buildmaster.getApplications()).thenReturn(new ObjectMapper().readValue(Application.EXAMPLE, Application[].class));
		
		
    }
	
	// TODO: Add BuildMasterApi groovy tests, see if can mock httpclient.execute so unit tests actually testing some code rather than
	// zilch that current mock tests.
	@Test
	public void checkConnection() throws IOException {
		// An exception will be thrown if fails
		buildmaster.checkConnection();
	}
	
	@Test
	public void getApplications() throws IOException  {
    	Application[] result = buildmaster.getApplications();
    	
        System.out.println(result[0].Application_Name);
	}
	
	@Test(expected=UnknownHostException.class)
	public void getApplicationsWithError() throws IOException {
		config.url = "http://buildmaster1";
				
		try {
			buildmaster.getApplications();
		} finally {
			config.url = "http://buildmaster";
		}
	}
	
	@Test
	public void getRelease() throws IOException {
		Release release = buildmaster.getRelease(testApplicationId, testReleaseNumber);
				
		assertTrue("Expect PaxHoldRelease Application to have active release", release.Releases_Extended.length > 0);

		String status = release.Releases_Extended[0].ReleaseStatus_Name;
		
		assertEquals("Expect PaxHoldRelease Application to have active release " + testReleaseNumber, "Active", status);
	}
	
	@Test
	public void getActiveReleases() throws IOException {
		Release[] releases = buildmaster.getActiveReleases(testApplicationId);
		
		assertTrue("Expect PaxHoldRelease Application to have active release(s)", releases.length > 0);
		
		for (Release release : releases) {
			for (ReleasesExtended extend : release.Releases_Extended) {
				System.out.println(extend.Release_Number);
			}
		}
	}

	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		
		assertTrue("Expect PaxHoldRelease Application to have an active release", release.length() > 0);
	}
	
	@Test
	public void getNextBuildNumber() throws NumberFormatException, IOException {
		Integer nextBuildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber));
		System.out.println("nextBuildNumber: " + nextBuildNumber);
		assertTrue("Expect nextBuildNumber to be greate than zero", nextBuildNumber > 0);
	}
	
	@Test
	public void createBuild() throws IOException, InterruptedException {
		String buildNumber = buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
		variablesList.put("cause", "unit test");
		
		String buildMasterBuildNumber = buildmaster.createBuild(testApplicationId, testReleaseNumber, buildNumber, variablesList);
		//String buildMasterBuildNumber = buildmaster.createBuild(testApplicationId, testReleaseNumber);
		
		assertEquals("Expect returned buildNumber to be the same as requested", buildNumber, buildMasterBuildNumber);
		
		boolean result = buildmaster.waitForBuildCompletion(testApplicationId, testReleaseNumber, buildMasterBuildNumber, true);
		
		assertTrue("Expect PaxHoldRelease build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void getBuild() throws IOException {
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber)) - 1);
		
		Build build = buildmaster.getBuild(testApplicationId, testReleaseNumber, buildNumber);
		
		assertTrue("Expect PaxHoldRelease Application to have build number " + buildNumber, build.Build_Number.length() > 0);
		
		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.Current_ExecutionStatus_Name);
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber)) - 1);
		
		boolean result = buildmaster.waitForBuildCompletion(testApplicationId, testReleaseNumber, buildNumber, true);
		
		assertTrue("Expect PaxHoldRelease build " + buildNumber + " to have built and deployed successfully", result);
		
		//System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.getJSONObject(0).getString("Current_ExecutionStatus_Name"));
	}
	
}
