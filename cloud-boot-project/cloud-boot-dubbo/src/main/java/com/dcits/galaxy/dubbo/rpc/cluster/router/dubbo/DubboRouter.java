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
package com.dcits.galaxy.dubbo.rpc.cluster.router.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DubboRouter implements Router {

    private static Logger               logger = LoggerFactory.getLogger(DubboRouter.class);

    private static volatile DubboRouter batchRouter;

    private DubboRouter() {
    }

    public static DubboRouter getInstance() {
        if (null == batchRouter) {
            synchronized (DubboRouter.class) {
                if (null == batchRouter) {
                    batchRouter = new DubboRouter();
                }
            }
        }
        return batchRouter;
    }

    @Override
    public URL getUrl() {
        return null;
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> list, URL url, Invocation invocation)
                                                                                            throws RpcException {
        String address;
        List<String> serviceProvider = new ArrayList<>();
        List<String> filterServiceProvider = new ArrayList<>();
        List<Invoker<T>> invokers = new ArrayList<>();
        for (Invoker<T> invoker : list) {
            address = invoker.getUrl().getAddress();
            serviceProvider.add(address);
            invokers.add(invoker);
            filterServiceProvider.add(address);
        }
        if (logger.isInfoEnabled()) {
            logger
                .info(
                    "Current task Service provider Node address {}, Filtered Service provider Node address {}",
                    serviceProvider, filterServiceProvider);
        }
        return invokers;
    }

    @Override
    public int compareTo(Router o) {
        return 0;
    }
}
