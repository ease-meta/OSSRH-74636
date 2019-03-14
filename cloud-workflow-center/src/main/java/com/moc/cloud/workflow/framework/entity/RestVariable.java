package com.moc.cloud.workflow.framework.entity;

import com.moc.cloud.workflow.common.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

/**
 * @author Leijian
 */
@ToString
public class RestVariable {
    private String name;
    private String type;
    private RestVariableScope variableScope;
    private Object value;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public RestVariableScope getVariableScope() {
        return this.variableScope;
    }

    public void setVariableScope(RestVariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getScope() {
        String scope = null;
        if (this.variableScope != null) {
            scope = this.variableScope.name().toLowerCase();
        }
        return scope;
    }

    public void setScope(String scope) {
        setVariableScope(getScopeFromString(scope));
    }


    public static RestVariableScope getScopeFromString(String scope) {
        if (scope != null) {
            for (RestVariableScope s : RestVariableScope.values()) {
                if (s.name().equalsIgnoreCase(scope)) {
                    return s;
                }
            }
            throw new BusinessException("Invalid variable scope: '" + scope + "'");
        }
        return null;
    }


    public static enum RestVariableScope {
        LOCAL, GLOBAL;
    }
}