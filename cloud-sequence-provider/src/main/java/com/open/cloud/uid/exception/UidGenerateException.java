package com.open.cloud.uid.exception;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/2 17:19
 **/
public class UidGenerateException extends RuntimeException {
    public UidGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UidGenerateException(String message) {
        super(message);
    }

    public UidGenerateException(String message, String s) {
        super(message);
    }
}
