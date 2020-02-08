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
package com.open.cloud.api.model;

import lombok.Data;

@Data
public class Head {
    private String serviceId;
    private String transId;
    private String productCode;
    private String bankSrlNo;
    private String subTransSeqNo;
    private String clientId;
    private String branchId;
    private String uesrId;
    private String apprUserId;
    private String authUserId;
    private String apprFlag;
    private String authFlag;
    private String company;
    private String userLang;
    private String channelId;
    private String wsId;
    private String requestServiceId;
    private String requesterId;
    private String requestServerId;
    private String systemId;
    private String tranDate;
    private String tranTimestamp;
    private String upFilePath;
    private String pressureFlag;
    private String sourceBranchNo;
    private String destBranchNo;
    private String macValue;
}