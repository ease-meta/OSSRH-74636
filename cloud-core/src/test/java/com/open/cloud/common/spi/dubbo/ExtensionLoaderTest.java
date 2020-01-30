package com.open.cloud.common.spi.dubbo;

import com.open.cloud.common.spi.java.JavaSPI;
import org.testng.annotations.Test;


public class ExtensionLoaderTest {

	@Test
	public void testLoadFirst() {
		JavaSPI javaSPI = ExtensionLoader.getExtensionLoader(JavaSPI.class).getExtension("JavaSPIService");

	}
}