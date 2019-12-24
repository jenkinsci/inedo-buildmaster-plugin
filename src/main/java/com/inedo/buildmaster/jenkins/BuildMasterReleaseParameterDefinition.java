package com.inedo.buildmaster.jenkins;

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
import org.kohsuke.stapler.export.Exported;

import java.io.IOException;

public class BuildMasterReleaseParameterDefinition extends SimpleParameterDefinition {
    private static final long serialVersionUID = 1L;

    private ConfigHelper configHelper = null;
    private final String applicationId;
    private final String releaseNumber;

    @DataBoundConstructor
    public BuildMasterReleaseParameterDefinition(String name, String applicationId, String description) {
        super(name, description);
        this.applicationId = applicationId;
        this.releaseNumber = null;
    }

    public BuildMasterReleaseParameterDefinition(String name, String applicationId, String releaseNumber, String description) {
        super(name, description);
        this.applicationId = applicationId;
        this.releaseNumber = releaseNumber;
    }

    @Exported
    public String getApplicationId() {
        return applicationId;
    }

    private ConfigHelper getConfigHelper() {
        if (configHelper == null) {
            configHelper = new ConfigHelper();
        }

        return configHelper;
    }

    public ListBoxModel getReleases() throws IOException {
        return getConfigHelper().doFillReleaseNumberItems(getApplicationId());
    }

    @Override
    public ParameterDefinition copyWithDefaultValue(ParameterValue defaultValue) {
        if (defaultValue instanceof BuildMasterReleaseParameterValue) {
            BuildMasterReleaseParameterValue value = (BuildMasterReleaseParameterValue) defaultValue;
            return new BuildMasterReleaseParameterDefinition(getName(), getApplicationId(), value.getReleaseNumber(), getDescription());
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
        return new BuildMasterReleaseParameterValue(getName(), getApplicationId(), value, getDescription());
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