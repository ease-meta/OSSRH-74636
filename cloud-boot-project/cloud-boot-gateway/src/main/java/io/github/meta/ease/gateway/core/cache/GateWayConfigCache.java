package io.github.meta.ease.gateway.core.cache;

import io.github.meta.ease.gateway.api.IGovernDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 19:12
 */
@Component
@Slf4j
public class GateWayConfigCache implements IGovernDataCache, InitializingBean {
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
