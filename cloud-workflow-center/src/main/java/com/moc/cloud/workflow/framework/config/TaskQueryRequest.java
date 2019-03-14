package com.moc.cloud.workflow.framework.config;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname: TaskQueryRequest
 * @Description: 任务查询请求类
 * @Author: leijian
 * @Date: 2019/1/24
 * @Version: 1.0
 */
public class TaskQueryRequest {

    private String name;
    private String nameLike;
    private String assignee;
    private String assigneeLike;
    private String owner;
    private String ownerLike;
    private Boolean unassigned;
    private String candidateUser;
    private String candidateGroup;
    private List<String> candidateGroupIn;
    private String involvedUser;
    private String processInstanceId;
    private String processInstanceBusinessKey;
    private String processInstanceBusinessKeyLike;
    private List<String> processInstanceIdIn;
    private String processDefinitionId;
    private String processDefinitionKey;
    private String processDefinitionName;
    private String processDefinitionKeyLike;
    private String processDefinitionNameLike;
    private String executionId;
    private LocalDateTime createdOn;
    private LocalDateTime createdBefore;
    private LocalDateTime createdAfter;
    private Boolean excludeSubTasks;
    private String taskDefinitionKey;
    private String taskDefinitionKeyLike;
    private LocalDateTime dueDate;
    private LocalDateTime dueBefore;
    private LocalDateTime dueAfter;
    private Boolean withoutDueDate;
    private Boolean active;
    private Boolean includeTaskLocalVariables;
    private Boolean includeProcessVariables;
    private String tenantId;
    private String tenantIdLike;
    private Boolean withoutTenantId;
    private String candidateOrAssigned;
    private String category;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLike() {
        return this.nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssigneeLike() {
        return this.assigneeLike;
    }

    public void setAssigneeLike(String assigneeLike) {
        this.assigneeLike = assigneeLike;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerLike() {
        return this.ownerLike;
    }

    public void setOwnerLike(String ownerLike) {
        this.ownerLike = ownerLike;
    }

    public Boolean getUnassigned() {
        return this.unassigned;
    }

    public void setUnassigned(Boolean unassigned) {
        this.unassigned = unassigned;
    }

    public String getCandidateUser() {
        return this.candidateUser;
    }

    public void setCandidateUser(String candidateUser) {
        this.candidateUser = candidateUser;
    }

    public String getCandidateGroup() {
        return this.candidateGroup;
    }

    public void setCandidateGroup(String candidateGroup) {
        this.candidateGroup = candidateGroup;
    }

    public List<String> getCandidateGroupIn() {
        return this.candidateGroupIn;
    }

    public void setCandidateGroupIn(List<String> candidateGroupIn) {
        this.candidateGroupIn = candidateGroupIn;
    }

    public String getInvolvedUser() {
        return this.involvedUser;
    }

    public void setInvolvedUser(String involvedUser) {
        this.involvedUser = involvedUser;
    }

    public String getProcessInstanceId() {
        return this.processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<String> getProcessInstanceIdIn() {
        return this.processInstanceIdIn;
    }

    public void setProcessInstanceIdIn(List<String> processInstanceIdIn) {
        this.processInstanceIdIn = processInstanceIdIn;
    }

    public String getProcessInstanceBusinessKey() {
        return this.processInstanceBusinessKey;
    }

    public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
        this.processInstanceBusinessKey = processInstanceBusinessKey;
    }

    public String getProcessInstanceBusinessKeyLike() {
        return this.processInstanceBusinessKeyLike;
    }

    public void setProcessInstanceBusinessKeyLike(String processInstanceBusinessKeyLike) {
        this.processInstanceBusinessKeyLike = processInstanceBusinessKeyLike;
    }

    public String getExecutionId() {
        return this.executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public LocalDateTime getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getCreatedBefore() {
        return this.createdBefore;
    }

    public void setCreatedBefore(LocalDateTime createdBefore) {
        this.createdBefore = createdBefore;
    }

    public LocalDateTime getCreatedAfter() {
        return this.createdAfter;
    }

    public void setCreatedAfter(LocalDateTime createdAfter) {
        this.createdAfter = createdAfter;
    }

    public Boolean getExcludeSubTasks() {
        return this.excludeSubTasks;
    }

    public void setExcludeSubTasks(Boolean excludeSubTasks) {
        this.excludeSubTasks = excludeSubTasks;
    }

    public String getTaskDefinitionKey() {
        return this.taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getTaskDefinitionKeyLike() {
        return this.taskDefinitionKeyLike;
    }

    public void setTaskDefinitionKeyLike(String taskDefinitionKeyLike) {
        this.taskDefinitionKeyLike = taskDefinitionKeyLike;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueBefore() {
        return this.dueBefore;
    }

    public void setDueBefore(LocalDateTime dueBefore) {
        this.dueBefore = dueBefore;
    }

    public LocalDateTime getDueAfter() {
        return this.dueAfter;
    }

    public void setDueAfter(LocalDateTime dueAfter) {
        this.dueAfter = dueAfter;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIncludeTaskLocalVariables() {
        return this.includeTaskLocalVariables;
    }

    public void setIncludeTaskLocalVariables(Boolean includeTaskLocalVariables) {
        this.includeTaskLocalVariables = includeTaskLocalVariables;
    }

    public Boolean getIncludeProcessVariables() {
        return this.includeProcessVariables;
    }

    public void setIncludeProcessVariables(Boolean includeProcessVariables) {
        this.includeProcessVariables = includeProcessVariables;
    }

    public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
        this.processDefinitionNameLike = processDefinitionNameLike;
    }

    public String getProcessDefinitionNameLike() {
        return this.processDefinitionNameLike;
    }

    public String getProcessDefinitionKeyLike() {
        return this.processDefinitionKeyLike;
    }

    public void setProcessDefinitionKeyLike(String processDefinitionKeyLike) {
        this.processDefinitionKeyLike = processDefinitionKeyLike;
    }

    public void setWithoutDueDate(Boolean withoutDueDate) {
        this.withoutDueDate = withoutDueDate;
    }

    public Boolean getWithoutDueDate() {
        return this.withoutDueDate;
    }

    public String getProcessDefinitionId() {
        return this.processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return this.processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessDefinitionName() {
        return this.processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantIdLike(String tenantIdLike) {
        this.tenantIdLike = tenantIdLike;
    }

    public String getTenantIdLike() {
        return this.tenantIdLike;
    }

    public void setWithoutTenantId(Boolean withoutTenantId) {
        this.withoutTenantId = withoutTenantId;
    }

    public Boolean getWithoutTenantId() {
        return this.withoutTenantId;
    }

    public String getCandidateOrAssigned() {
        return this.candidateOrAssigned;
    }

    public void setCandidateOrAssigned(String candidateOrAssigned) {
        this.candidateOrAssigned = candidateOrAssigned;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
