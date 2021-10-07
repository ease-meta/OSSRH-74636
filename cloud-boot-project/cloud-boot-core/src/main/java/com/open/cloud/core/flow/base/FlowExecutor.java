package com.open.cloud.core.flow.base;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.domain.BaseResponse;
import com.open.cloud.core.flow.api.BusinessEngine;
import com.open.cloud.core.flow.api.IProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 22:49
 */
@Component
public class FlowExecutor {

    private static final Logger logger = LoggerFactory.getLogger(FlowExecutor.class);

    @Resource
    private static BusinessEngine businessEngine;


    static Multimap<String, IProcess> ALL_IPROCESS = ArrayListMultimap.create();

    public static <T extends BaseRequest, R extends BaseResponse> R execute2Resp(T request) {
        try {
            String serviceName = ServiceHandler.getServiceName(request.getClass());
            if (ALL_IPROCESS.get(serviceName).size() > 1) {
                logger.warn("");
            }
            IProcess iProcess = ALL_IPROCESS.get(serviceName).stream().findFirst().get();
            return (R) FlowExecutor.businessEngine.execFlow(iProcess, request);
        } catch (Exception exception) {
            //TODO 兜底异常？
        }
        return null;

    }
}
