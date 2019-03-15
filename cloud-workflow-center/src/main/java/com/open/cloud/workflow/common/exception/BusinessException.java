package com.open.cloud.workflow.common.exception;

import com.open.cloud.workflow.common.utils.MessageUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author leijian
 */
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;


    public BusinessException(final String code, Throwable cause) {
        super(code, cause);
    }

    public BusinessException(final String code) {
        this.code = code;
    }

    public BusinessException(final String code, final Object[] args) {
        this.code = code;
        this.args = args;
    }

    private String code;

    private transient Object[]  args;

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        } else {
            message = code;
        }
        return message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(final Object[] args) {
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
