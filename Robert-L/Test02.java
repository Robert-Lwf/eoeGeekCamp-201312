package se2.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test02 {
	public static void main(String[] args) {
		PrintWriter writer = null;
		BufferedReader reader = null;
		try {
			URL url = new URL("http://127.0.0.1/user.txt");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			writer = new PrintWriter("E:/java_test/src/user.txt");
			reader = new BufferedReader(new InputStreamReader(in, "gbk"));
			String line;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
				writer.flush();
			}
			System.out.println("文件完成下载");
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
			if (writer != null) {
				writer.close();
			}
		}
	}
}
