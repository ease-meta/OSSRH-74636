/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.meta.ease.test.web.mybatis.demo.entity;

import io.github.meta.ease.mybatis.annotation.TableName;

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
        return "ActAppDeploymentResource{" + "id=" + id + ", name=" + name + ", deploymentId="
                + deploymentId + ", resourceBytes=" + resourceBytes + "}";
    }
}
