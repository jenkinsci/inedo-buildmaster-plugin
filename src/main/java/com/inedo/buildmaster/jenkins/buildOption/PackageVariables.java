package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class PackageVariables extends AbstractDescribableImpl<PackageVariables> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final boolean preserveVariables;
    private final String variables;

    public boolean isPreserveVariables() {
        return preserveVariables;
    }

    public String getVariables() {
        return variables;
    }

    @DataBoundConstructor
    public PackageVariables(boolean preserveVariables, String variables) {
        this.preserveVariables = preserveVariables;
        this.variables = variables;
    }
    
    @Extension
    public static class DescriptorImpl extends Descriptor<PackageVariables> {
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
