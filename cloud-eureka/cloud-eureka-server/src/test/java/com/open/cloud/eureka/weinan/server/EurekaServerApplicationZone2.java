package com.open.cloud.eureka.weinan.server;

import com.open.cloud.framework.ComponentScanFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan(useDefaultFilters = false, excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = {ComponentScanFilter.class})})
public class EurekaServerApplicationZone2 {
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "weinan");
		SpringApplication.run(EurekaServerApplicationZone2.class, args);
	}
}
