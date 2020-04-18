
package com.open.cloud.dubbo.consumer;

import com.open.cloud.business.api.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dubbo Spring Cloud Consumer Bootstrap.
 */
@RestController
@RequestMapping(value = "${spring.application.name}", produces = MediaType.APPLICATION_JSON_VALUE)
public class DubboConsumerController {

	@Reference
	private DemoService demoService;

	@PostMapping(value = "/dubboconsumercontroller/sayHello")
	public String sayHello(String message) {
		return demoService.sayHello(message);
	}
}
