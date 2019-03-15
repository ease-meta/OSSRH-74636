/**
 * <p>Title: CloudToolsProperties.java</p>  
 * <p>Description: </p> 
 * @author leijian
 * @date 2019年3月14日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * DevToolsProperties
 * 
 * @author leijian
 * @date 2019年3月14日
 */
@ConfigurationProperties(prefix = "cloud.workflow")
@Data
public class CloudToolsProperties {

	private Shiro shiro = new Shiro();

	@Data
	public static class Shiro {
		private boolean enabled = true;

	}
}
