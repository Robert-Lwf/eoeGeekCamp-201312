package se2.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * 从HFS服务端下载user.txt文件，并显示该文件内容。
 */
public class Test01 {
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			URL url = new URL("http://127.0.0.1/user.txt");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in, "gbk"));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
