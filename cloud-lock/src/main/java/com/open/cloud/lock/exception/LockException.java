package com.open.cloud.lock.exception;

import com.open.cloud.common.exception.BaseException;

/**
 * @author Leijian
 * @date 2020/2/19
 */
public class LockException extends BaseException {
	public LockException(Throwable cause) {
		super(cause);
	}

	public LockException(String code, Throwable cause) {
		super(code, cause);
	}

	public LockException(String code) {
		super(code);
	}

	public LockException(String code, Object[] args) {
		super(code, args);
	}
}
