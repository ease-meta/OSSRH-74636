package com.open.cloud.workflow.framework.entity;
import java.time.LocalDateTime;

/**
 * 历史行为表信息(ACT_HI_ACTINST)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActHiActinst implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 4784775393354890651L;

    /** id */
    private String id;

    /** procDefId */
    private String procDefId;

    /** procInstId */
    private String procInstId;

    /** executionId */
    private String executionId;

    /** actId */
    private String actId;

    /** taskId */
    private String taskId;

    /** callProcInstId */
    private String callProcInstId;

    /** actName */
    private String actName;

    /** actType */
    private String actType;

    /** assignee */
    private String assignee;


    /** startTime */
    private LocalDateTime startTime;

    /** endTime */
    private LocalDateTime endTime;

    /** duration */
    private Long duration;

    /** deleteReason */
    private String deleteReason;

    /** tenantId */
    private String tenantId;

    private String dealingUserId;

    public String getDealingUserId() {
        return dealingUserId;
    }

    public void setDealingUserId(final String dealingUserId) {
        this.dealingUserId = dealingUserId;
    }

    /**
     * 获取id
     * 
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取procDefId
     * 
     * @return procDefId
     */
    public String getProcDefId() {
        return this.procDefId;
    }

    /**
     * 设置procDefId
     * 
     * @param procDefId
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * 获取procInstId
     * 
     * @return procInstId
     */
    public String getProcInstId() {
        return this.procInstId;
    }

    /**
     * 设置procInstId
     * 
     * @param procInstId
     */
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * 获取executionId
     * 
     * @return executionId
     */
    public String getExecutionId() {
        return this.executionId;
    }

    /**
     * 设置executionId
     * 
     * @param executionId
     */
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    /**
     * 获取actId
     * 
     * @return actId
     */
    public String getActId() {
        return this.actId;
    }

    /**
     * 设置actId
     * 
     * @param actId
     */
    public void setActId(String actId) {
        this.actId = actId;
    }

    /**
     * 获取taskId
     * 
     * @return taskId
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 设置taskId
     * 
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取callProcInstId
     * 
     * @return callProcInstId
     */
    public String getCallProcInstId() {
        return this.callProcInstId;
    }

    /**
     * 设置callProcInstId
     * 
     * @param callProcInstId
     */
    public void setCallProcInstId(String callProcInstId) {
        this.callProcInstId = callProcInstId;
    }

    /**
     * 获取actName
     * 
     * @return actName
     */
    public String getActName() {
        return this.actName;
    }

    /**
     * 设置actName
     * 
     * @param actName
     */
    public void setActName(String actName) {
        this.actName = actName;
    }

    /**
     * 获取actType
     * 
     * @return actType
     */
    public String getActType() {
        return this.actType;
    }

    /**
     * 设置actType
     * 
     * @param actType
     */
    public void setActType(String actType) {
        this.actType = actType;
    }

    /**
     * 获取assignee
     * 
     * @return assignee
     */
    public String getAssignee() {
        return this.assignee;
    }

    /**
     * 设置assignee
     * 
     * @param assignee
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * 获取startTime
     * 
     * @return startTime
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * 设置startTime
     * 
     * @param startTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取endTime
     * 
     * @return endTime
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * 设置endTime
     * 
     * @param endTime
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取duration
     * 
     * @return duration
     */
    public Long getDuration() {
        return this.duration;
    }

    /**
     * 设置duration
     * 
     * @param duration
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * 获取deleteReason
     * 
     * @return deleteReason
     */
    public String getDeleteReason() {
        return this.deleteReason;
    }

    /**
     * 设置deleteReason
     * 
     * @param deleteReason
     */
    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    /**
     * 获取tenantId
     * 
     * @return tenantId
     */
    public String getTenantId() {
        return this.tenantId;
    }

    /**
     * 设置tenantId
     * 
     * @param tenantId
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}