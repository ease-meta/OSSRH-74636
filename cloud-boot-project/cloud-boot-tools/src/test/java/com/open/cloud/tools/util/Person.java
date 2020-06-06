package com.open.cloud.tools.util;

import lombok.Data;

/**
 * @author Leijian
 * @date 2020/6/3
 */
@Data
public class Person extends BasePo{


	private Body body;

	@Data
	public static  class  Body{

		private int age;

		private boolean sex;

		private String acctName;
	}
}
