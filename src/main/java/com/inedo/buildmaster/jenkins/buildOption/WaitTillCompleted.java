package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class WaitTillCompleted extends AbstractDescribableImpl<WaitTillCompleted> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final boolean printLogOnFailure;

    public boolean isPrintLogOnFailure() {
        return printLogOnFailure;
    }

    @DataBoundConstructor
    public WaitTillCompleted(boolean printLogOnFailure) {
        this.printLogOnFailure = printLogOnFailure;
    }
    
    @Extension
    public static class DescriptorImpl extends Descriptor<WaitTillCompleted> {
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
