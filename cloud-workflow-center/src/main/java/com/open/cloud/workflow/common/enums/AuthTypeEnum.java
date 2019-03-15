package com.open.cloud.workflow.common.enums;

import com.open.cloud.workflow.common.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AuthTypeEnum {

	/**
	 * 需要登录
	 */
	LOGIN(1),
	/**
	 * 开放,无需鉴权
	 */
	OPEN(2),
	/**
	 * 需要鉴定是否包含权限
	 */
	AUTH(3);

	private final int value;

	AuthTypeEnum(final int value) {
		this.value = value;
	}

	@JsonValue
	public int getValue() {
		return this.value;
	}

	@JsonCreator
	public static AuthTypeEnum getEnum(int value) {
		for (AuthTypeEnum menuTypeEnum : AuthTypeEnum.values()) {
			if (menuTypeEnum.getValue() == value) {
				return menuTypeEnum;
			}
		}
		throw new BusinessException("Error: Invalid AuthTypeEnum type value: " + value);
	}
}
