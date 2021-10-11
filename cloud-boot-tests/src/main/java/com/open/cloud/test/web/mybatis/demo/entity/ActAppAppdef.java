package com.open.cloud.test.web.mybatis.demo.entity;

import com.open.cloud.mybatis.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author leijian
 * @since 2021-10-11
 */
@TableName("act_app_appdef")
public class ActAppAppdef implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer rev;

    private String name;

    private String key;

    private Integer version;

    private String category;

    private String deploymentId;

    private String resourceName;

    private String description;

    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "ActAppAppdef{" +
                "id=" + id +
                ", rev=" + rev +
                ", name=" + name +
                ", key=" + key +
                ", version=" + version +
                ", category=" + category +
                ", deploymentId=" + deploymentId +
                ", resourceName=" + resourceName +
                ", description=" + description +
                ", tenantId=" + tenantId +
                "}";
    }
}
