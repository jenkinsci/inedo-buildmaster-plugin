package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;
import com.inedo.buildmaster.jenkins.buildOption.WaitTillCompleted;

public interface ICreatePackage {
    public boolean isWaitTillBuildCompleted();

    public boolean isPackageVariables();

    public boolean isEnableReleaseDeployable();

    public WaitTillCompleted getWaitTillBuildCompleted();

    public PackageVariables getPackageVariables();

    public EnableReleaseDeployable getEnableReleaseDeployable();

    public String getApplicationId();

    public String getReleaseNumber();

    public String getPackageNumber();

    public boolean getDeployToFirstStage();
}
