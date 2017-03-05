package com.inedo.buildmaster.domain;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class ApiVariable {
	public static String getExampleArray() throws IOException {
		return IOUtils.toString(Application.class.getResourceAsStream("ApiVariables.json")).replace(IOUtils.LINE_SEPARATOR_WINDOWS, IOUtils.LINE_SEPARATOR_UNIX);
	}

//	public static String getExampleSingle() throws IOException {
//		return IOUtils.toString(Application.class.getResourceAsStream("ApiVariable.json")).replace(IOUtils.LINE_SEPARATOR_WINDOWS, IOUtils.LINE_SEPARATOR_UNIX);
//	}
	
	public String name;
	public String value = null;
	public Boolean sensitive = false;
}
