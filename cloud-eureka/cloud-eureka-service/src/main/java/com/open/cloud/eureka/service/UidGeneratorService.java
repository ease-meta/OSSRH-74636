package com.open.cloud.eureka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UidGeneratorService {


    @GetMapping("/sayHello")
    public String getLoad(@RequestParam String message) {
        log.info("invoked name = " + message);
        return "你好:"+message;
    }
}
