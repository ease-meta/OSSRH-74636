package com.open.cloud.springcloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloud-boot-springcloud-provider")
public interface HiClient {

    @GetMapping("/hi")
    String hi();

}