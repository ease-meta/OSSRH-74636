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
package io.github.meta.ease.flow.stria.api;

import io.github.meta.ease.core.commons.Context;
import io.github.meta.ease.core.exception.BusinessException;
import io.github.meta.ease.core.exception.ExceptionUtil;
import io.github.meta.ease.domain.api.BaseRequest;
import io.github.meta.ease.domain.api.BaseResponse;
import io.github.meta.ease.flow.busi.api.IProcess;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 22:29
 */
@Component
public class SimpleBusinessEngine<T extends BaseRequest, R extends BaseResponse>
        extends
        AbstractService<T, R> {

    /**
     * Spring事务管理器
     */
    @Resource
    private PlatformTransactionManager transactionManager;

    private final TransactionDefinition newTransactionDefinition = new DefaultTransactionDefinition(
            TransactionDefinition.PROPAGATION_REQUIRES_NEW);

    //controller调用service入口
    @Override
    public R execFlow(IProcess iProcess, T request) throws BusinessException {
        if (request == null) {
            throw ExceptionUtil.creatBusinessException("000305");
        }
        //添加相应服务
        BaseResponse response = null;
        try {
            //初始化上下文
            Context.getContext().initBaseRequest(request);
            //初始化平台流水号
            initPlatformId(request);
            // 添加服务执行Reference
            traceNo(request);
            //登记主交易流水
            //IExtraTrace
            executeSystemCheck(request);
            response = execProcess(iProcess, request);
            return (R) response;
        } finally {
            cleanBeanResult(response);
            Context.removeContext();
        }

    }

    /**
     * 外部调用服务执行入口
     *
     * @param request
     * @return BeanResult
     */
    private BaseResponse execProcess(IProcess iProcess, T request) {

        TransactionStatus status = null;
        try {
            status = transactionManager.getTransaction(newTransactionDefinition);
            iProcess.beforeProcess(request);
            BaseResponse response = iProcess.process(request);
            iProcess.afterProcess(request, response);
            transactionManager.commit(status);
            return response;
        } catch (Throwable t) {
            if (status != null) {
                transactionManager.rollback(status);
            }
            throw t;
        }
    }

    @Override
    void executeSystemCheck(BaseRequest req) {
        //TODO
    }
}
