package com.open.cloud.sofa.framework;

import com.alipay.sofa.runtime.SofaFramework;
import com.alipay.sofa.runtime.configure.SofaRuntimeConfigurationProperties;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leijian
 * @date 2020/3/20
 */
@Configuration
@EnableConfigurationProperties(SofaRuntimeConfigurationProperties.class)
@ConditionalOnClass(SofaFramework.class)
public class SofaAutoConfiguration {

	@Bean
	public static ServiceBeanFactoryPostProcessor serviceBeanFactoryPostProcessor(SofaRuntimeContext sofaRuntimeContext,
																												 BindingConverterFactory bindingConverterFactory) {
		return new ServiceBeanFactoryPostProcessor(sofaRuntimeContext, bindingConverterFactory);
	}

}
