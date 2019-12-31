package com.open.cloud.framework.guice;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

public interface Service {
	void process();
}

@Slf4j
@Singleton
		// 这个注解告诉Guice，该实现全局单例
class DefaultService implements Service {
	@Override
	public void process() {
		log.info("2020年");
	}
}

@Slf4j
class DefaultService2 implements Service {
	@Override
	public void process() {
		log.info("20201");
	}
}