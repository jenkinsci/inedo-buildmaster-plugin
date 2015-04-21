package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecutionActionGroupActionLogEntries
{
	public int LogEntry_Sequence;
	public int LogEntry_Level;
	public String LogEntry_Text;
}

