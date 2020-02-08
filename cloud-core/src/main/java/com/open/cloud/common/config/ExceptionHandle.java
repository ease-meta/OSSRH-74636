package com.open.cloud.common.config;

import com.open.cloud.common.base.Response;
import com.open.cloud.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle extends BaseController<Void> {


	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public Response<Void> MethodArgumentNotValidException(Exception e) {
		log.error("异常信息:", e);
		return response(401, "参数异常[" + ExceptionUtils.getMessage(e) + "]");
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	public Response<Void> HttpMessageNotReadableException(Exception e) {
		log.error("异常信息:", e);
		return response(401, "参数异常[" + ExceptionUtils.getMessage(e) + "]");
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@ResponseBody
	public Response<Void> IllegalArgumentException(Exception e) {
		log.error("异常信息:", e);
		return response(401, "参数异常[" + ExceptionUtils.getMessage(e) + "]");
	}

	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public Response<Void> BusinessException(Exception e) {
		log.error("异常信息:", e);
		return response(666, "[" + ExceptionUtils.getMessage(e) + "]");
	}


	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Response<Void> handleException(Exception e) {
		log.error("异常信息:", e);
		return response(999, "系统异常[" + ExceptionUtils.getMessage(e) + "]");
	}
}