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
package com.open.cloud.sofa.rpc.server;

import com.alipay.sofa.rpc.boot.context.event.SofaBootRpcStartAfterEvent;
import com.alipay.sofa.rpc.bootstrap.ProviderBootstrap;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.server.UserThreadPool;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserThreadPoolListener implements ApplicationListener<SofaBootRpcStartAfterEvent> {
    @Override
    public void onApplicationEvent(SofaBootRpcStartAfterEvent event) {
        Map<Class<?>, Object> interfaceMapRef = new HashMap<>();
        List<ProviderBootstrap> providerBootstraps = RpcRuntimeContext.getProviderConfigs();
        for (ProviderBootstrap providerBootstrap : providerBootstraps) {
            ProviderConfig providerConfig = providerBootstrap.getProviderConfig();
            List<ServerConfig> server = providerConfig.getServer();
            for (ServerConfig serverConfig : server) {
                UserThreadPool userThreadPool = new UserThreadPool();
                interfaceMapRef.put(providerConfig.getProxyClass(), providerConfig.getRef());
            }
        }

    }
}