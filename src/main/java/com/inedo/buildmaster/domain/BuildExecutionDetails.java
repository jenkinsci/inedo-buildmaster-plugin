package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecutionDetails
{
	public static final String EXAMPLE = 
			"{" +
			"\"BuildExecutions_Extended\": " +
			"[" +
			"  { \"Execution_Id\": 92236, \"Application_Id\": 36, \"Release_Number\": \"1.3\", \"Build_Number\": \"26\", \"BuildStatus_Name\": \"Active\", \"Workflow_Id\": 62, \"Workflow_Name\": \"Standard\", \"Environment_Id\": 4, \"Environment_Name\": \"DEV\", \"ExecutionStart_Date\": \"2015-04-15T04:06:57.5870000\", \"ExecutionEnded_Date\": \"2015-04-15T04:07:05.7600000\", \"ExecutionStatus_Name\": \"Succeeded\", \"CreatedBy_User_Name\": \"SYSTEM\", \"CreatedOn_Date\": \"2015-04-15T04:06:57.4330000\", \"Application_Name\": \"Pax Hold Release #2\", \"PromotionStatus_Name\": \"Completed\", \"Promoted_Date\": \"2015-04-15T04:06:57.4330000\", \"PromotedBy_Name\": \"SYSTEM\", \"WarningLogEntry_Indicator\": \"N\", \"Release_Name\": \"1.3\", \"ReleaseStatus_Name\": \"Active\", \"Initial_BuildExecution_DeploymentPlan_Id\": 142077, \"VariableSupport_Code\": \"N\", \"ApplicationGroup_Id\": 4, \"Release_Sequence\": 3, \"Initial_DeploymentPlan_Id\": 214, \"Build_AutoPromote_Indicator\": \"Y\"}" +
			"]," +
			"\"BuildExecution_DeploymentPlanActionGroups\": " +
			"[" +
			"  { \"BuildExecution_DeploymentPlan_Id\": 142076, \"Original_DeploymentPlanActionGroup_Sequence\": 0, \"Original_DeploymentPlanActionGroup_Id\": 0}," +
			"  { \"BuildExecution_DeploymentPlan_Id\": 142077, \"Original_DeploymentPlanActionGroup_Sequence\": 1, \"Original_Deployable_Name\": \"PaxHoldRelease\", \"Original_DeploymentPlanActionGroup_Id\": 4694, \"Original_DeploymentPlanActionGroup_Name\": \"Deploy Application\"}," +
			"  { \"BuildExecution_DeploymentPlan_Id\": 142077, \"Original_DeploymentPlanActionGroup_Sequence\": 2, \"Original_Deployable_Name\": \"PaxHoldRelease\", \"Original_DeploymentPlanActionGroup_Id\": 4698, \"Original_DeploymentPlanActionGroup_Name\": \"Test Deployment\"}" +
			"]," +
			"\"BuildExecution_ActionGroupActions\": " +
			"[" +
			"  { \"ExecutionStarted_Date\": \"2015-04-15T04:06:57.6870000\", \"ExecutionEnded_Date\": \"2015-04-15T04:07:05.7600000\", \"ExecutionStatus_Name\": \"Succeeded\", \"ExecutionAction_Description\": \"\", \"Original_Action_Sequence\": 0, \"BuildExecution_ActionGroupAction_Id\": 1706457, \"Attempt_Sequence\": 1, \"WarningLogEntry_Indicator\": \"N\", \"BuildExecution_DeploymentPlan_Id\": 142076, \"Original_DeploymentPlanActionGroup_Sequence\": 0}," +
			"  { \"ExecutionStarted_Date\": \"2015-04-15T04:06:57.7130000\", \"ExecutionEnded_Date\": \"2015-04-15T04:07:03.9500000\", \"ExecutionStatus_Name\": \"Succeeded\", \"ExecutionAction_Description\": \"Deploy JenkinsBuild Artifact\\r\\nto $CurrentDirectory\", \"Original_Action_Sequence\": 1, \"BuildExecution_ActionGroupAction_Id\": 1706458, \"Original_Server_Name\": \"UAT dwdcl-mwas11 tomcat\", \"Attempt_Sequence\": 1, \"WarningLogEntry_Indicator\": \"N\", \"BuildExecution_DeploymentPlan_Id\": 142077, \"Original_DeploymentPlanActionGroup_Sequence\": 1}," +
			"  { \"ExecutionStarted_Date\": \"2015-04-15T04:07:04.1300000\", \"ExecutionEnded_Date\": \"2015-04-15T04:07:05.7500000\", \"ExecutionStatus_Name\": \"Succeeded\", \"ExecutionAction_Description\": \"Execute Shell Script\\r\\nExecute \\\"#!/bin/bash\\r\\n\\r\\n# Ensure all variables declared\\r\\nse...\\\"\", \"Original_Action_Sequence\": 2, \"BuildExecution_ActionGroupAction_Id\": 1706461, \"Original_Server_Name\": \"UAT dwdcl-mwas11 tomcat\", \"Attempt_Sequence\": 1, \"WarningLogEntry_Indicator\": \"N\", \"BuildExecution_DeploymentPlan_Id\": 142077, \"Original_DeploymentPlanActionGroup_Sequence\": 1}" +
			"]," +
			"\"BuildExecution_ActionGroupActionLogEntries\": " +
			"[" +
			"  { \"LogEntry_Sequence\": 1, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Initializing server 42...\", \"BuildExecution_ActionGroupAction_Id\": 1706457}," +
			"  { \"LogEntry_Sequence\": 2, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Server UAT dwdcl-mwas11 tomcat (ID: 42) initialized.\", \"BuildExecution_ActionGroupAction_Id\": 1706457}," +
			"  { \"LogEntry_Sequence\": 1, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Source and target directory are the same; splitting...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 2, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"AgentBasedAction initialization complete:\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 3, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Temp Directory: /usr/share/tomcat/BuildMaster/_A36/_S2070/TMP\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 4, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Source Directory: /usr/share/tomcat/BuildMaster/_A36/_S2070/SRC\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 5, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Target Directory: /usr/share/tomcat/BuildMaster/_A36/_S2070/WRK\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 6, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Server: UAT dwdcl-mwas11 tomcat (Id: 42)\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 7, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Initializing action...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 8, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Initialization complete. Executing...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 9, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Attempting to get artifact associated with deployable ID: 2070\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 10, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Artifact for action group\u0027s deployable found at: C:\\\\BuildMaster\\\\Artifacts\\\\36\\\\1.3\\\\26\\\\2070\\\\JenkinsBuild.zip\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 11, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Loading file from C:\\\\BuildMaster\\\\Artifacts\\\\36\\\\1.3\\\\26\\\\2070\\\\JenkinsBuild.zip...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 12, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Getting list of files in target path...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 13, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Getting list of files in artifact...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 14, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Determining which files need to be deployed...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 15, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"3 files in artifact; 3 files have changed\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 16, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Deploying files...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 17, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"PaxHoldRelease-1.2.217.war\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 18, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"prd_persistence.xml\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 19, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"uat_persistence.xml\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 20, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Extracting artifact zip file...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 21, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Cleaning up...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 22, \"LogEntry_Level\": 10, \"LogEntry_Text\": \"Artifact deployed.\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 23, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Execution complete. Finalizing...\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 24, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Target directory used. Abandoning source.\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 25, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Finalization complete.\", \"BuildExecution_ActionGroupAction_Id\": 1706458}," +
			"  { \"LogEntry_Sequence\": 1, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Source and target directory are the same; splitting...\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 2, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"AgentBasedAction initialization complete:\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 3, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Temp Directory: /usr/share/tomcat/BuildMaster/_A36/_S2070/TMP\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 4, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Source Directory: /usr/share/tomcat/BuildMaster/_A36/_S2070/SRC\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 5, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Target Directory: /usr/share/tomcat/BuildMaster/_A36/_S2070/WRK\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 6, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"  Server: UAT dwdcl-mwas11 tomcat (Id: 42)\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 7, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Initializing action...\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 8, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Initialization complete. Executing...\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 9, \"LogEntry_Level\": 10, \"LogEntry_Text\": \"Tomcat started.\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 10, \"LogEntry_Level\": 10, \"LogEntry_Text\": \"Script returned: 0\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 11, \"LogEntry_Level\": 10, \"LogEntry_Text\": \"Script completed.\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 12, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Execution complete. Finalizing...\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 13, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Target directory unused; joining with source.\", \"BuildExecution_ActionGroupAction_Id\": 1706461}," +
			"  { \"LogEntry_Sequence\": 14, \"LogEntry_Level\": 0, \"LogEntry_Text\": \"Finalization complete.\", \"BuildExecution_ActionGroupAction_Id\": 1706461}" +
			"]," +
			"\"BuildExecution_ActionGroupActionVariableValues\": " +
			"[" +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706458, \"Variable_Name\": \"cause\", \"Value_Text\": \"jenkins test\"}," +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706458, \"Variable_Name\": \"hello\", \"Value_Text\": \"world\"}," +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706458, \"Variable_Name\": \"test\", \"Value_Text\": \"me\"}," +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706458, \"Variable_Name\": \"WEBLINKS\", \"Value_Text\": \"no\"}," +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706461, \"Variable_Name\": \"cause\", \"Value_Text\": \"jenkins test\"}," +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706461, \"Variable_Name\": \"hello\", \"Value_Text\": \"world\"}," +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706461, \"Variable_Name\": \"test\", \"Value_Text\": \"me\"}," +
			"  { \"BuildExecution_ActionGroupAction_Id\": 1706461, \"Variable_Name\": \"WEBLINKS\", \"Value_Text\": \"no\"}" +
			"]" +
			"}";

	public BuildExecution[] BuildExecutions_Extended;
	public BuildExecutionDeploymentPlanActionGroups[] BuildExecution_DeploymentPlanActionGroups;
	public BuildExecutionActionGroupActions[] BuildExecution_ActionGroupActions;
	public BuildExecutionActionGroupActionLogEntries[] BuildExecution_ActionGroupActionLogEntries;
	public BuildExecutionActionGroupActionVariableValues[] BuildExecution_ActionGroupActionVariableValues;
}

