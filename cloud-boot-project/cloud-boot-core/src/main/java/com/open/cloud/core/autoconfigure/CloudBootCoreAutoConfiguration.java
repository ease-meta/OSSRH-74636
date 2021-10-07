package com.open.cloud.core.autoconfigure;

import com.open.cloud.core.flow.api.BusinessEngine;
import com.open.cloud.core.flow.base.FlowExecutor;
import com.open.cloud.core.flow.base.ProcessBeanPostProcessor;
import com.open.cloud.core.flow.base.SimpleBusinessEngine;
import com.open.cloud.core.flow.web.CustomerExceptionHandler;
import com.open.cloud.core.flow.web.ResponseAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/7 11:21
 */
@Configuration(proxyBeanMethods = false)
public class CloudBootCoreAutoConfiguration {

    @Bean
    public ProcessBeanPostProcessor processBeanPostProcessor() {
        return new ProcessBeanPostProcessor();
    }

    @Bean
    public BusinessEngine simpleBusinessEngine() {
        return new SimpleBusinessEngine();
    }

    @Bean
    public FlowExecutor flowExecutor(BusinessEngine businessEngine) {
        return new FlowExecutor();
    }

    @Bean
    public CustomerExceptionHandler customerExceptionHandler() {
        return new CustomerExceptionHandler();
    }

    @Bean
    public ResponseAdvice responseAdvice() {
        return new ResponseAdvice();
    }

}
