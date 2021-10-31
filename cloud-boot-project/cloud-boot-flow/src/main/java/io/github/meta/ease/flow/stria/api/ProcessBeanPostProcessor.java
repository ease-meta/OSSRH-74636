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
package io.github.meta.ease.flow.stria.api;

import io.github.meta.ease.core.commons.ServiceHandler;
import io.github.meta.ease.domain.api.BaseRequest;
import io.github.meta.ease.flow.busi.api.IProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 23:10
 */
public class ProcessBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ProcessBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof IProcess) {
            IProcess iProcess = (IProcess) bean;
            Class<? extends BaseRequest> clazz = iProcess.getRequestModel();
            FlowExecutor.ALL_IPROCESS.put(ServiceHandler.getServiceName(clazz), iProcess);
        }

        return bean;
    }
}
