package com.open.cloud.mybatis.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/25 21:14
 */
@SpringBootApplication(scanBasePackages = "com.open.cloud.mybatis")
@MapperScan(value = "com.open.cloud.mybatis")
public class MybatisSpringBootApplication {

    public static void main(String[] args) {
        new SpringApplication(MybatisSpringBootApplication.class).run(args);
    }
}
