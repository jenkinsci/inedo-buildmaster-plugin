package com.inedo.buildmaster.jenkins.utils;

import hudson.EnvVars;
import hudson.model.EnvironmentContributingAction;
import hudson.model.Run;

import javax.annotation.Nonnull;

/**
 * Allow plugin to update environment variables
 * 
 * @author Andrew Sumner
 *
 */
public class VariableInjectionAction implements EnvironmentContributingAction {

    private final String key;
    private final String value;

    public VariableInjectionAction(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void buildEnvironment(@Nonnull Run<?, ?> run, @Nonnull EnvVars env) {
        if (env != null && key != null && value != null) {
            env.put(key, value);
        }
    }

    public String getDisplayName() {
        return "VariableInjectionAction";
    }

    public String getIconFileName() {
        return null;
    }

    public String getUrlName() {
        return null;
    }
}
