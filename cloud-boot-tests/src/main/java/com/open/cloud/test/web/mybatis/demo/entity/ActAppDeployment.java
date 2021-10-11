package com.open.cloud.test.web.mybatis.demo.entity;

import com.open.cloud.mybatis.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author leijian
 * @since 2021-10-11
 */
@TableName("act_app_deployment")
public class ActAppDeployment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String category;

    private String key;

    private LocalDateTime deployTime;

    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalDateTime getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(LocalDateTime deployTime) {
        this.deployTime = deployTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "ActAppDeployment{" +
                "id=" + id +
                ", name=" + name +
                ", category=" + category +
                ", key=" + key +
                ", deployTime=" + deployTime +
                ", tenantId=" + tenantId +
                "}";
    }
}
