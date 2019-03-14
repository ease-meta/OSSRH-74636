package com.moc.cloud.workflow.framework.entity;
import java.util.Date;

/**
 * 流程实例（执行流表）(ACT_RU_EXECUTION)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActRuExecution implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 1001041858969377405L;

    /** id */
    private String id;

    /** 数据版本 */
    private Integer rev;

    /** 流程实例ID,一个流程实例可能有多个流程实例 */
    private String procInstId;

    /** 流程实例定义时的业务关键字 */
    private String businessKey;

    /** 执行流的ID或者是执行流所属的父ID，代表当前执行流所属的流程实例 */
    private String parentId;

    /** 流程数据定义的ID */
    private String procDefId;

    /** 父流程实例的ID，流程嵌套流程 */
    private String superExec;

    /** rootProcInstId */
    private String rootProcInstId;

    /** 当前执行流的任务节点ID */
    private String actId;

    /** 当前执行流是否活跃0否 */
    private Integer isActive;

    /** 当前执行流是否并行0否 */
    private Integer isConcurrent;

    /** isScope */
    private Integer isScope;

    /** isEventScope */
    private Integer isEventScope;

    /** isMiRoot */
    private Integer isMiRoot;

    /** suspensionState */
    private Integer suspensionState;

    /** cachedEntState */
    private Integer cachedEntState;

    /** tenantId */
    private String tenantId;

    /** 当前执行流的任务节点描述 */
    private String name;

    /** 开始事件 */
    private Date startTime;

    /** 发起人ID */
    private String startUserId;

    /** lockTime */
    private Date lockTime;

    /** isCountEnabled */
    private Integer isCountEnabled;

    /** evtSubscrCount */
    private Integer evtSubscrCount;

    /** taskCount */
    private Integer taskCount;

    /** jobCount */
    private Integer jobCount;

    /** timerJobCount */
    private Integer timerJobCount;

    /** suspJobCount */
    private Integer suspJobCount;

    /** deadletterJobCount */
    private Integer deadletterJobCount;

    /** varCount */
    private Integer varCount;

    /** idLinkCount */
    private Integer idLinkCount;

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
     * 获取数据版本
     * 
     * @return 数据版本
     */
    public Integer getRev() {
        return this.rev;
    }

    /**
     * 设置数据版本
     * 
     * @param rev
     *          数据版本
     */
    public void setRev(Integer rev) {
        this.rev = rev;
    }

    /**
     * 获取流程实例ID,一个流程实例可能有多个流程实例
     * 
     * @return 流程实例ID
     */
    public String getProcInstId() {
        return this.procInstId;
    }

    /**
     * 设置流程实例ID,一个流程实例可能有多个流程实例
     * 
     * @param procInstId
     *          流程实例ID
     */
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * 获取流程实例定义时的业务关键字
     * 
     * @return 流程实例定义时的业务关键字
     */
    public String getBusinessKey() {
        return this.businessKey;
    }

    /**
     * 设置流程实例定义时的业务关键字
     * 
     * @param businessKey
     *          流程实例定义时的业务关键字
     */
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * 获取执行流的ID或者是执行流所属的父ID，代表当前执行流所属的流程实例
     * 
     * @return 执行流的ID或者是执行流所属的父ID
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * 设置执行流的ID或者是执行流所属的父ID，代表当前执行流所属的流程实例
     * 
     * @param parentId
     *          执行流的ID或者是执行流所属的父ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取流程数据定义的ID
     * 
     * @return 流程数据定义的ID
     */
    public String getProcDefId() {
        return this.procDefId;
    }

    /**
     * 设置流程数据定义的ID
     * 
     * @param procDefId
     *          流程数据定义的ID
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * 获取父流程实例的ID，流程嵌套流程
     * 
     * @return 父流程实例的ID
     */
    public String getSuperExec() {
        return this.superExec;
    }

    /**
     * 设置父流程实例的ID，流程嵌套流程
     * 
     * @param superExec
     *          父流程实例的ID
     */
    public void setSuperExec(String superExec) {
        this.superExec = superExec;
    }

    /**
     * 获取rootProcInstId
     * 
     * @return rootProcInstId
     */
    public String getRootProcInstId() {
        return this.rootProcInstId;
    }

    /**
     * 设置rootProcInstId
     * 
     * @param rootProcInstId
     */
    public void setRootProcInstId(String rootProcInstId) {
        this.rootProcInstId = rootProcInstId;
    }

    /**
     * 获取当前执行流的任务节点ID
     * 
     * @return 当前执行流的任务节点ID
     */
    public String getActId() {
        return this.actId;
    }

    /**
     * 设置当前执行流的任务节点ID
     * 
     * @param actId
     *          当前执行流的任务节点ID
     */
    public void setActId(String actId) {
        this.actId = actId;
    }

    /**
     * 获取当前执行流是否活跃0否
     * 
     * @return 当前执行流是否活跃0否
     */
    public Integer getIsActive() {
        return this.isActive;
    }

    /**
     * 设置当前执行流是否活跃0否
     * 
     * @param isActive
     *          当前执行流是否活跃0否
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取当前执行流是否并行0否
     * 
     * @return 当前执行流是否并行0否
     */
    public Integer getIsConcurrent() {
        return this.isConcurrent;
    }

    /**
     * 设置当前执行流是否并行0否
     * 
     * @param isConcurrent
     *          当前执行流是否并行0否
     */
    public void setIsConcurrent(Integer isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    /**
     * 获取isScope
     * 
     * @return isScope
     */
    public Integer getIsScope() {
        return this.isScope;
    }

    /**
     * 设置isScope
     * 
     * @param isScope
     */
    public void setIsScope(Integer isScope) {
        this.isScope = isScope;
    }

    /**
     * 获取isEventScope
     * 
     * @return isEventScope
     */
    public Integer getIsEventScope() {
        return this.isEventScope;
    }

    /**
     * 设置isEventScope
     * 
     * @param isEventScope
     */
    public void setIsEventScope(Integer isEventScope) {
        this.isEventScope = isEventScope;
    }

    /**
     * 获取isMiRoot
     * 
     * @return isMiRoot
     */
    public Integer getIsMiRoot() {
        return this.isMiRoot;
    }

    /**
     * 设置isMiRoot
     * 
     * @param isMiRoot
     */
    public void setIsMiRoot(Integer isMiRoot) {
        this.isMiRoot = isMiRoot;
    }

    /**
     * 获取suspensionState
     * 
     * @return suspensionState
     */
    public Integer getSuspensionState() {
        return this.suspensionState;
    }

    /**
     * 设置suspensionState
     * 
     * @param suspensionState
     */
    public void setSuspensionState(Integer suspensionState) {
        this.suspensionState = suspensionState;
    }

    /**
     * 获取cachedEntState
     * 
     * @return cachedEntState
     */
    public Integer getCachedEntState() {
        return this.cachedEntState;
    }

    /**
     * 设置cachedEntState
     * 
     * @param cachedEntState
     */
    public void setCachedEntState(Integer cachedEntState) {
        this.cachedEntState = cachedEntState;
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
     * 获取当前执行流的任务节点描述
     * 
     * @return 当前执行流的任务节点描述
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置当前执行流的任务节点描述
     * 
     * @param name
     *          当前执行流的任务节点描述
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取开始事件
     * 
     * @return 开始事件
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置开始事件
     * 
     * @param startTime
     *          开始事件
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取发起人ID
     * 
     * @return 发起人ID
     */
    public String getStartUserId() {
        return this.startUserId;
    }

    /**
     * 设置发起人ID
     * 
     * @param startUserId
     *          发起人ID
     */
    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    /**
     * 获取lockTime
     * 
     * @return lockTime
     */
    public Date getLockTime() {
        return this.lockTime;
    }

    /**
     * 设置lockTime
     * 
     * @param lockTime
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * 获取isCountEnabled
     * 
     * @return isCountEnabled
     */
    public Integer getIsCountEnabled() {
        return this.isCountEnabled;
    }

    /**
     * 设置isCountEnabled
     * 
     * @param isCountEnabled
     */
    public void setIsCountEnabled(Integer isCountEnabled) {
        this.isCountEnabled = isCountEnabled;
    }

    /**
     * 获取evtSubscrCount
     * 
     * @return evtSubscrCount
     */
    public Integer getEvtSubscrCount() {
        return this.evtSubscrCount;
    }

    /**
     * 设置evtSubscrCount
     * 
     * @param evtSubscrCount
     */
    public void setEvtSubscrCount(Integer evtSubscrCount) {
        this.evtSubscrCount = evtSubscrCount;
    }

    /**
     * 获取taskCount
     * 
     * @return taskCount
     */
    public Integer getTaskCount() {
        return this.taskCount;
    }

    /**
     * 设置taskCount
     * 
     * @param taskCount
     */
    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    /**
     * 获取jobCount
     * 
     * @return jobCount
     */
    public Integer getJobCount() {
        return this.jobCount;
    }

    /**
     * 设置jobCount
     * 
     * @param jobCount
     */
    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    /**
     * 获取timerJobCount
     * 
     * @return timerJobCount
     */
    public Integer getTimerJobCount() {
        return this.timerJobCount;
    }

    /**
     * 设置timerJobCount
     * 
     * @param timerJobCount
     */
    public void setTimerJobCount(Integer timerJobCount) {
        this.timerJobCount = timerJobCount;
    }

    /**
     * 获取suspJobCount
     * 
     * @return suspJobCount
     */
    public Integer getSuspJobCount() {
        return this.suspJobCount;
    }

    /**
     * 设置suspJobCount
     * 
     * @param suspJobCount
     */
    public void setSuspJobCount(Integer suspJobCount) {
        this.suspJobCount = suspJobCount;
    }

    /**
     * 获取deadletterJobCount
     * 
     * @return deadletterJobCount
     */
    public Integer getDeadletterJobCount() {
        return this.deadletterJobCount;
    }

    /**
     * 设置deadletterJobCount
     * 
     * @param deadletterJobCount
     */
    public void setDeadletterJobCount(Integer deadletterJobCount) {
        this.deadletterJobCount = deadletterJobCount;
    }

    /**
     * 获取varCount
     * 
     * @return varCount
     */
    public Integer getVarCount() {
        return this.varCount;
    }

    /**
     * 设置varCount
     * 
     * @param varCount
     */
    public void setVarCount(Integer varCount) {
        this.varCount = varCount;
    }

    /**
     * 获取idLinkCount
     * 
     * @return idLinkCount
     */
    public Integer getIdLinkCount() {
        return this.idLinkCount;
    }

    /**
     * 设置idLinkCount
     * 
     * @param idLinkCount
     */
    public void setIdLinkCount(Integer idLinkCount) {
        this.idLinkCount = idLinkCount;
    }
}