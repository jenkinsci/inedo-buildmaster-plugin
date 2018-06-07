package com.inedo.buildmaster.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.concordion.cubano.driver.http.HttpEasy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiPackage;
import com.inedo.buildmaster.domain.ApiVariable;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.BuildExecution;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.jenkins.GlobalConfig;
import com.inedo.jenkins.JenkinsConsoleLogWriter;
import com.inedo.utils.JsonCompare;
import com.inedo.utils.MockData;
import com.inedo.utils.MockServer;
import com.inedo.utils.TestConfig;

/**
 * Tests for the BuildMasterClientApache class
 * 
 * @author Andrew Sumner
 */
public class BuildMasterApiTest {
	private static MockServer mockServer = null;
	private static BuildMasterApi buildmaster;
    private static boolean compareJson = false;
	
	@BeforeClass
    public static void beforeClass() throws IOException {
		// Set the default log level for SLF4J simple logger to TRACE (defaults to INFO)
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		
		BuildMasterConfig config;

		if (TestConfig.useMockServer()) {
			mockServer = new MockServer();
			config = mockServer.getBuildMasterConfig();
		} else {
			config = TestConfig.getProGetConfig();
            compareJson = true;
		}

		GlobalConfig.injectConfiguration(config);
		
		buildmaster = new BuildMasterApi(new JenkinsConsoleLogWriter()).setRecordResult();
        buildmaster.setRecordJson(compareJson);
	}
		
	@AfterClass
	public static void tearDown() throws Exception {
		if (mockServer != null) {
			mockServer.stop();
		}
		
		GlobalConfig.injectConfiguration(null);
	}

	
	@Test
	public void checkConnection() throws IOException {
		// An exception will be thrown if fails
		buildmaster.checkConnection();
	}
	
	@Test(expected=UnknownHostException.class)
	public void getWithIncorrectHost() throws IOException {
		String origUrl = GlobalConfig.getBuildMasterConfig().url; 
		
		HttpEasy.withDefaults().baseUrl("http://buildmaster1");
						
		try {
			buildmaster.getApplications();
		} finally {
			HttpEasy.withDefaults().baseUrl(origUrl);
		}
	}
		
	@Test
	public void getApplications() throws IOException  {
    	Application[] applications = buildmaster.getApplications();

    	assertThat("Expect BuildMaster to have applications created", applications.length, is(greaterThan(0)));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.APPLICATIONS.getAsString(), buildmaster.getJsonString(), "[?(@.Application_Name=='TestApplication')]", Application.class);
        }
	}
	
	@Test
	public void getApplication() throws IOException  {
    	Application application = buildmaster.getApplication(TestConfig.getApplicationid());
    	
        assertThat("Expect BuildMaster to have sample application", application.Application_Name, is("TestApplication"));

        if (compareJson) {
            // API returns an array even though asking for a single application
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.APPLICATION.getAsString(), buildmaster.getJsonString(), "Applications_Extended[0]", Application.class);
        }
	}
	
	@Test
	public void getDeployables() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(TestConfig.getApplicationid());
    	
        assertThat("Expect BuildMaster to have applications created", deployables.length, is(greaterThan(0)));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.DEPLOYABLES.getAsString(), buildmaster.getJsonString(), "[?(@.Deployable_Name=='ExampleDeployable')]", Deployable.class);
        }
	}
		
	@Test
	public void getDeployable() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(TestConfig.getApplicationid());
    	Deployable deployable = buildmaster.getDeployable(deployables[0].Deployable_Id);
    	
        assertThat("Expect deployable", deployable.Deployable_Id, is(deployables[0].Deployable_Id));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.DEPLOYABLE.getAsString(), buildmaster.getJsonString(), "[0]", Deployable.class);
        }
	}
	 
	@Test
	public void enableReleaseDeployable() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		ReleaseDetails before = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);
		Deployable[] deployables = buildmaster.getDeployables(TestConfig.getApplicationid());
		
        // TODO Disable deployable to prove that actually doing anything
		buildmaster.enableReleaseDeployable(TestConfig.getApplicationid(), releaseNumber, deployables[0].Deployable_Id);
		
		ReleaseDetails after = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);
		
		assertThat(after.Releases_Extended[0].Application_Id, is(before.Releases_Extended[0].Application_Id));
		assertThat(after.Releases_Extended[0].Release_Number, is(before.Releases_Extended[0].Release_Number));
		assertThat(after.Releases_Extended[0].Latest_Build_Id, is(before.Releases_Extended[0].Latest_Build_Id));
		assertThat(after.Releases_Extended[0].Pipeline_Id, is(before.Releases_Extended[0].Pipeline_Id));
        // assertThat(after.Releases_Extended[0].ReleaseTemplate_Name, is(before.Releases_Extended[0].ReleaseTemplate_Name));
		
        // assertThat(after.Releases_Extended[0].Target_Date, is(before.Releases_Extended[0].Target_Date));
		assertThat(after.Releases_Extended[0].Release_Name, is(before.Releases_Extended[0].Release_Name));
        // assertThat(after.Releases_Extended[0].Notes_Text, is(before.Releases_Extended[0].Notes_Text));
		
		boolean found = false;
		
		for (Deployable deployable : after.ReleaseDeployables_Extended) {
			if (deployable.Deployable_Id == deployables[0].Deployable_Id) {
				found = true;
				break;
			}
		}
		
		assertThat(found, is(true));
	}
	
	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		
		assertThat("Expect Test Application to have an active release", release.length(), is(greaterThan(0)));
	}
	
	@Test
	public void getRelease() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		ReleaseDetails release = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);

		assertThat("Expect Test Application to have active release", release.Releases_Extended.length, is(greaterThan(0)));

		String status = release.Releases_Extended[0].ReleaseStatus_Name;
		
		assertThat("Expect Test Application to have active release " + releaseNumber, "Active", is(status));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.RELEASE.getAsString(), buildmaster.getJsonString(), ReleaseDetails.class);

            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed - Releases_Extended property",
                    MockData.RELEASE.getAsString(), buildmaster.getJsonString(), "Releases_Extended[0]", Release.class);

            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed - ReleaseDeployables_Extended property",
                    MockData.RELEASE.getAsString(), buildmaster.getJsonString(), "ReleaseDeployables_Extended[0]", Deployable.class);
        }
	}
	
	@Test
	public void getActiveReleases() throws IOException {
		Release[] releases = buildmaster.getActiveReleases(TestConfig.getApplicationid());
		
		assertThat("Expect Test Application to have active release(s)", releases.length, is(greaterThan(0)));

		if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.RELEASES.getAsString(), buildmaster.getJsonString(), "[?(@.Release_Id==1)]", Release.class);
        }
	}

	@Test
	public void getPackageVariables() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String testPackageNumber = buildmaster.getPreviousPackageNumber(TestConfig.getApplicationid(), releaseNumber);
		
		ApiVariable[] variables = buildmaster.getPackageVariables(TestConfig.getApplicationid(), releaseNumber, testPackageNumber);
		
		assertThat("Expect Test previous build to have variables defined", variables.length, is(greaterThan(0)));

		if (compareJson) {
            // As the returned json is just a list of variables that has been massaged into the ApiVariable structure we're not checking the class
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.PACKAGE_VARIABLES.getAsString(), buildmaster.getJsonString(), null);
        }
	}
	
	@Test
	public void getNextPackageNumber() throws NumberFormatException, IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		Integer nextPackageNumber = Integer.parseInt(buildmaster.getNextPackageNumber(TestConfig.getApplicationid(), releaseNumber));
		
		assertThat("Expect nextPackageNumber to be greate than zero", nextPackageNumber , is(greaterThan(0)));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.BUILDS.getAsString(), buildmaster.getJsonString(), "[?(@.Release_Id==1)]", Build.class);
        }
	}
	
	@Test
	public void createPackage() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String packageNumber = buildmaster.getNextPackageNumber(TestConfig.getApplicationid(), releaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
        // variablesList.put("cause", "unit test");
		
		ApiPackage apiPackage = buildmaster.createPackage(TestConfig.getApplicationid(), releaseNumber, packageNumber, variablesList);

        if (compareJson) {
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.API_PACKAGE.getAsString(), buildmaster.getJsonString(), ApiPackage.class);
        }

		assertThat("Expect returned packageNumber to be the same as requested", packageNumber, is(apiPackage.number));
		
		boolean result = buildmaster.waitForBuildCompletion(TestConfig.getApplicationid(), releaseNumber, apiPackage.number, true);
		
		assertThat("Expect Test build " + packageNumber + " to have built and deployed successfully", result);

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.BUILD_EXECUTIONS.getAsString(), buildmaster.getJsonString(), "[0]", BuildExecution.class);
        }
	}
	
    @Test
    public void deployPackageToStage() throws IOException, InterruptedException {
        String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String packageNumber = String.valueOf(Integer.parseInt(buildmaster.getNextPackageNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);

        ApiDeployment[] deployments = buildmaster.deployPackageToStage(TestConfig.getApplicationid(), releaseNumber, packageNumber, "Testing");

        assertThat("Have a deployment", deployments.length, is(greaterThan(0)));
        assertThat("Envrionment is what was asked for", deployments[0].environmentName, is("Testing"));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_DEPLOYMENT.getAsString(), buildmaster.getJsonString(), "[0]", ApiDeployment.class);
        }
    }

	@Test
	public void getPackage() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String packageNumber = String.valueOf(Integer.parseInt(buildmaster.getNextPackageNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);
		
		Build build = buildmaster.getBuild(TestConfig.getApplicationid(), releaseNumber, packageNumber);
		
		assertThat("Expect Test Application to have build number " + packageNumber, build.Build_Number.length() , is(greaterThan(0)));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.BUILD.getAsString(), buildmaster.getJsonString(), Build.class);
        }
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String packageNumber = String.valueOf(Integer.parseInt(buildmaster.getNextPackageNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);
		
		boolean result = buildmaster.waitForBuildCompletion(TestConfig.getApplicationid(), releaseNumber, packageNumber, false);
		
		assertThat("Expect Test build " + packageNumber + " to have built and deployed successfully", result);

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.BUILD_EXECUTIONS.getAsString(), buildmaster.getJsonString(), "[0]", BuildExecution.class);
        }
	}

	@Test
	public void printExecutionLog() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String packageNumber = String.valueOf(Integer.parseInt(buildmaster.getNextPackageNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);
		Build build = buildmaster.getBuild(TestConfig.getApplicationid(), releaseNumber, packageNumber);

        //TODO May need to do something with printstream...
//		BuildMasterConfig config = GlobalConfig.getBuildMasterConfig();
//		PrintStream printSteamOrig = config.printStream;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		try {
//			PrintStream printSteam = new PrintStream(outContent);
			
//			config.printStream = printSteam;
			
			buildmaster.printExecutionLog(build.Latest_Execution_Id);//.Current_Execution_Id);

            if (compareJson) {
                JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                        MockData.BUILD.getAsString(), buildmaster.getJsonString(), "[0]", Build.class);
            }

		} finally {
//			config.printStream = printSteamOrig;
		}
		
		assertThat("Expect Test build " + packageNumber + " to have an execution log", outContent.size(), is(greaterThan(0)));
		
//		System.out.println(outContent.toString());
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
	public void queueBuilds() throws IOException, InterruptedException {
		if (TestConfig.useMockServer()) return;
		
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
				
		Map<String, String> variablesList = new HashMap<>();
		int exectiontimes = 1;
		
		for (int i = 0; i < exectiontimes; i++) {
			String testrun = String.valueOf(i + 1);
			
			System.out.println("");
			System.out.println("Test Run: " + testrun);
						
			variablesList.put("hello", "world" + testrun);			
			ApiPackage apiPackage = buildmaster.createPackage(TestConfig.getApplicationid(), releaseNumber, variablesList);
			System.out.println("PackageNumber=" + apiPackage.number);

			String prevPackageNumber = buildmaster.getPreviousPackageNumber(TestConfig.getApplicationid(), releaseNumber);
			System.out.println("PreviousPackageNumber=" + prevPackageNumber);

			ApiVariable[] variables = buildmaster.getPackageVariables(TestConfig.getApplicationid(), releaseNumber, apiPackage.number);
			String value = "not found";
			
			for (ApiVariable variable : variables) {
				if (variable.name.equalsIgnoreCase("hello")) {
					value = variable.value;
				}
			}			
			
			System.out.println("Variable HELLO=" + value);
			
			buildmaster.waitForExistingBuildStepToComplete(TestConfig.getApplicationid(), releaseNumber);
			
			//buildmaster.waitForBuildCompletion(TestConfig.getApplicationid(), releaseNumber, packageNumber, false);
		}
	}

}
