package com.open.cloud.flow.base;

import com.open.cloud.core.commons.Context;
import com.open.cloud.core.exception.BusinessException;
import com.open.cloud.core.exception.ExceptionUtil;
import com.open.cloud.domain.api.BaseRequest;
import com.open.cloud.domain.api.BaseResponse;
import com.open.cloud.flow.api.IProcess;
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
public class SimpleBusinessEngine<T extends BaseRequest, R extends BaseResponse> extends AbstractService<T, R> {


    /**
     * Spring事务管理器
     */
    @Resource
    private PlatformTransactionManager transactionManager;

    private final TransactionDefinition newTransactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);


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
