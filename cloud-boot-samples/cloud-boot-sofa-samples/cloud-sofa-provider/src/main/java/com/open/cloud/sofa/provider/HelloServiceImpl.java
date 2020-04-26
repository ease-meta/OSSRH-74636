package com.open.cloud.sofa.provider;

import com.open.cloud.sofa.api.HelloService;

/**
 * Quick Start demo implement
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String string) {
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }
}