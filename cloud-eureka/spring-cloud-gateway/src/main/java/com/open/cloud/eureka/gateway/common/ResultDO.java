package com.open.cloud.eureka.gateway.common;

import java.io.*;

public class ResultDO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private Object data;
    
    public String getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public ResultDO setCode(final String code) {
        this.code = code;
        return this;
    }
    
    public ResultDO setMessage(final String message) {
        this.message = message;
        return this;
    }
    
    public ResultDO setData(final Object data) {
        this.data = data;
        return this;
    }
}
