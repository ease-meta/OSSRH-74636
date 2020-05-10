package com.dcits.rpc.consumer.core;

import org.springframework.core.env.Environment;

/**
 * <p>Title: AbstractConsumerResolver</p>
 * <p>Description: </p>
 *
 * @author zhao.xiaobo
 * @version 3.0.0
 * @date 2020-03-12 11:07
 */
public abstract class AbstractConsumerResolver implements ConsumerResolver {

    private Environment environment;

    public AbstractConsumerResolver(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
