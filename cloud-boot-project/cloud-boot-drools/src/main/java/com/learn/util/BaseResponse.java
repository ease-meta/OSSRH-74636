package com.learn.util;

/**
 * Description: .
 *
 * @author : ys.
 * @date :
 */
public class BaseResponse<T> {

    private T message;

    public BaseResponse() {

    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
