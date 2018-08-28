package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class EnableReleaseDeployable extends AbstractDescribableImpl<EnableReleaseDeployable> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deployableId = "";

    public String getDeployableId() {
        return deployableId;
    }

    @DataBoundConstructor
    public EnableReleaseDeployable(String deployableId) {
        this.deployableId = deployableId;
    }
    
    @Extension
    public static class DescriptorImpl extends Descriptor<EnableReleaseDeployable> {
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
