package com.open.cloud.workflow.framework.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Classname: SysMenu
 * @Description:
 * @Author: leijian
 * @Date: 2019/3/15
 * @Version: 1.0
 */
@Entity
@Table(name = "sys_menu", schema = "workflow", catalog = "")
public class SysMenu {
    private int menuId;
    private String menuName;
    private Integer parentId;
    private Integer orderNum;
    private String url;
    private String menuType;
    private String visible;
    private String perms;
    private String icon;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;
    private String remark;

    @Id
    @Column(name = "menu_id")
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(final int menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(final String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(final Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "order_num")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(final Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "menu_type")
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(final String menuType) {
        this.menuType = menuType;
    }

    @Basic
    @Column(name = "visible")
    public String getVisible() {
        return visible;
    }

    public void setVisible(final String visible) {
        this.visible = visible;
    }

    @Basic
    @Column(name = "perms")
    public String getPerms() {
        return perms;
    }

    public void setPerms(final String perms) {
        this.perms = perms;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(final String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SysMenu sysMenu = (SysMenu) o;
        return menuId == sysMenu.menuId && Objects.equals(menuName, sysMenu.menuName) && Objects.equals(parentId, sysMenu.parentId) && Objects.equals(orderNum, sysMenu.orderNum) && Objects.equals(url, sysMenu.url) && Objects.equals(menuType, sysMenu.menuType) && Objects.equals(visible, sysMenu.visible) && Objects.equals(perms, sysMenu.perms) && Objects.equals(icon, sysMenu.icon) && Objects.equals(createBy, sysMenu.createBy) && Objects.equals(createTime, sysMenu.createTime) && Objects.equals(updateBy, sysMenu.updateBy) && Objects.equals(updateTime, sysMenu.updateTime) && Objects.equals(remark, sysMenu.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuName, parentId, orderNum, url, menuType, visible, perms, icon, createBy, createTime, updateBy, updateTime, remark);
    }
}
