package com.open.cloud.springcloud.provider.service;

import lombok.Data;

import java.io.Serializable;

/***
 * @description 客户级限制
 * @version V1.0
 * @author wanghy
 * @update 20160601
 */
@Data
public class Core12000122In {

	/***
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;


	private Body body;

	/**
	 * @return body : return the property body.
	 */
	@Data
	public static class Body implements Serializable {

		private static final long serialVersionUID = 1L;

		private String option;

		private String narrative;

		private String clientNo;

		private String restraintSeqNo;

		private String restraintType;

		private String startDate;

		private String term;

		private String termType;

		private String endDate;

		private String dealClass;

		private String dealType;

		private String maintainType;

	}
}
