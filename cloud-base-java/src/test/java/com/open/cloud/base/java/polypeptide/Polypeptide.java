package com.open.cloud.base.java.polypeptide;

/**
 * @author Leijian
 * @date 2020/2/4
 */
public class Polypeptide {
	public static void main(String[] args) {
		//向上转型
		SuperClass superClass = new SubClass();
		//
		((SubClass)superClass).sleep();
		SubClass subClass = new SubClass();
	}
}
