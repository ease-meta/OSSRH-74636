package com.moc.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@GetMapping("/test111")
	public String hello() {
		return "hello";
	}

	 
}
