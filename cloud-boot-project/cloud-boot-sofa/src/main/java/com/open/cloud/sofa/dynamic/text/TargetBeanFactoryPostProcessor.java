package com.open.cloud.sofa.dynamic.text;

import com.alipay.sofa.runtime.spi.binding.BindingAdapterFactory;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;

/**
 * @author Leijian
 * @date 2020/5/12
 */
public class TargetBeanFactoryPostProcessor implements BeanFactoryPostProcessor,
		PriorityOrdered, EnvironmentAware {

	private Logger logger = LoggerFactory.getLogger(TargetBeanFactoryPostProcessor.class);

	private Environment environment;

	protected final SofaRuntimeContext sofaRuntimeContext;

	protected final BindingConverterFactory bindingConverterFactory;
	/** binding adapter factory */
	protected final BindingAdapterFactory bindingAdapterFactory;

	public TargetBeanFactoryPostProcessor(Environment environment, SofaRuntimeContext sofaRuntimeContext, BindingConverterFactory bindingConverterFactory, BindingAdapterFactory bindingAdapterFactory) {
		this.environment = environment;
		this.sofaRuntimeContext = sofaRuntimeContext;
		this.bindingConverterFactory = bindingConverterFactory;
		this.bindingAdapterFactory = bindingAdapterFactory;
	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
