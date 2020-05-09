package com.open.cloud.sofa.consumer.rpc;

import com.open.cloud.business.api.DemoService;
import com.open.cloud.core.runtime.api.annotation.ConsumerReference;

/**
 * @author Leijian
 * @date 2020/5/7
 */
@ConsumerReference(interfaceType = DemoService.class)
public interface HelloService {

	String sayHello(String message);
}
