package se2.day08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import se2.day08.entity.User;

/*
 * 从HFS服务端下载user.txt文件，以对象序列化的方式保存至本地磁盘。
 */
public class Test03 {
	public static void main(String[] args) {
		User[] users = new User[0];
		ObjectOutputStream oos = null;
		BufferedReader reader = null;
		try {
			URL url = new URL("http://127.0.0.1/user.txt");
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			oos = new ObjectOutputStream(new FileOutputStream(
					"E:/java_test/src/user.dat"));
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				User user = parse(line);
				users=Arrays.copyOf(users, users.length+1);
				users[users.length-1]=user;
				
			}
			//对象数组可以序列化保存
			oos.writeObject(users);
			System.out.println("下载完成");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static User parse(String line) {
		String[] data = line.split(":");
		User user = new User();
		user.setId(Integer.parseInt(data[0]));
		user.setName(data[1]);
		user.setPassword(data[2]);
		user.setPhone(data[3]);
		user.setEmail(data[4]);
		return user;
	}
}
