package com.open.cloud.sofa.rpc.server;

public class UserThreadPool extends com.alipay.sofa.rpc.server.UserThreadPool {

	private String service;

	public UserThreadPool setService(String service) {
		this.service = service;
		return this;
	}

	public String getService() {
		return service;
	}

	@Override
	public com.alipay.sofa.rpc.server.UserThreadPool setThreadPoolName(String threadPoolName) {
		return super.setThreadPoolName(threadPoolName);
	}
}