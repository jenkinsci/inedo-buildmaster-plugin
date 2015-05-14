package com.inedo.buildmaster.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inedo.buildmaster.ConnectionType;
import com.inedo.buildmaster.TriggerBuildHelper;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.BuildExecution;
import com.inedo.buildmaster.domain.BuildExecutionActionGroupActionLogEntries;
import com.inedo.buildmaster.domain.BuildExecutionDetails;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.Variable;

/**
 * BuildMaster json api interface
 * 
 * @author Andrew Sumner
 */
public class BuildMasterClientApache {
	private BuildMasterConfig config;
	private HttpClient httpclient;

	public BuildMasterClientApache(HttpClient httpclient, BuildMasterConfig config) {
		this.httpclient = httpclient;
		this.config = config;
	}

	public BuildMasterClientApache(BuildMasterConfig config) {
		this.config = config;

		if (config.url.endsWith("/")) {
    		config.url = config.url.substring(0, config.url.length() - 1);
    	}
		
		HttpClientBuilder httpbuilder = HttpClients.custom();
		RequestConfig.Builder configbuilder = RequestConfig.custom();

		if (ConnectionType.BASIC.getId().equalsIgnoreCase(config.authentication)) {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(config.user,config.password));

			httpbuilder.setDefaultCredentialsProvider(credentialsProvider);
			configbuilder.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC));
		}

		if (ConnectionType.NTLM.getId().equalsIgnoreCase(config.authentication)) {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(
					AuthScope.ANY,
					new NTCredentials(config.user, config.password, config.getHost(), config.domain));

			httpbuilder.setDefaultCredentialsProvider(credentialsProvider);
			configbuilder.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM));
		}

		// Finally we instantiate the client. Client is a thread safe object and
		// can be used by several threads at the same time.
		// Client can be used for several request. The life span of the client
		// must be equal to the life span of this EJB.
		httpclient = httpbuilder.setDefaultRequestConfig(configbuilder.build()).build();
	}

	/**
	 * Ensure can call BuildMaster api. An exception will be thrown if cannot.
	 * 
	 * @throws IOException
	 */
	public void checkConnection() throws IOException {
		doGet(Application[].class, "Applications_GetApplications", "Application_Count", "1");
	}

	/**
	 * Gets a list of all applications in the system.
	 * 
	 * @throws IOException
	 */
	public Application[] getApplications() throws IOException {
		return doGet(Application[].class, "Applications_GetApplications");
	}

	/**
	 * Gets the deployables for a specific application
	 * 
	 * @throws IOException
	 */
	public Deployable[] getDeployables(String applicationId) throws IOException {
		return doGet(Deployable[].class, "Applications_GetDeployables", "Application_Id", applicationId);
	}
	
	/**
	 * Gets the specified deployable
	 * 
	 * @throws IOException
	 */
	public Deployable getDeployable(String deployableId) throws IOException {
		Deployable[] deployables = doGet(Deployable[].class, "Applications_GetDeployable", "Deployable_Id", deployableId);
		
		if (deployables.length > 0) {
			return deployables[0];
		}

		return null;
	}
	
	/**
	 * Gets release number of newest active release
	 * 
	 * @throws IOException
	 */
	public ReleaseDetails getRelease(String applicationId, String releaseNumber) throws IOException {
		return doGet(ReleaseDetails.class, "Releases_GetRelease", "Application_Id", applicationId, "Release_Number", releaseNumber);
	}
	
	/**
	 * Gets list of number of active releases
	 * 
	 * @throws IOException
	 */
	public Release[] getActiveReleases(String applicationId) throws IOException {
		return doGet(Release[].class, "Releases_GetReleases", "Application_Id", applicationId, "ReleaseStatus_Name", "Active");
	}

	/**
	 * Gets release number of newest active release, if no active releases will
	 * return an empty string
	 * 
	 * @throws IOException
	 */
	public String getLatestActiveReleaseNumber(String applicationId) throws IOException {
		Release[] release = doGet(Release[].class, "Releases_GetReleases",
				"Application_Id", applicationId, "ReleaseStatus_Name", "Active", "Release_Count", "1");

		if (release.length > 0) {
			return release[0].Release_Number;
		}

		return "";
	}

	/**
	 * Enables deployable on an existing release if it is not currently enabled
	 *  
	 * @throws IOException
	 */
	public void enableReleaseDeployable(String applicationId, String releaseNumber, String deployableId) throws IOException {
		List<Deployable> deployables = new ArrayList<>();
		int id = Integer.parseInt(deployableId);
		
		ReleaseDetails releaseDetails = getRelease(applicationId, releaseNumber);
		
		for (Deployable deployable : releaseDetails.ReleaseDeployables_Extended) {
			if ("I".equals(deployable.InclusionType_Code)) {
				deployables.add(deployable);
				
				if (deployable.Deployable_Id == id) {
					config.printStream.println(TriggerBuildHelper.LOG_PREFIX + "Deployable already enabled");
					return;
				}
			}
		}
		
		Deployable deployable = new Deployable();
		deployable.Deployable_Id = id;
		deployable.InclusionType_Code = "I";
		
		deployables.add(deployable);
		
		Release release = releaseDetails.Releases_Extended[0]; 
		
		doGet(Void.class, "Releases_CreateOrUpdateRelease", 
				"Application_Id", String.valueOf(release.Application_Id), 
				"Release_Number", release.Release_Number,
			    "Workflow_Id", String.valueOf(release.Workflow_Id),
			    "Target_Date", release.Target_Date,
			    "Release_Name", release.Release_Name,
			    //Notes_Text
			    "DeployableIds_Xml", encodeDeployables(deployables));
	}
	/**
	 * Gets the next available build number for the given release, if no builds
	 * have been performed will return 1
	 * 
	 * @throws IOException
	 */
	public String getNextBuildNumber(String applicationId, String releaseNumber) throws IOException {
		Build[] builds = doGet(Build[].class, "Builds_GetBuilds", "Application_Id", applicationId, "Release_Number", releaseNumber, "Build_Count", "1");

		if (builds.length > 0) {
			return String.valueOf(Integer.parseInt(builds[0].Build_Number) + 1);
		}

		return "1";
	}

	public String getPreviousBuildNumber(String applicationId, String releaseNumber) throws IOException {
		Build[] builds = doGet(Build[].class, "Builds_GetBuilds", "Application_Id", applicationId, "Release_Number", releaseNumber, "Build_Count", "1");

		if (builds.length > 0) {
			return builds[0].Build_Number;
		}

		return null;
	}
	
	/**
	 * Creates a new build for an application requesting it use a specific build
	 * number and returns the build number of the new build. Error thrown on
	 * failure.
	 * 
	 * @return BuildNumber
	 * 
	 * @throws IOException
	 */
	public String createBuild(String applicationId, String releaseNumber, String buildNumber, Map<String, String> variablesList) throws IOException {
		//PromoteBuild_Indicator required for those that don't have a build step
		return doGet(String.class, "Builds_CreateBuild", "Application_Id",
				applicationId, "Release_Number", releaseNumber, "Requested_Build_Number", buildNumber, 
				"PromoteBuild_Indicator", "Y", "BuildVariables_Xml", encodeVariables(variablesList));
	}

	/**
	 * Creates a new build of an application and optionally promotes it to the
	 * first environment. Error thrown on failure.
	 * 
	 * @return BuildNumber
	 * 
	 * @throws IOException
	 */
	public String createBuild(String applicationId, String releaseNumber, Map<String, String> variablesList) throws IOException {
		return createBuild(applicationId, releaseNumber, null, variablesList);
	}

	/**
	 * Gets the details for a specified build.
	 * 
	 * @throws IOException
	 */
	public Build getBuild(String applicationId, String releaseNumber, String buildNumber) throws IOException {
		Build[] builds = doGet(Build[].class, "Builds_GetBuild",
				"Application_Id", applicationId, "Release_Number", releaseNumber, "Build_Number", buildNumber);

		if (builds.length > 0) {
			return builds[0];
		}

		return null;
	}

	/**
	 * Gets all executions in the executing state.
	 * 
	 * @throws IOException
	 */
	public String getExecutionsInProgress(String applicationId) throws IOException {
		StringBuilder executions = doGet(StringBuilder.class, "Builds_GetExecutionsInProgress", "Application_Id", applicationId);

		return executions.toString();
	}

	/**
	 * Gets the latest build executions for the specified build
	 * 
	 * @return Latest execution or empty object if no executions have occurred yet
	 * 
	 * @throws IOException
	 */
	public BuildExecution getLatestExecution(String applicationId, String releaseNumber, String buildNumber) throws IOException {
		BuildExecution[] executions = doGet(BuildExecution[].class, "Builds_GetExecutions", "Application_Id", applicationId, "Release_Number", releaseNumber, "Build_Number", buildNumber, "Execution_Count", "1");

		if (executions.length > 0) {
			return executions[0];
		}

		return new BuildExecution();
	}
	
	/**
	 * Gets the variable values for the build scope.
	 * 
	 * @throws IOException
	 */
	public Variable[] getVariableValues(String applicationId, String releaseNumber, String buildNumber) throws IOException {
		if (applicationId == null || applicationId.isEmpty()) return new Variable[0];
		if (releaseNumber == null || releaseNumber.isEmpty()) return new Variable[0]; 
		if (buildNumber == null || buildNumber.isEmpty()) return new Variable[0]; 

		Variable[] variables = doGet(Variable[].class, "Variables_GetVariableValues",
				"Application_Id", applicationId, "Release_Number", releaseNumber, "Build_Number", buildNumber);
		
		ArrayList<Variable> inScope = new ArrayList<Variable>();
		
		for (Variable variable : variables) {
			if ("B".equals(variable.Scope_Code)) {
				inScope.add(variable);
			}
		}
		
		return inScope.toArray(new Variable[inScope.size()]);
	}

	/**
	 * Checks to see if any existing builds are running a build step, if so will wait for it to complete 
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void waitForExistingBuildStepToComplete(String applicationId, String releaseNumber) throws IOException, InterruptedException {
		config.printStream.println(TriggerBuildHelper.LOG_PREFIX + "Wait for any existing builds to complete");
		
		final List<String> executing = Arrays.asList(new String[] { null, "", "Pending", "Executing" });
		final List<String> pending = Arrays.asList(new String[] { null, "", "Pending" });

		BuildExecution execution = getLatestExecution(applicationId, releaseNumber, null);
		
		long startTime = new Date().getTime();		
		
		// Wait till both build step has completed
		while (executing.contains(execution.ExecutionStatus_Name) && execution.Environment_Id == null) {
			Thread.sleep(7000);
			
			execution = getLatestExecution(applicationId, releaseNumber, null);
			config.printStream.println(String.format("\tExecution Status: %s, Execution Id: %s, Environment Name: %s, AutoPromote: %s", execution.ExecutionStatus_Name, execution.Execution_Id, execution.Environment_Name, execution.Build_AutoPromote_Indicator));
			
			// If have been waiting for more than 5 minutes to enter pending state then bail out  
			if (pending.contains(execution.ExecutionStatus_Name)) {
				long endTime = new Date().getTime();
				long diffMinutes = (endTime - startTime) / (60 * 1000);

				if (diffMinutes >= 5) {
					config.printStream.println(String.format("\tRelease has been pending for over %s minutes, check the status of the build in BuildMaster to see if there is anything blocking it", diffMinutes));
					return;
				}
			}
		}
	}
	
	/**
	 * Wait for the build to complete
	 * 
	 * @param printLogOnFailure
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean waitForBuildCompletion(String applicationId, String releaseNumber, String buildNumber, boolean printLogOnFailure) throws IOException, InterruptedException {
		final List<String> executing = Arrays.asList(new String[] { null, "", "Pending", "Executing" });
		final List<String> pending = Arrays.asList(new String[] { null, "", "Pending" });

		BuildExecution execution = getLatestExecution(applicationId, releaseNumber, buildNumber);		
		config.printStream.println(String.format("\tExecution Status: %s, Execution Id: %s, Environment Name: %s, AutoPromote: %s", execution.ExecutionStatus_Name, execution.Execution_Id, execution.Environment_Name, execution.Build_AutoPromote_Indicator));
		
		Integer envrionmentId = execution.Environment_Id;
		long startTime = new Date().getTime();		
		
		// Wait till both build step (if exists) and deployment to the first environment have completed (if has build step with AutoPromote flag set)
		while (executing.contains(execution.ExecutionStatus_Name) || (execution.Environment_Id == null && execution.Build_AutoPromote_Indicator.equalsIgnoreCase("Y"))) {
			Thread.sleep(7000);
			
			execution = getLatestExecution(applicationId, releaseNumber, buildNumber);
			config.printStream.println(String.format("\tExecution Status: %s, Execution Id: %s, Environment Name: %s, AutoPromote: %s", execution.ExecutionStatus_Name, execution.Execution_Id, execution.Environment_Name, execution.Build_AutoPromote_Indicator));
			
			// Restart counter if now deploying to new environment
			if (envrionmentId != execution.Environment_Id) {
				envrionmentId = execution.Environment_Id;
				startTime = new Date().getTime();
			}
			
			// If have been waiting for more than 5 minutes to enter pending state then bail out  
			if (pending.contains(execution.ExecutionStatus_Name)) {
				long endTime = new Date().getTime();
				long diffMinutes = (endTime - startTime) / (60 * 1000);

				if (diffMinutes >= 5) {
					config.printStream.println(String.format("\tRelease has been pending for over %s minutes, check the status of the build in BuildMaster to see if there is anything blocking it", diffMinutes));
					return false;
				}
			}
		}

		if (!"Succeeded".equals(execution.ExecutionStatus_Name) && printLogOnFailure) {
			printExecutionLog(String.valueOf(execution.Execution_Id));
		}

		return "Succeeded".equals(execution.ExecutionStatus_Name);
	}

	/**
	 * Prints the build log for the execution id.
	 * 
	 * @throws IOException
	 */
	public void printExecutionLog(String executionId) throws IOException {
		BuildExecutionDetails log = doGet(BuildExecutionDetails.class,
				"Builds_GetExecutionLog", "Execution_Id", executionId);

		config.printStream.println("");
		config.printStream.println("BuildMaster Execution Log:");
		config.printStream.println("-------------------------");

		for (BuildExecutionActionGroupActionLogEntries entry : log.BuildExecution_ActionGroupActionLogEntries) {
			config.printStream.println(entry.LogEntry_Text);
		}

		config.printStream.println("");
	}

	private String encodeVariables(Map<String, String> variablesList) throws UnsupportedEncodingException {
		if (variablesList == null || variablesList.size() == 0) return null;
		
		StringBuilder variables = new StringBuilder();

		variables.append("<Variables>");

		for (Map.Entry<String, String> variable : variablesList.entrySet()) {
			variables.append("<Variable ")
					.append("Name=\"").append(variable.getKey()).append("\" ")
					.append("Value=\"").append(variable.getValue()).append("\" ")
					.append("/>");
		}

		variables.append("</Variables>");

		return URLEncoder.encode(variables.toString(), "UTF-8");
	}
			
	private String encodeDeployables(List<Deployable> deployablesList) throws UnsupportedEncodingException {
		if (deployablesList == null || deployablesList.size() == 0) return null;

		StringBuilder deployables = new StringBuilder();

		deployables.append("<ReleaseDeployables>");

		for (Deployable deployable : deployablesList) {
			deployables.append("<ReleaseDeployable ")
						.append("Deployable_Id=\"").append(deployable.Deployable_Id).append("\" ")
						.append("InclusionType_Code=\"I\" ")
						.append("/>");
			
				// InclusionType_Code: 'I' = indicate included in the release, 'R' = "referenced" so it’s only used for imported deployables and deployable dependencies
		        // Referenced_Release_Number: only include this attribute if InclusionType_Code is R
		        // Referenced_Application_Id: only include this attribute if InclusionType_Code is R
		}

		deployables.append("</ReleaseDeployables>");

		return URLEncoder.encode(deployables.toString(), "UTF-8");
	}
	
	// Do the work
	@SuppressWarnings("unchecked")
	protected <T> T doGet(Class<T> type, String path, String... query) throws IOException {
		StringBuilder url = new StringBuilder();
		url.append(config.url).append("/api/json/").append(path).append("?API_Key=").append(config.apiKey);

		for (int i = 0; i < query.length; i += 2) {
			if (query[i] != null && query[i + 1] != null) {
				url.append("&").append(query[i]).append("=").append(query[i + 1]);
			}
		}

		HttpGet httpget = new HttpGet(url.toString());
		HttpClientContext context = HttpClientContext.create();

		config.printStream.println(TriggerBuildHelper.LOG_PREFIX + "Executing request " + URLDecoder.decode(httpget.getRequestLine().getUri(), "UTF-8"));
		HttpResponse response = httpclient.execute(httpget, context);

		if (response.getStatusLine().getStatusCode() > 399) {
			throw new IOException(response.getStatusLine().toString() + ": " + getResponseBody(response));
		}

		T data = null;
		
		if (Void.class.isAssignableFrom(type)) {
			// Void is a special case for returning nothing
		} else if (StringBuilder.class.isAssignableFrom(type)) {
			// This is purely for developing calls when want to get the string value - the object mapper throws an exception 
			// when it encounters json notation about when using String.class
			data = (T)new StringBuilder(getResponseBody(response));
		} else {		
			data = new ObjectMapper().readValue(response.getEntity().getContent(), type);
		}

		EntityUtils.consume(response.getEntity());

		return data;
	}
	
	private String getResponseBody(HttpResponse response) throws UnsupportedOperationException, IOException {
		String encoding = response.getEntity().getContentEncoding() == null ? "UTF-8" : response.getEntity().getContentEncoding().getName();
		String body = IOUtils.toString(response.getEntity().getContent(), encoding);
		
		return body;
		
	}

}