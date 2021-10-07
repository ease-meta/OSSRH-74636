package com.open.cloud.core.flow.base;

import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.domain.BaseResponse;
import com.open.cloud.core.exception.BusinessException;
import com.open.cloud.core.exception.ExceptionUtil;
import com.open.cloud.core.flow.api.IProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 22:29
 */
@Component
public class SimpleBusinessEngine<T extends BaseRequest, R extends BaseResponse> extends AbstractService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleBusinessEngine.class);


    /**
     * Spring事务管理器
     */
    @Resource
    private PlatformTransactionManager transactionManager;

    //controller调用service入口
    @Override
    public BaseResponse execFlow(IProcess iProcess, BaseRequest request) throws BusinessException {
        if (request == null) {
            throw ExceptionUtil.creatBusinessException("000305");
        }

        if (logger.isTraceEnabled()) {
            logger.trace("request object ： " + request);
        }
        //初始化上下文

        //初始化平台流水号
        initPlatformId(request);
        //登记主交易流水
        //IExtraTrace
        // 添加服务执行Reference
        traceNo(request);
        //添加相应服务
        BaseResponse response = null;
        try {

            // 批处理服务限制
            //br = tranRestrictUtil.checkRestrict(req.getServiceId());
            //if (br != null) {
            //    return br;
            //}
            // 交易防重
            //br = antiRepeatUtil.process(req);
            //if (br != null) {
            //    return br;
            //}
            try {
                // 执行前置服务处理
                executeSystemCheck(request);
                // 对字段进行解密或者转加密
              /*  if (encryptUtil.switchOn()) {
                    encryptUtil.requestEncryptDecrypt(req);
                }*/
                // 服务处理
                response = execProcess(iProcess, request);
            } catch (Throwable t) {
               /* if (logger.isWarnEnabled()) {
                    logger.warn("process request " + req.getServiceId() + " exception.", t);
                }*/
                throw t;
            } finally {
                // 执行后置服务处理
                executeAfterService(request, response);
                // 防重更新
                //antiRepeatUtil.updateTranInfo(req, br);
            }
        } catch (Throwable t) {
          /*  if (logger.isWarnEnabled()) {
                logger.warn("before process request " + req.getServiceId() + " exception.", t);
            }*/
            // br = new BeanResult(t);
            throw t;
        } finally {
          /*  ISysHead sysHead = req.getSysHead();
            // 更新下送的交易时间
            sysHead.setTranTimestamp(getCurrentDate());
            br.setSysHead(sysHead);
            if (logger.isTraceEnabled()) {
                logger.trace("beanResult object ： " + br);
            }*/
            cleanBeanResult(response);
        }

        return response;
    }

    /**
     * 外部调用服务执行入口
     *
     * @param request
     * @return BeanResult
     */
    private BaseResponse execProcess(IProcess iProcess, BaseRequest request) {
        long start = -1L;
        if (logger.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }
        boolean isAppFlag = false;
      /*  if (null != req.getAppHead()) {
            isAppFlag = true;
            // 设置AppHead到上下文
            Context.getInstance().setAppHead(req.getAppHead());
        }*/
        TransactionStatus status = null;
        iProcess.beforeProcess(request);
        BaseResponse response = iProcess.process(request);
        iProcess.afterProcess(request, response);
       /* Context context = Context.getInstance();
        if (!StringUtils.isBlank(context.getRunDate())) {
            req.getSysHead().setRunDate(context.getRunDate());
        }*/
        /*if (GalaxyConstants.STATUS_SUCCESS.equals(br.getRetStatus())) {
            // 结果不为空且成功的，结果中不存在AppHead，上下文中有AppHead
            if (isAppFlag && null != br
                    && null == br.getAppHead()) {
                br = new BeanResult(br.getResponse(), Context.getInstance().getAppHead());
            }
        }*/
      /*  if (logger.isInfoEnabled()) {
            logger.info("Service [{}] execute time:{}", req.getServiceId(), (System.currentTimeMillis() - start));
        }*/
        return response;
    }


    @Override
    void executeSystemCheck(BaseRequest req) {

    }
}
