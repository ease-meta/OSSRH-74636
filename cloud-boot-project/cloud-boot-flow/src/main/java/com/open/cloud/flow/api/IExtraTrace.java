package com.open.cloud.flow.api;


import com.open.cloud.domain.api.BaseRequest;

public interface IExtraTrace {


    /**
     * 登记平台流水
     *
     * @param baseRequest 流程入参
     */
    void trace(BaseRequest baseRequest);
}