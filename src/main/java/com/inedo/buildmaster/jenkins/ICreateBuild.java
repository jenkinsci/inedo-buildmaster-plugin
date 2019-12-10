package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;

public interface ICreateBuild {
    boolean isDeployToFirstStage();

    boolean isBuildVariables();

    DeployToFirstStage getDeployToFirstStage();

    String getBuildVariables();

    String getApplicationId();

    String getReleaseNumber();
}
