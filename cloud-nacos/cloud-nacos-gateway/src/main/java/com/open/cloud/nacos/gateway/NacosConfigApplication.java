package com.open.cloud.nacos.gateway;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@EnableDiscoveryClient
public class NacosConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConfigApplication.class, args);
	}

	@Bean
	public DynamicRoutingConfig nacosContextRefresher(
			NacosConfigProperties configProperties) {
		return new DynamicRoutingConfig(configProperties.configServiceInstance());
	}
}
