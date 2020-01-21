package com.open.cloud.common.spi.google;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.open.cloud.common.exception.BaseException;

/**
 * @author Leijian
 */
public class DefaultInjector implements Injector {

	private com.google.inject.Injector m_injector;

	public DefaultInjector() {
		try {
			m_injector = Guice.createInjector(new CloudModule());
		} catch (Throwable ex) {
			throw new BaseException(ex);
		}
	}

	@Override
	public <T> T getInstance(Class<T> clazz) {
		try {
			return m_injector.getInstance(clazz);
		} catch (Throwable ex) {
			throw new BaseException(String.format("Unable to load instance for %s!", clazz.getName()), ex);
		}
	}

	@Override
	public <T> T getInstance(Class<T> clazz, String name) {
		//Guice does not support get instance by type and name
		return null;
	}

	private static class CloudModule extends AbstractModule {
		@Override
		protected void configure() {

		}
	}
}
