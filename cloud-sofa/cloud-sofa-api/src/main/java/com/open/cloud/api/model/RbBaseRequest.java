package com.open.cloud.api.model;

import lombok.Data;

@Data
public class RbBaseRequest {

    private String clientNo;

    private String baseAcctNo;

    private String prodType;

    private String ccy;

    private String acctSeqNo;

    private String eventType;

}