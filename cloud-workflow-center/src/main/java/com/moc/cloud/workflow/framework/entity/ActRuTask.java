package com.moc.cloud.workflow.framework.entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 流程任务表(ACT_RU_TASK)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActRuTask implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -9017860091324376859L;

    /** id */
    @NotNull(message="流程任务ID不能为空")
    private String id;

    /** rev */
    private Integer rev;

    /** 任务所处的执行流ID-act_ru_execution */
    private String executionId;

    /** 对应的流程实例ID */
    private String procInstId;

    /** 对应流程定义的数据ID */
    private String procDefId;

    /** 任务名称 */
    private String name;

    /** parentTaskId */
    private String parentTaskId;

    /** description */
    private String description;

    /** 任务定义的ID在任务定义时存在 */
    private String taskDefKey;

    /** 任务拥有人 */
    private String owner;

    /** 被指派的任务执行人 */
    private String assignee;

    /** delegation */
    private String delegation;

    /** priority */
    private Integer priority;

    /** createDate */
    private LocalDateTime createDate;

    /** dueDate */
    private LocalDateTime dueDate;

    /** category */
    private String category;

    /** suspensionState */
    private Boolean suspensionState;

    /** tenantId */
    private String tenantId;

    /** formKey */
    private String formKey;

    /** claimTime */
    private LocalDateTime claimTime;

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
     * 获取任务所处的执行流ID-act_ru_execution
     * 
     * @return 任务所处的执行流ID-act_ru_execution
     */
    public String getExecutionId() {
        return this.executionId;
    }

    /**
     * 设置任务所处的执行流ID-act_ru_execution
     * 
     * @param executionId
     *          任务所处的执行流ID-act_ru_execution
     */
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    /**
     * 获取对应的流程实例ID
     * 
     * @return 对应的流程实例ID
     */
    public String getProcInstId() {
        return this.procInstId;
    }

    /**
     * 设置对应的流程实例ID
     * 
     * @param procInstId
     *          对应的流程实例ID
     */
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * 获取对应流程定义的数据ID
     * 
     * @return 对应流程定义的数据ID
     */
    public String getProcDefId() {
        return this.procDefId;
    }

    /**
     * 设置对应流程定义的数据ID
     * 
     * @param procDefId
     *          对应流程定义的数据ID
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * 获取任务名称
     * 
     * @return 任务名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置任务名称
     * 
     * @param name
     *          任务名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取parentTaskId
     * 
     * @return parentTaskId
     */
    public String getParentTaskId() {
        return this.parentTaskId;
    }

    /**
     * 设置parentTaskId
     * 
     * @param parentTaskId
     */
    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    /**
     * 获取description
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置description
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取任务定义的ID在任务定义时存在
     * 
     * @return 任务定义的ID在任务定义时存在
     */
    public String getTaskDefKey() {
        return this.taskDefKey;
    }

    /**
     * 设置任务定义的ID在任务定义时存在
     * 
     * @param taskDefKey
     *          任务定义的ID在任务定义时存在
     */
    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    /**
     * 获取任务拥有人
     * 
     * @return 任务拥有人
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * 设置任务拥有人
     * 
     * @param owner
     *          任务拥有人
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取被指派的任务执行人
     * 
     * @return 被指派的任务执行人
     */
    public String getAssignee() {
        return this.assignee;
    }

    /**
     * 设置被指派的任务执行人
     * 
     * @param assignee
     *          被指派的任务执行人
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * 获取delegation
     * 
     * @return delegation
     */
    public String getDelegation() {
        return this.delegation;
    }

    /**
     * 设置delegation
     * 
     * @param delegation
     */
    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    /**
     * 获取priority
     * 
     * @return priority
     */
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * 设置priority
     * 
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取createDate
     * 
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate
     * 
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取dueDate
     * 
     * @return dueDate
     */
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    /**
     * 设置dueDate
     * 
     * @param dueDate
     */
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 获取category
     * 
     * @return category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 设置category
     * 
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取suspensionState
     * 
     * @return suspensionState
     */
    public Boolean getSuspensionState() {
        return this.suspensionState;
    }

    /**
     * 设置suspensionState
     * 
     * @param suspensionState
     */
    public void setSuspensionState(Boolean suspensionState) {
        this.suspensionState = suspensionState;
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
     * 获取formKey
     * 
     * @return formKey
     */
    public String getFormKey() {
        return this.formKey;
    }

    /**
     * 设置formKey
     * 
     * @param formKey
     */
    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    /**
     * 获取claimTime
     * 
     * @return claimTime
     */
    public LocalDateTime getClaimTime() {
        return this.claimTime;
    }

    /**
     * 设置claimTime
     * 
     * @param claimTime
     */
    public void setClaimTime(LocalDateTime claimTime) {
        this.claimTime = claimTime;
    }
}