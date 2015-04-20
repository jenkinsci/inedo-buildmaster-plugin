package com.inedo.api;

import static org.junit.Assert.*;

import com.inedo.api.BuildMasterConfig;
import com.inedo.domain.Application;
import com.inedo.domain.Build;
import com.inedo.domain.BuildExecutionDetails;
import com.inedo.domain.Release;
import com.inedo.domain.ReleaseDetails;

import org.apache.http.HttpStatus;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuildMasterClientApacheTest {
	private String testApplicationId = "36";	// BuildMaster application id to get/create builds for
	
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
		config.user = "user";
		config.password = "password";
		config.domain = "domain";
		config.apiKey = "apikey";
		
		if (MOCK_REQUESTS) {
			handler = new HttpHandler();
			
			server = new LocalTestServer(null, null);
		    server.register("/*", handler);
		    server.start();
		    
		    config.url = "http://" + server.getServiceAddress().getHostName() + ":" + server.getServiceAddress().getPort();
		}
		
		buildmaster = new BuildMasterClientApache(config);
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
    	Application[] applications = buildmaster.getApplications();
    	
        assertTrue("Expect BuildMaster to have applications created", applications.length > 0);
//        System.out.println(applications[0].Application_Name);
	}
	
	@Test(expected=UnknownHostException.class)
	public void getApplicationsWithError() throws IOException {
		String origUrl = config.url; 
		config.url = "http://buildmaster1";
				
		try {
			buildmaster.getApplications();
		} finally {
			config.url = origUrl;
		}
	}
	
	@Test
	public void getRelease() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		ReleaseDetails release = buildmaster.getRelease(testApplicationId, testReleaseNumber);
				
		assertTrue("Expect Test Application to have active release", release.Releases_Extended.length > 0);

		String status = release.Releases_Extended[0].ReleaseStatus_Name;
		
		assertEquals("Expect Test Application to have active release " + testReleaseNumber, "Active", status);
	}
	
	@Test
	public void getActiveReleases() throws IOException {
		Release[] releases = buildmaster.getActiveReleases(testApplicationId);
		
		assertTrue("Expect Test Application to have active release(s)", releases.length > 0);
		
//		for (Release release : releases) {
//			System.out.println(release.Release_Number);
//		}
	}

	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		
		assertTrue("Expect Test Application to have an active release", release.length() > 0);
	}
	
	@Test
	public void getNextBuildNumber() throws NumberFormatException, IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		Integer nextBuildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber));
		
		assertTrue("Expect nextBuildNumber to be greate than zero", nextBuildNumber > 0);
		
//		System.out.println("nextBuildNumber: " + nextBuildNumber);
	}
	
	@Test
	public void createBuild() throws IOException, InterruptedException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		String buildNumber = buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
		variablesList.put("cause", "unit test");
		
		String buildMasterBuildNumber = buildmaster.createBuild(testApplicationId, testReleaseNumber, buildNumber, variablesList);
		
		assertEquals("Expect returned buildNumber to be the same as requested", buildNumber, buildMasterBuildNumber);
		
		boolean result = buildmaster.waitForBuildCompletion(testApplicationId, testReleaseNumber, buildMasterBuildNumber, true);
		
		assertTrue("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void getBuild() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber)) - 1);
		
		Build build = buildmaster.getBuild(testApplicationId, testReleaseNumber, buildNumber);
		
		assertTrue("Expect Test Application to have build number " + buildNumber, build.Build_Number.length() > 0);
		
//		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.Current_ExecutionStatus_Name);
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber)) - 1);
		
		boolean result = buildmaster.waitForBuildCompletion(testApplicationId, testReleaseNumber, buildNumber, false);
		
		assertTrue("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void printExecutionLog() throws IOException, InterruptedException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(testApplicationId);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(testApplicationId, testReleaseNumber)) - 1);
		Build build = buildmaster.getBuild(testApplicationId, testReleaseNumber, buildNumber);
		
		PrintStream printSteamOrig = config.printStream;
		ByteArrayOutputStream outContent  = new ByteArrayOutputStream();
		
		try {
			PrintStream printSteam = new PrintStream(outContent );
			config.printStream = printSteam;
			
			buildmaster.printExecutionLog(build.Current_Execution_Id);
		} finally {
			config.printStream = printSteamOrig;
		}
		
		assertTrue("Expect Test build " + buildNumber + " to have an execution log", outContent.size() > 0);
		
//		System.out.println(outContent.toString());
	}
	
	// Handler for the test server that returns responses based on the requests.
	public class HttpHandler implements HttpRequestHandler {

		@Override
		public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
			URI uri = URI.create(request.getRequestLine().getUri());
			
			String method = uri.getPath().replace("/api/json/", "");
			
			switch (method) {
			case "Applications_GetApplications":
				response.setEntity(new StringEntity(Application.EXAMPLE));
				break;
				
			case "Releases_GetReleases":
				response.setEntity(new StringEntity(Release.EXAMPLE));
				break;
			
			case "Releases_GetRelease": 
				response.setEntity(new StringEntity(ReleaseDetails.EXAMPLE));
				break;
			
			case "Builds_GetBuilds": 
				response.setEntity(new StringEntity(Build.EXAMPLE));
				break;
				
			case "Builds_GetBuild":
				response.setEntity(new StringEntity(Build.EXAMPLE));
				break;				
				
			case "Builds_CreateBuild":
				String buildNumber = "99";
				String query = uri.getQuery();
								
				int pos = query.indexOf("Requested_Build_Number");
				
				if (pos > 0) {
					query = query.substring(pos + "Requested_Build_Number".length() + 1);
					
					buildNumber = query.substring(0, query.indexOf("&"));
				}
				
				// Return the quested build number if passed, else new build number
				response.setEntity(new StringEntity(buildNumber));
				break;
			
			case "Builds_GetExecutionLog":
				response.setEntity(new StringEntity(BuildExecutionDetails.EXAMPLE));
				break;
				
			default:
				response.setStatusCode(HttpStatus.SC_NOT_FOUND);
				response.setEntity(new StringEntity("API method " + method + " not found."));
			}
		}
		
	}
}
