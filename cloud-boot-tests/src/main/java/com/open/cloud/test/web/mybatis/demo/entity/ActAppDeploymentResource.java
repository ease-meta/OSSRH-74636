package com.open.cloud.test.web.mybatis.demo.entity;

import com.open.cloud.mybatis.annotation.TableName;

import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 *
 * </p>
 *
 * @author leijian
 * @since 2021-10-11
 */
@TableName("act_app_deployment_resource")
public class ActAppDeploymentResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String deploymentId;

    private Blob resourceBytes;

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

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Blob getResourceBytes() {
        return resourceBytes;
    }

    public void setResourceBytes(Blob resourceBytes) {
        this.resourceBytes = resourceBytes;
    }

    @Override
    public String toString() {
        return "ActAppDeploymentResource{" +
                "id=" + id +
                ", name=" + name +
                ", deploymentId=" + deploymentId +
                ", resourceBytes=" + resourceBytes +
                "}";
    }
}
