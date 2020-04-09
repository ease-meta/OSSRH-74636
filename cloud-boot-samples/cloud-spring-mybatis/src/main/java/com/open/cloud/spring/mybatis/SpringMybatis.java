package com.open.cloud.spring.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Leijian
 * @date 2020/4/4
 */
@SpringBootApplication
@MapperScan({"com.open.cloud.spring.mybatis.dao"})
public class SpringMybatis {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatis.class,args);
	}
}
