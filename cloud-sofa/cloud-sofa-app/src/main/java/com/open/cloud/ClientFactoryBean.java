package com.open.cloud;

import com.alipay.sofa.runtime.api.aware.ClientFactoryAware;
import com.alipay.sofa.runtime.api.client.ClientFactory;
import com.alipay.sofa.runtime.api.client.ReferenceClient;
import com.alipay.sofa.runtime.api.client.ServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/16 16:07
 **/
@Component
@Slf4j
public class ClientFactoryBean implements ClientFactoryAware {

    private ClientFactory clientFactory;

    @Override
    public void setClientFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        log.info("{}",clientFactory.getClient(ServiceClient.class));
        log.info("{}",clientFactory.getClient(ReferenceClient.class));
    }
}
