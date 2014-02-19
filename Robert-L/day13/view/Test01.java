package com.eoe.se2.day13.view;

import java.lang.reflect.Constructor;

/**
 * 本例演示获取本类 所有构造的方法
 * 
 * @author 1x2h33x
 * 
 */
public class Test01 {
	// View类的完整包名
	static final String PACKAGE = "com.eoe.se2.day13.view.View";

	public static void main(String[] args) {
		try {
			// 加载指定类的字节码至内存
			Class clazz = Class.forName(PACKAGE);
			// 获取本类的所有构造方法，共三个
			Constructor[] constructors = clazz.getDeclaredConstructors();
			// 遍历并显示构造方法签名
			for (Constructor c : constructors) {
				System.out.println(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
