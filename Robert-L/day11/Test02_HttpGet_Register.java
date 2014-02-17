package com.eoe.se2.day11;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.eoe.se2.day10.entity.User;

public class Test02_HttpGet_Register {
	static final String BASE_URL = "http://localhost:8080/Se2_day10_03/Test";

	public static void main(String[] args) {
		HttpClient client = new DefaultHttpClient();
		StringBuilder sb = new StringBuilder(BASE_URL);
		sb.append("?name=").append("李飞").append("&id=").append("1001")
				.append("&password=").append("123456").append("&phone=")
				.append("04726789").append("&email=").append("lifei@qq.com");
		HttpGet get = new HttpGet(sb.toString());
		ObjectInputStream ois = null;
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			ois = new ObjectInputStream(in);
			User user = (User) ois.readObject();
			System.out.println(user);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
