package com.inedo.api;

import static org.junit.Assert.*;
import com.inedo.api.Application;
import com.inedo.api.BuildMasterConfig;
import com.inedo.api.Release.ReleasesExtended;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuildMasterClientApacheTest {
	private String testApplicationId = "36";	// BuildMaster application id to get/create builds for
	private String testReleaseNumber;			// Latest active release number for the application under test
	
	private BuildMasterConfig config;
	private BuildMasterClientApache buildmaster;
	
	// Required for mocking via test server
	private final boolean MOCK_REQUESTS = true;
	private LocalTestServer server = null;
	private HttpRequestHandler handler;
	
	@Before
    public void before() throws Exception {
		config = new BuildMasterConfig();
		
		config.url = "http://buildmaster";
		config.authentication = "ntlm";
		config.user = "username";
		config.password = "password";
		config.domain = "customstw";
		config.apiKey = "customs";
		config.logCalls = false;
		
		if (MOCK_REQUESTS) {
			handler = new HttpHandler();
			
			server = new LocalTestServer(null, null);
		    server.register("/*", handler);
		    server.start();
		    
		    config.url = "http://" + server.getServiceAddress().getHostName() + ":" + server.getServiceAddress().getPort();
		}
		
		buildmaster = new BuildMasterClientApache(config);

		if (MOCK_REQUESTS) {
			testReleaseNumber = "1.3";
		} else {
			testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		}
	}
	
	@After
	public void tearDown() throws Exception {
    	if (server!= null) server.stop();
	}

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
	
	// Handler for the test server that returns responses based on the requests.
	public class HttpHandler implements HttpRequestHandler {

		@Override
		public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
			response.setEntity(new StringEntity(Application.EXAMPLE));
		}
		
	}
}
