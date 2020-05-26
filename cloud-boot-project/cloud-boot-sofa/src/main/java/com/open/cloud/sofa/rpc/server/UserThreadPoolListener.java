package com.open.cloud.sofa.rpc.server;

import com.alipay.sofa.rpc.boot.context.event.SofaBootRpcStartAfterEvent;
import com.alipay.sofa.rpc.bootstrap.ProviderBootstrap;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.config.UserThreadPoolManager;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.server.UserThreadPool;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserThreadPoolListener implements ApplicationListener<SofaBootRpcStartAfterEvent> {
	@Override
	public void onApplicationEvent(SofaBootRpcStartAfterEvent event) {
		Map<Class<?>, Object> interfaceMapRef = new HashMap<>();
		List<ProviderBootstrap> providerBootstraps = RpcRuntimeContext.getProviderConfigs();
		for (ProviderBootstrap providerBootstrap : providerBootstraps) {
			ProviderConfig providerConfig = providerBootstrap.getProviderConfig();
			List<ServerConfig> server = providerConfig.getServer();
			for (ServerConfig serverConfig : server) {
				UserThreadPool userThreadPool = new UserThreadPool();
				interfaceMapRef.put(providerConfig.getProxyClass(), providerConfig.getRef());
			}
		}

	}
}