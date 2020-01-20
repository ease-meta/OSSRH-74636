package com.open.cloud.common.exception;

import com.open.cloud.common.utils.MessageUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author leijian
 */
public class BaseException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1905122041950251207L;

	private Throwable source;

	public BaseException(Throwable cause) {
		super(cause);
		this.source = cause;
	}

	public BaseException(final String code, Throwable cause) {
		super(code, cause);
	}

	public BaseException(final String code) {
		this.code = code;
	}

	public BaseException(final String code, final Object[] args) {
		this.code = code;
		this.args = args;
	}

	private String code;

	private transient Object[] args;

	@Override
	public String getMessage() {
		String message = null;
		if (!StringUtils.isEmpty(code)) {
			message = MessageUtils.message(code, args);
		} else {
			message = code;
		}
		return message;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(final Object[] args) {
		this.args = args;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}
}