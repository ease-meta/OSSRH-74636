/**
 * <p>Title: EurekaClientApp.java</p>  
 * <p>Description: </p> 
 * @author leijian
 * @date 2019年1月28日
 * @version 1.0
 */
package com.moc.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author leijian
 * @date 2019年1月28日 服务提供商 eureka的客户端程序
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApp {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApp.class, args);
	}

	/**
	 * 使用ribbon负载均衡器，用于服务提供商的负载均衡
	 * 
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}