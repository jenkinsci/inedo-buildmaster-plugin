package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class DeployVariables extends AbstractDescribableImpl<DeployVariables> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String variables;

    public String getVariables() {
        return variables;
    }

    @DataBoundConstructor
    public DeployVariables(String variables) {
        this.variables = variables;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<DeployVariables> {
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
