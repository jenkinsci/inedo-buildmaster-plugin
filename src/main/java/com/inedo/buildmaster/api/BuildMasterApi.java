package com.inedo.buildmaster.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.driver.http.JsonReader;

import com.google.gson.JsonElement;
import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiPackageDeployment;
import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.domain.ApiReleasePackage;
import com.inedo.buildmaster.domain.ApiVariable;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.ApplicationDetail;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.DeploymentStatus;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.ReleaseStatus;
import com.inedo.jenkins.GlobalConfig;
import com.inedo.jenkins.JenkinsHelper;
import com.inedo.jenkins.JenkinsLogWriter;

/**
 * BuildMaster json api interface
 * 
 * Unless otherwise specified the api calls are specified in http://<buildmaster>/reference/api
 * 
 * @author Andrew Sumner
 */
public class BuildMasterApi {
    private final BuildMasterConfig config;
    private final JenkinsLogWriter logWriter;
    
    private boolean recordResult = false;
    private String jsonString;

    public BuildMasterApi(JenkinsLogWriter listener) {
        this(GlobalConfig.getBuildMasterConfig(), listener);

        if (!GlobalConfig.validateBuildMasterConfig()) {
            JenkinsHelper.fail("Please configure BuildMaster Plugin global settings");
        }
    }

    public BuildMasterApi(BuildMasterConfig config, JenkinsLogWriter logWriter) {
        this.config = config;
        this.logWriter = logWriter;

        HttpEasy.withDefaults()
                .baseUrl(config.url)
                .withLogWriter(logWriter)
                .trustAllCertificates(config.trustAllCertificates);
    }

    public void setRecordJson(boolean record) {
        this.recordResult = record;
    }

    public String getJsonString() {
        return jsonString;
    }

    public BuildMasterApi setRecordResult() {
        recordResult = true;
        return this;
    }

    /**
     * Ensure can call BuildMaster api. An exception will be thrown if cannot.
     * 
     * @throws IOException
     */
    public void checkConnection() throws IOException {
        HttpEasy.request()
                .path("/api/json/Applications_GetApplications")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Application_Count", 1)
                .get()
                .getJsonReader()
                .fromJson(Application[].class);
    }

    /**
     * Gets a list of all applications in the system.
     * 
     * @throws IOException
     */
    public Application[] getApplications() throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Applications_GetApplications")
                .queryParam("API_Key", config.apiKey)
                .get()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(Application[].class);
    }

    public Application getApplication(int applicationId) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Applications_GetApplication")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Application_Id", applicationId)
                .get()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        ApplicationDetail applicaton = reader.fromJson(ApplicationDetail.class);

        if (applicaton != null && applicaton.Applications_Extended.length > 0) {
            return applicaton.Applications_Extended[0];
        }

        return null;
    }

    /**
     * Gets the deployables for a specific application
     * 
     * @throws IOException
     */
    public Deployable[] getApplicationDeployables(int applicationId) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Applications_GetDeployables")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Application_Id", applicationId)
                .get()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(Deployable[].class);
    }

    /**
     * Gets the specified deployable
     * 
     * @throws IOException
     */
    public Deployable getDeployable(int deployableId) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Applications_GetDeployable")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Deployable_Id", deployableId)
                .get()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        Deployable[] deployables = reader.fromJson(Deployable[].class);

        if (deployables.length > 0) {
            return deployables[0];
        }

        return null;
    }

    /**
     * Gets the release details
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-releases">Endpoint Specification</a>
     */
    public ApiRelease getRelease(int applicationId, String releaseNumber) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/releases")
                .field("key", config.apiKey)
                .field("applicationId", applicationId)
                .field("releaseNumber", releaseNumber)
                .post()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        ApiRelease[] releases = reader.fromJson(ApiRelease[].class);
        if (releases.length > 0) {
            return releases[0];
        }

        // throw new IllegalStateException("Release was not found");
        return null;
    }

    /**
     * Gets list of number of active releases
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-releases">Endpoint Specification</a>
     */
    public ApiRelease[] getActiveReleases(int applicationId) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/releases")
                .field("key", config.apiKey)
                .field("applicationId", applicationId)
                .field("status", ReleaseStatus.ACTIVE.getText())
                .post()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(ApiRelease[].class);
    }

    /**
     * Gets the next available build number for the given release, if no builds
     * have been performed will return 1
     * 
     * @throws IOException
     */
    public String getReleaseNextPackageNumber(int applicationId, String releaseNumber) throws IOException {
        ApiRelease release = getRelease(applicationId, releaseNumber);

        if (release != null && release.latestPackageNumber != null) {
            return String.valueOf(Integer.parseInt(release.latestPackageNumber) + 1);
        }

        return "1";
    }

    /**
     * Gets the most recent build number for the given release, if no builds
     * have been performed will return null
     * 
     * @throws IOException
     */
    public String getReleaseCurrentPackageNumber(int applicationId, String releaseNumber) throws IOException {
        ApiRelease release = getRelease(applicationId, releaseNumber);

        if (release != null) {
            return release.latestPackageNumber;
        }

        return null;
    }

    /**
     * Gets release number of newest active release, if no active releases will
     * return an empty string
     * 
     * @throws IOException
     */
    public String getLatestActiveReleaseNumber(Integer applicationId) throws IOException {
        ApiRelease[] releases = getActiveReleases(applicationId);

        if (releases.length > 0) {
            return releases[0].number;
        }

        return "";
    }

    /**
     * Gets release number of newest active release
     * 
     * @throws IOException
     */
    public Deployable[] getReleaseDeployables(int applicationId, String releaseNumber) throws IOException {
        // There is no direct API so having to use old release api
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Releases_GetRelease")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Application_Id", applicationId)
                .queryParam("Release_Number", releaseNumber)
                .get()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(ReleaseDetails.class).ReleaseDeployables_Extended;
    }

    /**
     * Enables deployable on an existing release if it is not currently enabled
     * 
     * @throws IOException
     */
    public void enableReleaseDeployable(int applicationId, String releaseNumber, int deployableId) throws IOException {
        Deployable[] deployables = getReleaseDeployables(applicationId, releaseNumber);

        for (Deployable deployable : deployables) {
            if ("I".equals(deployable.InclusionType_Code)) {
                if (deployable.Deployable_Id == deployableId) {
                    logWriter.info("Deployable already enabled");
                    return;
                }
            }
        }

        HttpEasy.request()
                .path("/api/json/Releases_CreateOrUpdateReleaseDeployable")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Application_Id", applicationId)
                .queryParam("Release_Number", releaseNumber)
                .queryParam("Deployable_Id", deployableId)
                .get();
    }

    /**
     * Gets the details for a specified build.
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-packages">Endpoint Specification</a>
     */
    public ApiReleasePackage getPackage(int applicationId, String releaseNumber, String packageNumber) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/releases/packages")
                .field("key", config.apiKey)
                .field("applicationId", applicationId)
                .field("releaseNumber", releaseNumber)
                .field("packageNumber", packageNumber)
                .post()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        ApiReleasePackage[] packages = reader.fromJson(ApiReleasePackage[].class);

        if (packages.length > 0) {
            return packages[0];
        }

        return null;
    }

    /**
     * Creates a new build of an application and promote it to the
     * first environment. Error thrown on failure.
     * 
     * @return BuildNumber
     * 
     * @throws IOException
     */
    public ApiPackageDeployment createPackage(int applicationId, String releaseNumber, Map<String, String> variablesList) throws IOException {
        return createPackage(applicationId, releaseNumber, null, variablesList);
    }

    /**
     * Creates a new build for an application requesting it use a specific build
     * number and returns the build number of the new build. Error thrown on
     * failure.
     * 
     * @return BuildNumber
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#create-package">Endpoint Specification</a>
     */
    public ApiPackageDeployment createPackage(int applicationId, String releaseNumber, String packageNumber, Map<String, String> variablesList) throws IOException {
        HttpEasy request = HttpEasy.request()
                .path("/api/releases/packages/create")
                .skipEmptyValues(true)
                .field("key", config.apiKey)
                .field("applicationId", applicationId) 
                .field("releaseNumber", releaseNumber)
                .field("packageNumber", packageNumber);

        for (Map.Entry<String, String> variable : variablesList.entrySet()) {
            request.field("$" + variable.getKey(), variable.getValue());
        }
        
        JsonReader reader = request.put()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        ApiReleasePackage releasePackage = reader.fromJson(ApiReleasePackage.class);

        boolean storeRecordResult = recordResult;
        recordResult = false;

        try {
            // TODO should I be doing this - I would have thought buildmaster would do it. It would give me a deploymenId I could then pass to wait step
            // Perhaps like buildmaster I should have an option on the task
            ApiDeployment[] deployments = deployPackageToStage(applicationId, releaseNumber, releasePackage.number, null);

            return new ApiPackageDeployment(releasePackage, deployments);
        } finally {
            recordResult = storeRecordResult;
        }
    }
    
    /**
     * 
     * @param applicationId Required
     * @param releaseNumber Required
     * @param packageNumber Required
     * @param toStage Optional. If not supplied, the next stage in the pipeline will be used.
     * @return ApiDeployment[]
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-deployments">Endpoint Specification</a>
     */
    public ApiDeployment[] deployPackageToStage(int applicationId, String releaseNumber, String packageNumber, String toStage) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/releases/packages/deploy")
                .skipEmptyValues(true)
                .field("key", config.apiKey)
                .field("applicationId", applicationId) 
                .field("releaseNumber", releaseNumber)
                .field("packageNumber", packageNumber)
                .field("toStage", toStage)
                .put()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        ApiDeployment[] deployments = reader.fromJson(ApiDeployment[].class);

        return deployments;
    }
    
    /**
     * Gets all executions in the executing state.
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-deployments">Endpoint Specification</a>
     */
    /*
     * public String getDeploymentsInProgress(int applicationId) throws IOException {
     * return HttpEasy.request()
     * .path("/api/releases/packages/deployments")
     * .field("key", config.apiKey)
     * .field("applicationId", applicationId)
     * .post()
     * .asString();
     * }
     */

    /**
     * Gets the latest build executions for the specified build
     * 
     * @return Latest execution or empty object if no executions have occurred yet
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-deployments">Endpoint Specification</a>
     */
    public ApiDeployment getLatestDeployment(int applicationId, String releaseNumber, String packageNumber, Integer deploymentId) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/releases/packages/deployments")
                .skipEmptyValues(true)
                .field("key", config.apiKey)
                .field("applicationId", applicationId)
                .field("releaseNumber", releaseNumber)
                .field("packageNumber", packageNumber)
                .field("deploymentId", deploymentId)
                // .queryParam("PipelineStage_Name", "")
                // TODO Not supported and getting all deployments, latest at top so logic still works, although could use filter to confirm or pass in deploymentId
                // .field("Execution_Count", 1)
                .post()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        ApiDeployment[] deployments = reader.fromJson(ApiDeployment[].class);

        if (deployments.length > 0) {
            return deployments[0];
        }

        return new ApiDeployment();
    }

    /**
     * Gets the variable values for the build scope.
     * 
     * @throws IOException
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/variables">Variables Management</a>
     */
    public ApiVariable[] getPackageVariables(int applicationId, String releaseNumber, String packageNumber) throws IOException {
        // if (applicationId == null)
        // return new ApiVariable[0];
        if (releaseNumber == null || releaseNumber.isEmpty())
            return new ApiVariable[0];
        if (packageNumber == null || packageNumber.isEmpty())
            return new ApiVariable[0];

        Application application = getApplication(applicationId);

        JsonReader reader = HttpEasy.request()
                .path("/api/variables/packages/{«application-name»}/{«release-number»}/{«package-number»}")
                .urlParameters(application.Application_Name, releaseNumber, packageNumber)
                .queryParam("key", config.apiKey)
                .get()
                .getJsonReader();

        if (recordResult) {
            jsonString = reader.asPrettyString();
        }

        List<ApiVariable> variables = new ArrayList<ApiVariable>();

        for (Map.Entry<String, JsonElement> entry : reader.asJson().getAsJsonObject().entrySet()) {
            ApiVariable var = new ApiVariable();
            var.name = entry.getKey();
            if (entry.getValue().isJsonObject()) {
                for (Map.Entry<String, JsonElement> value : entry.getValue().getAsJsonObject().entrySet()) {
                    switch (value.getKey()) {
                    case "value":
                        var.value = value.getValue().getAsString();
                        break;
                    case "sensitive":
                        var.sensitive = value.getValue().getAsBoolean();
                        break;
                    }
                }
            } else {
                var.value = entry.getValue().getAsString();
            }

            variables.add(var);
        }

        return variables.toArray(new ApiVariable[variables.size()]);
    }

    /**
     * Checks to see if any existing builds are running a build step, if so will wait for it to complete
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean waitForExistingDeploymentsToComplete(int applicationId, String releaseNumber) throws IOException, InterruptedException {
        return waitForDeploymentToComplete(applicationId, releaseNumber, null, null, false);
    }

    public boolean waitForExistingDeploymentsToComplete(int applicationId, String releaseNumber, String packageNumber, boolean printLogOnFailure)
            throws IOException, InterruptedException {
        return waitForDeploymentToComplete(applicationId, releaseNumber, null, null, printLogOnFailure);
    }

    /**
     * Wait for the build to complete
     * 
     * @param printLogOnFailure
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean waitForDeploymentsToComplete(ApiDeployment[] deployments, boolean printLogOnFailure) throws IOException, InterruptedException {
        for (ApiDeployment deployment : deployments) {
            if (!waitForDeploymentToComplete(deployment.applicationId, deployment.releaseNumber, deployment.packageNumber, deployment.id, printLogOnFailure)) {
                return false;
            }
        }

        return true;
    }

    public boolean waitForDeploymentToComplete(int applicationId, String releaseNumber, String packageNumber, Integer deploymentId, boolean printLogOnFailure)
            throws IOException, InterruptedException {
        final List<String> executing = Arrays.asList(new String[] { null, "", DeploymentStatus.PENDING.getText(), DeploymentStatus.EXECUTING.getText() });
        final List<String> pending = Arrays.asList(new String[] { null, "", DeploymentStatus.PENDING.getText() });

        ApiDeployment deployment;

        try {
            deployment = getLatestDeployment(applicationId, releaseNumber, packageNumber, deploymentId);

            // TODO pause logging httpeasy?

            long startTime = new Date().getTime();
            Integer envrionmentId = deployment.environmentId;

            // Wait till both build step has completed
            while (executing.contains(deployment.status)) { // && deployment.environmentId == null
                Thread.sleep(7000);

                deployment = getLatestDeployment(applicationId, releaseNumber, packageNumber, deploymentId);
                // TODO still missing deployment.Build_AutoPromote_Indicator));
                logWriter.info(String.format("\tDeployment Status: %s, tDeployment Id: %s, Environment Name: %s, AutoPromote: %s", deployment.status, deployment.id,
                        deployment.environmentName, "?"));

                // Restart counter if now deploying to new environment
                if (envrionmentId != deployment.environmentId) {
                    envrionmentId = deployment.environmentId;
                    startTime = new Date().getTime();
                }

                // If have been waiting for more than 5 minutes to enter pending state then bail out
                if (pending.contains(deployment.status)) {
                    long endTime = new Date().getTime();
                    long diffMinutes = (endTime - startTime) / (60 * 1000);

                    if (diffMinutes >= 5) {
                        logWriter.info(String.format(
                                "\tRelease has been pending for over %s minutes, check the status of the build in BuildMaster to see if there is anything blocking it",
                                diffMinutes));
                        return false;
                    }
                }
            }
        } finally {
            // TODO resume logging httpeasy
            // this.logRequest = true;
        }

        if (!DeploymentStatus.SUCCEEDED.getText().equals(deployment.status) && printLogOnFailure) {
            printExecutionLog(deployment.id);
        }

        return DeploymentStatus.SUCCEEDED.getText().equals(deployment.status);
    }

    public String getExecutionLog(int deploymentId) throws IOException {
        // TODO: This is the only API that requires username / password - put that into the documentation
        String log = HttpEasy.request()
                .path("/executions/logs?executionId={}&level=0")
                .urlParameters(deploymentId)
                .authorization(config.user, config.password)
                .get()
                .asString();

        return log;
    }

    /**
     * Prints the build log for the execution id.
     * 
     * @throws IOException
     */
    public void printExecutionLog(int deploymentId) throws IOException {
        String log = getExecutionLog(deploymentId);

        logWriter.info("");
        logWriter.info("BuildMaster Execution Log:");
        logWriter.info("-------------------------");

        Arrays.stream(log.split("\\r?\\n")).forEach(line -> logWriter.info(line));

        logWriter.info("");
    }
}