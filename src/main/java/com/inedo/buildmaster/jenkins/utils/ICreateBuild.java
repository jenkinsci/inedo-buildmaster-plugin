package com.inedo.buildmaster.jenkins.utils;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;

public interface ICreateBuild {
    boolean isDeployToFirstStage();

    DeployToFirstStage getDeployToFirstStage();

    String getBuildVariables();

    String getApplicationId();

    String getReleaseNumber();
}
