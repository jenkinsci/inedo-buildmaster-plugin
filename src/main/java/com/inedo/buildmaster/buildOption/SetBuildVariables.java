package com.inedo.buildmaster.buildOption;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.kohsuke.stapler.DataBoundConstructor;

public class SetBuildVariables extends AbstractDescribableImpl<SetBuildVariables> {
	private final boolean preserveVariables;
	private final String variables;

	public boolean isPreserveVariables() {
		return preserveVariables;
	}
	
	public String getVariables() {
		return variables;
	}

	@DataBoundConstructor 
	public SetBuildVariables( boolean preserveVariables, String variables) {
        this.preserveVariables = preserveVariables;
        this.variables = variables;
    }
    
    @Extension
    public static class DescriptorImpl extends Descriptor<SetBuildVariables> {
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}