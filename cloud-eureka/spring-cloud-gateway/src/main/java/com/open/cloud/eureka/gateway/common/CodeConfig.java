package com.open.cloud.eureka.gateway.common;

import org.springframework.stereotype.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import java.util.*;

@Component
@ConfigurationProperties(prefix = "jupiter")
@PropertySource({ "classpath:/code.properties" })
public class CodeConfig
{
    private static final String UNKNOWN_CODE = "未知的错误代码";
    private static Map<String, String> codeProp;
    
    public Map<String, String> getCodeProp() {
        return CodeConfig.codeProp;
    }
    
    public static void setCodeProp(final Map<String, String> codeProp) {
        CodeConfig.codeProp = codeProp;
    }
    
    public static String getMessage(final String code) {
        if (CodeConfig.codeProp.containsKey(code)) {
            return CodeConfig.codeProp.get(code);
        }
        return "未知的错误代码";
    }
    
    static {
        CodeConfig.codeProp = new HashMap<String, String>();
    }
}
