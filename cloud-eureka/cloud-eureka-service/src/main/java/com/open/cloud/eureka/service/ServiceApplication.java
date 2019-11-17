package com.open.cloud.eureka.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ServiceApplication.class).run(args);
	}

}