package com.open.cloud.common.base;

import com.open.cloud.common.utils.MessageUtils;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Leijian
 * @date 2020/2/8
 */
public class ResponseTool<T> {

	public static <T> Response<T> response(int code, String message) {
		return Response.<T>builder().ret(extracted(code, message)).build();
	}

	public static  <T> Response<T> response(int code, String message, T object) {
		return Response.<T>builder().ret(extracted(code, message)).result(object).build();
	}

	public static  <T> Response<T> success(T object) {
		return Response.<T>builder().ret(extracted(200, "S")).result(object).build();
	}

	public static <T> Response<T> success() {
		return Response.<T>builder().ret(extracted(200, "S")).build();
	}

	public static <T> Response<T> response(int code) {
		return Response.<T>builder().ret(extracted(code)).build();
	}

	public static  <T> Response<T> response(int code, T object) {
		return Response.<T>builder().ret(extracted(code)).result(object).build();
	}

	private static  Ret extracted(int code) {
		return Ret.builder().retCode(code).retTime(LocalDateTime.now()).build();
	}

	private static  Ret extracted(int code, String message) {
		return Ret.builder().retCode(code).retMsg(message).retTime(LocalDateTime.now()).build();
	}
}
