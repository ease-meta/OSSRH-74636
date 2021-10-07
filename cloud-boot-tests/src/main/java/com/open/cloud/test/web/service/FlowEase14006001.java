package com.open.cloud.test.web.service;

import com.open.cloud.core.domain.BaseRequest;
import com.open.cloud.core.flow.base.AbstractProcess;
import com.open.cloud.test.web.module.Ease14006001In;
import com.open.cloud.test.web.module.Ease14006001Out;
import org.springframework.stereotype.Service;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 18:13
 */
@Service
public class FlowEase14006001 extends AbstractProcess<Ease14006001In, Ease14006001Out> {

    @Override
    public Ease14006001Out process(Ease14006001In request) {
        return null;
    }

    @Override
    public Class<? extends BaseRequest> getRequestModel() {
        return Ease14006001In.class;
    }
}
