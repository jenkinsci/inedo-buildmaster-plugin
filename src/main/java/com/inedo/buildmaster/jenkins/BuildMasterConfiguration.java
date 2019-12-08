package com.inedo.buildmaster.jenkins;

import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.jenkins.utils.JenkinsConsoleLogWriter;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import hudson.util.Secret;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;

/**
 * Has fields for global configuration
 * 
 * @author Andrew Sumner
 */
public class BuildMasterConfiguration extends GlobalConfiguration {

    @Extension
    public static final class DescriptorImpl extends Descriptor<GlobalConfiguration> {

        /**
         * To persist global configuration information,
         * simply store it in a field and call save().
         *
         * <p>
         * If you don't want fields to be persisted, use <tt>transient</tt>.
         */
        private String url;
        private String apiKey;
        private String user;
        private Secret password;
        private boolean logApiRequests;
        private boolean trustAllCertificates;
        
        public DescriptorImpl() {
            super(BuildMasterConfiguration.class);
            load();
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            // To persist global configuration information,
            // set that to properties and call save().
        
            req.bindJSON(this, formData);
            save();
            return super.configure(req,formData);
        }

        @Override
        public String getDisplayName() {
            return "BuildMaster Plugin";
        }

        /**
         * Field setters
         */
        public void setUrl(String value) {
            url = value;
        }

        public void setApiKey(String value) {
            apiKey = value;
        }

        public void setUser(String value) {
            user = value;
        }
        
        public void setPassword(Secret value) {
            password = value;
        }
        
        public void setLogApiRequests(boolean logApiRequests) {
            this.logApiRequests = logApiRequests;
        }

        public void setTrustAllCertificates(boolean value) {
            trustAllCertificates = value;
        }
        
        /**
         * Field getters
         */
        public String getUrl() {
            return url;
        }
        
        public String getApiKey() {
            return apiKey;
        }

        public String getUser() {
            return user;
        }
        
        public Secret getPassword() {
            return password;
        }
        
        public boolean getLogApiRequests() {
            return logApiRequests;
        }

        public boolean getTrustAllCertificates() {
            return trustAllCertificates;
        }
        
        public boolean isRequiredFieldsConfigured() {
            return url != null && apiKey != null &&
                    !url.isEmpty() && !apiKey.isEmpty();
        }
        
        /**
         * Performs on-the-fly validation of the form field 'url'.
         *
         * @param value
         *      This parameter receives the value that the user has typed.
         * @return
         *      Indicates the outcome of the validation. This is sent to the browser.
         *      <p>
         *      Note that returning {@link FormValidation#error(String)} does not
         *      prevent the form from being saved. It just means that a message
         *      will be displayed to the user. 
         */
        public FormValidation doCheckUrl(@QueryParameter String value) {
            if (value.length() == 0)
                return FormValidation.error("Please set a URL");
            
            if (!value.startsWith("http"))
                return FormValidation.warning("Is this a valid URL?");
            
            return FormValidation.ok();
        }

        public FormValidation doCheckUser(@QueryParameter String value, @QueryParameter String password) {
            if (password != null && !password.isEmpty() && value.length() == 0)
                return FormValidation.error("User is required");

            return FormValidation.ok();
        }

        public FormValidation doCheckPassword(@QueryParameter String value, @QueryParameter String user) {
            if (user != null && !user.isEmpty() && value.length() == 0)
                return FormValidation.error("Password is required");

            return FormValidation.ok();
        }

        /**
         *  BuildMaster connection test
         */
        public FormValidation doTestConnection(
                @QueryParameter("url") final String url,
                @QueryParameter("apiKey") final String apiKey,
                @QueryParameter("user") final String user,
                @QueryParameter("password") final Secret password,
                @QueryParameter("trustAllCertificates") final boolean trustAllCertificates) {

            BuildMasterConfig config = new BuildMasterConfig();

            config.url = url;
            config.apiKey = apiKey;
            config.user = user;
            config.password = Secret.toString(password);
            config.trustAllCertificates = trustAllCertificates;

            BuildMasterApi buildmaster = new BuildMasterApi(config, new JenkinsConsoleLogWriter());

            try {
                buildmaster.checkConnection();
            } catch (Exception ex) {
                return FormValidation.error("Failed. Please check the configuration. " + ex.getClass().getName() + ": " + ex.getMessage());
            }

            return FormValidation.ok("Success. Connection with BuildMaster verified.");
        }

        public BuildMasterConfig getBuildMasterConfig() {
            BuildMasterConfig config = new BuildMasterConfig();

            config.url = url;
            config.apiKey = apiKey;
            config.user = user;
            config.password = Secret.toString(password);
            config.logApiRequests = logApiRequests;
            config.trustAllCertificates = trustAllCertificates;

            return config;
        }
    }
}