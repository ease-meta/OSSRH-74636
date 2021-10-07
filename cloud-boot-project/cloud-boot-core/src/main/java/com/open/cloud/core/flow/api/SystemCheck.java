package com.open.cloud.core.flow.api;

import com.open.cloud.core.domain.BaseRequest;


public interface SystemCheck<T extends BaseRequest> {


    void systemCheck(T request);
}
