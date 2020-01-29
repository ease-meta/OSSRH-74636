package com.open.cloud.common.utils;

import java.lang.reflect.Array;

/**
 * @author Leijian
 */
public abstract class ObjectUtils {

	public static <T> T[] of(T... values) {
		return values;
	}

	public static <T> T[] emptyArray(Class<T> componentType) {
		return (T[]) Array.newInstance(componentType, 0);
	}
}