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
package io.github.meta.ease.flow.engine;

import io.github.meta.ease.core.commons.Context;
import io.github.meta.ease.core.commons.SpringApplicationContext;
import io.github.meta.ease.core.exception.BusinessException;
import io.github.meta.ease.core.exception.ExceptionUtil;
import io.github.meta.ease.core.exception.ResultCode;
import io.github.meta.ease.domain.dto.BaseRequest;
import io.github.meta.ease.domain.dto.BaseResponse;
import io.github.meta.ease.flow.engine.base.WorkResult;
import io.github.meta.ease.flow.engine.process.IProcess;
import io.github.meta.ease.flow.engine.process.IProcessHead;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 22:29
 */
@Component
public class SimpleBusinessEngine<T extends BaseRequest, R extends BaseResponse>
        extends
        AbstractService<T, R> {

    private final TransactionDefinition newTransactionDefinition = new DefaultTransactionDefinition(
            TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    /**
     * Spring事务管理器
     */
    @Resource
    private PlatformTransactionManager transactionManager;

    //controller调用service入口
    @Override
    public R execFlow(IProcess iProcess, T request) throws BusinessException {
        if (request == null) {
            throw ExceptionUtil.creatBusinessException("000305");
        }
        //添加相应服务
        R response = null;
        WorkResult workResult = new WorkResult(response, ResultCode.FAILED);
        try {
            //初始化上下文
            Context.getContext().initBaseRequest(request);
            init(request);
            //初始化平台流水号
            initPlatformId(request);
            executeSystemCheck(request);
            idempotenCheck(request);
            iProcess.begin();
            response = execProcess(iProcess, request, response);
            businessHanler(iProcess, request, response);
            workResult.setResultCode(ResultCode.SUCCESS);
            workResult.setResult(response);
            iProcess.result(true, request, workResult);
            return response;
        } catch (Exception e) {
            response = catchHanler(iProcess, e, request, response);
            workResult.setResultCode(ResultCode.FAILED);
            workResult.setResult(response);
            workResult.setEx(e);
            iProcess.result(true, request, workResult);
        } finally {
            Context.removeContext();
        }
        return response;
    }

    public void businessHanler(IProcess process, T request, R response) {
        Optional.ofNullable(response).ifPresent(r -> SpringApplicationContext.getContext().getBean(IProcessHead.class).success(request, response));
    }

    @SneakyThrows
    public R catchHanler(IProcess process, Throwable throwable, T request, R response) {
        response = Optional.ofNullable(response).orElseGet((Supplier<? extends R>) Optional.ofNullable(process.getResponseModel()).get().newInstance());
        SpringApplicationContext.getContext().getBean(IProcessHead.class).exception(request, response, throwable);
        return response;
    }

    @Override
    public void init(T request) {

    }

    /**
     * 外部调用服务执行入口
     *
     * @param request
     * @return BeanResult
     */
    private R execProcess(IProcess iProcess, T request, R response) {

        TransactionStatus status = null;
        try {
            status = transactionManager.getTransaction(newTransactionDefinition);
            response = (R) iProcess.process(request);
            transactionManager.commit(status);
            return response;
        } catch (Throwable t) {
            if (status != null && !status.isCompleted()) {
                transactionManager.rollback(status);
            }
            throw t;
        }
    }

    @Override
    void executeSystemCheck(BaseRequest req) {

    }

    @Override
    void idempotenCheck(T req) {

    }
}
