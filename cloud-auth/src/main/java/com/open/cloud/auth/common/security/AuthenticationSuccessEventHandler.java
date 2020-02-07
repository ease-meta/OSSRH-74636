package com.open.cloud.auth.common.security;

import com.open.cloud.auth.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

/**
 * @author Leijian
 * @date 2020/2/7
 */
@Slf4j
public class AuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 登录成功", authentication.getPrincipal());
	}
}
