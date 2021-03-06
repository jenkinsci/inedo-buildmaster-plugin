package com.inedo.buildmaster.jenkins.utils;

import hudson.AbortException;
import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.Run;
import hudson.model.TaskListener;

import java.util.Objects;

/**
 * Common Jenkins tasks. 
 * 
 * @author Andrew Sumner
 */
public class JenkinsHelper {
    private final Run<?, ?> run;
    private final TaskListener listener;
    private final JenkinsLogWriter logWriter;

    /**
     * For unit tests as they don't have access to the build or listener
     */
    public JenkinsHelper() {
        this.run = null;
        this.listener = null;
        this.logWriter = new JenkinsConsoleLogWriter();
    }

    public JenkinsHelper(Run<?, ?> run, TaskListener listener) {
        this.run = run;
        this.listener = listener;
        this.logWriter = new JenkinsTaskLogWriter(listener);
    }

    public String expandVariable(String variable) {
        if (run == null) {
            return variable;
        }

        if (variable == null || variable.isEmpty()) {
            return variable;
        }

        String expanded = variable;

        try {
            // Pipeline script doesn't support getting environment variables
            if (run instanceof AbstractBuild) {
                expanded = run.getEnvironment(Objects.requireNonNull(listener)).expand(variable);
            }
        } catch (Exception e) {
            getLogWriter().info("Exception thrown expanding '" + variable + "' : " + e.getClass().getName() + " " + e.getMessage());
        }

        return expanded;
    }

    public void injectEnvironmentVariable(String key, String value) {
        if (run == null) {
            return;
        }

        run.addAction(new VariableInjectionAction(key, value));
    }

    public JenkinsLogWriter getLogWriter() {
        return logWriter;
    }

    public String getEnvironmentVariable(String variable) throws AbortException {
        EnvVars envVars;

        try {
            envVars = Objects.requireNonNull(run).getEnvironment(Objects.requireNonNull(listener));
        } catch (Exception e) {
            throw new AbortException(e.getMessage());
        }

        return envVars.get(variable);
    }
}
