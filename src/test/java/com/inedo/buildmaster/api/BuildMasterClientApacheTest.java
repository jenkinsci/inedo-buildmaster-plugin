package com.inedo.buildmaster.api;

import static org.junit.Assert.*;

import com.inedo.buildmaster.MockServer;
import com.inedo.buildmaster.api.BuildMasterClientApache;
import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.BuildExecutionDetails;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.Variable;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpStatus;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuildMasterClientApacheTest {
	private final boolean MOCK_REQUESTS = false; 
	private MockServer mockServer;
	private BuildMasterClientApache buildmaster;
	
	@Before
    public void before() throws IOException {
		mockServer = new MockServer(MOCK_REQUESTS);
		buildmaster = new BuildMasterClientApache(mockServer.getBuildMasterConfig());
	}
	
	@After
	public void tearDown() throws Exception {
		mockServer.stop();
	}
	
	@Test
	public void mutliProject() throws IOException {
		// Set if any of these will help with fan in
		//Applications_GetDeployables Application_Id
		//Applications_GetDeployable 
		//Applications_CreateOrUpdateDeployable 
		//Releases_CreateOrUpdateRelease
		
//		Deployable[] deployables = buildmaster.getDeployables(MockServer.APPLICATION_ID);
		
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String previousBuildNumber = buildmaster.getPreviousBuildNumber(MockServer.APPLICATION_ID, releaseNumber);
		//Build build = buildmaster.getBuild(MockServer.APPLICATION_ID, releaseNumber, buildNumber);
		
		Variable[] variables = buildmaster.getVariableValues(MockServer.APPLICATION_ID, releaseNumber, previousBuildNumber);
		
		ReleaseDetails releaseDetails = buildmaster.getRelease(MockServer.APPLICATION_ID, releaseNumber);
		
		for (Deployable deployable : releaseDetails.ReleaseDeployables_Extended) {
			deployable = deployable;
		}
		 
//		File artifact1File = new File("\\\\buildmaster/BuildMaster_FileTransfer/Sample/app." + releaseNumber + "." + buildNumber + ".txt");
//		FileUtils.writeStringToFile(artifact1File, "hello", false);
		
//		buildmaster.createBuild(MockServer.APPLICATION_ID, releaseNumber, null);
		
//		File artifact2File = new File("\\\\buildmaster/BuildMaster_FileTransfer/Sample/db." + releaseNumber + "." + buildNumber + ".txt");
//		FileUtils.writeStringToFile(artifact2File, "world", false);
		
		//buildmaster.createBuild(MockServer.APPLICATION_ID, releaseNumber, null);
		
	}
	
	@Test
	public void checkConnection() throws IOException {
		// An exception will be thrown if fails
		buildmaster.checkConnection();
	}
	
	@Test(expected=UnknownHostException.class)
	public void getWithIncorrectHost() throws IOException {
		String origUrl = mockServer.getBuildMasterConfig().url; 
		mockServer.getBuildMasterConfig().url = "http://buildmaster1";
				
		try {
			buildmaster.getApplications();
		} finally {
			mockServer.getBuildMasterConfig().url = origUrl;
		}
	}
	
	@Test(expected=IOException.class)
	public void getWithIncorrectMethod() throws IOException {
		buildmaster.doGet(String.class, "RubbishMethod");
	}
	
	@Test
	public void getApplications() throws IOException  {
    	Application[] applications = buildmaster.getApplications();
    	
        assertTrue("Expect BuildMaster to have applications created", applications.length > 0);
//        System.out.println(applications[0].Application_Name);
	}
	
	@Test
	public void getDeployables() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(MockServer.APPLICATION_ID);
    	
        assertTrue("Expect BuildMaster to have applications created", deployables.length > 0);
	}
	
	
	@Test
	public void getDeployable() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(MockServer.APPLICATION_ID);
    	
        assertTrue("Expect BuildMaster to have applications created", deployables.length > 0);
	}
	 
	
	@Test
	public void getRelease() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		ReleaseDetails release = buildmaster.getRelease(MockServer.APPLICATION_ID, testReleaseNumber);
				
		assertTrue("Expect Test Application to have active release", release.Releases_Extended.length > 0);

		String status = release.Releases_Extended[0].ReleaseStatus_Name;
		
		assertEquals("Expect Test Application to have active release " + testReleaseNumber, "Active", status);
	}
	
	@Test
	public void getActiveReleases() throws IOException {
		Release[] releases = buildmaster.getActiveReleases(MockServer.APPLICATION_ID);
		
		assertTrue("Expect Test Application to have active release(s)", releases.length > 0);
		
//		for (Release release : releases) {
//			System.out.println(release.Release_Number);
//		}
	}

	@Test
	public void getVariableValues() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String testBuildNumber = buildmaster.getPreviousBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber);
		
		Variable[] variables = buildmaster.getVariableValues(MockServer.APPLICATION_ID, testReleaseNumber, testBuildNumber);
		
		assertTrue("Expect Test previous build to have variables defined", variables.length > 0);
	}
	
	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		
		assertTrue("Expect Test Application to have an active release", release.length() > 0);
	}
	
	@Test
	public void getNextBuildNumber() throws NumberFormatException, IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		Integer nextBuildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber));
		
		assertTrue("Expect nextBuildNumber to be greate than zero", nextBuildNumber > 0);
		
//		System.out.println("nextBuildNumber: " + nextBuildNumber);
	}
	
	@Test
	public void createBuild() throws IOException, InterruptedException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
		variablesList.put("cause", "unit test");
		
		String buildMasterBuildNumber = buildmaster.createBuild(MockServer.APPLICATION_ID, testReleaseNumber, buildNumber, variablesList);
		
		assertEquals("Expect returned buildNumber to be the same as requested", buildNumber, buildMasterBuildNumber);
		
		boolean result = buildmaster.waitForBuildCompletion(MockServer.APPLICATION_ID, testReleaseNumber, buildMasterBuildNumber, true);
		
		assertTrue("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void getBuild() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber)) - 1);
		
		Build build = buildmaster.getBuild(MockServer.APPLICATION_ID, testReleaseNumber, buildNumber);
		
		assertTrue("Expect Test Application to have build number " + buildNumber, build.Build_Number.length() > 0);
		
//		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.Current_ExecutionStatus_Name);
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber)) - 1);
		
		boolean result = buildmaster.waitForBuildCompletion(MockServer.APPLICATION_ID, testReleaseNumber, buildNumber, false);
		
		assertTrue("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void printExecutionLog() throws IOException, InterruptedException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber)) - 1);
		Build build = buildmaster.getBuild(MockServer.APPLICATION_ID, testReleaseNumber, buildNumber);
		
		PrintStream printSteamOrig = mockServer.getBuildMasterConfig().printStream;
		ByteArrayOutputStream outContent  = new ByteArrayOutputStream();
		
		try {
			PrintStream printSteam = new PrintStream(outContent);
			mockServer.getBuildMasterConfig().printStream = printSteam;
			
			buildmaster.printExecutionLog(build.Current_Execution_Id);
		} finally {
			mockServer.getBuildMasterConfig().printStream = printSteamOrig;
		}
		
		assertTrue("Expect Test build " + buildNumber + " to have an execution log", outContent.size() > 0);
		
//		System.out.println(outContent.toString());
	}
}
