package com.moc.cloud.workflow.framework.entity;

import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 流程定义节点信息(REPROCDEF_FLOW)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
@ToString
public class ReprocdefFlow implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2985481247493399931L;

    /** 流程定义时XML的ID */
    private String key;

    /** 节点ID */
    private String sid;

    /** 流程定义的任务ID */
    private String taskDefKey;

    /** 开始事件、结束事件、 */
    private String sidType;

    /** 流程描述 */
    private String sidTitle;

    /** 初始流程节数 */
    private Integer initNum;

    /** 版本号 */
    private String version;

    /** 可用开关 0不可用代表false */
    private Boolean state;

    /** 创建时间 */
    private LocalDateTime createtime;

    /** 创建人 */
    private String creator;

    /**
     * 获取流程定义时XML的ID
     * 
     * @return 流程定义时XML的ID
     */
    public String getKey() {
        return this.key;
    }

    /**
     * 设置流程定义时XML的ID
     * 
     * @param key
     *          流程定义时XML的ID
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取节点ID
     * 
     * @return 节点ID
     */
    public String getSid() {
        return this.sid;
    }

    /**
     * 设置节点ID
     * 
     * @param sid
     *          节点ID
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取流程定义的任务ID
     * 
     * @return 流程定义的任务ID
     */
    public String getTaskDefKey() {
        return this.taskDefKey;
    }

    /**
     * 设置流程定义的任务ID
     * 
     * @param taskDefKey
     *          流程定义的任务ID
     */
    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    /**
     * 获取开始事件、结束事件、
     * 
     * @return 开始事件、结束事件、
     */
    public String getSidType() {
        return this.sidType;
    }

    /**
     * 设置开始事件、结束事件、
     * 
     * @param sidType
     *          开始事件、结束事件、
     */
    public void setSidType(String sidType) {
        this.sidType = sidType;
    }

    /**
     * 获取流程描述
     * 
     * @return 流程描述
     */
    public String getSidTitle() {
        return this.sidTitle;
    }

    /**
     * 设置流程描述
     * 
     * @param sidTitle
     *          流程描述
     */
    public void setSidTitle(String sidTitle) {
        this.sidTitle = sidTitle;
    }

    /**
     * 获取初始流程节数
     * 
     * @return 初始流程节数
     */
    public Integer getInitNum() {
        return this.initNum;
    }

    /**
     * 设置初始流程节数
     * 
     * @param initNum
     *          初始流程节数
     */
    public void setInitNum(Integer initNum) {
        this.initNum = initNum;
    }

    /**
     * 获取版本号
     * 
     * @return 版本号
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * 设置版本号
     * 
     * @param version
     *          版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取可用开关 0不可用代表false
     * 
     * @return 可用开关 0不可用代表false
     */
    public Boolean getState() {
        return this.state;
    }

    /**
     * 设置可用开关 0不可用代表false
     * 
     * @param state
     *          可用开关 0不可用代表false
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public LocalDateTime getCreatetime() {
        return this.createtime;
    }

    /**
     * 设置创建时间
     * 
     * @param createtime
     *          创建时间
     */
    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取创建人
     * 
     * @return 创建人
     */
    public String getCreator() {
        return this.creator;
    }

    /**
     * 设置创建人
     * 
     * @param creator
     *          创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}