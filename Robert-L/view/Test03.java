package com.eoe.se2.day13.view;

import java.lang.reflect.Constructor;

/**
 * 本例演示通过反射调用构造器按以下三种方式创建对象 带所有参数的构造器对象 无参构造器创建对象 带一个参数的构造器创建对象
 */
public class Test03 {
	static final String PACKAGE = "com.eoe.se2.day13.view.View";

	public static void main(String[] args) throws Exception {
		// 加载字节码至内存
		Class clazz = Class.forName(PACKAGE);
		// 调用带完整参数的构造器
		Constructor constructors = clazz.getConstructor(String.class,
				String.class, String.class, String.class, String.class);
		// 创建对象
		Object o = constructors.newInstance("1001", "100", "100", "Horizontal",
				"黑色");
		// 打印对象o.toString(),o是View类型
		System.out.println(o.toString());
		// 调用无参构造器创建对象
		o = clazz.newInstance();
		if (o.getClass() == View.class) {// 若o是View类型
			View v = (View) o;
			v.setId("10001");
			v.setLayout_height("300");
			v.setLayout_width("200");
			v.setOrientation("Viertical");
			v.setBackgroud("红色");
			System.out.println(v);
		}
		// 创建无参构造器的第二种方式
		constructors = clazz.getConstructor();
		View v = (View) constructors.newInstance();
		// 获取访问权限为protected的构造器
		constructors = clazz.getConstructor(String.class);
		// 调用访问权限是protected的带一个参数的构造器
		constructors.setAccessible(true);
		// 设置protected权限的构造器可操作
		o = constructors.newInstance("白色");
		System.out.println(o.toString());
	}
}
