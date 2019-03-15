package com.open.cloud.workflow.framework.entity;
import java.time.LocalDateTime;

/**
 * 权限信息表(SYS_PERMISSION)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class SysPermission implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 2874531314969806047L;

    /** permissionId */
    private String permissionId;

    /** permissionName */
    private String permissionName;

    /** remark */
    private String remark;

    /** createBy */
    private String createBy;

    /** createDate */
    private LocalDateTime createDate;

    /** updateBy */
    private String updateBy;

    /** updateDate */
    private LocalDateTime updateDate;
    /** 0不可用*/
    private Boolean delFlag;

    public void setDelFlag( Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
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

    /**
     * 获取permissionName
     * 
     * @return permissionName
     */
    public String getPermissionName() {
        return this.permissionName;
    }

    /**
     * 设置permissionName
     * 
     * @param permissionName
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 获取remark
     * 
     * @return remark
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置remark
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取createBy
     * 
     * @return createBy
     */
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     * 设置createBy
     * 
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
     * 获取updateBy
     * 
     * @return updateBy
     */
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 设置updateBy
     * 
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取updateDate
     * 
     * @return updateDate
     */
    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 设置updateDate
     * 
     * @param updateDate
     */
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}