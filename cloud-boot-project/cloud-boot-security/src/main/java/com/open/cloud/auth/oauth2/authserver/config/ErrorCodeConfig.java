package com.open.cloud.auth.oauth2.authserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guankai created on 2018/8/7.
 */
@Component
@ConfigurationProperties(prefix = "error")
@PropertySource("classpath:/error.properties")
@SuppressWarnings("all")
public class ErrorCodeConfig {

  private static final String UNKNOWN_ERROR_CODE = "未知的错误代码，";

  private static Map<String, String> code = new HashMap<String, String>();

  public static String getErrorMessage(String errorCode) {
    if (code.containsKey(errorCode)) {
      return code.get(errorCode);
    }
    return UNKNOWN_ERROR_CODE;
  }

  public Map<String, String> getCode() {
    return code;
  }

  public void setCode(Map<String, String> code) {
    ErrorCodeConfig.code = code;
  }
}
