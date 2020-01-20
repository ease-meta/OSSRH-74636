package com.open.cloud.common.utils;

import com.open.cloud.common.exception.BaseException;
import org.springframework.boot.convert.ApplicationConversionService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Leijian
 */
public class ConvertUtils {
	public static <T> T convert(Object value, Class<T> type) {
		if (value == null) {
			return null;
		}
		return (T) ApplicationConversionService.getSharedInstance().convert(value, type);
	}

	public static <T> T tryConvert(Object value, Class<T> type) {
		try {
			return convert(value, type);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T deepClone(T obj) {
		try {
			try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
				try (ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
					out.writeObject(obj);
					try (ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray())) {
						ObjectInputStream in = new ObjectInputStream(byteIn);
						return (T) in.readObject();
					}
				}
			}
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
}
