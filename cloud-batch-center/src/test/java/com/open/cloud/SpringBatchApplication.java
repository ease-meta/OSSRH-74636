package com.open.cloud;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/4 21:36
 **/
@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}