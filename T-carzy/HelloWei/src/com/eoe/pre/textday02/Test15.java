package com.eoe.pre.textday02;
/**
 * 示例：int转换为byte类型出现的数据位变符号位的现象。
 */
public class Test15 {
	public static void main(String[] args){
		int i=129;
		byte b=(byte) i;
		System.out.println(Integer.toBinaryString(i)+"   "+i);
		System.out.println(Integer.toBinaryString(b)+"   "+b);
	}

}
