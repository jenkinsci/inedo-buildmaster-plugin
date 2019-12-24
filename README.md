Inedo BuildMaster Plugin
========================

[![Jenkins Plugin](https://img.shields.io/jenkins/plugin/v/inedo-buildmaster.svg)](https://plugins.jenkins.io/inedo-buildmaster)
[![GitHub release](https://img.shields.io/github/release/jenkinsci/inedo-buildmaster-plugin.svg?label=changelog)](https://github.com/jenkinsci/inedo-buildmaster-plugin/releases/latest)
[![Jenkins Plugin Installs](https://img.shields.io/jenkins/plugin/i/inedo-buildmaster.svg?color=blue)](https://plugins.jenkins.io/inedo-buildmaster)

## About this plugin
This plugin allows Jenkins jobs to request version information and trigger builds on a [Inedo BuildMaster](http://inedo.com/buildmaster) application as part of a Jenkins build process. Supported features:

* Release Parameter: select a release at build time and inject environment variables into the job for the create build step
* Build Environment: inject environment variables into the job for the create build step
* Create Build: trigger a build in BuildMaster
* Deploy To Stage: deploy a build to a specific stage in BuildMaster


## Usage
Note: A minimum BuildMaster version of 6.1.0 is required for this plugin

### Installing and configuring the plugin

This plugin can be installed from any Jenkins installation connected to the Internet using the **Plugin Manager** screen.

First, you need to ensure that an api key as been configured in BuildMaster at BuildMaster > Administration > Api Keys & Access Logs.  Without this the plugin won't be able access BuildMaster.  Ensure that the following items are checked:
Native API
Variables management
Release & package deployment:

![BuildMaster Admin](/docs/images/buildmaster_admin.png)

Next, you need to go to Jenkins' system config screen to tell Jenkins where's your BuildMaster resides.  I have found that I need to configure it for NTLM authentication on our network otherwise my account gets locked out.  Clicking the "Test Connection" button a few times will confirm whether this is required or not.

![Global Configuration](/docs/images/global_configuration.png)

### Obtaining Information from BuildMaster

If you require the release or next build numbers from BuildMaster to use withing your build, e.g. to version your application, you have two options as outlined below. These will inject the following environment variables into your build:

* BUILDMASTER_APPLICATION_ID
* BUILDMASTER_APPLICATION_NAME
* BUILDMASTER_RELEASE_NUMBER
* BUILDMASTER_LATEST_BUILD_NUMBER
* BUILDMASTER_NEXT_BUILD_NUMBER

#### Release Parameter  

The "BuildMaster Release Parameter" allows you to select a release at build time.  This would only be useful if you have multiple active releases on the go at any one time.

![Build Environment](/docs/images/build_parameter.png)

#### Build Environment

The "Inject BuildMaster release details as environment variables" build environment setting allows you to select the the BuildMaster application you are dealing with and the settings will be used to inject these environment variables into the job at build time.

![Build Environment](/docs/images/build_envrionment.png)

### Triggering a Build

#### Create Build
The "Create BuildMaster Build" action can be added as either a build step or post build action.  The choice of which to use will be largely dependent on how you import the build artifacts into BuildMaster and your personal preference:

1. You are using the BuildMaster Jenkins Build Importer Build Step which imports build artifacts from Jenkins: the post build action is required
2. You are using a standard BuildMaster build step and importing files from a folder that you've placed the artifacts into from the Jenkins build (eg using ArtifactDeployer Plugin): either the post build or build step actions will be fine
3. You use an external artifact repository such as Nexus or Artifactory: either the post build or build step actions will be fine

If you haven't used the "Select BuildMaster Application" action then you will need to select an application and release, otherwise you can leave the default settings which will picked up the injected environment variables.

To deploy the build to the first environment ensure that you have the post-build step action "Auto-Promote Build to the Next Environment" set in BuildMaster.

![Create Build](/docs/images/create_build.png)

#### Deploy to Stage
The "Deploy BuildMaster Build To Stage" action can be used to deploy (or re-deploy) a package to a specified stage by specifying a Stage Name, or deploy to the next stage in the pipeline by leaving Stage Name empty.

![Deploy to Stage](/docs/images/deploy_to_stage.png)


### Pipeline Script Support

All the above tasks can also be performed with Jenkins Script. While the basic syntax can be generated using the pipeline syntax snippet generator the resulting code will have to be tweaked.

#### Scripted Pipeline Example 
```
node {
  buildMasterWithApplicationRelease(applicationId: 'Demo') {
    echo """
      Application id = $BUILDMASTER_APPLICATION_ID
      Application Name = $BUILDMASTER_APPLICATION_NAME
      Release Number = $BUILDMASTER_RELEASE_NUMBER
      Next Build Number = $BUILDMASTER_NEXT_BUILD_NUMBER
    """
  
    BUILDMASTER_BUILD_NUMBER = buildMasterCreateBuild(applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, variables: "JenkinsJobName=$JOB_NAME\nJenkinsBuildNumber=$BUILD_NUMBER", deployToFirstStage: [waitUntilCompleted: true])
  
    echo "BUILDMASTER_BUILD_NUMBER = BUILDMASTER_BUILD_NUMBER"
    buildMasterDeployBuildToStage(applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, buildNumber: BUILDMASTER_BUILD_NUMBER, stage: 'Integration')
  }
}
```

#### Declarative Pipeline Example
```
pipeline {
  agent any
  
  stages {
    stage('Main') {
      steps {
        buildMasterWithApplicationRelease(applicationId: '1', packageNumberSource: 'BUILDMASTER') {
          echo """
            Application id = $BUILDMASTER_APPLICATION_ID
            Application Name = $BUILDMASTER_APPLICATION_NAME
            Release Number = $BUILDMASTER_RELEASE_NUMBER
            Next Build Number = $BUILDMASTER_NEXT_BUILD_NUMBER
          """

          // Jenkins declarative pipeline script has a somewhat restricted syntax.  Unfortunately to return package 
          // number you need to wrap this in a script block
          // See: https://jenkins.io/doc/book/pipeline/syntax/#script
          script {
            BUILDMASTER_BUILD_NUMBER = buildMasterCreateBuild(applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, variables: "JenkinsJobName=$JOB_NAME\nJenkinsBuildNumber=$BUILD_NUMBER", deployToFirstStage: [waitUntilCompleted: true])
          }
            
          echo "BUILDMASTER_PACKAGE_NUMBER = $BUILDMASTER_PACKAGE_NUMBER"
          buildMasterDeployBuildToStage(applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, buildNumber: BUILDMASTER_BUILD_NUMBER, stage: 'Integration')
        }
      }
    }
  }
}
```

## Reporting an Issue
Select Create Issue on the [JIRA home page](https://issues.jenkins-ci.org/secure/Dashboard.jspa") and ensure that the component is set to inedo-buildmaster-plugin.

For more information see the Jenkins guide on [how to report an issue](https://wiki.jenkins.io/display/JENKINS/How+to+report+an+issue).

## More information

* [Changelog](https://github.com/jenkinsci/inedo-buildmaster-plugin/releases)
* [Developer documentation](./docs/DEVELOPER.md)
