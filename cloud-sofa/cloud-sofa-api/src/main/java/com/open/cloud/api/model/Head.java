package com.open.cloud.api.model;

import lombok.Data;

@Data
public class Head {
    private String serviceId;
    private String transId;
    private String productCode;
    private String bankSrlNo;
    private String subTransSeqNo;
    private String clientId;
    private String branchId;
    private String uesrId;
    private String apprUserId;
    private String authUserId;
    private String apprFlag;
    private String authFlag;
    private String company;
    private String userLang;
    private String channelId;
    private String wsId;
    private String requestServiceId;
    private String requesterId;
    private String requestServerId;
    private String systemId;
    private String tranDate;
    private String tranTimestamp;
    private String upFilePath;
    private String pressureFlag;
    private String sourceBranchNo;
    private String destBranchNo;
    private String macValue;
}