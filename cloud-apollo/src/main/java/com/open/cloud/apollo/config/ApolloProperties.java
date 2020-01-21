package com.open.cloud.apollo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Leijian
 */
@ConfigurationProperties(value = ApolloConstant.prefix)
@Data
public class ApolloProperties {
	private String appId;
	private String meta;
	private boolean bootstrapEnabled;
	private String bootstrapNamespaces;
	private boolean bootstrapEagerLoadEnabled;
	private String cacheDir;
	private String userDir;
	private boolean enabled;
}
