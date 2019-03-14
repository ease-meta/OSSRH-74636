package com.moc.cloud.workflow.framework.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
public class ProcessInstanceCreateRequest {
    /**
     * 流程定义的ID
     */
    private String processDefinitionId;
    /**
     * 流程定义主键
     */
    @NotNull(message = "流程定义关键字不能为空")
    private String processDefinitionKey;

    /**
     * 业务关键字
     */
    @NotNull(message = "业务关键字不能为空")
    private String businessKey;


    @NotNull(message = "流程创建人不能为空")
    private String userId;
    /**
     * 业务变量
     */
    private List<RestVariable> variables;


    private boolean returnVariables;

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

    public String getBusinessKey() {
        return this.businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }


    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, defaultImpl = RestVariable.class)
    public List<RestVariable> getVariables() {
        return this.variables;
    }

    public void setVariables(List<RestVariable> variables) {
        this.variables = variables;
    }


    public boolean getReturnVariables() {
        return this.returnVariables;
    }

    public void setReturnVariables(boolean returnVariables) {
        this.returnVariables = returnVariables;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}