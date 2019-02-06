package com.inedo.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;

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

/**
 * A Mocked server that replaces a live BuildMaster installation
 * 
 * @author Andrew Sumner
 */
public class MockServer {
	// Required for mocking via test server
	private HttpServer server = null;
	private HttpRequestHandler handler;

	private BuildMasterConfig config;

	public MockServer() throws IOException {
		handler = new HttpHandler();
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
	public class HttpHandler implements HttpRequestHandler {

		@Override
		public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
			URI uri = URI.create(request.getRequestLine().getUri());
			
			String method = uri.getPath().replace("/api/json/", "");
			
			switch (method) {
            case "/api/releases/packages":
                response.setEntity(MockData.API_RELEASE_BUILDS.getInputSteam());

            case "/api/releases/packages/deploy":
                response.setEntity(MockData.API_DEPLOYMENT.getInputSteam());
                break;

            case "/api/releases/packages/deployments":
                response.setEntity(MockData.API_DEPLOYMENT.getInputSteam());

            case "/api/variables/packages/TestApplication/0.0.0/29":
                response.setEntity(MockData.BUILD_VARIABLES.getInputSteam());

            case "/api/releases":
                response.setEntity(MockData.API_RELEASE.getInputSteam());
                break;

            case "/api/releases/packages/create":
                response.setEntity(MockData.API_RELEASE_BUILD.getInputSteam());
                break;

            case "/api/variables/packages/TestApplication/0.0.0/16":
                response.setEntity(MockData.BUILD_VARIABLES.getInputSteam());
                break;

			case "Applications_GetApplications":
                response.setEntity(MockData.APPLICATIONS.getInputSteam());
				break;
				
            case "Applications_GetApplication":
                response.setEntity(MockData.APPLICATION.getInputSteam());
                break;

			case "Applications_GetDeployables":
                response.setEntity(MockData.DEPLOYABLES.getInputSteam());
				break;
				
			case "Applications_GetDeployable":
                response.setEntity(MockData.DEPLOYABLE.getInputSteam());
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
