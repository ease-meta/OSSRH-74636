package com.open.cloud.auth.oauth2.authserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.cloud.auth.oauth2.authserver.constant.ErrorCode;
import com.open.cloud.auth.oauth2.authserver.domain.dto.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setContentType("application/json;charset=UTF-8");
    Map<String, String> map = new HashMap<>();
//        map.put("exception", accessDeniedException.getMessage());
    map.put("path", request.getServletPath());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    map.put("reqTime", sdf.format(new Date()));
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(objectMapper.writeValueAsString(new CommonResult()
            .setCode(ErrorCode.NO_AUTH)
            .setMessage(ErrorCodeConfig.getErrorMessage(ErrorCode.NO_AUTH))
            .setData(map)));
  }


}
