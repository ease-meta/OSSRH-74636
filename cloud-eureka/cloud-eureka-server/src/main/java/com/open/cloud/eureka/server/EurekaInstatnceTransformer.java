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
package com.open.cloud.eureka.server;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EurekaInstatnceTransformer {

    public static InstanceInfo.InstanceStatus toEurekaInstanceStatus(InstanceStatus status) {
        switch (status) {
            case UP:
                return InstanceInfo.InstanceStatus.UP;
            case DOWN:
                return InstanceInfo.InstanceStatus.DOWN;
            case UNKNOWN:
                return InstanceInfo.InstanceStatus.UNKNOWN;
            case STARTING:
                return InstanceInfo.InstanceStatus.STARTING;
            case OUT_OF_SERVICE:
                return InstanceInfo.InstanceStatus.OUT_OF_SERVICE;
            default:
                log.error("不支持{}类型的实例状态", status);
                throw new UnsupportedOperationException("不支持的实例状态");

        }
    }

    public static InstanceStatus toGrayInstanceStatus(InstanceInfo.InstanceStatus status) {
        if (status == null) {
            return InstanceStatus.UNKNOWN;
        }
        switch (status) {
            case DOWN:
                return InstanceStatus.DOWN;
            case UP:
                return InstanceStatus.UP;
            case STARTING:
                return InstanceStatus.STARTING;
            case OUT_OF_SERVICE:
                return InstanceStatus.OUT_OF_SERVICE;
            default:
                return InstanceStatus.UNKNOWN;
        }
    }

}
