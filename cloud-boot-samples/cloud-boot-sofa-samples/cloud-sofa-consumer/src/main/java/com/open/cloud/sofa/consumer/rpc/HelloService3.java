package com.open.cloud.sofa.consumer.rpc;

import com.dcits.comet.rpc.api.annotation.CometConsumer;
import com.dcits.comet.rpc.consumer.sofa.annotation.CometSofaConsumer;
import com.open.cloud.business.api.DemoService;

/**
 * @author Leijian
 * @date 2020/5/7
 */
@CometConsumer(interfaceType = DemoService.class)
@CometSofaConsumer
public interface HelloService3 {

	String sayHello(String message);
}
