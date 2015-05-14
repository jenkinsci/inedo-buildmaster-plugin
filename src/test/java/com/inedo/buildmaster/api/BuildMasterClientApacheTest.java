package com.inedo.buildmaster.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.inedo.buildmaster.MockServer;
import com.inedo.buildmaster.api.BuildMasterClientApache;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.BuildExecution;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.Variable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the BuildMasterClientApache class
 * 
 * TODO: There is timing issue when running all tests against a live server as tests randomly fail.  Running one at a time works fine.   
 * 
 * @author Andrew Sumner
 */
public class BuildMasterClientApacheTest {
	private final boolean MOCK_REQUESTS = true;	// Set this value to false to run against a live BuildMaster installation 
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

	/*
	 * Checks what would happen if several Jenkins jobs trigger the same application at similar times
	 * 
	 * The job this is calling should have a BUILD STEP with a powershell action with this script:
	 * 	echo "start sleep"
	 *  Start-Sleep -s 60
	 *	echo "end sleep"
	 */
	@Test
	public void queueBuilds() throws IOException {
		if (MOCK_REQUESTS) return;
		
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
				
		Map<String, String> variablesList = new HashMap<>();
		int exectiontimes = 1;
		
		for (int i = 1; 0 < exectiontimes; i++) {
			String testrun = String.valueOf(i + 1);
			
			System.out.println("");
			System.out.println("Test Run: " + testrun);
			String prevBuildNumber = buildmaster.getPreviousBuildNumber(MockServer.APPLICATION_ID, releaseNumber);
			System.out.println("PreviousBuildNumber=" + prevBuildNumber);
						
			variablesList.put("hello", "world" + testrun);			
			String buildNumber = buildmaster.createBuild(MockServer.APPLICATION_ID, releaseNumber, variablesList);
			System.out.println("BuildNumber=" + buildNumber);
			
			Variable[] variables = buildmaster.getVariableValues(MockServer.APPLICATION_ID, releaseNumber, buildNumber);
			String value = "not found";
			
			for (Variable variable : variables) {
				if (variable.Variable_Name.equalsIgnoreCase("hello")) {
					value = variable.Value_Text;
				}
			}
			
			System.out.println("Variable HELLO=" + value);
		}
	}
	
	/*
	 * Checks what would happen if a Jenkins jobs trigger the same application while a deployment is in progress
	 * 
	 * The job this is calling should have a DEPLOYMENT STEP with a powershell action with this script:
	 *  echo "start sleep"
	 *  Start-Sleep -s 60
	 *	echo "end sleep"
	 */
	@Test
	public void addBuildsOnceExecuting() throws IOException, InterruptedException {
		if (MOCK_REQUESTS) return;
		
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		
		System.out.println("Test Run: Start First Build");			
		String buildNumber = buildmaster.createBuild(MockServer.APPLICATION_ID, releaseNumber, null);
		System.out.println("BuildNumber=" + buildNumber);
		
		BuildExecution execution = buildmaster.getLatestExecution(MockServer.APPLICATION_ID, releaseNumber, buildNumber);
				
		System.out.println("\tExecution ExecutionStatus_Name: " + execution.ExecutionStatus_Name);
		System.out.println("\tExecution Execution_Id: " + execution.Execution_Id);
		System.out.println("\tExecution Environment_Id: " + execution.Environment_Id);
		System.out.println("\tExecution Environment_Name: " + execution.Environment_Name);
		System.out.println("\tExecution BuildStatus_Name: " + execution.BuildStatus_Name);
		System.out.println("\tExecution Build_AutoPromote_Indicator: " + execution.Build_AutoPromote_Indicator);
		System.out.println("\tExecution PromotionStatus_Name: " + execution.PromotionStatus_Name);

		final List<String> executing = Arrays.asList(new String[] { null, "", "Pending", "Executing" });

		// Wait till both build step (if exists) and deployment to the first environment have completed (if has build step with AutoPromote flag set)
		while (executing.contains(execution.ExecutionStatus_Name) || (execution.Environment_Id == null && execution.Build_AutoPromote_Indicator.equalsIgnoreCase("Y"))) {
			Thread.sleep(7000);

			execution = buildmaster.getLatestExecution(MockServer.APPLICATION_ID, releaseNumber, buildNumber);
			
			System.out.println("\tExecution ExecutionStatus_Name: " + execution.ExecutionStatus_Name);
			System.out.println("\tExecution Execution_Id: " + execution.Execution_Id);
			System.out.println("\tExecution Environment_Name: " + execution.Environment_Name);
			System.out.println("\tExecution BuildStatus_Name: " + execution.BuildStatus_Name);
			System.out.println("\tExecution Build_AutoPromote_Indicator: " + execution.Build_AutoPromote_Indicator);
			System.out.println("\tExecution PromotionStatus_Name: " + execution.PromotionStatus_Name);
		}

//		System.out.println("Test Run: Start Second Build");
//		buildNumber = buildmaster.createBuild(MockServer.APPLICATION_ID, releaseNumber, null);
//		System.out.println("BuildNumber=" + buildNumber);
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
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		
		ReleaseDetails before = buildmaster.getRelease(MockServer.APPLICATION_ID, releaseNumber);
		
		buildmaster.enableReleaseDeployable(MockServer.APPLICATION_ID, releaseNumber, "2077");
		
		ReleaseDetails after = buildmaster.getRelease(MockServer.APPLICATION_ID, releaseNumber);
		
		assertThat(after.Releases_Extended[0].Application_Id, is(before.Releases_Extended[0].Application_Id));
		assertThat(after.Releases_Extended[0].Release_Number, is(before.Releases_Extended[0].Release_Number));
		assertThat(after.Releases_Extended[0].Workflow_Id, is(before.Releases_Extended[0].Workflow_Id));
		
		assertThat(after.Releases_Extended[0].Target_Date, is(before.Releases_Extended[0].Target_Date));
		assertThat(after.Releases_Extended[0].Release_Name, is(before.Releases_Extended[0].Release_Name));
		assertThat(after.Releases_Extended[0].Notes_Text, is(before.Releases_Extended[0].Notes_Text));
		
		if (!MOCK_REQUESTS) {
			assertThat(after.ReleaseDeployables_Extended.length, is(before.ReleaseDeployables_Extended.length + 1));
		}
	}
	
	@Test
	public void getRelease() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		ReleaseDetails release = buildmaster.getRelease(MockServer.APPLICATION_ID, releaseNumber);
				
		assertThat("Expect Test Application to have active release", release.Releases_Extended.length, is(greaterThan(0)));

		String status = release.Releases_Extended[0].ReleaseStatus_Name;
		
		assertThat("Expect Test Application to have active release " + releaseNumber, "Active", is(status));
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
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String testBuildNumber = buildmaster.getPreviousBuildNumber(MockServer.APPLICATION_ID, releaseNumber);
		
		Variable[] variables = buildmaster.getVariableValues(MockServer.APPLICATION_ID, releaseNumber, testBuildNumber);
		
		assertThat("Expect Test previous build to have variables defined", variables.length, is(greaterThan(0)));
	}
	
	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		
		assertThat("Expect Test Application to have an active release", release.length(), is(greaterThan(0)));
	}
	
	@Test
	public void getNextBuildNumber() throws NumberFormatException, IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		Integer nextBuildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, releaseNumber));
		
		assertThat("Expect nextBuildNumber to be greate than zero", nextBuildNumber , is(greaterThan(0)));
		
//		System.out.println("nextBuildNumber: " + nextBuildNumber);
	}
	
	@Test
	public void createBuild() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, releaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
		variablesList.put("cause", "unit test");
		
		String buildMasterBuildNumber = buildmaster.createBuild(MockServer.APPLICATION_ID, releaseNumber, buildNumber, variablesList);
		
		assertThat("Expect returned buildNumber to be the same as requested", buildNumber, is(buildMasterBuildNumber));
		
		boolean result = buildmaster.waitForBuildCompletion(MockServer.APPLICATION_ID, releaseNumber, buildMasterBuildNumber, true);
		
		assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void getBuild() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, releaseNumber)) - 1);
		
		Build build = buildmaster.getBuild(MockServer.APPLICATION_ID, releaseNumber, buildNumber);
		
		assertThat("Expect Test Application to have build number " + buildNumber, build.Build_Number.length() , is(greaterThan(0)));
		
//		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.Current_ExecutionStatus_Name);
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, releaseNumber)) - 1);
		
		boolean result = buildmaster.waitForBuildCompletion(MockServer.APPLICATION_ID, releaseNumber, buildNumber, false);
		
		assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void printExecutionLog() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(MockServer.APPLICATION_ID);
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(MockServer.APPLICATION_ID, releaseNumber)) - 1);
		Build build = buildmaster.getBuild(MockServer.APPLICATION_ID, releaseNumber, buildNumber);
		
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
