package com.moc.cloud.workflow.framework.entity;
import lombok.ToString;

import java.util.Date;

/**
 * 审批意见表和流程中的附件信息表(ACT_HI_COMMENT)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
@ToString
public class ActHiComment implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 3633452877101034756L;

    /** id */
    private String id;

    /** type */
    private String type;

    /** time */
    private Date time;

    /** userId */
    private String userId;

    /** taskId */
    private String taskId;

    /** procInstId */
    private String procInstId;

    /** action */
    private String action;

    /** message */
    private String message;

    /** url */
    private String url;

    /** contentId */
    private String contentId;

    /** fullMsg */
    private String fullMsg;

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
     * 获取time
     * 
     * @return time
     */
    public Date getTime() {
        return this.time;
    }

    /**
     * 设置time
     * 
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
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
     * 获取action
     * 
     * @return action
     */
    public String getAction() {
        return this.action;
    }

    /**
     * 设置action
     * 
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取message
     * 
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置message
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取url
     * 
     * @return url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置url
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取contentId
     * 
     * @return contentId
     */
    public String getContentId() {
        return this.contentId;
    }

    /**
     * 设置contentId
     * 
     * @param contentId
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * 获取fullMsg
     * 
     * @return fullMsg
     */
    public String getFullMsg() {
        return this.fullMsg;
    }

    /**
     * 设置fullMsg
     * 
     * @param fullMsg
     */
    public void setFullMsg(String fullMsg) {
        this.fullMsg = fullMsg;
    }
}