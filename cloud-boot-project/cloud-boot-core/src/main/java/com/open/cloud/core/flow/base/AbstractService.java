
package com.open.cloud.core.flow.base;

import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.domain.BaseResponse;
import com.open.cloud.core.flow.api.BusinessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractService<T extends BaseRequest, R extends BaseResponse> implements BusinessEngine<T, R> {

    private transient final Logger logger = LoggerFactory.getLogger(AbstractService.class);


    /**
     * 公共域校验
     *
     * @param validatedObj
     */
    private void validateCommon(BaseRequest validatedObj) {
        long start = -1L;
        if (logger.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }
        try {

        } catch (Throwable t) {

        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("Validate - 执行时间["
                        + (System.currentTimeMillis() - start) + "]["
                        + validatedObj.getClass().getName() + "]");
            }
        }
    }

    /**
     * 清理不必要的下送报文
     *
     * @param br
     */
    protected void cleanBeanResult(BaseResponse br) {
       /* br.setThrowable(null);
        ISysHead sysHead = br.getSysHead();
        if (sysHead != null) {
            sysHead.cleanSysHead();
        }*/
    }

    /**
     * 获取当前的时间
     *
     * @return
     */
    protected String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * 获取系统Reference
     *
     * @param req
     */
    void traceNo(BaseRequest req) {
      /*  long start = System.currentTimeMillis();
        try {
            initPlatformId(req);

            BusinessTraceNo businessTraceNo = ServiceHandler.getBusinessTraceNo();
            if (null == businessTraceNo) {
                if (logger.isWarnEnabled()) {
                    logger.warn("BusinessTraceNo Service not exist, skip afterService process!");
                }
            } else {
                String reference = businessTraceNo.generator();
                req.getSysHead().setReference(reference);
                MDC.put(GalaxyConstants.REFERENCE, reference);
            }
        } catch (Throwable t) {
            if (logger.isWarnEnabled()) {
                logger.warn(t.getMessage(), t);
            }
        } finally {
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("BusinessTraceNo execute time:" + (end - start));
            }
        }*/
    }

    abstract void executeSystemCheck(BaseRequest req);

    void initPlatformId(BaseRequest req) {
       /* if (StringUtils.isEmpty(req.getUid())) {
            if (StringUtils.isEmpty(ThreadLocalManager.getUID())) {
                ThreadLocalManager.setUID(SeqUtils.getStringSeq());
            }
            req.setUid(ThreadLocalManager.getUID());
        } else {
            if (StringUtils.isEmpty(ThreadLocalManager.getUID())) {
                ThreadLocalManager.setUID(req.getUid());
            }
        }*/
    }
}
