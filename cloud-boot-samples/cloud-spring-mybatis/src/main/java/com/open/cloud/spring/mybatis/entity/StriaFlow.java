package com.open.cloud.spring.mybatis.entity;

import java.io.Serializable;

/**
 * Stria流程定义(StriaFlow)实体类
 *
 * @author makejava
 * @since 2020-04-04 03:38:54
 */
public class StriaFlow implements Serializable {
    private static final long serialVersionUID = 872167078452792922L;
    /**
    * 流程ID
    */
    private String flowid;
    /**
    * 流程类型 01-服务流程；02-事件流程
    */
    private String flowType;
    /**
    * 流程描述
    */
    private String title;
    /**
    * 初始流程节数
    */
    private Double initNum;
    /**
    * 版本号
    */
    private String version;
    /**
    * 可用开关
    */
    private Double state;
    /**
    * 创建时间
    */
    private String createtime;
    /**
    * 创建人
    */
    private String creator;
    /**
    * 全局事物管理开关
    */
    private String dtpFlag;
    /**
    * 流程处理超时时间
    */
    private Double timeOut;
    /**
    * 产品类型
    */
    private String prodType;
    /**
    * 事件类型
    */
    private String eventType;
    /**
    * 事务开关
    */
    private String txFlag;
    /**
    * 读写分离开关
    */
    private String separateRw;


    public String getFlowid() {
        return flowid;
    }

    public void setFlowid(String flowid) {
        this.flowid = flowid;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getInitNum() {
        return initNum;
    }

    public void setInitNum(Double initNum) {
        this.initNum = initNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getState() {
        return state;
    }

    public void setState(Double state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDtpFlag() {
        return dtpFlag;
    }

    public void setDtpFlag(String dtpFlag) {
        this.dtpFlag = dtpFlag;
    }

    public Double getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Double timeOut) {
        this.timeOut = timeOut;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTxFlag() {
        return txFlag;
    }

    public void setTxFlag(String txFlag) {
        this.txFlag = txFlag;
    }

    public String getSeparateRw() {
        return separateRw;
    }

    public void setSeparateRw(String separateRw) {
        this.separateRw = separateRw;
    }

}