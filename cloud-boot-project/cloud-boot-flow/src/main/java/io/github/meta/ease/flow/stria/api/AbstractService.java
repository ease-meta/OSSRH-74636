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

import io.github.meta.ease.domain.api.BaseRequest;
import io.github.meta.ease.domain.api.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractService<T extends BaseRequest, R extends BaseResponse>
        implements
        BusinessEngine<T, R> {

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
                logger.info("Validate - 执行时间[" + (System.currentTimeMillis() - start) + "]["
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
