package com.open.cloud.sofa.provider;


import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.config.UserThreadPoolManager;
import com.open.cloud.sofa.api.HelloService;

/**
 * @author Leijian
 * 服务发布过程涉及到三个类 RegistryConfig ，ServerConfig ，ProviderConfig
 */
public class HelloProviderTest {
	static Object lock = new Object();

	public static void main(String[] args) {
		//RegistryConfig 表示注册中心。声明了服务注册中心的地址和端口是127.0.0.1:2181，协议是 Zookeeper
		RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol("zookeeper")
				.setAddress("122.51.108.224:2181");
		//ServerConfig 表示服务运行容器。声明了一个使用8803端口和 bolt 协议的 server 。
		ServerConfig serverConfig = new ServerConfig()
				.setPort(8803)
				.setProtocol("bolt");
		//ProviderConfig 表示服务发布。声明了服务的接口，实现和该服务运行的 server 。 最终通过 export 方法将这个服务发布出去了。
		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setRef(new HelloServiceImpl())
				.setServer(serverConfig)
				.setRegistry(registryConfig);
		//UserThreadPoolManager.registerUserThread();
		providerConfig.export();
		while (true) {
			synchronized (lock) {
				System.out.println("2.无限期等待中...");
				try {
					lock.wait(); //等待，直到其它线程调用 lock.notify()
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
