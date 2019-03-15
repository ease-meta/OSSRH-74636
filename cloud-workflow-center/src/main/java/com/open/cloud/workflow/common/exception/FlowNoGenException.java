package com.open.cloud.workflow.common.exception;

/**
 * @Classname: FlowNoGenException
 * @Description:
 * @Author: leijian
 * @Date: 2019/1/12
 * @Version: 1.0
 */
public class FlowNoGenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FlowNoGenException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowNoGenException(String message) {
        super(message);
    }
}

