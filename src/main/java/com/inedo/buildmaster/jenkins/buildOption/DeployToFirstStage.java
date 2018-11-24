package com.inedo.buildmaster.jenkins.buildOption;

import java.io.Serializable;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

public class DeployToFirstStage extends AbstractDescribableImpl<DeployToFirstStage> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean waitUntilDeploymentCompleted = true;
    private boolean printLogOnFailure = true;

    @DataBoundConstructor
    public DeployToFirstStage() {
    }

    @DataBoundSetter
    public final void setWaitUntilDeploymentCompleted(boolean waitUntilDeploymentCompleted) {
        this.waitUntilDeploymentCompleted = waitUntilDeploymentCompleted;
    }

    @DataBoundSetter
    public final void setPrintLogOnFailure(boolean printLogOnFailure) {
        this.printLogOnFailure = printLogOnFailure;
    }

    public boolean isWaitUntilDeploymentCompleted() {
        return waitUntilDeploymentCompleted;
    }

    public boolean isPrintLogOnFailure() {
        return printLogOnFailure;
    }


    @Extension
    public static class DescriptorImpl extends Descriptor<DeployToFirstStage> {
        @Override
        public String getDisplayName() {
            return "";
        }
    }
}
