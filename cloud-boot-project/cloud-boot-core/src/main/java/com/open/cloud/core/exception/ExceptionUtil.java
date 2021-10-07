package com.open.cloud.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 11:18
 */
public class ExceptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    public static BusinessException creatBusinessException(String errorCode, String errorMsg) {
        return new BusinessException(errorCode, errorMsg);
    }

    public static BusinessException creatBusinessException(String errorCode) {
        return new BusinessException(errorCode);
    }
}
