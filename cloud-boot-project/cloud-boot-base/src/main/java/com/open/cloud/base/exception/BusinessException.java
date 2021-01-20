/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.base.exception;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author leijian
 */
public class BusinessException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1905122041950251207L;
	private String code;
	private transient Object[] args;

	public BusinessException(final String code, Throwable cause) {
		super(code, cause);
	}

	public BusinessException(final String code) {
		this.code = code;
	}

	public BusinessException(final String code, final Object[] args) {
		this.code = code;
		this.args = args;
	}

	@Override
	public String getMessage() {
		String message = null;
		if (!StringUtils.isEmpty(code)) {
			//todo 国际化
			// message = MessageUtils.message(code, args);
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
