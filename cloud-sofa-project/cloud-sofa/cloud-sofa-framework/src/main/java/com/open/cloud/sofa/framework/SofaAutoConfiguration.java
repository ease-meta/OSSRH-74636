package com.open.cloud.sofa.framework;

import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;

/**
 * @author Leijian
 * @date 2020/3/20
 */
public class SofaAutoConfiguration {

	public static ServiceBeanFactoryPostProcessor serviceBeanFactoryPostProcessor(SofaRuntimeContext sofaRuntimeContext,
																												 BindingConverterFactory bindingConverterFactory) {
		return new ServiceBeanFactoryPostProcessor(sofaRuntimeContext, bindingConverterFactory);
	}

}
