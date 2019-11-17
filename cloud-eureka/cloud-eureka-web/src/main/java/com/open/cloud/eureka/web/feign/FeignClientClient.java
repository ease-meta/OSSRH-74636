package com.open.cloud.eureka.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("CLOUD-EUREKA-SERVICE")
public interface FeignClientClient {

	@GetMapping(value = "/sayHello")
	String sayHello(@RequestParam("message") String message);


}