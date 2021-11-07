package io.github.meta.ease.gateway.core;

import io.github.meta.ease.gateway.api.IGovernDataCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 订阅关系api，网关启动的时候从redis获取
 *
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 18:59
 */
@Component
public class SubscribeCache implements InitializingBean, IGovernDataCache {
    @Override
    public void clear() {

    }

    @Override
    public void load() {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
