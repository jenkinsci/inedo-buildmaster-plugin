package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

import javax.annotation.Nonnull;

public class DeployToFirstStage extends AbstractDescribableImpl<DeployToFirstStage> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final boolean waitUntilCompleted;
    private boolean printLogOnFailure = true;

    @DataBoundConstructor
    public DeployToFirstStage(boolean waitUntilCompleted) {
        this.waitUntilCompleted = waitUntilCompleted;
    }

    @DataBoundSetter
    public final void setPrintLogOnFailure(boolean printLogOnFailure) {
        this.printLogOnFailure = printLogOnFailure;
    }

    public boolean isWaitUntilCompleted() {
        return waitUntilCompleted;
    }

    public boolean isPrintLogOnFailure() {
        return printLogOnFailure;
    }


    @Extension
    public static class DescriptorImpl extends Descriptor<DeployToFirstStage> {
        @Nonnull
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
