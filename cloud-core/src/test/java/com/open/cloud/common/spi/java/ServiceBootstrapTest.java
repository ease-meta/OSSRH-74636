package com.open.cloud.common.spi.java;

public class ServiceBootstrapTest {

	@org.testng.annotations.Test
	public void testLoadFirst() {
		JavaSPI javaSPI = ServiceBootstrap.loadFirst(JavaSPI.class);
		JavaSPI javaSPI1 = ServiceBootstrap.loadFirst(JavaSPI.class);
	}
}