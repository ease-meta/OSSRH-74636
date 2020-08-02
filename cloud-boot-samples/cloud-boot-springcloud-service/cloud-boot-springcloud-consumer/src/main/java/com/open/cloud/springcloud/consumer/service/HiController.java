package com.open.cloud.springcloud.consumer.service;

import com.open.cloud.springcloud.consumer.SpringCloudConsumerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class HiController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    SpringCloudConsumerApplication.Client client;

    @RequestMapping(value="/consumer")
    public String hi() {
        return restTemplate.getForObject("http://cloud-boot-springcloud-provider/hi", String.class);
    }

    @RequestMapping(value="/openfeign")
    public String openfeign() {
        return client.hi();
    }

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/webclient")
    public Mono<String> test() {
        Mono<String> result = webClientBuilder.build()
                .get()
                .uri("http://cloud-boot-springcloud-provider/hi")
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }
}