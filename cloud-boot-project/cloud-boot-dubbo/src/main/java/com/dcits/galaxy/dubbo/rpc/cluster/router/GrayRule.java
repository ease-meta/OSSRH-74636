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
package com.dcits.galaxy.dubbo.rpc.cluster.router;

import java.time.LocalDateTime;

/**
 * @author Leijian
 * @date 2020/4/7
 */
public class GrayRule {

    /**
     * 灰度业务关键字
     */
    private String        bizzKey;
    /**
     * 业务关键字属性值
     */
    private String        bizzKeyValue;
    /**
     * 服务接口
     */
    private String        applicationType;
    /**
     * 应用id
     */
    private String        applicationId;
    /**
     * 规则开启 0-关闭 1-开启
     */
    private boolean       enable;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public String getBizzKey() {
        return bizzKey;
    }

    public void setBizzKey(String bizzKey) {
        this.bizzKey = bizzKey;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getBizzKeyValue() {
        return bizzKeyValue;
    }

    public void setBizzKeyValue(String bizzKeyValue) {
        this.bizzKeyValue = bizzKeyValue;
    }
}
