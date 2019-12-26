package com.open.cloud.eureka.gateway.exception;

import com.open.cloud.eureka.gateway.common.CodeConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonException extends RuntimeException
{
    @Autowired
    private CodeConfig codeConfig;
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    
    @Override
    public String getMessage() {
        if (null == this.msg) {
            return CodeConfig.getMessage(this.code);
        }
        return this.msg;
    }
    
    public CodeConfig getCodeConfig() {
        return this.codeConfig;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public CommonException setCodeConfig(final CodeConfig codeConfig) {
        this.codeConfig = codeConfig;
        return this;
    }
    
    public CommonException setCode(final String code) {
        this.code = code;
        return this;
    }
    
    public CommonException setMsg(final String msg) {
        this.msg = msg;
        return this;
    }
}
