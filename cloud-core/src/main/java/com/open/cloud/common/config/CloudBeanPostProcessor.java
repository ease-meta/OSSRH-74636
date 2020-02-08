/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import javax.annotation.Nullable;

@Slf4j
public class CloudBeanPostProcessor implements BeanPostProcessor {

	private final ConfigurableListableBeanFactory beanFactory;

	private final int beanPostProcessorTargetCount;

	public CloudBeanPostProcessor(ConfigurableListableBeanFactory beanFactory,
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
