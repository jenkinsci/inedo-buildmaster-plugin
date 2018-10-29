package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class PackageVariables extends AbstractDescribableImpl<PackageVariables> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean preserveVariables = false;
    private final String variables;

    public boolean isPreserveVariables() {
        return preserveVariables;
    }

    public String getVariables() {
        return variables;
    }

    @DataBoundConstructor
    public PackageVariables(String variables) {
        this.variables = variables;
    }
    
    @DataBoundSetter
    public final void setPreserveVariables(boolean preserveVariables) {
        this.preserveVariables = preserveVariables;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<PackageVariables> {
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
