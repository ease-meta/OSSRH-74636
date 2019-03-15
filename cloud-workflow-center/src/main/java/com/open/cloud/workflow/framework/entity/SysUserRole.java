package com.open.cloud.workflow.framework.entity;

/**
 * 角色用户信息对照表(SYS_USER_ROLE)
 *
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class SysUserRole implements java.io.Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = -1474485548700313626L;

    /**
     * userId
     */
    private String userId;

    /**
     * roleId
     */
    private String roleId;
    /**
     * 0不可用
     */
    private Boolean delFlag;

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
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
     * 获取roleId
     *
     * @return roleId
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 设置roleId
     *
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}