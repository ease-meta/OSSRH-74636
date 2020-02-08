package com.open.cloud.auth.handler;

import com.alibaba.fastjson.JSON;
import com.open.cloud.common.base.Response;
import com.open.cloud.common.base.ResponseTool;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 退出成功处理器
 * @author Leijian
 * @date   2020/2/8
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Response responses = ResponseTool.response(HttpServletResponse.SC_OK, "Exit successfully");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(JSON.toJSONString(responses));
    }

}