package com.eoe.se2.day11;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 本例使用HttpClient完成向ServletSe02Day10_03/RegisterByPostMethod 
 * 发送注册用户信息，由ServletSe02Day10_03/RegisterByPostMethod
 * 将注册用户信息保存至 d:/se2/day09/user2.txt
 *
 */
public class Test05 {
private static final String BASE_URL="http://localhost:8080/Se2_day10_03/Test";
public static void main(String[] args) {
	ArrayList<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
	params.add(new BasicNameValuePair("id", "1111"));
	params.add(new BasicNameValuePair("name", "汪峰"));
	params.add(new BasicNameValuePair("password", "123456"));
	params.add(new BasicNameValuePair("phone","04721234"));
	params.add(new BasicNameValuePair("email", "wangfeng@qq.com"));
	HttpClient client=new DefaultHttpClient();
	HttpUriRequest request=new HttpPost(BASE_URL);
	try {
		UrlEncodedFormEntity reqEntity=new UrlEncodedFormEntity(params,HTTP.UTF_8);
		((HttpPost)request).setEntity(reqEntity);
		HttpResponse response=client.execute(request);
		if(response.getStatusLine().getStatusCode()==200){
			HttpEntity entity=response.getEntity();
			String message=EntityUtils.toString(entity,"utf-8");
			System.out.println(message);
		}else{
			System.out.println("connect failure!");
		}
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
