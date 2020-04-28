package com.open.cloud.sofa.consumer;


import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.open.cloud.sofa.api.HelloService;

public class HelloConsumerTest {
	public static void main(String[] args) {
		//RegistryConfig 表示注册中心。声明了服务注册中心的地址和端口是127.0.0.1:2181，协议是 Zookeeper
		//RegistryConfig registryConfig = new RegistryConfig()
		//    .setProtocol(RpcConstants.REGISTRY_PROTOCOL_ZK)
		//    .setAddress("122.51.108.224:2181");
		RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol("nacos")
				.setAddress("122.51.108.224:8848/public");
		//ConsumerConfig 表示服务引用，如上声明了所引用服务的接口和服务注册中心。 最终通过 refer 方法将这个服务引用，获取到该服务的远程调用的代理
		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setRegistry(registryConfig);
		//生成代理类
		HelloService helloService = consumerConfig.refer();

		while (true) {
			System.out.println(helloService.sayHello("world"));
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
		}
	}
}
