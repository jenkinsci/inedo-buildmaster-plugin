package com.inedo;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BuildMasterConfig {
	// Global Configuration Items
	protected String url;
	protected String authentication;
	protected String domain;
	protected String user;
	protected String password;
	protected String apiKey;
	protected boolean logCalls = false;
	protected PrintStream printStream = System.out;
    
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
