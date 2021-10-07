package com.open.cloud.core.flow.base;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.domain.BaseResponse;
import com.open.cloud.core.flow.api.BusinessEngine;
import com.open.cloud.core.flow.api.IProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;


/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 22:49
 */
public class FlowExecutor {

    private static final Logger logger = LoggerFactory.getLogger(FlowExecutor.class);

    @Resource
    private BusinessEngine businessEngine;

    private static FlowExecutor flowExecutor;

    public FlowExecutor() {
        flowExecutor = this;
    }

    static Multimap<String, IProcess> ALL_IPROCESS = ArrayListMultimap.create();

    @lombok.SneakyThrows
    public static <T extends BaseRequest, R extends BaseResponse> R execute2Resp(T request) {
        String serviceName = ServiceHandler.getServiceName(request.getClass());
        if (ALL_IPROCESS.get(serviceName).size() > 1) {
            logger.warn("");
        }
        IProcess iProcess = ALL_IPROCESS.get(serviceName).stream().findFirst().orElseThrow();
        return (R) flowExecutor.businessEngine.execFlow(iProcess, request);
    }
}
