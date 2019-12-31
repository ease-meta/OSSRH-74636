package com.open.cloud.framework.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorFactory {

	public static Injector getInjector() {
		return InjectorFactoryHolder.injector;
	}

	private static class InjectorFactoryHolder {

		private static Injector injector;

		static {
			injector = Guice.createInjector(new CloudModule());
		}

	}
}
