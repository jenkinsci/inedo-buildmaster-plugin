package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.BuildVariables;

public interface ICreateBuild {
    boolean isDeployToFirstStage();

    boolean isBuildVariables();

    DeployToFirstStage getDeployToFirstStage();

    BuildVariables getBuildVariables();

    String getApplicationId();

    String getReleaseNumber();

    String getBuildNumber();
}
