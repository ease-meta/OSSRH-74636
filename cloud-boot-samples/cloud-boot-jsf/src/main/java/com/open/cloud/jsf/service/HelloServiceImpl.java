package com.open.cloud.jsf.service;

import com.open.cloud.jsf.api.IHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServiceImpl implements IHelloService {

	private static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

	@Override
	public String echoStr(String str) {
		logger.info("server get request : {}", str);
		return str;
	}


}