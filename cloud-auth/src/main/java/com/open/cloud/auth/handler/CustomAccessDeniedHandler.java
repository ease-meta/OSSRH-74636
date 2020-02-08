package com.open.cloud.auth.handler;

import com.alibaba.fastjson.JSON;
import com.open.cloud.common.base.Response;
import com.open.cloud.common.base.ResponseTool;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 拒绝访问处理器（登录状态下，无权限会触发）
 * @author Leijian
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Response<Object> responses = ResponseTool.response(HttpServletResponse.SC_OK, "authenticate fail");

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(responses));
    }
}
