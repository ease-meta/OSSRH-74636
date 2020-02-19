/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        RegistryConfig registryConfig = new RegistryConfig().setProtocol("zookeeper").setAddress(
            "10.7.25.201:2282");

        ConsumerConfig<GenericService> consumerConfig = new ConsumerConfig<GenericService>()
            .setInterfaceId("com.open.cloud.api.IRbAcctTransfer").setGeneric(true)
            .setRegistry(registryConfig);
        GenericService testService = consumerConfig.refer();

        GenericObject genericObject = new GenericObject("com.open.cloud.api.model.RbAcctTransferIn");
        genericObject.putField("reference", "123456");
        genericObject.putField("acctSystem", "555");

        GenericObject o2 = (GenericObject) testService.$genericInvoke("rbAcctTransfer",
            new String[] { "com.open.cloud.api.model.RbAcctTransferIn" },
            new Object[] { genericObject });
        System.out.println(o2);
    }
}
