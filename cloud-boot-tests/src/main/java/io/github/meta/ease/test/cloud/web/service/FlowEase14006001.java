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
package io.github.meta.ease.test.cloud.web.service;


import io.github.meta.ease.domain.api.BaseRequest;
import io.github.meta.ease.flow.engine.process.AbstractProcess;
import io.github.meta.ease.resilience4j.CircuitBreakerUtil;
import io.github.meta.ease.test.api.mapper.SysUserMapper;
import io.github.meta.ease.test.api.module.Ease14006001In;
import io.github.meta.ease.test.api.module.Ease14006001Out;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 18:13
 */
@Service
@Slf4j
public class FlowEase14006001 extends AbstractProcess<Ease14006001In, Ease14006001Out> {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @SneakyThrows
    @Override
    public Ease14006001Out process(Ease14006001In request)  {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");
        CircuitBreakerUtil.getCircuitBreakerStatus("执行开始前：", circuitBreaker);
        circuitBreaker.executeCheckedSupplier(Ease14006001Out::new);
        /*IWorker worker1 = (IWorker<Ease14006001In, Ease14006001Out>) (request1, allWrappers) -> {
            log.info("1{}", request1);
            return new Ease14006001Out();
        };

        IWorker worker2 = (IWorker<Ease14006001In, Ease14006001Out>) (request1, allWrappers) -> {
            log.info("2{}", request1);
            return new Ease14006001Out();
        };

        WorkerWrapper<Ease14006001In, Ease14006001Out> workerWrapper = new WorkerWrapper.Builder<Ease14006001In, Ease14006001Out>().worker(worker1).build();
        try {
            Async.beginWork(90000, workerWrapper);
            Async.shutDown();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(request);

        Ease14006001Out ease14006001Out = new Ease14006001Out();
        ease14006001Out.setName(request.getName());
        return ease14006001Out;
    }

    @Override
    public Class<? extends BaseRequest> getRequestModel() {
        return Ease14006001In.class;
    }
}
