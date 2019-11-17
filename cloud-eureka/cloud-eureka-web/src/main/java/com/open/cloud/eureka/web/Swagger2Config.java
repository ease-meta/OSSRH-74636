package com.open.cloud.eureka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Autowired
	Environment environment;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
						.genericModelSubstitutes(DeferredResult.class)
						.useDefaultResponseMessages(false)
						.forCodeGeneration(false)
						.pathMapping("/")
						.select()
						.build()
						.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		String appName = environment.getProperty("spring.application.name");
		return new ApiInfoBuilder()
						.title("open-cloud")
						.version("1.0")
						.description(appName)
						.license("The Apache License, Version 2.0")
						.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
						.build();
	}
}
