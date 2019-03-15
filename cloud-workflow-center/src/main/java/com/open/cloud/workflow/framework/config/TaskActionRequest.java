package com.open.cloud.workflow.framework.config;

import com.open.cloud.workflow.framework.entity.ActHiComment;
import com.open.cloud.workflow.framework.entity.RestVariable;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Classname: TaskActionRequest
 * @Description:
 * @Author: leijian
 * @Date: 2019/1/23
 * @Version: 1.0
 */
public class TaskActionRequest extends RestActionRequest {
    public static final String ACTION_COMPLETE = "complete";
    public static final String ACTION_CLAIM = "claim";
    public static final String ACTION_RESOLVE = "resolve";
    private String assignee;
    private String dealingUserId;
    private List<RestVariable> variables;
    private List<ActHiComment> actHiComments;

    @NotNull(message = "任务ID不能为空")
    private String taskId;

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public void setVariables(List<RestVariable> variables) {
        this.variables = variables;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, defaultImpl = RestVariable.class)
    public List<RestVariable> getVariables() {
        return this.variables;
    }

    public void setActHiComments(final List<ActHiComment> actHiComments) {
        this.actHiComments = actHiComments;
    }

    public List<ActHiComment> getActHiComments() {
        return actHiComments;
    }

    public void setTaskId(final String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setDealingUserId(final String dealingUserId) {
        this.dealingUserId = dealingUserId;
    }

    public String getDealingUserId() {
        return dealingUserId;
    }
}