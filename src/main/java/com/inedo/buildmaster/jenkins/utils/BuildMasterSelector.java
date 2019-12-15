package com.inedo.buildmaster.jenkins.utils;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.ReleaseStatus;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

import java.io.IOException;

public class BuildMasterSelector {
    private BuildMasterApi buildmaster;
    private Boolean isBuildMasterAvailable = null;
    private String connectionError = "";

    public BuildMasterApi getBuildMasterApi() {
        if (buildmaster == null) {
            buildmaster = new BuildMasterApi(new JenkinsConsoleLogWriter());
        }

        return buildmaster;
    }

    /**
     * Check if can connect to BuildMaster - if not prevent any more calls
     */
    public boolean isAvailable() {
        if (isBuildMasterAvailable == null) {
            try {
                getBuildMasterApi().checkConnection();
                isBuildMasterAvailable = true;
            } catch (Exception ex) {
                isBuildMasterAvailable = false;
                connectionError = ex.getClass().getName() + ": " + ex.getMessage();

                System.err.println(connectionError);
            }
        }

        return isBuildMasterAvailable;
    }

    public String getConnectionError() {
        return connectionError;
    }

    public ListBoxModel doFillApplicationIdItems(String firstItem) throws IOException {
        ListBoxModel items = new ListBoxModel();

        if (firstItem == null) {
            items.add("", "");
        }
        else
        {
            items.add(firstItem);
        }

        if (!isAvailable()) {
            return items;
        }

        Application[] applications = getBuildMasterApi().getApplications();

        for (Application application : applications) {
            items.add((application.ApplicationGroup_Name != null ? application.ApplicationGroup_Name + " > " : "") + application.Application_Name,
                    String.valueOf(application.Application_Id));
        }

        return items;
    }

    public FormValidation doCheckApplicationId(String value) {
        if (value.length() == 0)
            return FormValidation.error("Please set an application id or name");

        return FormValidation.ok();
    }

    public ListBoxModel doFillReleaseNumberItems(String applicationId) throws IOException {
        return doFillReleaseNumberItems(applicationId, null, false);
    }

    public ListBoxModel doFillReleaseNumberItems(String applicationId, String firstItem, boolean includeLatestOption) throws IOException {
        ListBoxModel items = new ListBoxModel();

        if (firstItem != null) {
            items.add(firstItem);
        }

        if (includeLatestOption) {
            items.add("Latest Active Release", SelectApplicationHelper.LATEST_RELEASE);
        }

        if (!isAvailable()) {
            return items;
        }

        if (applicationId != null && !applicationId.isEmpty() && !applicationId.startsWith("$")) {
            ApiRelease[] releases = getBuildMasterApi().getActiveReleases(Integer.parseInt(applicationId));

            for (ApiRelease release : releases) {
                items.add(release.number);
            }
        }

        return items;
    }

    public FormValidation doCheckReleaseNumber(String releaseNumber, String applicationId) {
        if (releaseNumber == null || releaseNumber.isEmpty())
            return FormValidation.error("Please set a release number");

        if (!isAvailable()) {
            return FormValidation.ok();
        }

        if (SelectApplicationHelper.LATEST_RELEASE.equalsIgnoreCase(releaseNumber)) {
            return FormValidation.ok();
        } else if (releaseNumber.startsWith("$")) {
            // Is a variable reference
            return FormValidation.ok();
        } else if (applicationId != null && !applicationId.isEmpty()) {
            return FormValidation.ok();
        }

        try {
            ApiRelease releaseDetails = getBuildMasterApi().getRelease(getBuildMasterApi().getApplicationIdFrom(applicationId), releaseNumber);

            if (releaseDetails == null) {
                return FormValidation.error("The release " + releaseNumber + " does not exist for this application");
            }

            String status = releaseDetails.status;

            if (!ReleaseStatus.ACTIVE.getText().equalsIgnoreCase(status)) {
                return FormValidation.error(String.format("The release status for release %s must be Active, the actual status is %s", releaseNumber, status));
            }
        } catch (Exception ex) {
            return FormValidation.error(ex.getClass().getName() + ": " + ex.getMessage());
        }

        return FormValidation.ok();
    }

    // TODO jelly expandableTextbox does not support form validation currently so this does nothing:
    // https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/lib/form/expandableTextbox.jelly
    public FormValidation doCheckVariables(String value) {
        try {
            BuildHelper.getVariablesList(value);
        } catch (Exception e) {
            return FormValidation.error(e.getMessage());
        }

        return FormValidation.ok();
    }

    public String getDefaultBuildNumber() {
        return ""; // BuildHelper.DEFAULT_BUILD_NUMBER;
    }

    public Application getApplication(String identifier) throws IOException {
        if (identifier == null) {
            return null;
        }

        int applicationId = getBuildMasterApi().getApplicationIdFrom(identifier);

        return getBuildMasterApi().getApplication(applicationId);
    }
}
