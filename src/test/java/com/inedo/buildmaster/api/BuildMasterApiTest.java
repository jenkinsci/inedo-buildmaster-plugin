package com.inedo.buildmaster.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.concordion.cubano.driver.http.HttpEasy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.domain.ApiReleaseBuild;
import com.inedo.buildmaster.domain.ApiVariable;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.jenkins.GlobalConfig;
import com.inedo.buildmaster.jenkins.utils.JenkinsConsoleLogWriter;
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
		
		buildmaster = new BuildMasterApi(new JenkinsConsoleLogWriter());
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
    public void getApplicationDeployables() throws IOException {
        Deployable[] deployables = buildmaster.getApplicationDeployables(TestConfig.getApplicationid());
    	
        assertThat("Expect BuildMaster to have applications created", deployables.length, is(greaterThan(0)));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.DEPLOYABLES.getAsString(), buildmaster.getJsonString(), "[?(@.Deployable_Name=='Test Application Deployable')]", Deployable.class);
        }
	}
		
	@Test
	public void getDeployable() throws IOException  {
        Deployable[] deployables = buildmaster.getApplicationDeployables(TestConfig.getApplicationid());

        assertThat("Expect deployable", deployables.length, is(greaterThan(0)));

    	Deployable deployable = buildmaster.getDeployable(deployables[0].Deployable_Id);
    	
        assertThat("Deployable is the one asked for", deployable.Deployable_Id, is(deployables[0].Deployable_Id));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.DEPLOYABLE.getAsString(), buildmaster.getJsonString(), "[0]", Deployable.class);
        }
	}

    @Test
    public void getPipelineStages() throws IOException {
        ApiRelease[] releases = buildmaster.getActiveReleases(TestConfig.getApplicationid());

        List<String> stages = buildmaster.getPipelinesStages(releases[0].pipelineId);

        assertThat("Expect pipeline stages", stages.size(), is(greaterThan(0)));
    }

    @Test
    public void getReleaseDeployables() throws IOException {
        String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());

        Deployable[] releaseDeployables = buildmaster.getReleaseDeployables(TestConfig.getApplicationid(), releaseNumber);

        assertThat("Expect deployable", releaseDeployables.length, is(greaterThan(0)));

        if (compareJson) {
            // Calling old Releases_GetRelease API to get this information
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.RELEASE.getAsString(), buildmaster.getJsonString(), ReleaseDetails.class);

            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed - Releases_Extended property",
                    MockData.RELEASE.getAsString(), buildmaster.getJsonString(), "Releases_Extended[0]", Release.class);

            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed - ReleaseDeployables_Extended property",
                    MockData.RELEASE.getAsString(), buildmaster.getJsonString(), "ReleaseDeployables_Extended[0]", Deployable.class);
        }
    }

	@Test
	public void enableReleaseDeployable() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		
        Deployable[] applicationDeployables = buildmaster.getApplicationDeployables(TestConfig.getApplicationid());

        assertThat("Application must have a deployable defined.", applicationDeployables.length, is(greaterThan(0)));

        int targetDeployableId = applicationDeployables[0].Deployable_Id;

        buildmaster.enableReleaseDeployable(TestConfig.getApplicationid(), releaseNumber, targetDeployableId);

        Deployable[] releaseDeployables = buildmaster.getReleaseDeployables(TestConfig.getApplicationid(), releaseNumber);

		boolean found = false;
		
        for (Deployable deployable : releaseDeployables) {
            if (deployable.Deployable_Id == targetDeployableId) {
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
        ApiRelease release = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);

        assertThat("Expect Test Application to have active release", release, is(notNullValue()));

		String status = release.status;
		
		assertThat("Expect Test Application to have active release " + releaseNumber, "active", is(status));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE.getAsString(), buildmaster.getJsonString(), "[0]", ApiRelease.class);
        }
	}
	
	@Test
	public void getActiveReleases() throws IOException {
        ApiRelease[] releases = buildmaster.getActiveReleases(TestConfig.getApplicationid());
		
		assertThat("Expect Test Application to have active release(s)", releases.length, is(greaterThan(0)));

		if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE.getAsString(), buildmaster.getJsonString(), "[0]", ApiRelease.class);
        }
	}

	@Test
    public void getBuildVariables() throws IOException {
        Application application = buildmaster.getApplication(TestConfig.getApplicationid());
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String testBuildNumber = buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber);
		
        ApiVariable[] variables = buildmaster.getBuildVariables(application.Application_Name, releaseNumber, testBuildNumber);
		
        assertThat("Expect Test previous build to have variables defined", variables.length, is(greaterThan(0)));

		if (compareJson) {
            // As the returned json is just a list of variables that has been massaged into the ApiVariable structure we're not checking the class
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.BUILD_VARIABLES.getAsString(), buildmaster.getJsonString(), null);
        }
	}
	
	@Test
    public void getNextBuildNumber() throws NumberFormatException, IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        Integer nextBuildNumber = Integer.parseInt(buildmaster.getReleaseNextBuildNumber(TestConfig.getApplicationid(), releaseNumber));
		
        assertThat("Expect nextBuildNumber to be greate than zero", nextBuildNumber, is(greaterThan(0)));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE.getAsString(), buildmaster.getJsonString(), "[0]", ApiRelease.class);
        }
	}
	
	@Test
    public void createBuild() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = buildmaster.getReleaseNextBuildNumber(TestConfig.getApplicationid(), releaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
        variablesList.put("cause", "unit test");
		
        ApiReleaseBuild releaseBuild = buildmaster.createBuild(TestConfig.getApplicationid(), releaseNumber, buildNumber, variablesList);
        if (compareJson) {
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE_BUILD.getAsString(), buildmaster.getJsonString(), ApiReleaseBuild.class);
        }

        assertThat("Expect returned buildNumber to be the same as requested", buildNumber, is(releaseBuild.number));
		
        ApiDeployment[] deployments = buildmaster.deployBuildToStage(TestConfig.getApplicationid(), releaseNumber, releaseBuild.number, null, null);

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_DEPLOYMENT.getAsString(), buildmaster.getJsonString(), "[0]", ApiDeployment.class);
        }

        boolean result = buildmaster.waitForDeploymentToComplete(deployments, true);

        assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
    @Test
    public void deployBuildToStage() throws IOException, InterruptedException {
        String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getReleaseNextBuildNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);

        ApiDeployment[] deployments = buildmaster.deployBuildToStage(TestConfig.getApplicationid(), releaseNumber, buildNumber, null, "Integration");

        assertThat("Have a deployment", deployments.length, is(greaterThan(0)));
        assertThat("Envrionment is what was asked for", deployments[0].environmentName, is("Integration"));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_DEPLOYMENT_WITH_ENV.getAsString(), buildmaster.getJsonString(), "[0]", ApiDeployment.class);
        }
    }

	@Test
    public void getBuild() throws IOException {
        String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = String.valueOf(buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber));
		
        ApiReleaseBuild releaseBuild = buildmaster.getBuild(TestConfig.getApplicationid(), releaseNumber, buildNumber);
		
        assertThat("Expect Test Application to have build number " + buildNumber, releaseBuild.number.length(), is(greaterThan(0)));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE_BUILDS.getAsString(), buildmaster.getJsonString(), "[0]", ApiReleaseBuild.class);
        }
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber);
		
        boolean result = buildmaster.waitForActiveDeploymentsToComplete(TestConfig.getApplicationid(), releaseNumber);
		
        assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}

	@Test
    public void printExecutionLog() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber);

        ApiDeployment deployment = buildmaster.getLatestDeployment(TestConfig.getApplicationid(), releaseNumber, buildNumber);
		
        buildmaster.waitForActiveDeploymentsToComplete(deployment.applicationId, deployment.releaseNumber);

        String log = buildmaster.getExecutionLog(deployment.id);
		
        assertThat("Expect Test build " + buildNumber + " to have an execution log", log.length(), is(greaterThan(0)));
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
            ApiReleaseBuild releaseBuild = buildmaster.createBuild(TestConfig.getApplicationid(), releaseNumber, variablesList);
            System.out.println("BuildNumber=" + releaseBuild.number);

            ApiDeployment[] deployments = buildmaster.deployBuildToStage(TestConfig.getApplicationid(), releaseNumber, releaseBuild.number, null, null);

            String currentBuildNumber = buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber);
            System.out.println("CurrentBuildNumber=" + currentBuildNumber);

            ApiVariable[] variables = buildmaster.getBuildVariables(releaseBuild.applicationName, releaseNumber, releaseBuild.number);
			String value = "not found";
			
			for (ApiVariable variable : variables) {
				if (variable.name.equalsIgnoreCase("hello")) {
					value = variable.value;
				}
			}			
			
			System.out.println("Variable HELLO=" + value);
			
            buildmaster.waitForDeploymentToComplete(deployments, false);
		}
	}

}
