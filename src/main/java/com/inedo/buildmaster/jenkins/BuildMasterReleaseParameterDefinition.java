package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.jenkins.utils.ConfigHelper;
import hudson.Extension;
import hudson.model.ParameterDefinition;
import hudson.model.ParameterValue;
import hudson.model.SimpleParameterDefinition;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.bind.JavaScriptMethod;
import org.kohsuke.stapler.export.Exported;

import java.io.IOException;
import java.util.*;

public class BuildMasterReleaseParameterDefinition extends SimpleParameterDefinition {
    private static final long serialVersionUID = 1L;

    private ConfigHelper configHelper = null;
    private final String applicationId;
    private final String releaseNumber;
    private final boolean showApplicationId;

    @DataBoundConstructor
    public BuildMasterReleaseParameterDefinition(String name, String applicationId, boolean showApplicationId, String description) {
        super(name, description);
        this.applicationId = applicationId;
        this.releaseNumber = null;
        this.showApplicationId = showApplicationId;
    }

    public BuildMasterReleaseParameterDefinition(String name, String applicationId, boolean showApplicationId, String releaseNumber, String description) {
        super(name, description);
        this.applicationId = applicationId;
        this.releaseNumber = releaseNumber;
        this.showApplicationId = showApplicationId;
    }

    @Exported
    public String getApplicationId() {
        return applicationId;
    }

    @Exported
    public boolean isShowApplicationId() {
        return showApplicationId;
    }

    private ConfigHelper getConfigHelper() {
        if (configHelper == null) {
            configHelper = new ConfigHelper();
        }

        return configHelper;
    }

    public ListBoxModel getApplications() throws IOException {
        return getConfigHelper().doFillApplicationIdItems();
    }

    public ListBoxModel getReleases() throws IOException {
        return getConfigHelper().doFillReleaseNumberItems(getApplicationId());
    }

    @JavaScriptMethod
    public String[] getReleases(String applicationId) throws IOException {
        List<String> items = new ArrayList<>();

        ApiRelease[] releases = getConfigHelper().getBuildMasterApi().getActiveReleases(Integer.parseInt(applicationId));

        for (ApiRelease release : releases) {
            items.add(release.number);
        }

        return items.toArray(new String[0]);
    }

    @Override
    public ParameterDefinition copyWithDefaultValue(ParameterValue defaultValue) {
        if (defaultValue instanceof BuildMasterReleaseParameterValue) {
            BuildMasterReleaseParameterValue value = (BuildMasterReleaseParameterValue) defaultValue;
            return new BuildMasterReleaseParameterDefinition(getName(), getApplicationId(), isShowApplicationId(), value.getReleaseNumber(), getDescription());
        } else {
            return this;
        }
    }

    @Override
    public ParameterValue getDefaultParameterValue() {
        if (releaseNumber != null && !releaseNumber.isEmpty()) {
            return createValue(releaseNumber);
        }

        return null;
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        BuildMasterReleaseParameterValue value = req.bindJSON(BuildMasterReleaseParameterValue.class, jo);
        return value;
    }

    public BuildMasterReleaseParameterValue createValue(String value) {
        return new BuildMasterReleaseParameterValue(getName(), getApplicationId(), value, isShowApplicationId(), getDescription());
    }

    @Extension
    @Symbol("buildMasterRelease")
    public static class DescriptorImpl extends ParameterDescriptor {
        private final ConfigHelper configHelper = new ConfigHelper();

        @Override
        public String getDisplayName() {
            return "BuildMaster Release Parameter";
        }

        @Override
        public ParameterDefinition newInstance(StaplerRequest req, JSONObject formData) {
            return req.bindJSON(BuildMasterReleaseParameterDefinition.class, formData);
        }

        public ConfigHelper getBuildmaster() {
            return configHelper;
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return configHelper.doFillApplicationIdItems(null);
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return configHelper.doCheckApplicationId(value);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return configHelper.doFillReleaseNumberItems(applicationId, null, true);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return configHelper.doCheckReleaseNumber(value, applicationId);
        }
    }
}