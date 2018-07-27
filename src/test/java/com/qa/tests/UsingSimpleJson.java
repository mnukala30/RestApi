package com.qa.tests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsingSimpleJson extends TestBase{
	
	TestBase testBase;	
	RestClient restClient;
	CloseableHttpResponse httpresponse;
	String serviceURL;
	String APIUrl;
	String url;
	String PutUrl;
	String DeleteURL;
	
	@BeforeMethod
	public void setup(){
		
		testBase = new TestBase();
		serviceURL=prop.getProperty("ServiceURL");
		APIUrl=prop.getProperty("APIURL");
		url=serviceURL+APIUrl;
		PutUrl=serviceURL+prop.getProperty("PutAPIURL");
		DeleteURL=serviceURL+prop.getProperty("DeleteURL");
	}
	
	@Test(enabled=false)
	public void postAPI(){
		
		restClient= new RestClient();
		Response response=restClient.post(url);
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);
		String value=response.getHeader("cf-ray");
		if(value.contains("-SIN")){
			System.out.println("It is posted successfully");
		}
		
	}
	
	@Test(enabled=false)
	public void putAPI(){
		restClient= new RestClient();
		Response response=restClient.put(PutUrl);
		System.out.println("Status code is--->"+ response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), RESPONSE_STATUS_CODE_201);
	}
	
	@Test
	public void deleteAPI(){
		
		RequestSpecification request=RestAssured.given();
		Response respose=request.delete(DeleteURL);
		int statusCode=respose.getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204);
		
	}

}
