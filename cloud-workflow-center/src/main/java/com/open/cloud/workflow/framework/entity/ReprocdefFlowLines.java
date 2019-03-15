package com.open.cloud.workflow.framework.entity;

import lombok.ToString;

/**
 * 流程连接线定义(REPROCDEF_FLOW_LINES)
 *
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
@ToString
public class ReprocdefFlowLines implements java.io.Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = -1157765486781084627L;
    /**
     * 流程定义时XML的ID
     */
    private String key;
    /**
     * 流程ID
     */
    private String sid;

    /**
     * 流程定义的任务ID
     */
    private String taskDefKey;

    /**
     * 连接线ID
     */
    private Integer id;

    /**
     * 连接线描述
     */
    private String sidName;

    /**
     * 节点类型
     */
    private String sidType;

    /**
     * 源节点
     */
    private String fromNode;

    /**
     * 目标节点
     */
    private String toNode;

    /**
     * 布尔类型表达式，用于决策节点后的连接线
     */
    private String expr;

    /**
     * 表达式为true的值
     */
    private String exprValue;

    /**
     * 可用开关 0不可用代表false
     */
    private Boolean state;

    /**
     * 布尔类型表达式动态目标节点0为false
     */
    private String finalToNode;

    /**
     * 获取流程ID
     *
     * @return 流程ID
     */
    public String getSid() {
        return this.sid;
    }

    /**
     * 设置流程ID
     *
     * @param sid 流程ID
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
     * @param taskDefKey 流程定义的任务ID
     */
    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    /**
     * 获取连接线ID
     *
     * @return 连接线ID
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置连接线ID
     *
     * @param id 连接线ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取连接线描述
     *
     * @return 连接线描述
     */
    public String getSidName() {
        return this.sidName;
    }

    /**
     * 设置连接线描述
     *
     * @param sidName 连接线描述
     */
    public void setSidName(String sidName) {
        this.sidName = sidName;
    }

    /**
     * 获取节点类型
     *
     * @return 节点类型
     */
    public String getSidType() {
        return this.sidType;
    }

    /**
     * 设置节点类型
     *
     * @param sidType 节点类型
     */
    public void setSidType(String sidType) {
        this.sidType = sidType;
    }

    /**
     * 获取源节点
     *
     * @return 源节点
     */
    public String getFromNode() {
        return this.fromNode;
    }

    /**
     * 设置源节点
     *
     * @param fromNode 源节点
     */
    public void setFromNode(String fromNode) {
        this.fromNode = fromNode;
    }

    /**
     * 获取目标节点
     *
     * @return 目标节点
     */
    public String getToNode() {
        return this.toNode;
    }

    /**
     * 设置目标节点
     *
     * @param toNode 目标节点
     */
    public void setToNode(String toNode) {
        this.toNode = toNode;
    }

    /**
     * 获取布尔类型表达式，用于决策节点后的连接线
     *
     * @return 布尔类型表达式
     */
    public String getExpr() {
        return this.expr;
    }

    /**
     * 设置布尔类型表达式，用于决策节点后的连接线
     *
     * @param expr 布尔类型表达式
     */
    public void setExpr(String expr) {
        this.expr = expr;
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
     * @param state 可用开关 0不可用代表false
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * 获取布尔类型表达式动态目标节点0为false
     *
     * @return 布尔类型表达式动态目标节点0为false
     */
    public String getFinalToNode() {
        return this.finalToNode;
    }

    /**
     * 设置布尔类型表达式动态目标节点0为false
     *
     * @param finalToNode 布尔类型表达式动态目标节点0为false
     */
    public void setFinalToNode(String finalToNode) {
        this.finalToNode = finalToNode;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setExprValue(final String exprValue) {
        this.exprValue = exprValue;
    }

    public String getExprValue() {
        return exprValue;
    }
}