package com.open.cloud.eureka.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping({"/"})
@Slf4j
public class HystrixController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping({"/transfer"})
	public Object transfer() {
		final JSONObject str = (JSONObject) restTemplate.getForObject("http://127.0.0.1:9996/test", JSONObject.class, new Object[0]);
		HystrixController.log.info("降级消息:" + str.toJSONString());
		return str;
	}

	@GetMapping({"/fallback"})
	public String facllbackGet() {
		return "{\"message\":\"降级执行成功\"}";
	}

	@PostMapping({"/fallback"})
	public String facllbackPost() {
		return "{\"message\":\"降级执行成功\"}";
	}

	/*@GetMapping({"/reactor"})
	public Mono<Map<String, String>> reactor() {
		return (Mono<Map<String, String>>) Mono.create(sink -> sink.success(Collections.singletonMap("message", "success")));
	}*/


}
