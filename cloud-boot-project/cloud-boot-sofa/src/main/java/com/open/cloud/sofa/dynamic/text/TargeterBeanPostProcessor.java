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
package com.open.cloud.sofa.dynamic.text;

import com.alipay.sofa.runtime.spi.binding.BindingAdapterFactory;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *openfeign
 */
@Component
public class TargeterBeanPostProcessor implements BeanPostProcessor, BeanClassLoaderAware {

    private Logger                          logger = LoggerFactory
                                                       .getLogger(TargeterBeanPostProcessor.class);

    private final Environment               environment;

    private ClassLoader                     classLoader;

    protected final SofaRuntimeContext      sofaRuntimeContext;

    protected final BindingConverterFactory bindingConverterFactory;
    /** binding adapter factory */
    protected final BindingAdapterFactory   bindingAdapterFactory;

    public TargeterBeanPostProcessor(Environment environment,
                                     SofaRuntimeContext sofaRuntimeContext,
                                     BindingConverterFactory bindingConverterFactory,
                                     BindingAdapterFactory bindingAdapterFactory) {
        this.environment = environment;
        this.sofaRuntimeContext = sofaRuntimeContext;
        this.bindingConverterFactory = bindingConverterFactory;
        this.bindingAdapterFactory = bindingAdapterFactory;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
                                                                               throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
                                                                              throws BeansException {
        return bean;
    }
}