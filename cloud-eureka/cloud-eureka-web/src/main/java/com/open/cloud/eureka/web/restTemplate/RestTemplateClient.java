package com.open.cloud.eureka.web.restTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateClient {

	@Autowired
	RestTemplate restTemplate;

	//@HystrixCommand(fallbackMethod = "sayHelloFallback")
	public String sayHello(String message) {
		return restTemplate.getForEntity("http://CLOUD-EUREKA-SERVICE/sayHello?message=" + message, String.class).getBody();
	}

	public String sayHelloFallback(String message) {
		return "error";
	}

}
