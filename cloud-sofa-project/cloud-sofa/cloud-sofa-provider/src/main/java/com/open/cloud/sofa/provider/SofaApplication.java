package com.open.cloud.sofa.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Leijian
 * @date 2020/3/20
 */
@ImportResource({ "classpath*:rpc-sofa-boot-starter-samples.xml" })
@SpringBootApplication
public class SofaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SofaApplication.class, args);
	}
}
