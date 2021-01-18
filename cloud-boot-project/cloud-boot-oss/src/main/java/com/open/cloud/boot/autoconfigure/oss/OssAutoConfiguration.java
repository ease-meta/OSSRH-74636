package com.open.cloud.boot.autoconfigure.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.open.cloud.boot.autoconfigure.oss.alibaba.AliOssProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Leijian
 * @date 2021/1/18
 */
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

	static class AliOss {
		@ConditionalOnMissingBean
		@Bean
		public OSS ossClient(AliOssProperties ossProperties) {
			if (ossProperties.getAuthorizationMode() == AliCloudAuthorizationMode.AK_SK) {
				Assert.isTrue(!StringUtils.isEmpty(ossProperties.getEndpoint()),
						"Oss endpoint can't be empty.");
				Assert.isTrue(!StringUtils.isEmpty(aliCloudProperties.getAccessKey()),
						"${alibaba.cloud.access-key} can't be empty.");
				Assert.isTrue(!StringUtils.isEmpty(aliCloudProperties.getSecretKey()),
						"${alibaba.cloud.secret-key} can't be empty.");
				return new OSSClientBuilder().build(ossProperties.getEndpoint(),
						aliCloudProperties.getAccessKey(), aliCloudProperties.getSecretKey(),
						ossProperties.getConfig());
			} else if (ossProperties.getAuthorizationMode() == AliCloudAuthorizationMode.STS) {
				Assert.isTrue(!StringUtils.isEmpty(ossProperties.getEndpoint()),
						"Oss endpoint can't be empty.");
				Assert.isTrue(!StringUtils.isEmpty(ossProperties.getSts().getAccessKey()),
						"Access key can't be empty.");
				Assert.isTrue(!StringUtils.isEmpty(ossProperties.getSts().getSecretKey()),
						"Secret key can't be empty.");
				Assert.isTrue(!StringUtils.isEmpty(ossProperties.getSts().getSecurityToken()),
						"Security Token can't be empty.");
				return new OSSClientBuilder().build(ossProperties.getEndpoint(),
						ossProperties.getSts().getAccessKey(),
						ossProperties.getSts().getSecretKey(),
						ossProperties.getSts().getSecurityToken(), ossProperties.getConfig());
			} else {
				throw new IllegalArgumentException("Unknown auth mode.");
			}
		}
	}
}
