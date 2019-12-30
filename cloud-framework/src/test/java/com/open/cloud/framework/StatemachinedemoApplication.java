package com.open.cloud.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StatemachinedemoApplication {

	@Bean
	public StartupRunner startupRunner() {
		return new StartupRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(StatemachinedemoApplication.class, args);
	}

}