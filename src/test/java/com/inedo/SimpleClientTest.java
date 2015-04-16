package com.inedo;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.inedo.BuildMasterConfig;
import com.inedo.api.SimpleClient;
import com.inedo.api.SimpleClientBuilder;
import com.inedo.api.SimpleClientComments;

public class SimpleClientTest {
	
	private SimpleClient simple;
	private final boolean MOCK_REQUESTS = false;
	
	@Before
    public void before() {
		if (!MOCK_REQUESTS) {
			simple = SimpleClientBuilder.build();
			return;
		}
		
		
		//simple = spy(SimpleClientBuilder.build());
		simple = mock(SimpleClient.class);
		
		SimpleClientComments[] comments = new SimpleClientComments[1];
		
		SimpleClientComments comment = new SimpleClientComments();
		comment.email = "andy";
		comment.body = "fred";
		comments[0] = comment;
		
//		String [
//		        {
//		            "postId": 1,
//		            "id": 1,
//		            "name": "id labore ex et quam laborum",
//		            "email": "Eliseo@gardner.biz",
//		            "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
//		          },
//		          {
//		            "postId": 1,
//		            "id": 2,
//		            "name": "quo vero reiciendis velit similique earum",
//		            "email": "Jayne_Kuhic@sydney.com",
//		            "body": "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"
//		          }
//		SimpleClientComments[] comm = new ObjectMapper().readValue(jsonString, SimpleClientComments[].class);
        when(simple.getComments("1")).thenReturn(comments);
    }
	
	@Test 
	public void getCommentsRestEasy() {
		// this may work for ntlm authentication
		//http://stackoverflow.com/questions/21330767/do-jersey-client-support-ntlm-proxy
				
		
		//basic HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("user", "superSecretPassword");
		//basic nonPreemptive  HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive().credentials("user", "superSecretPassword").build();
//	 req
//     .pathParameter("version", "2.0")
//     .queryParameter("query", "health");
		
	 
		//SimpleClient 
		SimpleClientComments[] result = simple.getComments("1");


//	 
//		WebTarget target = ClientBuilder.newClient()
//								.target("http://jsonplaceholder.typicode.com")
//								.path("comments")			
//								.queryParam("postId", "1");
//					
//		String result = target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	
			
		System.out.println(result[0].email);
	
			

	}
}
