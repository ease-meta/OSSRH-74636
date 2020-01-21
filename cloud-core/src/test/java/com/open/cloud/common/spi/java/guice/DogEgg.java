package com.open.cloud.common.spi.java.guice;

import com.google.inject.Inject;

public class DogEgg {
	@Inject //告诉Guice，这里要注入东西，具体的注入规则从Module里找吧
	public Service service;

	public void work() {
		service.process();
	}
}