package com.inedo.buildmaster.domain;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Deployable
{
	public int Deployable_Id;
	public String Deployable_Name;
	public int Dependencies_Count;
	public int Dependants_Count;
	
	// Obsolete?
	public String InclusionType_Code;
	public String Referenced_Release_Number;
	public Integer Referenced_Application_Id;
	
	public static String getExampleArray() throws IOException {
		return IOUtils.toString(Deployable.class.getResourceAsStream("Deployables.json")).replace(IOUtils.LINE_SEPARATOR_WINDOWS, IOUtils.LINE_SEPARATOR_UNIX);
	}
	
	public static String getExampleSingle() throws IOException {
		return getExampleArray();
	}
}

