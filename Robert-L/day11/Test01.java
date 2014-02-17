package com.eoe.se2.day11;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Test01 {
	static final String URI = "http://localhost:8080/Se2_day09/Test01?name=张飞&age=35";

	public static void main(String[] args) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(URI);
		try {
			HttpResponse response = client.execute(get);
			System.out.println(response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}

	}
}
