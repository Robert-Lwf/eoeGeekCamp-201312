package com.eoe.se2.day11;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Test01_HttpGet {
	static final String URL = "http://localhost:8080/Se2_day09/Test01";

	public static void main(String[] args) {
		// 创建客户端
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(get);
			// 打印服务端响应码
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
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
