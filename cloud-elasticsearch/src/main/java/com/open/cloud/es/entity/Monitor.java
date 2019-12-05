package com.open.cloud.es.entity;

import lombok.Data;

@Data
public class Monitor {
    private String index;
    private String id;
    private String transDate;
    private String startTime;
    private String endTime;
    private String sofaTraceId;
    private String bankSrlNo;
    private String channelId;
    private String cardNo;
    private String tranAmt;
    private String messageType;
    private String messageCode;
    private String messageDesc;
    private String retCode;
    private String retMsg;
    private String hostName;
    private String ip;
    private long spendTime;
}
