package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Variable
{
	//TODO Should be in file
	public static final String EXAMPLE =
			"[" +
			"  { \"Variable_Name\": \"cause\", \"Value_Text\": \"unit test\", \"Scope_Code\": \"B\", \"Sensitive_Indicator\": \"N\"}," +
			"  { \"Variable_Name\": \"COUNTRY\", \"Value_Text\": \"NZ\", \"Scope_Code\": \"S\", \"Sensitive_Indicator\": \"N\"}," +
			"  { \"Variable_Name\": \"hello\", \"Value_Text\": \"world\", \"Scope_Code\": \"B\", \"Sensitive_Indicator\": \"N\"}," +
			"  { \"Variable_Name\": \"test\", \"Value_Text\": \"me\", \"Scope_Code\": \"B\", \"Sensitive_Indicator\": \"N\"}," +
			"  { \"Variable_Name\": \"WEBLINKS\", \"Value_Text\": \"no\", \"Scope_Code\": \"S\", \"Sensitive_Indicator\": \"N\"}" +
			"]";
			
	public String Variable_Name;
	public String Value_Text;
	public String Scope_Code;
	public String Sensitive_Indicator;
}

