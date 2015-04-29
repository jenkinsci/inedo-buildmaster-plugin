package com.inedo.buildmaster.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.inedo.buildmaster.MockServer;
import com.inedo.buildmaster.api.BuildMasterClientApache;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.Variable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
    	
        assertThat("Expect BuildMaster to have applications created", applications.length, is(greaterThan(0)));
//        System.out.println(applications[0].Application_Name);
	}
	
	@Test
	public void getDeployables() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(MockServer.APPLICATION_ID);
    	
        assertThat("Expect BuildMaster to have applications created", deployables.length, is(greaterThan(0)));
	}
	
	
	@Test
	public void getDeployable() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(MockServer.APPLICATION_ID);
    	
        assertThat("Expect BuildMaster to have applications created", deployables.length, is(greaterThan(0)));
	}
	 
	@Test
	public void enableReleaseDeployable() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		
		ReleaseDetails before = buildmaster.getRelease(MockServer.APPLICATION_ID, testReleaseNumber);
		
		buildmaster.enableReleaseDeployable(MockServer.APPLICATION_ID, testReleaseNumber, "2077");
		
		ReleaseDetails after = buildmaster.getRelease(MockServer.APPLICATION_ID, testReleaseNumber);
		
		assertThat(after.Releases_Extended[0].Application_Id, is(before.Releases_Extended[0].Application_Id));
		assertThat(after.Releases_Extended[0].Release_Number, is(before.Releases_Extended[0].Release_Number));
		assertThat(after.Releases_Extended[0].Workflow_Id, is(before.Releases_Extended[0].Workflow_Id));
		
		assertThat(after.Releases_Extended[0].Target_Date, is(before.Releases_Extended[0].Target_Date));
		assertThat(after.Releases_Extended[0].Release_Name, is(before.Releases_Extended[0].Release_Name));
		assertThat(after.Releases_Extended[0].Notes_Text, is(before.Releases_Extended[0].Notes_Text));
		
		assertThat(after.ReleaseDeployables_Extended.length, is(before.ReleaseDeployables_Extended.length + 1));
	}
	
	@Test
	public void getRelease() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		ReleaseDetails release = buildmaster.getRelease(MockServer.APPLICATION_ID, testReleaseNumber);
				
		assertThat("Expect Test Application to have active release", release.Releases_Extended.length, is(greaterThan(0)));

		String status = release.Releases_Extended[0].ReleaseStatus_Name;
		
		assertThat("Expect Test Application to have active release " + testReleaseNumber, "Active", is(status));
	}
	
	@Test
	public void getActiveReleases() throws IOException {
		Release[] releases = buildmaster.getActiveReleases(MockServer.APPLICATION_ID);
		
		assertThat("Expect Test Application to have active release(s)", releases.length, is(greaterThan(0)));
		
//		for (Release release : releases) {
//			System.out.println(release.Release_Number);
//		}
	}

	@Test
	public void getVariableValues() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String testBuildNumber = buildmaster.getPreviousBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber);
		
		Variable[] variables = buildmaster.getVariableValues(MockServer.APPLICATION_ID, testReleaseNumber, testBuildNumber);
		
		assertThat("Expect Test previous build to have variables defined", variables.length, is(greaterThan(0)));
	}
	
	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		
		assertThat("Expect Test Application to have an active release", release.length(), is(greaterThan(0)));
	}
	
	@Test
	public void getNextBuildNumber() throws NumberFormatException, IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		Integer nextBuildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber));
		
		assertThat("Expect nextBuildNumber to be greate than zero", nextBuildNumber , is(greaterThan(0)));
		
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
		
		assertThat("Expect returned buildNumber to be the same as requested", buildNumber, is(buildMasterBuildNumber));
		
		boolean result = buildmaster.waitForBuildCompletion(MockServer.APPLICATION_ID, testReleaseNumber, buildMasterBuildNumber, true);
		
		assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void getBuild() throws IOException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber)) - 1);
		
		Build build = buildmaster.getBuild(MockServer.APPLICATION_ID, testReleaseNumber, buildNumber);
		
		assertThat("Expect Test Application to have build number " + buildNumber, build.Build_Number.length() , is(greaterThan(0)));
		
//		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.Current_ExecutionStatus_Name);
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String testReleaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, testReleaseNumber)) - 1);
		
		boolean result = buildmaster.waitForBuildCompletion(MockServer.APPLICATION_ID, testReleaseNumber, buildNumber, false);
		
		assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
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
		
		assertThat("Expect Test build " + buildNumber + " to have an execution log", outContent.size(), is(greaterThan(0)));
		
//		System.out.println(outContent.toString());
	}
}
