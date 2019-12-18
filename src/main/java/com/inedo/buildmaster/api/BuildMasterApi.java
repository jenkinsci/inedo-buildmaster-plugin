package com.inedo.buildmaster.api;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import javax.annotation.Nonnull;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.concordion.cubano.driver.http.HttpEasy;
import org.concordion.cubano.driver.http.JsonReader;
import org.concordion.cubano.driver.http.XmlReader;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Strings;
import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.domain.ApiReleaseBuild;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.ApplicationDetail;
import com.inedo.buildmaster.domain.DeploymentStatus;
import com.inedo.buildmaster.domain.ReleaseStatus;
import com.inedo.buildmaster.jenkins.utils.GlobalConfig;
import com.inedo.buildmaster.jenkins.utils.JenkinsLogWriter;

/**
 * BuildMaster json api interface
 * 
 * Unless otherwise specified the api calls are specified in {@literal http://<buildmaster>/reference/api}
 * 
 * @author Andrew Sumner
 */
public class BuildMasterApi {
    private final BuildMasterConfig config;
    private final JenkinsLogWriter logWriter;
    
    private boolean recordJson = false;
    private String jsonString;

    public BuildMasterApi(JenkinsLogWriter listener) {
        this(GlobalConfig.getBuildMasterConfig(), listener);

        if (!GlobalConfig.isRequiredFieldsConfigured()) {
            throw new IllegalStateException("Please configure BuildMaster Plugin global settings");
        }
    }

    public BuildMasterApi(@Nonnull BuildMasterConfig config, JenkinsLogWriter logWriter) {
        this.config = config;
        this.logWriter = logWriter;

        HttpEasy.withDefaults()
                .baseUrl(config.url)
                .withLogWriter(logWriter)
                .logRequest(config.logApiRequests)
                .trustAllCertificates(config.trustAllCertificates)
                .trustAllHosts(config.trustAllCertificates)
                .sensitiveParameters("key", "API_Key");
    }

    public BuildMasterApi setRecordJson(boolean record) {
        this.recordJson = record;
        return this;
    }

    public String getJsonString() {
        return jsonString;
    }

    /**
     * Ensure can call the BuildMaster api. An exception will be thrown if cannot.
     * 
     * @throws IOException Http request exception
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
     * @throws IOException Http request exception
     */
    public Application[] getApplications() throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Applications_GetApplications")
                .queryParam("API_Key", config.apiKey)
                .get()
                .getJsonReader();

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(Application[].class);
    }

    /**
     * Get applicationId from application name or id.
     * @param identifier Application Name or Id.
     * @return applicationId
     * @throws IOException Http request exception
     */
    public int getApplicationIdFrom(String identifier) throws IOException {
        if (identifier.matches("[0-9]{1,}")) {
            return Integer.parseInt(identifier);
        }

        Optional<Application> application = Arrays.stream(getApplications()).filter(a -> a.Application_Name.equalsIgnoreCase(identifier)).findFirst();

        if (application.isPresent()) {
            return application.get().Application_Id;
        }

        throw new IOException("Application '" + identifier + "' was not found");
    }

    public Application getApplication(int applicationId) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Applications_GetApplication")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Application_Id", applicationId)
                .get()
                .getJsonReader();

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        ApplicationDetail application = reader.fromJson(ApplicationDetail.class);

        if (application != null && application.Applications_Extended.length > 0) {
            return application.Applications_Extended[0];
        }

        return null;
    }

    /**
     * Gets the applications pipelines
     *
     * @throws IOException Http request exception
     */
    public List<String> getPipelinesStages(int pipelineId) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/json/Pipelines_GetPipeline")
                .queryParam("API_Key", config.apiKey)
                .queryParam("Pipeline_Id", pipelineId)
                .get()
                .getJsonReader();

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        String xml = reader.getAsString("Pipeline_Configuration");
        xml = URLDecoder.decode(xml, "UTF-8");

        List<String> stages = new ArrayList<>();

        try {
            XmlReader xmlReader = new XmlReader(xml);
            Object evaluate = xmlReader.evaluate("//*/Stages/*/Properties/@Name", XPathConstants.NODESET);
            if (evaluate != null) {
                NodeList nodes = (NodeList) evaluate;

                int length = nodes.getLength();
                for (int i = 0; i < length; i++) {
                    Attr attr = (Attr) nodes.item(i);
                    stages.add(attr.getValue());
                }
            }
        } catch (ParserConfigurationException | SAXException | XPathExpressionException e) {
            logWriter.error("Unable to parse XML for pipleline stages", e);
        }

        return stages;
    }

    /**
     * Gets the release details
     * 
     * @throws IOException Http request exception
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

        if (recordJson) {
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
     * @throws IOException Http request exception
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

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(ApiRelease[].class);
    }

    /**
     * Gets the most recent build number for the given release, if no builds
     * have been performed will return null
     *
     * @throws IOException Http request exception
     */
    public BuildNumber getReleaseBuildNumber(int applicationId, String releaseNumber) throws IOException {
        BuildNumber buildNumber = new BuildNumber();
        ApiRelease release = getRelease(applicationId, releaseNumber);

        if (release == null || release.latestBuildNumber == null || release.latestBuildNumber.isEmpty()) {
            buildNumber.latest = "null";
            buildNumber.next = "1";
        } else {
            buildNumber.latest = release.latestBuildNumber;
            buildNumber.next = String.valueOf(Integer.parseInt(release.latestBuildNumber) + 1);
        }

        return buildNumber;
    }

    /**
     * Gets release number of newest active release, if no active releases will
     * return an empty string
     * 
     * @throws IOException Http request exception
     */
    public String getLatestActiveReleaseNumber(Integer applicationId) throws IOException {
        ApiRelease[] releases = getActiveReleases(applicationId);

        // TODO Assumes that order will always be newest first
        if (releases.length > 0) {
            return releases[0].number;
        }

        return "";
    }

    /**
     * Gets the details for a specified build.
     * 
     * @throws IOException Http request exception
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-packages">Endpoint Specification</a>
     */
    public ApiReleaseBuild getBuild(int applicationId, String releaseNumber, String buildNumber) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/releases/builds")
                .field("key", config.apiKey)
                .field("applicationId", applicationId)
                .field("releaseNumber", releaseNumber)
                .field("buildNumber", buildNumber)
                .post()
                .getJsonReader();

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        ApiReleaseBuild[] builds = reader.fromJson(ApiReleaseBuild[].class);

        if (builds.length > 0) {
            return builds[0];
        }

        return null;
    }

    /**
     * Creates a new build for an application.
     * 
     * @return ApiReleaseBuild
     * 
     * @throws IOException Http request exception
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#create-package">Endpoint Specification</a>
     */
    public ApiReleaseBuild createBuild(int applicationId, String releaseNumber, Map<String, String> variablesList)
            throws IOException {
        HttpEasy request = HttpEasy.request()
                .path("/api/releases/builds/create")
                .field("key", config.apiKey)
                .field("applicationId", applicationId) 
                .field("releaseNumber", releaseNumber);

        for (Map.Entry<String, String> variable : variablesList.entrySet()) {
            request.field("$" + variable.getKey(), variable.getValue());
        }
        
        JsonReader reader = request.put()
                .getJsonReader();

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(ApiReleaseBuild.class);
    }
    
    /**
     * 
     * @param applicationId Required
     * @param releaseNumber Required
     * @param buildNumber Required
     * @param stage Optional. If not supplied, the next stage in the pipeline will be used.
     * @return ApiDeployment[]
     * 
     * @throws IOException Http request exception
     * @throws InterruptedException Failed while waiting for action
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#deploy-package">Endpoint Specification</a>
     */
    public ApiDeployment[] deployBuildToStage(int applicationId, String releaseNumber, String buildNumber, Map<String, String> variablesList, String stage, boolean forceDeployment)
            throws IOException, InterruptedException {
        // This is a fail safe step - BuildMaster can tie itself in knots if a new build is created while and existing one is being performed.
        waitForActiveDeploymentsToComplete(applicationId, releaseNumber);

        HttpEasy request = HttpEasy.request()
                .path("/api/releases/builds/deploy")
                .field("key", config.apiKey)
                .field("applicationId", applicationId) 
                .field("releaseNumber", releaseNumber)
                .field("buildNumber", buildNumber)
                .field("toStage", stage);

        if (variablesList != null) {
            for (Map.Entry<String, String> variable : variablesList.entrySet()) {
                request.field("$" + variable.getKey(), variable.getValue());
            }
        }

        if (forceDeployment) {
            request.field("force", forceDeployment);
        }

        JsonReader reader = request
                .put()
                .getJsonReader();

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        ApiDeployment[] deployments = reader.fromJson(ApiDeployment[].class);

        return deployments;
    }
    
    /**
     * Gets all executions in the executing, optionally limiting to a particular state.
     * 
     * @throws IOException Http request exception
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-deployments">Endpoint Specification</a>
     */
    private ApiDeployment[] getDeployments(int applicationId, String releaseNumber, String buildNumber, Integer deploymentId, DeploymentStatus status) throws IOException {
        JsonReader reader = HttpEasy.request()
                .path("/api/releases/builds/deployments")
                .field("key", config.apiKey)
                .field("applicationId", applicationId)
                .field("releaseNumber", releaseNumber)
                .field("buildNumber", buildNumber)
                .field("deploymentId", deploymentId)
                .field("status", status == null ? null : status.getText())
                .post()
                .getJsonReader();

        if (recordJson) {
            jsonString = reader.asPrettyString();
        }

        return reader.fromJson(ApiDeployment[].class);
    }

    /**
     * Gets the latest build executions for the specified build
     * 
     * @return Latest execution or empty object if no executions have occurred yet
     * 
     * @throws IOException Http request exception
     * 
     * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#get-deployments">Endpoint Specification</a>
     */
    public ApiDeployment getLatestDeployment(int applicationId, String releaseNumber, String buildNumber) throws IOException {
        ApiDeployment[] deployments = getDeployments(applicationId, releaseNumber, buildNumber, null, null);

        if (deployments.length > 0) {
            return deployments[0];
        }

        return new ApiDeployment();
    }

    public ApiDeployment getDeployment(int applicationId, String releaseNumber, String buildNumber, Integer deploymentId) throws IOException {
        ApiDeployment[] deployments = getDeployments(applicationId, releaseNumber, buildNumber, deploymentId, null);

        if (deployments.length > 0) {
            return deployments[0];
        }

        // TODO Set this to null?
        return new ApiDeployment();
    }

    public ApiDeployment[] getActiveDeployments(int applicationId, String releaseNumber, String buildNumber) throws IOException {
        List<ApiDeployment> deployments = new ArrayList<>();

        deployments.addAll(Arrays.asList(getDeployments(applicationId, releaseNumber, buildNumber, null, DeploymentStatus.PENDING)));
        deployments.addAll(Arrays.asList(getDeployments(applicationId, releaseNumber, buildNumber, null, DeploymentStatus.EXECUTING)));

        return deployments.toArray(new ApiDeployment[0]);
    }

    /**
     * Checks to see deployments are running for a release, if so will wait for them to complete.
     * 
     * @throws IOException Http request exception
     * @throws InterruptedException Failed why waiting for action
     */
    public boolean waitForActiveDeploymentsToComplete(int applicationId, String releaseNumber) throws IOException, InterruptedException {
        ApiDeployment[] deployments = getActiveDeployments(applicationId, releaseNumber, null);

        for (ApiDeployment deployment : deployments) {
            if (!waitForDeploymentToComplete(deployment.applicationId, deployment.releaseNumber, deployment.buildNumber, deployment.id, false, true)) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Wait for the build deployment, and any automatic promotions, to complete.
     * 
     * @param deployments
     * @param printLogOnFailure
     * @return
     */
    public boolean waitForDeploymentToComplete(ApiDeployment[] deployments, boolean printLogOnFailure) throws IOException, InterruptedException {
        for (ApiDeployment deployment : deployments) {
            // Wait for the initial deployment to complete
            if (!waitForDeploymentToComplete(deployment.applicationId, deployment.releaseNumber, deployment.buildNumber, deployment.id, printLogOnFailure, false)) {
                return false;
            }
        }

        if (deployments.length > 0) {
            // Wait for any automatic promotions to complete
            ApiDeployment[] secondaryDeployments;
            while ((secondaryDeployments = getActiveDeployments(deployments[0].applicationId, deployments[0].releaseNumber, deployments[0].buildNumber)).length > 0) {
                for (ApiDeployment deployment : secondaryDeployments) {
                    if (!waitForDeploymentToComplete(deployment.applicationId, deployment.releaseNumber, deployment.buildNumber, deployment.id, printLogOnFailure, false)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static final List<String> executing = Arrays.asList(null, "", DeploymentStatus.PENDING.getText(), DeploymentStatus.EXECUTING.getText());
    private static final List<String> pending = Arrays.asList(null, "", DeploymentStatus.PENDING.getText());

    private boolean waitForDeploymentToComplete(int applicationId, String releaseNumber, String buildNumber, Integer deploymentId, boolean printLogOnFailure,
                                                boolean includeBuildNumberInLog)
            throws IOException, InterruptedException {

        ApiDeployment deployment;

        try {
            deployment = getDeployment(applicationId, releaseNumber, buildNumber, deploymentId);

            logWriter.info("Waiting for deployment to the {0} stage to complete{1}...",
                    deployment.pipelineStageName,
                    (includeBuildNumberInLog ? " for build " + buildNumber : ""));

            // Pause logging of API requests
            // TODO HttpEasy.withDefaults().logRequest(false);

            long startTime = new Date().getTime();
            Integer environmentId = deployment.environmentId;

            // TODO Originally waited for any automatic promotions to complete, should we? Would need to wait an extra few seconds to ensure that a new build had not started.
            // Don't have Build_AutoPromote_Indicator anymore to help with this

            // Wait till both build step has completed
            while (executing.contains(deployment.status)) { // && deployment.environmentId == null
                Thread.sleep(7000);

                deployment = getDeployment(applicationId, releaseNumber, buildNumber, deploymentId);

                // Restart counter if now deploying to new environment
                if (!Objects.equals(environmentId, deployment.environmentId)) {
                    environmentId = deployment.environmentId;
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

            boolean successful = DeploymentStatus.SUCCEEDED.getText().equalsIgnoreCase(deployment.status) || DeploymentStatus.WARNED.getText().equalsIgnoreCase(deployment.status);

            if (!successful && printLogOnFailure) {
                logWriter.info("Deployment has failed");
                printExecutionLog(deployment.id);
            }

            return successful;

        } finally {
            // Resume logging - if enabled
            // TODO HttpEasy.withDefaults().logRequest(config.logApiRequests);
        }
    }

    public String getExecutionLog(int deploymentId) throws IOException {
        if (Strings.isNullOrEmpty(config.user) || Strings.isNullOrEmpty(config.password)) {
            throw new IOException("Unable to get BuildMaster execution logs - username and password must be configured in global settings");
        }

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
     */
    public void printExecutionLog(int deploymentId) {
        try {
            String log = getExecutionLog(deploymentId);

            logWriter.info("");
            logWriter.info("BuildMaster Execution Log:");
            logWriter.info("-------------------------");

            Arrays.stream(log.split("\\r?\\n")).forEach(line -> logWriter.info(line));

            logWriter.info("");
        } catch (Exception e) {
            logWriter.error(String.format("Unable to get BuildMaster deployment execution log: %s", e.getMessage()));
        }
    }

    public class BuildNumber {
        public String latest;
        public String next;
    }
}