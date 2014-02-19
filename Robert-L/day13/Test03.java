package com.eoe.se2.day13;

import com.eoe.se2.day13.view.View;

public class Test03 {
	public static void main(String[] args) throws ClassNotFoundException {
		// 第一种方式获取或加载Class对象
		// View.class;
		View view = new View();// 第二种方式：加载或获取view.class至内存
		Class clazz = view.getClass();
		System.out.println(clazz);
		// 第三种方式：
		Class clazz2 = Class.forName("com.eoe.se2.day13.view.View");
		System.out.println(clazz2);
		System.out.println("clazz==clazz2:" + (clazz == clazz2));

	}
}
