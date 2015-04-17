package com.inedo.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inedo.api.ExecutionLog.BuildExecution_ActionGroupActionLogEntries;

import org.apache.http.NameValuePair;

public class BuildMasterClientApache
{
	private BuildMasterConfig config;
	private HttpClient httpclient;
	
	public BuildMasterClientApache(BuildMasterConfig config) {
		this.config = config;
		
		RequestConfig requestConfig = RequestConfig.custom()
    	        .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM))
    	        .build();

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new NTCredentials(config.user, config.password, config.getHost(), config.domain));

        // Finally we instantiate the client. Client is a thread safe object and can be used by several threads at the same time. 
        // Client can be used for several request. The life span of the client must be equal to the life span of this EJB.
        httpclient = HttpClients.custom()
	        .setDefaultCredentialsProvider(credentialsProvider)
	        .setDefaultRequestConfig(requestConfig)
	        .build();
	}
	
	/**
	 * Ensure can call BuildMaster api.  An exception will be thrown if cannot.  
	 * @throws IOException 
	 */
	public void checkConnection() throws IOException {
		doGet(Application[].class, "Applications_GetApplications", "Application_Count", "1");
	}
	
	/**
	 *  Gets a list of all applications in the system.
	 *  @throws IOException 
	 */
	public Application[] getApplications() throws IOException {
		return doGet(Application[].class, "Applications_GetApplications");
	}
	
	/**
	 * Gets release number of newest active release
	 * @throws IOException 
	 */
	public Release getRelease(String applicationId, String releaseNumber) throws IOException {
		return doGet(Release.class, "Releases_GetRelease", "Application_Id", applicationId, "Release_Number", releaseNumber);
	}
	
	/**
	 * Gets list of number of active releases
	 * @throws IOException 
	 */
	public Release[] getActiveReleases(String applicationId) throws IOException {
		return doGet(Release[].class, "Releases_GetReleases", "Application_Id", applicationId, "ReleaseStatus_Name", "Active");
	}
	
	/**
	 * Gets release number of newest active release, if no active releases will return an empty string
	 * @throws IOException 
	 */
	public String getLatestActiveReleaseNumber(String applicationId) throws IOException {
		Release release = doGet(Release.class, "Releases_GetReleases", "Application_Id", applicationId, "ReleaseStatus_Name", "Active", "Release_Count", "1");
		
		if (release.Releases_Extended.length > 0) {
			return release.Releases_Extended[0].Release_Number;
		}
		
		return "";
	}
	
	/**
	 * Gets the next available build number for the given release, if no builds have been performed will return 1
	 * @throws IOException 
	 */
	public String getNextBuildNumber(String applicationId, String releaseNumber) throws IOException {
		Build[] builds = doGet(Build[].class, "Builds_GetBuilds", "Application_Id", applicationId, "Release_Number", releaseNumber, "Build_Count", "1");

		if (builds.length > 0) {
			return String.valueOf(Integer.parseInt(builds[0].Build_Number) + 1);
		}

		return "1";
	}
	
	/**
	 *  Creates a new build for an application requesting it use a specific build number and returns the build number of the new build.  Error thrown on failure.
	 * @throws IOException 
	 */
	public String createBuild(String applicationId, String releaseNumber, String buildNumber, Map<String, String> variablesList) throws IOException {
		return doGet(String.class, "Builds_CreateBuild", 
						"Application_Id", applicationId, 
						"Release_Number", releaseNumber, 
						"Requested_Build_Number", buildNumber,
						"BuildVariables_Xml", getVariables(variablesList));
	}
	
	/**
	 * Creates a new build of an application and optionally promotes it to the first environment.  Error thrown on failure.
	 * @throws IOException 
	 */
	public String createBuild(String applicationId, String releaseNumber, Map<String, String> variablesList) throws IOException {
		return createBuild(applicationId, releaseNumber, null, variablesList);
	}
	
	/**
	 *  Gets the details for a specified build.
	 * @throws IOException 
	 */
	public Build getBuild(String applicationId, String releaseNumber, String buildNumber) throws IOException {
		return doGet(Build.class, "Builds_GetBuild", "Application_Id", applicationId, "Release_Number", releaseNumber, "Build_Number", buildNumber);
	}
	
	/**
	 * Wait for the build to complete
	 * @param printLogOnFailure 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public boolean waitForBuildCompletion(String applicationId, String releaseNumber, String buildNumber, boolean printLogOnFailure) throws IOException, InterruptedException {
		final List<String> waiting = Arrays.asList(new String[] {null, "Pending", "Executing"});
		
		Build build = getBuild(applicationId, releaseNumber, buildNumber);
		
		String status = build.Current_ExecutionStatus_Name;
		config.printStream.println("\tExecution Status: " + status);
		
		long startTime = new Date().getTime();
		
		while (waiting.contains(status)) {
			Thread.sleep(7000);
			
			build = getBuild(applicationId, releaseNumber, buildNumber);
			
			status = build.Current_ExecutionStatus_Name;
			config.printStream.println("\tExecution Status: " + status);
			
			if ("Pending".equals(status)) {
				long endTime = new Date().getTime();
				long diffMinutes = (endTime - startTime) / (60 * 1000);
				
				if (diffMinutes >= 2) {
					config.printStream.println(String.format("\tRelease has been pending for over %s minutes, check the status of the build in BuildMaster to see if there is anything blocking it", diffMinutes));
					return false;
				}
			}
		}
		
		if ("Succeeded".equals(status) && printLogOnFailure) {
			printExecutionLog(build.Current_Execution_Id);
		}
		
		return status == "Succeeded";
	}
		
	/**
	 * Prints the build log for the execution id.
	 * @throws IOException 
	 */
	public void printExecutionLog(String executionId) throws IOException {
		ExecutionLog log = doGet(ExecutionLog.class, "Builds_GetExecutionLog", "Execution_Id", executionId);

		config.printStream.println("");
		config.printStream.println("BuildMaster Execution Log:");
		config.printStream.println("-------------------------");
		
		for (BuildExecution_ActionGroupActionLogEntries entry : log.BuildExecution_ActionGroupActionLogEntries) {
			config.printStream.println(entry.LogEntry_Text);
		}
						
		config.printStream.println("");
	}
	
	private String getVariables(Map<String, String> variablesList) {
		if (variablesList == null) return null;
		
		StringBuilder variables = new StringBuilder();
		
		if (variablesList.size() > 0) {
			variables.append("<Variables>");
			
			for (Map.Entry<String, String> variable : variablesList.entrySet())
			{
				variables.append("<Variable Name=\"").append(variable.getKey()).append("\" Value=\"").append(variable.getValue()).append("\" />");
			}
			
			variables.append("</Variables>");
		}
		
		return variables.toString();
	}
	
	// Do the work
	private <T> T doGet(Class<T> type, String path, String... query) throws IOException {
		StringBuilder url = new StringBuilder();
		url.append(config.url).append("/api/json/").append(path).append("?API_Key=").append(config.apiKey);
		
		for( int i = 0; i < query.length; i+=2) {
			if (query[i] != null && query[i+1] != null) {
				url.append("&").append(query[i]).append("=").append(query[i+1]);
			}
	    }
				
		HttpGet httpget = new HttpGet(url.toString());            
        HttpClientContext context = HttpClientContext.create();    
        
        System.out.println("Executing request " + httpget.getRequestLine());
        HttpResponse response = httpclient.execute(httpget, context);
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
            
        T myObject = new ObjectMapper().readValue(response.getEntity().getContent(), type);
        
        //System.out.println(EntityUtils.toString(response.getEntity()));
        EntityUtils.consume(response.getEntity());
        
        return myObject;
	}

}