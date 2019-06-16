package com.open.cloud.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leijian
 */
@SpringBootApplication
public class SpringbootApplicationTest {

    public static void main(String[] args) {
        //new SpringApplicationBuilder(SpringbootApplicationTest.class).run(args);
        SpringApplication.run(SpringbootApplicationTest.class,args);
    }
}