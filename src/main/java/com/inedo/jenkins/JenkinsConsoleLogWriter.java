package com.inedo.jenkins;

public class JenkinsConsoleLogWriter implements JenkinsLogWriter {
	@Override
	public void info(String message) {
		System.out.println(LOG_PREFIX + message);
	}

	public void error(String message) {
	    System.err.println(LOG_PREFIX + message);
	}
	
	public void fatalError(String message) {
	    System.err.println(LOG_PREFIX + message);
    }
}
