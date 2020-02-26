package com.open.cloud.common.log;


/**
 * @author Leijian
 * @version 1.0
 */
public enum MDCConstans {
    /**
     * 卡号
     */
    CARD_NO("cardNo"),

    /**
     * 交易金额
     */
    TRAN_AMT("tranAmt"),
    /**
     * 交易渠道
     */
    CHANNEL_ID("channelId"),

    TRANMODE("tranMode"),

    TIMER("timer"),

    BATCH("batch"),

    COMET("comet"),

    MONITOR("monitor"),

    BATCHTRACEID("batchTraceId"),

    STEPNAME("stepName"),

    COMET_TRACEID("CometTraceId"),

    BANK_SRL_NO("bankSrlNo"),

    TRANS_ID("transId");

    private String MDCCode;

    MDCConstans(String MDCCode) {
        this.MDCCode = MDCCode;
    }

    public String getMDCCode() {
        return MDCCode;
    }

}
