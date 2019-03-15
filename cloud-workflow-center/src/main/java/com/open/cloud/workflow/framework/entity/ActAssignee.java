package com.open.cloud.workflow.framework.entity;

import com.open.cloud.workflow.common.utils.ToolsStrBusi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

/**
 * 流程定义审批人(ACT_ASSIGNEE)
 *
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
@ToString
public class ActAssignee implements java.io.Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 2573493712230682309L;

    /**
     * id
     */
    private Integer id;

    /**
     * 流程定义时XML的ID
     */
    private String key;

    /**
     * 节点id
     */
    private String sid;

    /**
     * 办理人
     */
    private String assignee;

    /**
     * 候选组
     */
    private String groupId;

    /**
     * 角色
     */
    private String roleId;

    /**
     * 办理人类型1办理人2候选人3组
     */
    private String assigneeType;

    /**
     * 节点名称
     */
    private String activtiName;

    @JsonIgnore
    private String dealingUserId;

    public String getDealingUserId() {
        if ("1".equalsIgnoreCase(assigneeType)) {
            return assignee;
        }
        if ("2".equalsIgnoreCase(assigneeType)) {
            return groupId;
        }
        if ("3".equalsIgnoreCase(assigneeType)) {
            return roleId;
        }

        return "";
    }

    public void setDealingUserId(final String dealingUserId) {
        this.dealingUserId = dealingUserId;
    }

    /**
     * 获取id
     *
     * @return id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取流程定义时XML的ID
     *
     * @return 流程定义时XML的ID
     */
    public String getKey() {
        return this.key;
    }

    /**
     * 设置流程定义时XML的ID
     *
     * @param key 流程定义时XML的ID
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取节点id
     *
     * @return 节点id
     */
    public String getSid() {
        return this.sid;
    }

    /**
     * 设置节点id
     *
     * @param sid 节点id
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取办理人
     *
     * @return 办理人
     */
    public String getAssignee() {
        return this.assignee;
    }

    /**
     * 设置办理人
     *
     * @param assignee 办理人
     */
    public void setAssignee(String assignee) {
        this.assignee = ToolsStrBusi.getString(assignee);
    }

    /**
     * 获取候选组
     *
     * @return 候选组
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * 设置候选组
     *
     * @param groupId 候选组
     */
    public void setGroupId(String groupId) {
        this.groupId = ToolsStrBusi.getString(groupId);
    }

    /**
     * 获取角色
     *
     * @return 角色
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 设置角色
     *
     * @param roleId 角色
     */
    public void setRoleId(String roleId) {
        this.roleId = ToolsStrBusi.getString(roleId);
    }

    /**
     * 获取办理人类型1办理人2候选人3组
     *
     * @return 办理人类型1办理人2候选人3组
     */
    public String getAssigneeType() {
        return this.assigneeType;
    }

    /**
     * 设置办理人类型1办理人2候选人3组
     *
     * @param assigneeType 办理人类型1办理人2候选人3组
     */
    public void setAssigneeType(String assigneeType) {
        this.assigneeType = ToolsStrBusi.getString(assigneeType);
    }

    /**
     * 获取节点名称
     *
     * @return 节点名称
     */
    public String getActivtiName() {
        return this.activtiName;
    }

    /**
     * 设置节点名称
     *
     * @param activtiName 节点名称
     */
    public void setActivtiName(String activtiName) {
        this.activtiName = ToolsStrBusi.getString(activtiName);
    }
}