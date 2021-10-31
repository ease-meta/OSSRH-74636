package com.open.cloud.web.autoconfigure;


import com.open.cloud.web.CometConsumerMessageConvert;
import com.open.cloud.web.CustomerExceptionHandler;
import com.open.cloud.web.ResponseAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/7 11:21
 */
@Configuration(proxyBeanMethods = false)
public class WebMvcCoreAutoConfiguration {


    @Bean
    public CustomerExceptionHandler customerExceptionHandler() {
        return new CustomerExceptionHandler();
    }

    @Bean
    public ResponseAdvice responseAdvice() {
        return new ResponseAdvice();
    }

    @Bean
    public CometConsumerMessageConvert cometConsumerMessageConvert() {
        return new CometConsumerMessageConvert();
    }
}
