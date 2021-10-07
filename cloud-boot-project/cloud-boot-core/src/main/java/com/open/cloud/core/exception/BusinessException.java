package com.open.cloud.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 11:17
 */
public class BusinessException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);

    public BusinessException(String errorCode, String errorMsg) {
        super(errorCode + ":" + errorMsg);
    }

    public BusinessException(String errorCode) {
        super(errorCode);
    }
}
