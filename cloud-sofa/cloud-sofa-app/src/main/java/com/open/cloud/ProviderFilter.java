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

import com.alipay.sofa.rpc.core.exception.RpcErrorType;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.google.common.base.Stopwatch;
import com.open.cloud.api.model.BaseRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

@Extension(value = "providerFilter", order = -1)
@AutoActive(providerSide = true)
@Slf4j
public class ProviderFilter extends Filter {

    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Type type = request.getMethod().getGenericReturnType();
        Class returnType = request.getMethod().getReturnType();
        try {
            Object baseObject = returnType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            log.info("<====== 进入 ProviderFilter  ======>");
            log.info("request is: {}", request);
            log.info("invoker is: {}", invoker);
            // 先调用
            BaseRequest baseRequest = (BaseRequest) request.getMethodArgs()[0];

            SofaResponse sofaResponse = invoker.invoke(request);
            return sofaResponse;
        } catch (SofaRpcException e) {
            throw e;
        } catch (Exception e) {
            throw new SofaRpcException(RpcErrorType.SERVER_FILTER, e);
        } finally {
            //Context.getInstance().cleanResource();
            log.info("<====== 结束 ProviderFilter, elapsedTime is [{}] ======>",
                stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
        // TODO 是否特殊处理？
    }

}
