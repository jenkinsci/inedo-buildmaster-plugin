# buildmaster-plugin
Allows Jenkins to trigger a build on a BuildMaster application.

# Usage
The plugin has a lot of help that you can access while using Jenkins, but as a brief overview:

## Global Configuratiuon
Before using the plugin in a job you will need to go to the global configurn section at configure how the plugin will find your BuildMaster instance.

![ScreenShot](/screenshots/global_configuration.png)

## Select BuildMaster Application
Within your Job optionally add a build step to select the BuildMaster application and set the release number and build number that you wish to trigger the build for.

![ScreenShot](/screenshots/selectapplication_configuration.png)

## Trigger Build
Add the trigger build step.  If you haven't used the "Select BuildMaster Application" action then you will need to go into the advanced section and set the application id, release and build number details.

![ScreenShot](/screenshots/triggerbuild_configuration.png)
