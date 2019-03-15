package com.open.cloud.workflow.framework.entity;
/**
 * 流程实例与身份关系表(ACT_RU_IDENTITYLINK)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActRuIdentitylink implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -4841164493857938154L;

    /** id */
    private String id;

    /** rev */
    private Integer rev;

    /** roleId */
    private String roleId;

    /** type */
    private String type;

    /** userId */
    private String userId;

    /** taskId */
    private String taskId;

    /** procInstId */
    private String procInstId;

    /** procDefId */
    private String procDefId;

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
     * 获取rev
     * 
     * @return rev
     */
    public Integer getRev() {
        return this.rev;
    }

    /**
     * 设置rev
     * 
     * @param rev
     */
    public void setRev(Integer rev) {
        this.rev = rev;
    }

    /**
     * 获取roleId
     * 
     * @return roleId
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 设置roleId
     * 
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取type
     * 
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置type
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取userId
     * 
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置userId
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
}