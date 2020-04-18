package com.leijian.moc;

/**
 * @author Leijian
 * @date 2020/2/24
 */
public class SuperClass {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {
		SubClass1 subClass1 = new SubClass1();
		subClass1.setName("SubClass1");

	}
}
