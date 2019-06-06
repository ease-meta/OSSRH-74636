package com.open.cloud.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author leijian
 */
@SpringBootApplication
public class SpringbootApplicationTest {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringbootApplicationTest.class).run(args);
    }

}