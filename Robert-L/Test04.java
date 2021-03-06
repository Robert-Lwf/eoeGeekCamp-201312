package se2.day08;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import se2.day08.entity.User;

/*
 * 从HFS下载user.dat，并反序列化该文件。
 */
public class Test04 {
	public static void main(String[] args) {

		ObjectInputStream ois = null;
		try {
			URL url = new URL("http://127.0.0.1/user.dat");
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			ois = new ObjectInputStream(in);
			User[] users = (User[]) ois.readObject();
			for (User user : users) {
				System.out.println(user);
			}
		} catch (MalformedURLException e) {
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