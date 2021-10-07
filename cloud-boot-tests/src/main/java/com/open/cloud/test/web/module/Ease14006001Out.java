package com.open.cloud.test.web.module;

import com.open.cloud.core.domain.BaseResponse;

import java.time.LocalDateTime;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 18:09
 */
public class Ease14006001Out extends BaseResponse {

    private String name;

    private LocalDateTime localDateTime = LocalDateTime.now();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
