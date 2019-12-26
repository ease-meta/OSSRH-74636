package com.open.cloud.eureka.gateway.model;

public class SystemHeader
{
    private String svcCd;
    private String scnCd;
    private String busiCd;
    private String cnlCd;
    private String cnsmrSysNo;
    private String hostCd;
    private String instNo;
    private String tmlIndNo;
    
    public SystemHeader setSvcCd(final String svcCd) {
        this.svcCd = svcCd;
        return this;
    }
    
    public SystemHeader setScnCd(final String scnCd) {
        this.scnCd = scnCd;
        return this;
    }
    
    public SystemHeader setBusiCd(final String busiCd) {
        this.busiCd = busiCd;
        return this;
    }
    
    public SystemHeader setCnlCd(final String cnlCd) {
        this.cnlCd = cnlCd;
        return this;
    }
    
    public SystemHeader setCnsmrSysNo(final String cnsmrSysNo) {
        this.cnsmrSysNo = cnsmrSysNo;
        return this;
    }
    
    public SystemHeader setHostCd(final String hostCd) {
        this.hostCd = hostCd;
        return this;
    }
    
    public SystemHeader setInstNo(final String instNo) {
        this.instNo = instNo;
        return this;
    }
    
    public SystemHeader setTmlIndNo(final String tmlIndNo) {
        this.tmlIndNo = tmlIndNo;
        return this;
    }
    
    public String getSvcCd() {
        return this.svcCd;
    }
    
    public String getScnCd() {
        return this.scnCd;
    }
    
    public String getBusiCd() {
        return this.busiCd;
    }
    
    public String getCnlCd() {
        return this.cnlCd;
    }
    
    public String getCnsmrSysNo() {
        return this.cnsmrSysNo;
    }
    
    public String getHostCd() {
        return this.hostCd;
    }
    
    public String getInstNo() {
        return this.instNo;
    }
    
    public String getTmlIndNo() {
        return this.tmlIndNo;
    }
}
