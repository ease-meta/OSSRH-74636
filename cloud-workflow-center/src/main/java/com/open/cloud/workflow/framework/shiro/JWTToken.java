package com.open.cloud.workflow.framework.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 
 * @author leijian
 * @date 2019年1月26日
 */
public class JWTToken implements AuthenticationToken {

	private static final long serialVersionUID = 8596499123592988933L;
	private final String token;

	public JWTToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}