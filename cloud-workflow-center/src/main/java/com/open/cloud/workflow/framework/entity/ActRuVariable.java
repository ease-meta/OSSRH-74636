package com.open.cloud.workflow.framework.entity;
/**
 * 任务参数(ACT_RU_VARIABLE)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActRuVariable implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3426722624655353778L;

    /** id */
    private String id;

    /** rev */
    private Integer rev;

    /** type */
    private String type;

    /** name */
    private String name;

    /** executionId */
    private String executionId;

    /** procInstId */
    private String procInstId;

    /** taskId */
    private String taskId;

    /** bytearrayId */
    private String bytearrayId;

    /** doubleValue */
    private Double doubleValue;

    /** longValue */
    private Long longValue;

    /** textValue */
    private String textValue;

    /** text2Value */
    private String text2Value;

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
     * 获取name
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取bytearrayId
     * 
     * @return bytearrayId
     */
    public String getBytearrayId() {
        return this.bytearrayId;
    }

    /**
     * 设置bytearrayId
     * 
     * @param bytearrayId
     */
    public void setBytearrayId(String bytearrayId) {
        this.bytearrayId = bytearrayId;
    }

    /**
     * 获取doubleValue
     * 
     * @return doubleValue
     */
    public Double getDoubleValue() {
        return this.doubleValue;
    }

    /**
     * 设置doubleValue
     * 
     * @param doubleValue
     */
    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    /**
     * 获取longValue
     * 
     * @return longValue
     */
    public Long getLongValue() {
        return this.longValue;
    }

    /**
     * 设置longValue
     * 
     * @param longValue
     */
    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    /**
     * 获取textValue
     * 
     * @return textValue
     */
    public String getTextValue() {
        return this.textValue;
    }

    /**
     * 设置textValue
     * 
     * @param textValue
     */
    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    /**
     * 获取text2Value
     * 
     * @return text2Value
     */
    public String getText2Value() {
        return this.text2Value;
    }

    /**
     * 设置text2Value
     * 
     * @param text2Value
     */
    public void setText2Value(String text2Value) {
        this.text2Value = text2Value;
    }
}