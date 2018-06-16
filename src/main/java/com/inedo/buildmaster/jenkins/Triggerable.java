package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.SetBuildVariables;
import com.inedo.buildmaster.jenkins.buildOption.WaitTillCompleted;

public interface Triggerable {
    public boolean isWaitTillBuildCompleted();

    public boolean isSetBuildVariables();

    public boolean isEnableReleaseDeployable();

    public WaitTillCompleted getWaitTillBuildCompleted();

    public SetBuildVariables getSetBuildVariables();

    public EnableReleaseDeployable getEnableReleaseDeployable();

    public String getApplicationId();

    public String getReleaseNumber();

    public String getBuildNumber();

    public boolean getDeployToFirstStage();
}
