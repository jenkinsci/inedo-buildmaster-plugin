package com.inedo.buildmaster.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Configuration settings required to can the BuildMaster json api
 * 
 * @author Andrew Sumner
 */
public class BuildMasterConfig {
	// Global Configuration Items
	public String url;
	public String user;
	public String password;
	public String apiKey;
    
	/**
	 * Get the name of the host the service is running on.
	 */
	public String getHost() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
			return "Unknown";
		}
	}
}
