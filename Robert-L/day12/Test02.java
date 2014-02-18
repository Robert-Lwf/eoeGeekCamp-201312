package com.eoe.se2.day12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.eoe.se2.day10.entity.User;

public class Test02 {
	static final String SRC_PATH = "E:/java_test/src/user/";
	static final String FILENAME = "users2.xml";

	public static void main(String[] args) {
		FileInputStream fis = null;
		ArrayList<User> users = new ArrayList<>();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			fis = new FileInputStream(SRC_PATH + FILENAME);
			parser.setInput(fis, "utf-8");
			for (int type = XmlPullParser.START_DOCUMENT; type != XmlPullParser.END_DOCUMENT; type = parser
					.next()) {
				if (type == XmlPullParser.START_TAG) {
					if ("user".equals(parser.getName())) {
						User user = new User();
						for (int i = 0; i < parser.getAttributeCount(); i++) {
							String attrName = parser.getAttributeName(i);
							String attrValue = parser.getAttributeValue(i);
							if ("id".equals(attrName)) {
								user.setId(Integer.parseInt(attrValue));

							} else if ("name".equals(attrName)) {
								user.setName(attrValue);

							} else if ("password".equals(attrName)) {
								user.setPassword(attrValue);

							} else if ("phone".equals(attrName)) {
								user.setPhone(attrValue);
							} else if ("email".equals(attrName)) {
								user.setEmail(attrValue);
							}
						}
						users.add(user);
					}
				}
			}
			for (User user : users) {
				System.out.println(user);
			}

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
