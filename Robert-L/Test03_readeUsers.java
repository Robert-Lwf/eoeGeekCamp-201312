package se2.day08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import se2.day08.entity.User;

public class Test03_readeUsers {
	public static void main(String[] args) {
		User[] users;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(
					"E:/java_test/src/user.dat"));
			users = (User[]) ois.readObject();
			for (User user : users) {
				System.out.println(user);
			}
		} catch (FileNotFoundException e) {
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
