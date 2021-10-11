package com.open.cloud.core.flow.api;


import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.domain.BaseResponse;

import java.lang.reflect.ParameterizedType;

public interface IProcess<T extends BaseRequest, R extends BaseResponse> {

    R process(T request);

    /**
     * @param request
     * @return R
     */
    default R asyncProcess(T request) {
        return null;
    }

    default Class<? extends BaseRequest> getRequestModel() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    default Class<? extends BaseResponse> getResponseModel() {
        return (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * 服务前处理
     *
     * @param request
     */
    default void beforeProcess(T request) {

    }

    /**
     * 服务后处理
     *
     * @param request
     * @param response
     */
    default void afterProcess(T request, R response) {

    }
}
