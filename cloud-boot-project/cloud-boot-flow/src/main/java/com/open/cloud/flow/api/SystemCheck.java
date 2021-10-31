package com.open.cloud.flow.api;


import com.open.cloud.domain.api.BaseRequest;

public interface SystemCheck<T extends BaseRequest> {


    void systemCheck(T request);
}
