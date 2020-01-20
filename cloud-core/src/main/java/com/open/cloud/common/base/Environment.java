package com.open.cloud.common.base;

/**
 * @author Leijian
 */
public enum Environment {
	dev("开发"),
	test("测试"),
	prd("生产");

	private String name;

	Environment(String name) {
		this.name = name;
	}
}