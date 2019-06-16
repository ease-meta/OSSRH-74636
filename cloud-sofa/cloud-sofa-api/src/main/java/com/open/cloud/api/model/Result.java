package com.open.cloud.api.model;

import lombok.Data;

@Data
public class Result extends BaseData {

    private String retCode;

    private String retMsg;

    public Result(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
}