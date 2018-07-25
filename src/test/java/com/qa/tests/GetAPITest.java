package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{

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
	
	@Test(priority=1)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{

		restClient= new RestClient();
		HashMap<String,String> headersMap=new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");
		//headersMap.put("UserName", "testuser@amazon.com");
		//headersMap.put("password", "Welcome1234");
		//headersMap.put("Auth Token", "5674563");
		
		
		httpresponse=restClient.get(url,headersMap);
		int statuscode= httpresponse.getStatusLine().getStatusCode();
		System.out.println("statuscode is---->"+ statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		String response=EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		JSONObject responsejson= new JSONObject(response);
		System.out.println("Rsponse JSON is --->"+ responsejson);
		
		String PerPageValue=TestUtil.getValueByJPath(responsejson, "/per_page");
		System.out.println("The value of per page is: "+ PerPageValue);
		Assert.assertEquals(Integer.parseInt(PerPageValue),3);
		
		
		String totalValue=TestUtil.getValueByJPath(responsejson, "/total");
		System.out.println("The value of total is: "+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12);
		
		String lastName=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
		String firstName=TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		Header[] allHeaders=httpresponse.getAllHeaders();
		HashMap<String,String> headers= new HashMap<String,String>();
		
		for(Header header:allHeaders){
			headers.put(header.getName(), header.getValue());
		}
		System.out.println("Headers in the array are -->"+ headers);
		
	}
	
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException{

		restClient= new RestClient();
		httpresponse=restClient.get(url);
		int statuscode= httpresponse.getStatusLine().getStatusCode();
		System.out.println("statuscode is---->"+ statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		String response=EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		JSONObject responsejson= new JSONObject(response);
		System.out.println("Rsponse JSON is --->"+ responsejson);
		
		String PerPageValue=TestUtil.getValueByJPath(responsejson, "/per_page");
		System.out.println("The value of per page is: "+ PerPageValue);
		Assert.assertEquals(Integer.parseInt(PerPageValue),3);
		
		
		String totalValue=TestUtil.getValueByJPath(responsejson, "/total");
		System.out.println("The value of total is: "+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12);
		
		String lastName=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
		String firstName=TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		Header[] allHeaders=httpresponse.getAllHeaders();
		HashMap<String,String> headers= new HashMap<String,String>();
		
		for(Header header:allHeaders){
			headers.put(header.getName(), header.getValue());
		}
		System.out.println("Headers in the array are -->"+ headers);
		
	}
}
