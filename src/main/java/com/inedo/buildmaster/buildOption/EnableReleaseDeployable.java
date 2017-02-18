package com.inedo.buildmaster.buildOption;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.kohsuke.stapler.DataBoundConstructor;

public class EnableReleaseDeployable extends AbstractDescribableImpl<EnableReleaseDeployable> {
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