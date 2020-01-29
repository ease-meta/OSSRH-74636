package com.open.cloud.common.spi.dubbo;

/**
 * @author Leijian
 */
public class SpiExtensionFactory implements ExtensionFactory {

	@Override
	public <T> T getExtension(Class<T> type, String name) {
		if (type.isInterface() && type.isAnnotationPresent(SPI.class)) {
			ExtensionLoader<T> loader = ExtensionLoader.getExtensionLoader(type);
			if (!loader.getSupportedExtensions().isEmpty()) {
				return loader.getAdaptiveExtension();
			}
		}
		return null;
	}

}
