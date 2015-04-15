package com.inedo

import java.util.LinkedHashMap;

import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.auth.*
import org.apache.http.client.params.*
import org.apache.http.auth.params.*

public class BuildMasterApi {	
	private final BuildMasterConfig config
	
	public BuildMasterApi(BuildMasterConfig config) {
		this.config = config;
	}

	/**
	 *  Ensure can call buildmaster.  An exception will be thrown if cannot.  
	 */
	def checkConnection() {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Applications_GetApplications'
			uri.query = [API_Key: config.apiKey, Application_Count: 1]
			
			if (config.logCalls) { config.printStream.println "Call $uri.base" }
		
			response.success = { resp, json ->
				if (config.logCalls) { printResult(json, null) }
			}
		}
	}
	
	/**
	 *  Gets a list of all applications in the system.
	 */
	def JSONArray getApplications() {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Applications_GetApplications'
			uri.query = [API_Key: config.apiKey]
		
			if (config.logCalls) { config.printStream.println "Call $uri.base" }

			response.success = { resp, json ->
				if (config.logCalls) { printResult(json, null) }
				
				return JSONArray.fromObject(json);
			}
		}
	}
	
	/**
	 *  Gets a list of all workflows, optionally filtered by application.
	 */
//	def JSONArray getWorkflows(applicationId) {
//		def http = obtainServerConnection()
//		
//		http.request( GET ) {
//			uri.path = '/api/json/Workflows_GetWorkflows'
//			uri.query = [API_Key: config.apiKey, Application_Id: applicationId]
//		
//			config.printStream.println "Call $uri.base"
//
//			response.success = { resp, json -> 
//				return JSONArray.fromObject(json);
//			}
//		}
//	}
	
	/**
	 * Gets release number of newest active release
	 */
	def JSONObject getRelease(applicationId, releaseNumber) {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Releases_GetRelease'
			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, Release_Number: releaseNumber]
		
			if (config.logCalls) { config.printStream.println "Call $uri.base" }
			
			response.success = { resp, json ->
				if (config.logCalls) { printResult(json, null) }
				
				return JSONObject.fromObject(json);
			}
		}
	}
	
	/**
	 * Gets release number of newest active release
	 */
	def JSONArray getActiveReleases(applicationId) {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Releases_GetReleases'
			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, ReleaseStatus_Name: 'Active']
		
			if (config.logCalls) { config.printStream.println "Call $uri.base" }
			
			response.success = { resp, json ->
				if (config.logCalls) { printResult(json, null) }
				
				return JSONArray.fromObject(json);
			}
		}
	}
	
	/**
	 * Gets release number of newest active release, if no active releases will return an empty string
	 */
	def String getLatestActiveReleaseNumber(applicationId) {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Releases_GetReleases'
			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, ReleaseStatus_Name: 'Active', Release_Count: 1]
		
			config.printStream.println "Call $uri.base"
			
			response.success = { resp, json ->
				if (config.logCalls) { printResult(json, null) }
				
				if (json.size() > 0) { 
					return json[0].Release_Number 
				} else {
					return ""
				}
			}
		}
	}
	
	
	/**
	 * Gets release number of newest active release, if no active releases will return an empty string
	 */
	def String getNextBuildNumber(applicationId, releaseNumber) {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Builds_GetBuilds'
			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, Release_Number: releaseNumber, Build_Count: 1]
		
			config.printStream.println "Call $uri.base"
			
			response.success = { resp, json ->
				if (config.logCalls) { printResult(json, null) }
				
				if (json.size() > 0) {
					return Integer.parseInt(json[0].Build_Number) + 1
				} else {
					return "1"
				}
			}
		}
	}
	
	/**
	 *  Creates a new build of an application and optionally promotes it to the first environment.  Error thrown on failure.
	 */
	def String createBuild(applicationId, releaseNumber, buildNumber, Map<String, String> variablesList) {
		def http = obtainServerConnection()
		
		http.request( GET, TEXT ) {
			uri.path = '/api/json/Builds_CreateBuild'
			uri.query = [API_Key: config.apiKey, 
							Application_Id: applicationId, 
							Release_Number: releaseNumber, 
							Requested_Build_Number: buildNumber, 
							BuildVariables_Xml: getVariables(variablesList)]
					
			config.printStream.println "Call $uri.base"

			response.success = { resp, reader -> 
				String buildMasterBuildNumber = reader.text.replaceAll('"', '')
				
				if (config.logCalls) { config.printStream.println buildMasterBuildNumber }
				
				return buildMasterBuildNumber;
			}
		}
	}
	
	/**
	 *  Creates a new build of an application and optionally promotes it to the first environment.  Error thrown on failure.
	 */
	def String createBuild(applicationId, releaseNumber, Map<String, String> variablesList) {
		def http = obtainServerConnection()
		
		http.request( GET, TEXT ) {
			uri.path = '/api/json/Builds_CreateBuild'
			uri.query = [API_Key: config.apiKey, 
							Application_Id: applicationId, 
							Release_Number: releaseNumber, 
							BuildVariables_Xml: getVariables(variablesList)]
					
			config.printStream.println "Call $uri.base"

			response.success = { resp, reader ->
				String buildMasterBuildNumber = reader.text.replaceAll('"', '')
				
				if (config.logCalls) { config.printStream.println buildMasterBuildNumber }
				
				return buildMasterBuildNumber;
			}
		}
	}
	
	private def getVariables(Map<String, String> variablesList) {
		if (variablesList == null) return null;
		
		def variables = ''
		
		if (variablesList.size() > 0) {
			variables += '<Variables>'
			
			variablesList.each{ key, value ->
				variables += '<Variable Name="' + key + '" Value="' + value + '" />'
			}
			
			variables += '</Variables>'
		}
		
		return variables
	}
	
	/**
	 * Wait for the build to complete
	 * @param printLogOnFailure 
	 */
	def boolean waitForBuildCompletion(applicationId, releaseNumber, buildNumber, boolean printLogOnFailure) {
		def data = getBuild(applicationId, releaseNumber, buildNumber)
		
		def status = data[0].Current_ExecutionStatus_Name
		config.printStream.println "\tExecution Status: $status"
		
		def startTime = new Date().getTime()
		
		while ([null, 'Pending', 'Executing'].contains(status)) {
			Thread.sleep(7000)
			
			data = getBuild(applicationId, releaseNumber, buildNumber)
			
			status = data[0].Current_ExecutionStatus_Name
			config.printStream.println "\tExecution Status: $status"
			
			if (status == 'Pending') {
				def endTime = new Date().getTime()
				long diffMinutes = (endTime - startTime) / (60 * 1000)
				
				if (diffMinutes >= 2) {
					config.printStream.println "\tRelease has been pending for over $diffMinutes minutes, check the status of the build in BuildMaster to see if there is anything blocking it"
					return false
				}
			}
		}
		
		if(status != 'Succeeded' && printLogOnFailure) {
			printExecutionLog(data[0].Current_Execution_Id)
		}
		
		return status == 'Succeeded'
	}
	
	/**
	 *  Gets the details for a specified build.
	 */
	def JSONArray getBuild(applicationId, releaseNumber, buildNumber) {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Builds_GetBuild'
			uri.query = [API_Key: config.apiKey, Application_Id: applicationId, Release_Number: releaseNumber, Build_Number: buildNumber]
		
			config.printStream.println "Call $uri.base"

			response.success = { resp, json -> 
				if (config.logCalls) { printResult(json, null) }
				
				return JSONArray.fromObject(json); 
			}
		}
	}
	
	/**
	 *  Gets the build output report for a specified build and sequence number.
	 */
	def printExecutionLog(executionId) {
		def http = obtainServerConnection()
		
		http.request( GET ) {
			uri.path = '/api/json/Builds_GetExecutionLog'
			uri.query = [API_Key: config.apiKey, Execution_Id:executionId]
		
			config.printStream.println "Call $uri.base"

			response.success = { resp, reader ->
				config.printStream.println("")
				config.printStream.println("BuildMaster Execution Log:")
				config.printStream.println("-------------------------")
				
				reader.BuildExecution_ActionGroupActionLogEntries.each { item ->
					config.printStream.println(item.LogEntry_Text)
				}
				
				config.printStream.println("")
				
				return true;
			}
			response.failure = { resp, reader -> printResponse(resp, reader); return false; }
		}
	}
	
	private HTTPBuilder obtainServerConnection() {
		def http = new HTTPBuilder(config.url)
		
		if (config.authentication == 'basic-non-preemptive') {
			http.client.auth.basic config.user, config.password
		}
		
		if (config.authentication == 'basic') {
			http.client.headers['Authorization'] = 'Basic ' + "$config.user:$config.password".getBytes('iso-8859-1').encodeBase64()
		}

		if (config.authentication == 'ntlm') {
			http.client.credentialsProvider.setCredentials(AuthScope.ANY, new NTCredentials(config.user, config.password, config.host, config.domain));
			http.client.params.setParameter(AuthPNames.TARGET_AUTH_PREF, [AuthPolicy.NTLM]);
		}

		return http
	}

	def tabs = ''
	
	private def printResult(data, parentId) {
		tabs += '\t'
				
		if (data instanceof groovy.json.internal.LazyMap) {
			config.printStream.println tabs + data.getClass() + " {"
			data.each { id, item ->
				printResult(item, id)
			}
			config.printStream.println "$tabs}"
		} else if (data instanceof java.util.ArrayList) {		
			config.printStream.println tabs + (parentId == null ? "" : ".$parentId[] - ") + data.getClass()
			int i = 0;
			data.each { item ->
				config.printStream.println "${tabs}\tArrayList [$i]:"
				i++
				printResult(item, data)
			}
		} else {
			config.printStream.println tabs + "." + parentId.toString().padRight(45) + data.toString().padRight(40) + data.getClass()
		}
		
		if(tabs.length() > 0) tabs = tabs.substring(1)
	}
}
