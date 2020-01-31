package com.open.cloud.common.spi.dubbo;


@SPI()
public interface ExtensionFactory {

	<T> T getExtension(Class<T> type, String name);
}