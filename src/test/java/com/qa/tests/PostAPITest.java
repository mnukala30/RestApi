package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.testData.Users;

public class PostAPITest extends TestBase {

	TestBase testBase;	
	RestClient restClient;
	CloseableHttpResponse httpresponse;
	String serviceURL;
	String APIUrl;
	String url;
	
	@BeforeMethod
	public void setup(){
		
		testBase = new TestBase();
		serviceURL=prop.getProperty("ServiceURL");
		APIUrl=prop.getProperty("APIURL");
		url=serviceURL+APIUrl;
	}
	
	@Test
	public void postAPIWithHeaders() throws JsonGenerationException, JsonMappingException, IOException{
		
		RestClient restClient= new RestClient();
		HashMap<String,String> headersMap=new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");
		ObjectMapper mapper= new ObjectMapper();
		Users users= new Users("Mahesh","TestLead");
		//Converting JAVA to JSON
		mapper.writeValue(new File("C:\\Users\\Mahesh Nukala\\SandhyaWorkspace\\restapi\\src\\main\\java\\com\\qa\\testData\\users.json"), users);
		
		//Convert Json to String
		
		String usersJsonString=mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		CloseableHttpResponse httpResponse=restClient.post(url, usersJsonString, headersMap);
		int status=httpResponse.getStatusLine().getStatusCode();
		
		Assert.assertEquals(status, RESPONSE_STATUS_CODE_201);
		
		String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		JSONObject jsonObject= new JSONObject(responseString);
		System.out.println("The response from API is-->" + jsonObject);
		
		//Converting JSON to JAVA
		
		Users userObjectResponse=mapper.readValue(responseString, Users.class);
		System.out.println(userObjectResponse);
		
		Assert.assertTrue(users.getName().equals(userObjectResponse.getName()));
		Assert.assertTrue(users.getJob().equals(userObjectResponse.getJob()));
		
		System.out.println(userObjectResponse.getId());
		System.out.println(userObjectResponse.getCreatedAt());
		
	}
}
