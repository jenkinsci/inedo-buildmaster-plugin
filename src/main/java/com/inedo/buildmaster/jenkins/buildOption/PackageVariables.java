package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

import javax.annotation.Nonnull;

public class PackageVariables extends AbstractDescribableImpl<PackageVariables> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String variables;

    public String getVariables() {
        return variables;
    }

    @DataBoundConstructor
    public PackageVariables(String variables) {
        this.variables = variables;
    }
    
    @Extension
    public static class DescriptorImpl extends Descriptor<PackageVariables> {
        @Nonnull
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
