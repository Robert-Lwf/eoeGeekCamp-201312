package com.eoe.se2.day11;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Test04 {
	static final String BASE_URL = "http://localhost:8080/Se2_day10_03/Test";

	public static void main(String[] args) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(BASE_URL);
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("id", "1002"));
		params.add(new BasicNameValuePair("name", "李伟"));
		params.add(new BasicNameValuePair("password", "123456"));
		params.add(new BasicNameValuePair("phone", "04729876"));
		params.add(new BasicNameValuePair("email", "liwei@qq=.com"));
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
					"utf-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200)
				;
			InputStream in = response.getEntity().getContent();
			byte[] data = new byte[100];
			int len = in.read(data);
			String msg = new String(data, "utf-8");
			System.out.println(msg);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(client!=null){
				client.getConnectionManager().shutdown();
			}
		}

	}
}
