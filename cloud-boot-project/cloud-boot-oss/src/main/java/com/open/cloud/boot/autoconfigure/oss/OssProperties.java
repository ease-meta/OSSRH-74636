package com.open.cloud.boot.autoconfigure.oss;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * The type Oss properties.
 * @author Leijian
 * @date 2021 /1/18 20:48
 * @since
 */
@ConfigurationProperties(prefix = OssConstants.PREFIX)
public class OssProperties implements BeanClassLoaderAware, InitializingBean {
	private Class<? extends FileBase> type;

	private ClassLoader classLoader;

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}
}
