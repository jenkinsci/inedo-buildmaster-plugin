package com.inedo.buildmaster.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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
import com.inedo.buildmaster.TriggerBuildHelper;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.BuildExecutionActionGroupActionLogEntries;
import com.inedo.buildmaster.domain.BuildExecutionDetails;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.Variable;

public class BuildMasterClientApache {
	private BuildMasterConfig config;
	private HttpClient httpclient;

	public BuildMasterClientApache(HttpClient httpclient, BuildMasterConfig config) {
		this.httpclient = httpclient;
		this.config = config;
	}

	public BuildMasterClientApache(BuildMasterConfig config) {
		this.config = config;

		HttpClientBuilder httpbuilder = HttpClients.custom();
		RequestConfig.Builder configbuilder = RequestConfig.custom();

		if ("basic".equalsIgnoreCase(config.authentication)) {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(config.user,config.password));

			httpbuilder.setDefaultCredentialsProvider(credentialsProvider);
			configbuilder.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC));
		}

		if ("ntlm".equalsIgnoreCase(config.authentication)) {
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
	 * @throws IOException
	 */
	public String createBuild(String applicationId, String releaseNumber, String buildNumber, Map<String, String> variablesList) throws IOException {
		return doGet(String.class, "Builds_CreateBuild", "Application_Id",
				applicationId, "Release_Number", releaseNumber, "Requested_Build_Number", buildNumber, "BuildVariables_Xml", getVariables(variablesList));
	}

	/**
	 * Creates a new build of an application and optionally promotes it to the
	 * first environment. Error thrown on failure.
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
	 * Wait for the build to complete
	 * 
	 * @param printLogOnFailure
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean waitForBuildCompletion(String applicationId, String releaseNumber, String buildNumber, boolean printLogOnFailure) throws IOException, InterruptedException {
		final List<String> inProgess = Arrays.asList(new String[] { null,
				"Pending", "Executing" });
		final List<String> pending = Arrays.asList(new String[] { null,
				"Pending" });

		Build build = getBuild(applicationId, releaseNumber, buildNumber);

		String status = build.Current_ExecutionStatus_Name;
		config.printStream.println("\tExecution Status: " + status);

		long startTime = new Date().getTime();

		while (inProgess.contains(status)) {
			Thread.sleep(7000);

			build = getBuild(applicationId, releaseNumber, buildNumber);

			status = build.Current_ExecutionStatus_Name;
			config.printStream.println("\tExecution Status: " + status);

			if (pending.contains(status)) {
				long endTime = new Date().getTime();
				long diffMinutes = (endTime - startTime) / (60 * 1000);

				if (diffMinutes >= 2) {
					config.printStream
							.println(String
									.format("\tRelease has been pending for over %s minutes, check the status of the build in BuildMaster to see if there is anything blocking it",
											diffMinutes));
					return false;
				}
			}
		}

		if (!"Succeeded".equals(status) && printLogOnFailure) {
			printExecutionLog(build.Current_Execution_Id);
		}

		return "Succeeded".equals(status);
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

	private String getVariables(Map<String, String> variablesList) throws UnsupportedEncodingException {
		if (variablesList == null)
			return null;

		StringBuilder variables = new StringBuilder();

		if (variablesList.size() > 0) {
			variables.append("<Variables>");

			for (Map.Entry<String, String> variable : variablesList.entrySet()) {
				variables.append("<Variable Name=\"").append(variable.getKey())
						.append("\" Value=\"").append(variable.getValue())
						.append("\" />");
			}

			variables.append("</Variables>");
		}

		return URLEncoder.encode(variables.toString(), "UTF-8");
	}

	// Do the work
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

		config.printStream.println(TriggerBuildHelper.LOG_PREFIX + "Executing request " + httpget.getRequestLine());
		HttpResponse response = httpclient.execute(httpget, context);

		if (response.getStatusLine().getStatusCode() > 399) {
			throw new IOException(response.getStatusLine().toString() + ": " + getResponseBody(response));
		}

		if (String.class.isAssignableFrom(type)) {
			return (T)getResponseBody(response);
		}
		
		T data = new ObjectMapper().readValue(response.getEntity().getContent(), type);

		EntityUtils.consume(response.getEntity());

		return data;
	}
	
	private String getResponseBody(HttpResponse response) throws UnsupportedOperationException, IOException {
		String encoding = response.getEntity().getContentEncoding() == null ? "UTF-8" : response.getEntity().getContentEncoding().getName();
		String body = IOUtils.toString(response.getEntity().getContent(), encoding);
		
		return body;
		
	}

}