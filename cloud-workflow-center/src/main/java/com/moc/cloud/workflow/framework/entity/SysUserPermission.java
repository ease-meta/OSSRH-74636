package com.moc.cloud.workflow.framework.entity;
/**
 * 角色用户信息对照表(SYS_ROLE_PERMISSION)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class SysUserPermission implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5925494486547474418L;

    /** roleId */
    private String userId;

    /** permissionId */
    private String permissionId;

    /** 0不可用*/
    private Boolean delFlag;

    public void setDelFlag( Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }
    /**
     * 获取roleId
     * 
     * @return roleId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置roleId
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取permissionId
     * 
     * @return permissionId
     */
    public String getPermissionId() {
        return this.permissionId;
    }

    /**
     * 设置permissionId
     * 
     * @param permissionId
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}