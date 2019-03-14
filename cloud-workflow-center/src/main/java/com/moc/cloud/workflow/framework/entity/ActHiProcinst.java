package com.moc.cloud.workflow.framework.entity;
import java.time.LocalDateTime;

/**
 * 流程实例ID(ACT_HI_PROCINST)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActHiProcinst implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 3733079190312985461L;

    /** 作为task表等表的外键 */
    private String procInstId;

    /** 业务关键字 */
    private String businessKey;

    private String procDefKey;

    /** act_re_procdef的ID */
    private String procDefId;

    /** 流程起始时间 */
    private LocalDateTime startTime;

    /** 流程结束时间 */
    private LocalDateTime endTime;

    /** 流程耗时 */
    private Long duration;

    /** startUserId */
    private String startUserId;

    /** 起始节点号 */
    private String startActId;

    /** 结束节点号 */
    private String endActId;

    /** superProcessInstanceId */
    private String superProcessInstanceId;

    /** 删除原因eg完成 */
    private String deleteReason;

    /** tenantId */
    private String tenantId;

    /** 流程实例描述信息 */
    private String name;

    /** 业务人员ID */
    private String userId;

    /** 流程状态1销毁 */
    private Boolean suspensionState;

    /**
     * 获取作为task表等表的外键
     * 
     * @return 作为task表等表的外键
     */
    public String getProcInstId() {
        return this.procInstId;
    }

    /**
     * 设置作为task表等表的外键
     * 
     * @param procInstId
     *          作为task表等表的外键
     */
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * 获取业务关键字
     * 
     * @return 业务关键字
     */
    public String getBusinessKey() {
        return this.businessKey;
    }

    /**
     * 设置业务关键字
     * 
     * @param businessKey
     *          业务关键字
     */
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * 获取act_re_procdef的ID
     * 
     * @return act_re_procdef的ID
     */
    public String getProcDefId() {
        return this.procDefId;
    }

    /**
     * 设置act_re_procdef的ID
     * 
     * @param procDefId
     *          act_re_procdef的ID
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * 获取流程起始时间
     * 
     * @return 流程起始时间
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * 设置流程起始时间
     * 
     * @param startTime
     *          流程起始时间
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取流程结束时间
     * 
     * @return 流程结束时间
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * 设置流程结束时间
     * 
     * @param endTime
     *          流程结束时间
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取流程耗时
     * 
     * @return 流程耗时
     */
    public Long getDuration() {
        return this.duration;
    }

    /**
     * 设置流程耗时
     * 
     * @param duration
     *          流程耗时
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * 获取startUserId
     * 
     * @return startUserId
     */
    public String getStartUserId() {
        return this.startUserId;
    }

    /**
     * 设置startUserId
     * 
     * @param startUserId
     */
    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    /**
     * 获取起始节点号
     * 
     * @return 起始节点号
     */
    public String getStartActId() {
        return this.startActId;
    }

    /**
     * 设置起始节点号
     * 
     * @param startActId
     *          起始节点号
     */
    public void setStartActId(String startActId) {
        this.startActId = startActId;
    }

    /**
     * 获取结束节点号
     * 
     * @return 结束节点号
     */
    public String getEndActId() {
        return this.endActId;
    }

    /**
     * 设置结束节点号
     * 
     * @param endActId
     *          结束节点号
     */
    public void setEndActId(String endActId) {
        this.endActId = endActId;
    }

    /**
     * 获取superProcessInstanceId
     * 
     * @return superProcessInstanceId
     */
    public String getSuperProcessInstanceId() {
        return this.superProcessInstanceId;
    }

    /**
     * 设置superProcessInstanceId
     * 
     * @param superProcessInstanceId
     */
    public void setSuperProcessInstanceId(String superProcessInstanceId) {
        this.superProcessInstanceId = superProcessInstanceId;
    }

    /**
     * 获取删除原因eg完成
     * 
     * @return 删除原因eg完成
     */
    public String getDeleteReason() {
        return this.deleteReason;
    }

    /**
     * 设置删除原因eg完成
     * 
     * @param deleteReason
     *          删除原因eg完成
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

    /**
     * 获取流程实例描述信息
     * 
     * @return 流程实例描述信息
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置流程实例描述信息
     * 
     * @param name
     *          流程实例描述信息
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取业务人员ID
     * 
     * @return 业务人员ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置业务人员ID
     * 
     * @param userId
     *          业务人员ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProcDefKey() {
        return procDefKey;
    }

    public void setProcDefKey(final String procDefKey) {
        this.procDefKey = procDefKey;
    }

    public boolean getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(final boolean suspensionState) {
        this.suspensionState = suspensionState;
    }
}