package com.open.cloud.springcloud.provider.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Value("${server.port}")
    private String zoneName;
    
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi() {
        return zoneName;
    }
}