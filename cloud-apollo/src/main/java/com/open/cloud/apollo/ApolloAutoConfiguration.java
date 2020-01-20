package com.open.cloud.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.open.cloud.apollo.config.ApolloProperties;
import com.open.cloud.common.utils.LogUtils;
import com.open.cloud.common.utils.PropertyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "open.cloud.apollo.enabled", havingValue = "true")
@EnableApolloConfig
public class ApolloAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() {
		LogUtils.info(ApolloAutoConfiguration.class, ApolloProperties.Project, "已启动!!!" + " " + ApolloProperties.ApolloMeta + "=" + PropertyUtils.getPropertyCache(ApolloProperties.ApolloMeta, ""));
	}

}