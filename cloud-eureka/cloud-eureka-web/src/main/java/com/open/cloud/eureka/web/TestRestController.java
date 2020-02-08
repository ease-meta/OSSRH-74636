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
package com.open.cloud.eureka.web;

import com.open.cloud.eureka.web.feign.FeignClientClient;
import com.open.cloud.eureka.web.restTemplate.RestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class TestRestController {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private RestTemplateClient restTemplateClient;

	@Autowired
	FeignClientClient feignClientClient;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@GetMapping(value = "/sayHello")
	public String queryPayOrder(@RequestParam String params) {
		if ("rest".equalsIgnoreCase(params)) {
			return restTemplateClient.sayHello(params);
		} else if ("feign".equalsIgnoreCase(params)) {
			return feignClientClient.sayHello(params);
		} else {
			Mono<String> result = webClientBuilder.build()
							.get()
							.uri("http://CLOUD-EUREKA-SERVICE/sayHello?message="+params)
							.retrieve()
							.bodyToMono(String.class);
			return result.block();
		}

	}
}
