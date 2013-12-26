package com.eoe.basic.day01;

public class Test04 {
	/**
	 * 已知：计算机的基本存储单位是字节(byte)，一个字节有8位。
	8位2进制的最大值=(11111111)2=2的0次方+2的1次方+2的2次方+2的3次方+
	2的4次方+2的5次方+2的6次方+2的7次方
	编写程序，文件名：J06.java，计算并显示8位2进制数的最大值，即将
	(11111111)2转换为10进制数。
	 */
	public static void main(String[] args) {
		System.out.print("(11111111)2=");
		System.out.println(Math.pow(2, 7)+
						   Math.pow(2,6)+
						   Math.pow(2, 5)+
						   Math.pow(2, 4)+
						   Math.pow(2, 3)+
						   Math.pow(2,2)+
						   Math.pow(2, 1)+
						   Math.pow(2, 0));
		//简便计算法
		System.out.println(Math.pow(2, 8)-1);
	}
}
