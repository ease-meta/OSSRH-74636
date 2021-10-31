package com.open.cloud.core.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 22:54
 */
public class ServiceHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServiceHandler.class);

    public static String getServiceName(Class clazz) {
        return clazz.getName();
    }
}
