package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1.Get Method without Headers
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient	=HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);
		CloseableHttpResponse httpResponse= httpClient.execute(httpget);
		
		return httpResponse;
	}
	
	//2.Get Method with Headers
	
	public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient	=HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);
		for(Map.Entry<String, String> entry:headerMap.entrySet()){
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpResponse= httpClient.execute(httpget);
		
		return httpResponse;
	}
	
	//3. Post Method With Headers
	
	public CloseableHttpResponse post(String url, String entity,HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient	=HttpClients.createDefault();
		HttpPost httppost= new HttpPost(url);
		httppost.setEntity(new StringEntity(entity));
		for(Map.Entry<String, String> entry:headerMap.entrySet()){
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpResponse=httpClient.execute(httppost);
		return httpResponse;
	}
}
