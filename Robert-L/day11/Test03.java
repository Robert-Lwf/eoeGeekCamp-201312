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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * 本例使用HttpClient完成向ServletSe02Day10_03/RegisterByPostMethod 发送注册用户信息，
 * 由ServletSe02Day10_03/RegisterByPostMethod将注册用户信息保存至 E:/Se2/day09/user2.txt
 * 
 * 
 */
public class Test03 {
	private static final String BASE_URL = "http://localhost:8080/Se2_day09/Test01";

	public static void main(String[] args) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(BASE_URL);
		try {
			ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("name", "刘亦菲"));
			params.add(new BasicNameValuePair("age", "" + 30));
			HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.getConnectionManager();
			}
		}
	}
}
