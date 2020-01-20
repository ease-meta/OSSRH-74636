package com.open.cloud.common.base;

import lombok.Data;

/**
 * @author Leijian
 */
@Data
public class Ref<T> {
	private volatile T data;

	public Ref(T data) {
		this.data = data;
	}

	public boolean isNull() {
		return data == null;
	}
}