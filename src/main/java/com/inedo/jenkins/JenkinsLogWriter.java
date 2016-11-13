package com.inedo.jenkins;

import com.inedo.http.LogWriter;

public interface JenkinsLogWriter extends LogWriter {
	static final String LOG_PREFIX = "[BuildMaster] ";
	
	public void error(String message);
	
	public void fatalError(String message);
}
