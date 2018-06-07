package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecutionDetails
{
	public BuildExecution[] BuildExecutions_Extended;
	public BuildExecutionDeploymentPlanActionGroups[] BuildExecution_DeploymentPlanActionGroups;
	public BuildExecutionActionGroupActions[] BuildExecution_ActionGroupActions;
	public BuildExecutionActionGroupActionLogEntries[] BuildExecution_ActionGroupActionLogEntries;
	public BuildExecutionActionGroupActionVariableValues[] BuildExecution_ActionGroupActionVariableValues;
}

