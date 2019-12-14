package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.jenkins.utils.BuildMasterSelector;
import com.inedo.buildmaster.jenkins.utils.JenkinsConsoleLogWriter;
import com.inedo.buildmaster.jenkins.utils.SelectApplicationHelper;
import hudson.Extension;
import hudson.model.*;
import hudson.util.FormValidation;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import hudson.util.ListBoxModel;
import hudson.util.RunList;
import net.sf.json.JSONObject;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.export.Exported;

//https://github.com/jenkinsci/validating-string-parameter-plugin/blob/master/src/main/resources/hudson/plugins/validating_string_parameter/ValidatingStringParameterDefinition/index.jelly

/**
 * String based parameter that supports setting a regular expression to validate the
 * user's entered value, giving real-time feedback on the value.
 *
 * @author Peter Hayes
 * @since 1.0
 * @see {@link ParameterDefinition}
 */
public class BuildMasterReleaseParameterDefinition extends SimpleParameterDefinition {
    private static final long serialVersionUID = 1L;
    private BuildMasterSelector buildmaster = new BuildMasterSelector();

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

    public BuildMasterSelector getBuildmaster() {
        return buildmaster;
    }

    @Exported
    public String getApplicationId() {
        return applicationId;
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
        if (releaseNumber !=null && !releaseNumber.isEmpty()) {
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

//    public Application[] getApplications() throws IOException {
//        BuildMasterApi buildmaster = new BuildMasterApi(new JenkinsConsoleLogWriter());
//
//        Application[] applications = buildmaster.getApplications();
//
//        return applications;
//    }

    @Extension
    @Symbol("buildMasterRelease")
    public static class DescriptorImpl extends ParameterDescriptor {
        private BuildMasterSelector buildmaster = new BuildMasterSelector();

        @Override
        public String getDisplayName() {
            return "BuildMaster Release Parameter";
        }

        @Override
        public ParameterDefinition newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            return req.bindJSON(BuildMasterReleaseParameterDefinition.class, formData);
        }

        public BuildMasterSelector getBuildmaster() {
            return buildmaster;
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return buildmaster.doFillApplicationIdItems(null);
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return buildmaster.doCheckApplicationId(value);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return buildmaster.doFillReleaseNumberItems(applicationId,null);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return buildmaster.doCheckReleaseNumber(value, applicationId);
        }
    }
}