package com.open.cloud.sofa.dynamic;

import com.alipay.sofa.runtime.api.aware.ClientFactoryAware;
import com.alipay.sofa.runtime.api.client.ClientFactory;

/**
 * SOFABoot 环境 dynamic API 使用
 * @author Leijian
 * @date 2020/5/6
 */
public class ClientFactoryBean implements ClientFactoryAware {

    private ClientFactory clientFactory;

    @Override
    public void setClientFactory(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }
}
