package io.github.meta.ease.gateway.core.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.github.meta.ease.gateway.core.GatewayConstant.APP_NAME;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 19:15
 */
@Component
public class RedisKeyGenerator {
    @Value("${" + APP_NAME + "}")
    private String gatewayCode;

    public String getGatewayCode() {
        return gatewayCode;
    }

    public void setGatewayCode(String gatewayCode) {
        this.gatewayCode = gatewayCode;
    }
}
