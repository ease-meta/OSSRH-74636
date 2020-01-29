package com.open.cloud.common.spi.dubbo;

public interface ExtensionFactory {

	<T> T getExtension(Class<T> type, String name);
}