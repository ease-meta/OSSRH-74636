package com.open.cloud;

import com.alipay.hessian.generic.model.GenericObject;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/16 15:24
 **/
public class Main {

    public static void main(String[] args) {
        泛华调用();

    }

    private static void 泛华调用() {
        RegistryConfig registryConfig = new RegistryConfig().setProtocol("zookeeper").setAddress("10.7.25.201:2282");

        ConsumerConfig<GenericService> consumerConfig = new ConsumerConfig<GenericService>().setInterfaceId("com.open.cloud.api.IRbAcctTransfer").setGeneric(true).setRegistry(registryConfig);
        GenericService testService = consumerConfig.refer();

        GenericObject genericObject = new GenericObject(
                "com.open.cloud.api.model.RbAcctTransferIn");
        genericObject.putField("reference", "123456");
        genericObject.putField("acctSystem", "555");

        GenericObject o2 = (GenericObject) testService.$genericInvoke("rbAcctTransfer",
                new String[]{"com.open.cloud.api.model.RbAcctTransferIn"},
                new Object[]{genericObject});
        System.out.println(o2);
    }
}
