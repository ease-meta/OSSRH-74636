package org.elasticsearch.entity;

import lombok.Data;

/**
 * @ClassName: Monitor
 * @Description: java类作用描述
 * @Author: liyuq
 * @CreateDate: 2019/9/4 10:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
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
