package com.inedo.buildmaster.buildOption;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class WaitTillCompleted extends AbstractDescribableImpl<WaitTillCompleted> {
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
