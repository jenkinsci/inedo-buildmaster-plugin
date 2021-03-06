package com.inedo.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;

import com.inedo.buildmaster.api.BuildMasterConfig;

import hidden.jth.org.apache.http.HttpRequest;
import hidden.jth.org.apache.http.HttpResponse;
import hidden.jth.org.apache.http.HttpStatus;
import hidden.jth.org.apache.http.entity.StringEntity;
import hidden.jth.org.apache.http.impl.bootstrap.HttpServer;
import hidden.jth.org.apache.http.impl.bootstrap.ServerBootstrap;
import hidden.jth.org.apache.http.protocol.HttpContext;
import hidden.jth.org.apache.http.protocol.HttpRequestHandler;

/**
 * A Mocked server that replaces a live BuildMaster installation
 * 
 * @author Andrew Sumner
 */
public class MockServer {
	// Required for mocking via test server
	private HttpServer server;

	private BuildMasterConfig config;

	public MockServer() throws IOException {
		HttpRequestHandler handler = new HttpHandler();
		server = ServerBootstrap.bootstrap()
					.setLocalAddress(InetAddress.getLocalHost())
					.setListenerPort(0)	// Any free port
					.registerHandler("*", handler)
					.create();
	    
	    server.start();

		config = new BuildMasterConfig();
		config.url = "http://" + server.getInetAddress().getHostName() + ":" + server.getLocalPort();
		//config.printStream = logger;
	}
	
	public BuildMasterConfig getBuildMasterConfig() {
		return config;
	}
	
	public void stop() {
		if (server!= null) {
			server.stop();
		}
	}
	
	// Handler for the test server that returns responses based on the requests.
	public static class HttpHandler implements HttpRequestHandler {

		@Override
		public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws IOException {
			URI uri = URI.create(request.getRequestLine().getUri());
			
			String method = uri.getPath().replace("/api/json/", "");
			
			switch (method) {
            case "/api/releases/builds":
                response.setEntity(MockData.API_RELEASE_BUILDS.getInputSteam());

            case "/api/releases/builds/deploy":
                response.setEntity(MockData.API_DEPLOYMENT.getInputSteam());
                break;

            case "/api/releases/builds/deployments":
                response.setEntity(MockData.API_DEPLOYMENT.getInputSteam());

            case "/api/variables/builds/TestApplication/0.0.0/29":
                response.setEntity(MockData.BUILD_VARIABLES.getInputSteam());

            case "/api/releases":
                response.setEntity(MockData.API_RELEASE.getInputSteam());
                break;

            case "/api/releases/builds/create":
                response.setEntity(MockData.API_RELEASE_BUILD.getInputSteam());
                break;

            case "/api/variables/builds/TestApplication/0.0.0/16":
                response.setEntity(MockData.BUILD_VARIABLES.getInputSteam());
                break;

			case "Applications_GetApplications":
                response.setEntity(MockData.APPLICATIONS.getInputSteam());
				break;
				
            case "Applications_GetApplication":
                response.setEntity(MockData.APPLICATION.getInputSteam());
                break;

			case "Releases_GetRelease": 
                response.setEntity(MockData.RELEASE.getInputSteam());
				break;
				
			case "Releases_CreateOrUpdateRelease":
				//response.setEntity(new StringEntity(""));
				break;
				
            // case "Builds_CreateBuild":
            // String buildNumber = "99";
            // String query = uri.getQuery();
            //
            // int pos = query.indexOf("Requested_Build_Number");
            //
            // if (pos > 0) {
            // query = query.substring(pos + "Requested_Build_Number".length() + 1);
            //
            // pos = query.indexOf("&");
            // if (pos > 0) {
            // buildNumber = query.substring(0, pos);
            // } else {
            // buildNumber = query;
            // }
            // }
            //
            // // Return the quested build number if passed, else new build number
            // response.setEntity(new StringEntity(buildNumber));
            // break;
			
            // case "Builds_GetExecutionLog":
            // response.setEntity(MockData.BUILD_EXECUTION_DETAILS.getInputSteam());
            // break;
				
			default:
				response.setStatusCode(HttpStatus.SC_NOT_FOUND);
				response.setEntity(new StringEntity("API method " + method + " not found."));
			}
		}
		
	}
}
