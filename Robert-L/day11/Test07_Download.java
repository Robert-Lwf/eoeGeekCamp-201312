package com.eoe.se2.day11;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Test07_Download {
	static final String BASE_URL = "http://127.0.0.1/httpcomponents-client-4.3.2-bin.zip";
	static final String FILENAME = "httpcomponents-client-4.3.2-bin.zip";

	public static void main(String[] args) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(BASE_URL);
		FileOutputStream fos = null;
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				fos = new FileOutputStream("E:/java_test/dest/" + FILENAME);
				InputStream in = response.getEntity().getContent();
				byte[] buffer = new byte[1024 * 10];
				int len;
				System.out.println(FILENAME + "开始下载");
				while ((len = in.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				System.out.println(FILENAME + "下载结束");

			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}

	}
}
