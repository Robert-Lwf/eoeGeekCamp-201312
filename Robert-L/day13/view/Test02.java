package com.eoe.se2.day13.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 本类演示获取类的所有构造器，并打印构造器的访问权限和方法签名
 * 
 * @author 1x2h33x
 * 
 */
public class Test02 {
	static final String PACKAGE = "com.eoe.se2.day13.view.View";

	public static void main(String[] args) throws Exception {
		// 加载字节至内存
		Class clazz = Class.forName(PACKAGE);
		// 获取本类的所有构造方法，共三个
		Constructor[] con = clazz.getConstructors();
		// 遍历并显示构造方法签名
		for (Constructor c : con) {
			// 获取访问修饰符
			int m = c.getModifiers();
			String permiss = Modifier.toString(m);
			// 获取构造方法名，去掉包名
			StringBuilder constructorName = new StringBuilder(c.getName());
			constructorName.delete(0, constructorName.lastIndexOf(".") + 1);
			// 获取构造器的所有参数
			Class[] parames = c.getParameterTypes();
			// 变量p用于拼接参数，格式如：（String arg0,String agr1）
			StringBuilder p = new StringBuilder("(");
			// 拼接参数列表
			for (int i = 0; i < parames.length; i++) {
				// 获取一个参数的类型
				StringBuilder param = new StringBuilder(parames[i].getName());
				param.delete(0, param.lastIndexOf(".") + 1);
				p.append(param + " arg" + i + ",");// arg:参数名
			}
			// 若不是无参构造器，则删除最后一个多余的逗号
			if (p.length() > 1) {
				p.deleteCharAt(p.length() - 1);
			}
			p.append(")");
			// 显示当前构造方法，包括访问权限和方法签名
			System.out.println(permiss + " " + constructorName + p);
		}

	}

}
