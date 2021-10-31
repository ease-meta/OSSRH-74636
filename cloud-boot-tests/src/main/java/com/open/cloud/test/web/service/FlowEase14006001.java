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
package com.open.cloud.test.web.service;

import cn.hutool.db.Page;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.cloud.async.callback.IWorker;
import com.open.cloud.async.executor.Async;
import com.open.cloud.async.wrapper.WorkerWrapper;
import com.open.cloud.core.commons.SpringApplicationContext;
import com.open.cloud.domain.api.BaseRequest;
import com.open.cloud.flow.busi.api.AbstractProcess;
import com.open.cloud.test.web.controler.Hello;
import com.open.cloud.test.web.module.Ease14006001In;
import com.open.cloud.test.web.module.Ease14006001Out;
import com.open.cloud.test.web.mybatis.demo.entity.SysUser;
import com.open.cloud.test.web.mybatis.demo.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

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

    @Override
    public Ease14006001Out process(Ease14006001In request) {
        IWorker worker1 = (IWorker<Ease14006001In, Ease14006001Out>) (request1, allWrappers) -> {
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
        }

        Hello hello = SpringApplicationContext.getContext().getBean(Hello.class);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(request);

        PageInfo<SysUser> p = PageHelper.startPage(1, 3).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                HashMap hashMap = new HashMap();
                hashMap.put("userId", "9999");
                hashMap.put("page", new Page());
                sysUserMapper.deleteByPage(hashMap);
            }
        });
        Ease14006001Out ease14006001Out = new Ease14006001Out();
        ease14006001Out.setName(request.getName());
        return ease14006001Out;
    }

    @Override
    public Class<? extends BaseRequest> getRequestModel() {
        return Ease14006001In.class;
    }
}
