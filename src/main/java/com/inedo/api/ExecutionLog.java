package com.inedo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ExecutionLog
{
	public static final String EXAMPLE = "";

	public BuildExecution_ActionGroupActionLogEntries[] BuildExecution_ActionGroupActionLogEntries;
	
	public class BuildExecution_ActionGroupActionLogEntries {
		String LogEntry_Text;		
	}
}

