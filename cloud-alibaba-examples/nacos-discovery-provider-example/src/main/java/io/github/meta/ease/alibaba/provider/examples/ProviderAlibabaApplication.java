package io.github.meta.ease.alibaba.provider.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProviderAlibabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderAlibabaApplication.class, args);
    }
}