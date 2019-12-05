package com.open.cloud.mq.kafka;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.open.cloud.mq.kafka")
@Import(HelloImportBeanDefinitionRegistrar.class)
public class HelloConfiguration {

}