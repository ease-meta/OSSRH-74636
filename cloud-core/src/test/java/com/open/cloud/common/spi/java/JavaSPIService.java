package com.open.cloud.common.spi.java;

public class JavaSPIService implements JavaSPI {
	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public String supportName() {
		return null;
	}
}
