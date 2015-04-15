/**
 * TODO: I don't want to add groovy as groovy library increases the size of the plugin, but its proving to be the fastest 
 * way to call buildmaster, can we get pure java working?
 * 
 * This is an attempt to use pure java rather than groovy to call the rest service, however it is proving significantly slower, 
 * presumably due to the way authenticating.
 * 
 * requires the following dependancies
 * compile 'org.glassfish.jersey.core:jersey-client:2.17'
 * compile 'org.glassfish.jersey.connectors:jersey-apache-connector:2.17'
	
 */

package com.inedo;

//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.NTCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.glassfish.jersey.apache.connector.ApacheClientProperties;
//import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
//
//import net.sf.json.JSONArray;
//
//public class BuildMasterApi2 {	
//	private final BuildMasterConfig config;
//	
//	public BuildMasterApi2(BuildMasterConfig config) {
//		this.config = config;
//	}
//
////	/**
////	 *  Ensure can call buildmaster.  An exception will be thrown if cannot.  
////	 */
////	def checkConnection() {
////		def http = obtainServerConnection();
////		
////		http.request( GET ) {
////			uri.path = '/api/json/Applications_GetApplications'
////			uri.query = [API_Key: config.apiKey, Application_Count: 1]
////			
////			if (config.logCalls) { config.printStream.println "Call $uri.base" }
////		
////			response.success = { resp, json ->
////				if (config.logCalls) { printResult(json, null) }
////			}
////		}
////	}
////	
//	/**
//	 *  Gets a list of all applications in the system.
//	 */
//	public JSONArray getApplications() {
//		WebTarget target = obtainServerConnection();
//		
//		target = target.path("/api/json/Applications_GetApplications")			
//						.queryParam("API_Key", config.apiKey);
//
//		if (config.logCalls) { config.printStream.println("Call " + target.toString()); }
//		
//		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
//		
//		if (response.getStatus() > 399) {
//			throw new RuntimeException("Failed with status " + response.getStatus() + " reason: " + response.getStatusInfo().getReasonPhrase()); 
//		}
//		
//		JSONArray applications = JSONArray.fromObject(response.readEntity(String.class));
//		
//		if (config.logCalls) { printResult(applications, null); }
//		
//		return applications;
//	}
////	
////	/**
////	 *  Gets a list of all workflows, optionally filtered by application.
////	 */
//////	def JSONArray getWorkflows(applicationId) {
//////		def http = obtainServerConnection()
//////		
//////		http.request( GET ) {
//////			uri.path = '/api/json/Workflows_GetWorkflows'
//////			uri.query = [API_Key: config.apiKey, Application_Id: applicationId]
//////		
//////			config.printStream.println "Call $uri.base"
//////
//////			response.success = { resp, json -> 
//////				return JSONArray.fromObject(json);
//////			}
//////		}
//////	}
////	
////	/**
////	 * Gets release number of newest active release
////	 */
////	def JSONObject getRelease(applicationId, releaseNumber) {
////		def http = obtainServerConnection()
////		
////		http.request( GET ) {
////			uri.path = '/api/json/Releases_GetRelease'
////			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, Release_Number: releaseNumber]
////		
////			if (config.logCalls) { config.printStream.println "Call $uri.base" }
////			
////			response.success = { resp, json ->
////				if (config.logCalls) { printResult(json, null) }
////				
////				return JSONObject.fromObject(json);
////			}
////		}
////	}
////	
////	/**
////	 * Gets release number of newest active release
////	 */
////	def JSONArray getActiveReleases(applicationId) {
////		def http = obtainServerConnection()
////		
////		http.request( GET ) {
////			uri.path = '/api/json/Releases_GetReleases'
////			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, ReleaseStatus_Name: 'Active']
////		
////			if (config.logCalls) { config.printStream.println "Call $uri.base" }
////			
////			response.success = { resp, json ->
////				if (config.logCalls) { printResult(json, null) }
////				
////				return JSONArray.fromObject(json);
////			}
////		}
////	}
////	
////	/**
////	 * Gets release number of newest active release, if no active releases will return an empty string
////	 */
////	def String getLatestActiveReleaseNumber(applicationId) {
////		def http = obtainServerConnection()
////		
////		http.request( GET ) {
////			uri.path = '/api/json/Releases_GetReleases'
////			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, ReleaseStatus_Name: 'Active', Release_Count: 1]
////		
////			config.printStream.println "Call $uri.base"
////			
////			response.success = { resp, json ->
////				if (config.logCalls) { printResult(json, null) }
////				
////				if (json.size() > 0) { 
////					return json[0].Release_Number 
////				} else {
////					return ""
////				}
////			}
////		}
////	}
////	
////	/**
////	 *  Creates a new build of an application and optionally promotes it to the first environment.  Error thrown on failure.
////	 */
////	def String createBuild(applicationId, releaseNumber, buildNumber, Map<String, String> variablesList) {
////		def http = obtainServerConnection()
////		
////		http.request( GET, TEXT ) {
////			uri.path = '/api/json/Builds_CreateBuild'
////			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, Release_Number: releaseNumber, Requested_Build_Number: buildNumber, BuildVariables_Xml: getVariables(variablesList)]
////					
////			config.printStream.println "Call $uri.base"
////
////			response.success = { resp, reader -> 
////				String buildMasterBuildNumber = reader.text.replaceAll('"', '')
////				
////				if (config.logCalls) { config.printStream.println buildMasterBuildNumber }
////				
////				return buildMasterBuildNumber;
////			}
////		}
////	}
////	
////	/**
////	 *  Creates a new build of an application and optionally promotes it to the first environment.  Error thrown on failure.
////	 */
////	def String createBuild(applicationId, releaseNumber, Map<String, String> variablesList) {
////		def http = obtainServerConnection()
////		
////		http.request( GET, TEXT ) {
////			uri.path = '/api/json/Builds_CreateBuild'
////			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, Release_Number: releaseNumber, BuildVariables_Xml: getVariables(variablesList)]
////					
////			config.printStream.println "Call $uri.base"
////
////			response.success = { resp, reader ->
////				String buildMasterBuildNumber = reader.text.replaceAll('"', '')
////				
////				if (config.logCalls) { config.printStream.println buildMasterBuildNumber }
////				
////				return buildMasterBuildNumber;
////			}
////		}
////	}
////	
////	private def getVariables(Map<String, String> variablesList) {
////		def variables = ''
////		
////		if (variablesList.size() > 0) {
////			variables += '<Variables>'
////			
////			variables.each{ key, value ->
////				variables += "<Variable Name=""${key}"" Value=""${value}"" />"
////			}
////			
////			variables += '</Variables>'
////		}
////		
////		return variables
////	}
////	
////	/**
////	 * Wait for the build to complete
////	 * @param printLogOnFailure 
////	 */
////	def boolean waitForBuildCompletion(applicationId, releaseNumber, buildNumber, boolean printLogOnFailure) {
////		def data = getBuild(applicationId, releaseNumber, buildNumber)
////		
////		def status = data[0].Current_ExecutionStatus_Name
////		config.printStream.println "\tExecution Status: $status"
////		
////		def startTime = new Date().getTime()
////		
////		while (['Pending', 'Executing'].contains(status)) {
////			Thread.sleep(7000)
////			
////			data = getBuild(applicationId, releaseNumber, buildNumber)
////			
////			status = data[0].Current_ExecutionStatus_Name
////			config.printStream.println "\tExecution Status: $status"
////			
////			if (status == 'Pending') {
////				def endTime = new Date().getTime()
////				long diffMinutes = (endTime - startTime) / (60 * 1000)
////				
////				if (diffMinutes >= 2) {
////					config.printStream.println "\tRelease has been pending for over $diffMinutes minutes, check the status of the build in BuildMaster to see if there is anything blocking it"
////					return false
////				}
////			}
////		}
////		
////		if(status != 'Succeeded' && printLogOnFailure) {
////			printExecutionLog(data[0].Current_Execution_Id)
////		}
////		
////		return status == 'Succeeded'
////	}
////	
////	/**
////	 *  Gets the details for a specified build.
////	 */
////	def JSONArray getBuild(applicationId, releaseNumber, buildNumber) {
////		def http = obtainServerConnection()
////		
////		http.request( GET ) {
////			uri.path = '/api/json/Builds_GetBuild'
////			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, Release_Number: releaseNumber, Build_Number: buildNumber]
////		
////			config.printStream.println "Call $uri.base"
////
////			response.success = { resp, json -> 
////				if (config.logCalls) { printResult(json, null) }
////				
////				return JSONArray.fromObject(json); 
////			}
////		}
////	}
////	
////	/**
////	 *  Gets the build output report for a specified build and sequence number.
////	 */
////	def printExecutionLog(executionId) {
////		def http = obtainServerConnection()
////		
////		http.request( GET ) {
////			uri.path = '/api/json/Builds_GetExecutionLog'
////			uri.query = [API_Key: config.apiKey, Execution_Id:executionId]
////		
////			config.printStream.println "Call $uri.base"
////
////			response.success = { resp, reader ->
////				config.printStream.println("")
////				config.printStream.println("BuildMaster Execution Log:")
////				config.printStream.println("-------------------------")
////				
////				reader.BuildExecution_ActionGroupActionLogEntries.each { item ->
////					config.printStream.println(item.LogEntry_Text)
////				}
////				
////				config.printStream.println("")
////				
////				return true;
////			}
////			response.failure = { resp, reader -> printResponse(resp, reader); return false; }
////		}
////	}
//	
//	private WebTarget obtainServerConnection() {
//		
////		if ("ntlm".equals(config.authentication)) {
////			ClientConfig clientConfig = new ClientConfig();
////			Credentials credentials = new NTCredentials(config.user, config.password, config.getHost(), config.domain);
////			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
////			credentialsProvider.setCredentials(AuthScope.ANY, credentials);
////			clientConfig.property(ApacheClientProperties.CREDENTIALS_PROVIDER, credentialsProvider);
////			clientConfig.connectorProvider(new ApacheConnectorProvider());
////		
////			return ClientBuilder.newClient(clientConfig).target(config.url);
////		}
//
//		
//
//		HttpAuthenticationFeature feature;
//		WebTarget target = ClientBuilder.newClient().target(config.url);
//
//		switch (config.authentication) {
//		case "basic-non-preemptive":
//			feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive().credentials(config.user, config.password).build();
//			target = target.register(feature);
//			break;
//		
//		case "basic":
//			feature = HttpAuthenticationFeature.basic(config.user, config.password);
//			target = target.register(feature);
//			break;
//		
//		case "ntlm":
//			ClientConfig clientConfig = (ClientConfig)target.getConfiguration();
//			
//			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//			credentialsProvider.setCredentials(AuthScope.ANY, new NTCredentials(config.user, config.password, config.getHost(), config.domain));
//			clientConfig.property(ApacheClientProperties.CREDENTIALS_PROVIDER, credentialsProvider);
//			clientConfig.connectorProvider(new ApacheConnectorProvider());
//			break;
//		}
//
//		return target;
//	}
//
//	private String tabs = "";
//	
//	private void printResult(Object data, String parentId) {
//		tabs += "\t";
//				
//		if (data instanceof JSONArray) {
//			config.printStream.println(tabs + data.getClass() + " {");
//			
//			JSONArray array = (JSONArray)data;
//			for (Object item : array) {
//				printResult(item, item.getClass().getName());
//			}
//			config.printStream.println("$tabs}");
//		} else if (data instanceof java.util.ArrayList) {		
//			config.printStream.println(tabs + (parentId == null ? "" : ".$parentId[] - ") + data.getClass().getName());
//			int i = 0;
//			
//			List array = (ArrayList)data;
//			for (Object item : array) {
//				printResult(item, item.getClass().getName());
//				i++;
//				printResult(item, data.getClass().getName());
//			}
//		} else {
//			config.printStream.println(tabs + "." + padRight(parentId.toString(), 45) + padRight(data.toString(), 40) + data.getClass().getName());
//		}
//		
//		if(tabs.length() > 0) tabs = tabs.substring(1);
//	}
//	
//	private String padRight(String s, int n) {
//	     return String.format("%1$-" + n + "s", s);  
//	}
//}
//
