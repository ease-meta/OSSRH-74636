package com.open.cloud.common.spi.google;

import com.open.cloud.common.exception.BaseException;

/**
 * @author Leijian
 */
public class CloudInjector {
	private static volatile Injector s_injector;
	private static final Object lock = new Object();

	private static Injector getInjector() {
		if (s_injector == null) {
			synchronized (lock) {
				if (s_injector == null) {
					try {
						s_injector = ServiceBootstrap.loadFirst(Injector.class);
					} catch (Throwable ex) {
						throw new BaseException(ex);
					}
				}
			}
		}

		return s_injector;
	}

	public static <T> T getInstance(Class<T> clazz) {
		try {
			return getInjector().getInstance(clazz);
		} catch (Throwable ex) {
			throw new BaseException(String.format("Unable to load instance for type %s!", clazz.getName()), ex);
		}
	}

	public static <T> T getInstance(Class<T> clazz, String name) {
		try {
			return getInjector().getInstance(clazz, name);
		} catch (Throwable ex) {
			throw new BaseException(
					String.format("Unable to load instance for type %s and name %s !", clazz.getName(), name), ex);
		}
	}
}
