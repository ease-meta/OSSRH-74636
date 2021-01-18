package com.open.cloud.boot.autoconfigure.oss;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author Leijian
 * @date 2021/1/18 20:48
 * @since
 */
@ConfigurationProperties(prefix = OssConstants.PREFIX)
public class OssProperties implements BeanClassLoaderAware, InitializingBean {
	private Class<? extends FileBase> type;

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}
}
