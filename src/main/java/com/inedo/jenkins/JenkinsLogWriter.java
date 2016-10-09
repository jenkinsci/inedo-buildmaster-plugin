package com.inedo.jenkins;

import com.inedo.http.LogWriter;

import hudson.model.TaskListener;

public class JenkinsLogWriter implements LogWriter {
	private static final String LOG_PREFIX = "[BuildMaster] ";
	private final TaskListener listener;
	
	public JenkinsLogWriter(TaskListener listener) {
		this.listener = listener;
	}
	
	@Override
	public void info(String message) {
		if (listener != null) {
			listener.getLogger().println(LOG_PREFIX + message);
		} else {
			System.out.println(LOG_PREFIX + message);
		}
	}

	public void error(String message) {
		if (listener != null) {
			listener.error(LOG_PREFIX + message);
		}
	}
}
