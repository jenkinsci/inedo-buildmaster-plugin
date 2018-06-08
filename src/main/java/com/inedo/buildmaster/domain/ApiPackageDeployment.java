package com.inedo.buildmaster.domain;

/**
 * A custom object to provide the response from the api calls made to create a package
 *
 */
public class ApiPackageDeployment {
    public final ApiReleasePackage releasePackage;
    public final ApiDeployment[] deployments;

    public ApiPackageDeployment(ApiReleasePackage releasePackage, ApiDeployment[] deployments) {
        this.releasePackage = releasePackage;
        this.deployments = deployments;
    }

}
