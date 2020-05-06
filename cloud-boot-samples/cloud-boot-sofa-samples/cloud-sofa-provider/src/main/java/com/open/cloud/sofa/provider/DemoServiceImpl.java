package com.open.cloud.sofa.provider;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.open.cloud.business.api.DemoService;
import org.springframework.stereotype.Service;

@SofaService(bindings={@SofaServiceBinding(bindingType="bolt")})
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String message) {
        return message;
    }
}