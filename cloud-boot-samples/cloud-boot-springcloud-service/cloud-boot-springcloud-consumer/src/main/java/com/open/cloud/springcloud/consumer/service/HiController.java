package com.open.cloud.springcloud.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.open.cloud.springcloud.consumer.SpringCloudConsumerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HiController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/consumer")
	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String hi() {
		return restTemplate.getForObject("http://cloud-boot-springcloud-provider/hi", String.class);
	}

	public String addServiceFallback() {
		return "error";
	}

	/*@Autowired
	private WebClient.Builder webClientBuilder;

	@GetMapping("/webclient")
	public Mono<String> test() {
		Mono<String> result = webClientBuilder.build()
				.get()
				.uri("http://cloud-boot-springcloud-provider/hi")
				.retrieve()
				.bodyToMono(String.class);
		return result;
	}*/

	@Autowired
	private HiClient hiClient;

	/*@GetMapping("/feign")
	public Flux<String> feign() {
		Flux<String> result = Flux.just(hiClient.hi());
		return result;
	}*/

	@RequestMapping(value = "/openfeign")
	public String openfeign() {
		return hiClient.hi();
	}
}