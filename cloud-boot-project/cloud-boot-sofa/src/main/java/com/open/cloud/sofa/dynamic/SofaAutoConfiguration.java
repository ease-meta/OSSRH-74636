package com.open.cloud.sofa.dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SofaAutoConfiguration {

	/**
	 * OpenFeign Targeter class name.
	 */
	public static final String TARGETER_CLASS_NAME = "org.springframework.cloud.openfeign.Targeter";

	@Bean
	public TargeterBeanPostProcessor targeterBeanPostProcessor(Environment environment) {
		return new TargeterBeanPostProcessor(environment);
	}
}