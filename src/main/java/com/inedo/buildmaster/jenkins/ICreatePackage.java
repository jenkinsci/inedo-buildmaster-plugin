package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;

public interface ICreatePackage {
    public boolean isDeployToFirstStage();

    public boolean isPackageVariables();

    public boolean isEnableReleaseDeployable();

    public DeployToFirstStage getDeployToFirstStage();

    public PackageVariables getPackageVariables();

    public EnableReleaseDeployable getEnableReleaseDeployable();

    public String getApplicationId();

    public String getReleaseNumber();

    public String getPackageNumber();
}
