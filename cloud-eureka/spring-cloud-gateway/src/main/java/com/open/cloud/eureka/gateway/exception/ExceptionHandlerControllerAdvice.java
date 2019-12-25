package com.open.cloud.eureka.gateway.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.open.cloud.eureka.gateway.common.CodeConfig;
import com.open.cloud.eureka.gateway.common.ResultDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
	private static final Logger log;

	@ExceptionHandler({CommonException.class})
	public ResponseEntity<?> commonExceptionHandler(final CommonException e) {
		e.printStackTrace();
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((Object) new ResultDO().setCode(e.getCode()).setMessage(e.getMessage()));
	}

	@ExceptionHandler({ServerWebInputException.class})
	public ResponseEntity<?> ServerWebInputExceptionHandler(final ServerWebInputException e) {
		e.printStackTrace();
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((Object) new ResultDO().setCode("1016").setMessage(CodeConfig.getMessage("1016")));
	}

	@ExceptionHandler({InvalidFormatException.class})
	public ResponseEntity<?> InvalidFormatExceptionHandler(final InvalidFormatException e) {
		e.printStackTrace();
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((Object) new ResultDO().setCode("1005").setMessage("1005"));
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<?> ExceptionHandler(final Exception e) {
		e.printStackTrace();
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((Object) new ResultDO().setCode("1000").setMessage(CodeConfig.getMessage("1000")));
	}

	static {
		log = LoggerFactory.getLogger((Class) ExceptionHandlerControllerAdvice.class);
	}
}
