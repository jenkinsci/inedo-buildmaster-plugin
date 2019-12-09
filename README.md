[![Build Status](https://jenkins.ci.cloudbees.com/job/plugins/job/inedo-buildmaster-plugin/badge/icon)](https://jenkins.ci.cloudbees.com/job/plugins/job/inedo-buildmaster-plugin/)

This plugin allows Jenkins to request version information and trigger a build on an application in [Inedo BuildMaster](http://inedo.com/buildmaster) as part of a Jenkins build process.

See the [Wiki page](http://wiki.jenkins-ci.org/display/JENKINS/Inedo+BuildMaster+Plugin) for more details.

https://jenkins.io/doc/developer/plugin-development/pipeline-integration/
https://jenkins.io/doc/book/pipeline/syntax/


# Building The Plugin

The plugin is built using <a href="http://www.gradle.org/">Gradle</a> and the <a href="https://wiki.jenkins-ci.org/display/JENKINS/Gradle+JPI+Plugin">Gradle Jenkins JPI Plugin</a>.  The code base includes the Gradle Wrapper, which will automatically download the correct version of Gradle. 

Gradle can be run from the command line or from your IDE:

## Command line

From the command line, `cd` to the folder containing a copy of this project, and run 

  `./gradlew clean jpi` on Unix-based systems, or 
  
  `gradlew clean jpi` on Windows.
  
  `gradlew -Dhttp.proxyHost=yourProxy -Dhttp.proxyPort=yourPort -Dhttp.proxyUser=yourUsername -Dhttp.proxyPassword=yourPassword -Dhttps.proxyHost=yourProxy -Dhttps.proxyPort=yourPort -Dhttps.proxyUser=yourUsername -Dhttps.proxyPassword=yourPassword clean jpi` from behind a proxy. It is vital that any tasks come after the proxy configuration. 

This will download the required dependencies, clean the existing project, recompile all source code and build the jpi file required by jenkins.
 

## IDE

For Eclipse and NetBeans, you will need to install a Gradle plugin to your IDE before importing the project. See [Gradle tooling](https://www.gradle.org/tooling) for details.

On importing the project to your IDE, the required dependencies will be downloaded.


# Testing The Plugin

## Manual

To spin up a Jenkins instance with this plugin installed for manual testing, run `gradlew clean server` (see "building the plugin" above). The Jenkins instance will be available at http://localhost:8080. You may need to specify a path to your JDK, if so use `gradlew clean server -Dorg.gradle.java.home=/JDK_PATH`

To login the username will be admin and the password can be found in <project root>/work/secrets/initialAdminPassword

### Prerequisites
* BuildMaster:
    * Installed and configured with an API key
    * Add an Application called TestApplication with the default pipleline stages
    * Create an Application Group called TestAutomation and assign TestApplication to it, while not overly important this allows the plugins select application dropdown to display the group and the automated tests to not fail because get applications api has not returned the group fields.
    * Create a new Plan step called Build, with auto promote set to true, and add a log task 
    * Create an active release
* Jenkins System Configuration page updated with BuildMaster server details and the Test Connection button returning success

See the [Wiki page](http://wiki.jenkins-ci.org/display/JENKINS/Inedo+BuildMaster+Plugin) for more details.

### Job Configuration

1. In Jenkins create a FreeStyle job called test-freestyle
    1.	Add build step "BuildMaster: Select Application"
    1.	Add build step "BuildMaster: Trigger Build"
    1.	Add build step "BuildMaster: Deploy To Stage"
1. In Jenkins create a Pipeline job called test-pipleline and configure as for the free style job
```
// Declarative pipeline example
pipeline {
  agent any
  
  stages {
    stage('Main') {
      steps {
        buildMasterWithApplicationRelease(applicationId: '1') {
            echo """
    			Application id = $BUILDMASTER_APPLICATION_ID
    			Release Number = $BUILDMASTER_RELEASE_NUMBER
    			Build Number = $BUILDMASTER_BUILD_NUMBER
            """

            // Jenkins declarative pipeline script has a somewhat restricted syntax.  Unfortunately to return build 
            // number you need to wrap this in a script block
            // See: https://jenkins.io/doc/book/pipeline/syntax/#script
            script {
                BUILDMASTER_BUILD_NUMBER = buildMasterCreateBuild(applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, buildNumber: BUILDMASTER_BUILD_NUMBER, deployToFirstStage: true, waitTillBuildCompleted: [printLogOnFailure: true])
            }
            
            echo "BUILDMASTER_BUILD_NUMBER = $BUILDMASTER_BUILD_NUMBER"

            buildMasterDeployBuildToStage(stage: 'Integration', applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, buildNumber: BUILDMASTER_BUILD_NUMBER, waitTillBuildCompleted: [printLogOnFailure: true])
            
            echo "Redeploy to Integration"
            buildMasterDeployBuildToStage(stage: 'Integration', applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, buildNumber: BUILDMASTER_BUILD_NUMBER, waitTillBuildCompleted: [printLogOnFailure: true])
        }
      }
    }
  }
}

/*
// Scripted pipeline example
node {
    buildMasterWithApplicationRelease(applicationId: '1') {
		echo """
		Application id = $BUILDMASTER_APPLICATION_ID
		Release Number = $BUILDMASTER_RELEASE_NUMBER
		Build Number = $BUILDMASTER_BUILD_NUMBER
		"""
		
		BUILDMASTER_BUILD_NUMBER = buildMasterCreateBuild(applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, buildNumber: BUILDMASTER_BUILD_NUMBER)
		
		echo "BUILDMASTER_BUILD_NUMBER = $BUILDMASTER_BUILD_NUMBER"
		
		buildMasterDeployBuildToStage(applicationId: BUILDMASTER_APPLICATION_ID, releaseNumber: BUILDMASTER_RELEASE_NUMBER, buildNumber: BUILDMASTER_BUILD_NUMBER, waitTillBuildCompleted: [printLogOnFailure: true])
		
    }
}
*/
```

## Automated

Update <project root>/test.properties with the required details and run the tests.  If useMockServer is false then the tests will be run against the installed application, if true it will run against a mock server.  While the mock server is useful for unit testing, the real service is required to test the plugin against application upgrades.

The tests mainly verify the BuildMaster APIs are still functioning as expected, although there are a couple of tests that attempt to use the plugin from a mocked Jenkins job.  
