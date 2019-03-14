/**
 * <p>Title: BrancnWorkFlowBeanPostProcessor.java</p>  
 * <p>Description: </p> 
 * @author leijian
 * @date 2019年1月25日
 * @version 1.0
 */
package com.moc.cloud.workflow.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.lang.Nullable;

/**
 * @author leijian
 * @date 2019年1月25日
 */
@Slf4j
public class BrancnWorkFlowBeanPostProcessor implements BeanPostProcessor {

	private final ConfigurableListableBeanFactory beanFactory;

	private final int beanPostProcessorTargetCount;

	public BrancnWorkFlowBeanPostProcessor(ConfigurableListableBeanFactory beanFactory,
			int beanPostProcessorTargetCount) {
		this.beanFactory = beanFactory;
		this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName)
				&& this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
			if (log.isInfoEnabled()) {
				log.info("Bean '" + beanName + "' of type [" + bean.getClass().getName()
						+ "] is not eligible for getting processed by all BeanPostProcessors "
						+ "(for example: not eligible for auto-proxying)");
			}
		}
		return bean;
	}

	private boolean isInfrastructureBean(@Nullable String beanName) {
		if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
			BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
			return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
		}
		return false;
	}
}
