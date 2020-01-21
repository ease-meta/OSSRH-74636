package com.open.cloud.common.spi.java.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public final class CloudModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Service.class).to(DefaultService.class).in(Scopes.SINGLETON);
	}
}