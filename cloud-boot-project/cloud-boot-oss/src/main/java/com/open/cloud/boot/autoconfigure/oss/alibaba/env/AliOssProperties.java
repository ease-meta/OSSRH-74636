package com.open.cloud.boot.autoconfigure.oss.alibaba.env;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.open.cloud.boot.autoconfigure.oss.alibaba.AliCloudAuthorizationMode;

/**
 * The type Ali oss properties.
 */
public class AliOssProperties {

	/**
	 * Authorization Mode, please see <a href=
	 * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
	 * docs</a>.
	 */
	//@Value("${" + PREFIX + ".authorization-mode:AK_SK}")
	private AliCloudAuthorizationMode authorizationMode;

	/**
	 * Endpoint, please see <a href=
	 * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
	 * docs</a>.
	 */
	private String endpoint;

	/**
	 * Sts token, please see <a href=
	 * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
	 * docs</a>.
	 */
	private StsToken sts;

	/**
	 * Client Configuration, please see <a href=
	 * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
	 * docs</a>.
	 */
	private ClientBuilderConfiguration config;

	/**
	 * Gets authorization mode.
	 *
	 * @return the authorization mode
	 */
	public AliCloudAuthorizationMode getAuthorizationMode() {
		return authorizationMode;
	}

	/**
	 * Sets authorization mode.
	 *
	 * @param authorizationMode the authorization mode
	 */
	public void setAuthorizationMode(AliCloudAuthorizationMode authorizationMode) {
		this.authorizationMode = authorizationMode;
	}

	/**
	 * Gets config.
	 *
	 * @return the config
	 */
	public ClientBuilderConfiguration getConfig() {
		return config;
	}

	/**
	 * Sets config.
	 *
	 * @param config the config
	 */
	public void setConfig(ClientBuilderConfiguration config) {
		this.config = config;
	}

	/**
	 * Gets endpoint.
	 *
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * Sets endpoint.
	 *
	 * @param endpoint the endpoint
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * Gets sts.
	 *
	 * @return the sts
	 */
	public StsToken getSts() {
		return sts;
	}

	/**
	 * Sets sts.
	 *
	 * @param sts the sts
	 */
	public void setSts(StsToken sts) {
		this.sts = sts;
	}

	/**
	 * The type Sts token.
	 */
	public static class StsToken {

		private String accessKey;

		private String secretKey;

		private String securityToken;

		/**
		 * Gets access key.
		 *
		 * @return the access key
		 */
		public String getAccessKey() {
			return accessKey;
		}

		/**
		 * Sets access key.
		 *
		 * @param accessKey the access key
		 */
		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}

		/**
		 * Gets secret key.
		 *
		 * @return the secret key
		 */
		public String getSecretKey() {
			return secretKey;
		}

		/**
		 * Sets secret key.
		 *
		 * @param secretKey the secret key
		 */
		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

		/**
		 * Gets security token.
		 *
		 * @return the security token
		 */
		public String getSecurityToken() {
			return securityToken;
		}

		/**
		 * Sets security token.
		 *
		 * @param securityToken the security token
		 */
		public void setSecurityToken(String securityToken) {
			this.securityToken = securityToken;
		}

	}

}