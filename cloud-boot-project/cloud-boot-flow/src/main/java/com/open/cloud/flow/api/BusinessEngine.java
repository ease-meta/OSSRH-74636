package com.open.cloud.flow.api;


import com.open.cloud.core.exception.BusinessException;
import com.open.cloud.domain.api.BaseRequest;
import com.open.cloud.domain.api.BaseResponse;

public interface BusinessEngine<T extends BaseRequest, R extends BaseResponse> {

    R execFlow(IProcess iProcess, T request) throws BusinessException;

}