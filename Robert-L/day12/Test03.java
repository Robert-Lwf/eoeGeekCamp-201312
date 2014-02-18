package com.eoe.se2.day12;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.WritePendingException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;
public class Test03 {
	static final String SRC_PATH = "E:/java_test/src/user/";
	static final String FILENAME = "users3.xml";

	public static void main(String[] args) {
		ArrayList<User> users = initUsers();
		PrintWriter writer = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlSerializer serializer = factory.newSerializer();
			writer = new PrintWriter(SRC_PATH + FILENAME);
			serializer.setOutput(writer);
			serializer.startDocument("utf-8", null);
			serializer.text("\n");
			serializer.startTag(null, "users");
			for (User user2 : users) {
				serializer.text("\n");
				serializer.startTag(null, "user");
				serializer.attribute(null, "id", user2.getId() + "");

				serializer.startTag(null, "name");
				serializer.text(user2.getName());
				serializer.endTag(null, "name");

				serializer.startTag(null, "password");
				serializer.text(user2.getPassword());
				serializer.endTag(null, "password");

				serializer.startTag(null, "phone");
				serializer.text(user2.getPhone());
				serializer.endTag(null, "phone");

				serializer.startTag(null, "email");
				serializer.text(user2.getEmail());
				serializer.endTag(null, "email");

				serializer.endTag(null, "user");

			}
			serializer.endTag(null, "users");
			serializer.endDocument();
			System.out.println(FILENAME + "生成完毕");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}

	private static ArrayList<User> initUsers() {
		ArrayList<User> list = new ArrayList<>();
		User user = new User(1001, "张飞", "1234", "68337755", "zf@qq.com");
		list.add(user);
		user = new User(1002, "王飞", "1234", "68337755", "zf@qq.com");
		list.add(user);
		user = new User(1003, "李飞", "1234", "68337755", "zf@qq.com");
		list.add(user);
		user = new User(1004, "陈飞", "1234", "68337755", "zf@qq.com");
		list.add(user);
		user = new User(1005, "黄飞", "1234", "68337755", "zf@qq.com");
		list.add(user);
		return list;

	}
}
