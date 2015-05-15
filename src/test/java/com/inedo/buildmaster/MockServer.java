package com.inedo.buildmaster;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URI;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.BuildExecution;
import com.inedo.buildmaster.domain.BuildExecutionDetails;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.Variable;

/**
 * A Mocked server that replaces a live BuildMaster installation
 * 
 * @author Andrew Sumner
 */
public class MockServer {
	public static final String APPLICATION_ID = "36";	// BuildMaster application id to get/create builds for
	
	private BuildMasterConfig config;
	
	// Required for mocking via test server
	private HttpServer server = null;
	private HttpRequestHandler handler;
	
	public MockServer(boolean mockRequests, PrintStream logger) throws IOException {
		config = new BuildMasterConfig();
		config.url = "http://buildmaster";
		config.printStream = logger;
		
		if (mockRequests) {
			config.authentication = "none";
			
			handler = new HttpHandler();
			
			
			server = ServerBootstrap.bootstrap()
						.setLocalAddress(InetAddress.getLocalHost())
						.setListenerPort(0)	// Any free port
						.registerHandler("*", handler)
						.create();
		    
		    server.start();
		    
		    config.url = "http://" + server.getInetAddress().getHostName() + ":" + server.getLocalPort();
		} else {
			String[] cred = FileUtils.readFileToString(new File("c:/temp/bm.txt")).split(Pattern.quote("|"));
			config.authentication = "ntlm";
			config.user = cred[0];
			config.password = cred[1];
			config.domain = cred[2];
			config.apiKey = cred[3];
		}
	}
	
	public BuildMasterConfig getBuildMasterConfig() {
		return config;
	}
	
	public void stop() {
		if (server!= null) server.stop();
	}
	
	// Handler for the test server that returns responses based on the requests.
	public class HttpHandler implements HttpRequestHandler {

		@Override
		public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
			URI uri = URI.create(request.getRequestLine().getUri());
			
			String method = uri.getPath().replace("/api/json/", "");
			
			switch (method) {
			case "Applications_GetApplications":
				response.setEntity(new StringEntity(Application.EXAMPLE));
				break;
				
			case "Applications_GetDeployables":
				response.setEntity(new StringEntity(Deployable.EXAMPLE));
				break;
				
			case "Applications_GetDeployable":
				response.setEntity(new StringEntity(Deployable.EXAMPLE));
				break;	
				
			case "Releases_GetReleases":
				response.setEntity(new StringEntity(Release.EXAMPLE));
				break;
			
			case "Releases_GetRelease": 
				response.setEntity(new StringEntity(ReleaseDetails.EXAMPLE));
				break;
				
			case "Releases_CreateOrUpdateRelease":
				//response.setEntity(new StringEntity(""));
				break;
				
			case "Builds_GetBuilds": 
				response.setEntity(new StringEntity(Build.EXAMPLE));
				break;
				
			case "Builds_GetBuild":
				response.setEntity(new StringEntity(Build.EXAMPLE));
				break;				
				
			case "Builds_CreateBuild":
				String buildNumber = "99";
				String query = uri.getQuery();
								
				int pos = query.indexOf("Requested_Build_Number");
				
				if (pos > 0) {
					query = query.substring(pos + "Requested_Build_Number".length() + 1);
					
					pos = query.indexOf("&");
					if (pos > 0) {
						buildNumber = query.substring(0, pos);
					} else {
						buildNumber = query;
					}
				}
				
				// Return the quested build number if passed, else new build number
				response.setEntity(new StringEntity(buildNumber));
				break;
			
			case "Builds_GetExecutionLog":
				response.setEntity(new StringEntity(BuildExecutionDetails.EXAMPLE));
				break;
			
			case "Builds_GetExecutions":
				response.setEntity(new StringEntity(BuildExecution.EXAMPLE));
				break;
			
			case "Variables_GetVariableValues":
				response.setEntity(new StringEntity(Variable.EXAMPLE));
				break;
				
			default:
				response.setStatusCode(HttpStatus.SC_NOT_FOUND);
				response.setEntity(new StringEntity("API method " + method + " not found."));
			}
		}
		
	}
}
