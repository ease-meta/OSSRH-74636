package com.open.cloud.auth.handler;

import com.alibaba.fastjson.JSON;
import com.open.cloud.common.base.Response;
import com.open.cloud.common.base.ResponseTool;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 认证处理成功
 *
 * @author Leijian
 * @date 2020/2/8
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		Response responses = ResponseTool.response(HttpServletResponse.SC_OK, "Login successfully");
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		httpServletResponse.getWriter().write(JSON.toJSONString(responses));
	}

}