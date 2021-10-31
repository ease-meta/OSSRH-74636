package com.open.cloud.flow.base;

import com.open.cloud.core.commons.ServiceHandler;
import com.open.cloud.domain.api.BaseRequest;
import com.open.cloud.flow.api.IProcess;
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
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof IProcess) {
            IProcess iProcess = (IProcess) bean;
            Class<? extends BaseRequest> clazz = iProcess.getRequestModel();
            FlowExecutor.ALL_IPROCESS.put(ServiceHandler.getServiceName(clazz), iProcess);
        }

        return bean;
    }
}
