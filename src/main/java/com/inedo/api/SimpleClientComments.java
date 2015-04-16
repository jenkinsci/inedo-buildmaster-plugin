package com.inedo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SimpleClientComments
{
//	public int postId;
//	public int id;
//	public String name;
	public String email;
	public String body;
}