package com.open.cloud.flow.base;

import com.open.cloud.domain.api.BaseRequest;
import com.open.cloud.domain.api.BaseResponse;
import com.open.cloud.flow.api.IProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/30 22:09
 */
public abstract class AbstractProcess<T extends BaseRequest, R extends BaseResponse> implements IProcess<T, R> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractProcess.class);


}
