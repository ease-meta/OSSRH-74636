package com.open.cloud.apollo;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Leijian
 */
@Slf4j
public class ApolloConfigChange implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@ApolloConfigChangeListener
	private void onApolloConfigChangeListener(ConfigChangeEvent configChangeEvent) {
		configChangeEvent.changedKeys().forEach((v) -> {
			log.info("Found change: {}", v.toString());
		});
		this.applicationContext.publishEvent(new EnvironmentChangeEvent(configChangeEvent.changedKeys()));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}