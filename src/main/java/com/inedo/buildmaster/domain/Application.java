package com.inedo.buildmaster.domain;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Application
{
	public int Application_Id;
	public String Application_Name;
	public int Active_Releases_Count;
	public Integer ApplicationGroup_Id;
	public String ApplicationGroup_Name;
	
	public static String getExampleArray() throws IOException {
		return IOUtils.toString(Application.class.getResourceAsStream("Applications.json")).replace(IOUtils.LINE_SEPARATOR_WINDOWS, IOUtils.LINE_SEPARATOR_UNIX);
	}
}