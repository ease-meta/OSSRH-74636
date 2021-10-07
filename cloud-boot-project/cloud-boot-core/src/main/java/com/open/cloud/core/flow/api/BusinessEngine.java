package com.open.cloud.core.flow.api;

import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.domain.BaseResponse;
import com.open.cloud.core.exception.BusinessException;

public interface BusinessEngine<T extends BaseRequest, R extends BaseResponse> {

    R execFlow(IProcess iProcess, T request) throws BusinessException;

}