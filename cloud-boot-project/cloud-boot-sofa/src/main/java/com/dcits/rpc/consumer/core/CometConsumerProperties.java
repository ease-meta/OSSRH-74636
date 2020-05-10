package com.dcits.rpc.consumer.core;

import java.util.Properties;

/**
 * <p>Title: CometConsumerEnvironmentPostProcessor</p>
 * <p>Description: </p>
 *
 * @author zhao.xiaobo
 * @version 3.0.0
 * @date 2020-03-13 17:42
 */
class CometConsumerProperties {

    static final String RESOLVER_KEY = "com.dcits.comet.client.registrar";

    private static final String LOCATION_PATH = "consumer/consumer.properties";

    static String getResolverClazz() {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(LOCATION_PATH));
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't find comsumer properties by LOCATION_PATH.", e);
        }
        return properties.getProperty(RESOLVER_KEY);
    }


}
