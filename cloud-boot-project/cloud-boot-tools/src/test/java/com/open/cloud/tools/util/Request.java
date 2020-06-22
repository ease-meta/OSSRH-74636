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
package com.open.cloud.tools.util;

/**
 * @author Leijian
 * @date 2020/6/3
 */
public class Request {

    /**
     * appHead : {"currentNum":"0","pageEnd":"0","pageStart":"0","pgupOrPgdn":"0","totalNum":"-1"}
     * body : {"baseAcctNo":"24000200000006048"}
     * localHead : {}
     * sysHead : {"apprFlag":"","apprUserId":"","authFlag":"N","authPassword":"","authUserId":"","branchId":"2000","destBranchNo":"2000","messageType":"1400","messageCode":"2101","moduleId":"CL","programId":"4100","reversalTranType":"","sceneId":"01","seqNo":"68f728852cc36723142306371","serverId":"192.168.161.156","serviceCode":"MbsdCore","sourceBranchNo":"9903","sourceType":"MT","tranCode":"","tranDate":"20240710","tranMode":"ONLINE","tranTimestamp":"142306371","tranType":"","userId":"CP0101","userLang":"CHINESE","wsId":"05"}
     */

    private AppHead   appHead;
    private Body      body;
    private LocalHead localHead;
    private SysHead   sysHead;

    public AppHead getAppHead() {
        return appHead;
    }

    public void setAppHead(AppHead appHead) {
        this.appHead = appHead;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public LocalHead getLocalHead() {
        return localHead;
    }

    public void setLocalHead(LocalHead localHead) {
        this.localHead = localHead;
    }

    public SysHead getSysHead() {
        return sysHead;
    }

    public void setSysHead(SysHead sysHead) {
        this.sysHead = sysHead;
    }

    public static class AppHead {
        /**
         * currentNum : 0
         * pageEnd : 0
         * pageStart : 0
         * pgupOrPgdn : 0
         * totalNum : -1
         */

        private String currentNum;
        private String pageEnd;
        private String pageStart;
        private String pgupOrPgdn;
        private String totalNum;

        public String getCurrentNum() {
            return currentNum;
        }

        public void setCurrentNum(String currentNum) {
            this.currentNum = currentNum;
        }

        public String getPageEnd() {
            return pageEnd;
        }

        public void setPageEnd(String pageEnd) {
            this.pageEnd = pageEnd;
        }

        public String getPageStart() {
            return pageStart;
        }

        public void setPageStart(String pageStart) {
            this.pageStart = pageStart;
        }

        public String getPgupOrPgdn() {
            return pgupOrPgdn;
        }

        public void setPgupOrPgdn(String pgupOrPgdn) {
            this.pgupOrPgdn = pgupOrPgdn;
        }

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }
    }

    public static class Body {
        /**
         * baseAcctNo : 24000200000006048
         */

        private String baseAcctNo;

        public String getBaseAcctNo() {
            return baseAcctNo;
        }

        public void setBaseAcctNo(String baseAcctNo) {
            this.baseAcctNo = baseAcctNo;
        }
    }

    public static class LocalHead {
    }

    public static class SysHead {
        /**
         * apprFlag :
         * apprUserId :
         * authFlag : N
         * authPassword :
         * authUserId :
         * branchId : 2000
         * destBranchNo : 2000
         * messageType : 1400
         * messageCode : 2101
         * moduleId : CL
         * programId : 4100
         * reversalTranType :
         * sceneId : 01
         * seqNo : 68f728852cc36723142306371
         * serverId : 192.168.161.156
         * serviceCode : MbsdCore
         * sourceBranchNo : 9903
         * sourceType : MT
         * tranCode :
         * tranDate : 20240710
         * tranMode : ONLINE
         * tranTimestamp : 142306371
         * tranType :
         * userId : CP0101
         * userLang : CHINESE
         * wsId : 05
         */

        private String apprFlag;
        private String apprUserId;
        private String authFlag;
        private String authPassword;
        private String authUserId;
        private String branchId;
        private String destBranchNo;
        private String messageType;
        private String messageCode;
        private String moduleId;
        private String programId;
        private String reversalTranType;
        private String sceneId;
        private String seqNo;
        private String serverId;
        private String serviceCode;
        private String sourceBranchNo;
        private String sourceType;
        private String tranCode;
        private String tranDate;
        private String tranMode;
        private String tranTimestamp;
        private String tranType;
        private String userId;
        private String userLang;
        private String wsId;

        public String getApprFlag() {
            return apprFlag;
        }

        public void setApprFlag(String apprFlag) {
            this.apprFlag = apprFlag;
        }

        public String getApprUserId() {
            return apprUserId;
        }

        public void setApprUserId(String apprUserId) {
            this.apprUserId = apprUserId;
        }

        public String getAuthFlag() {
            return authFlag;
        }

        public void setAuthFlag(String authFlag) {
            this.authFlag = authFlag;
        }

        public String getAuthPassword() {
            return authPassword;
        }

        public void setAuthPassword(String authPassword) {
            this.authPassword = authPassword;
        }

        public String getAuthUserId() {
            return authUserId;
        }

        public void setAuthUserId(String authUserId) {
            this.authUserId = authUserId;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getDestBranchNo() {
            return destBranchNo;
        }

        public void setDestBranchNo(String destBranchNo) {
            this.destBranchNo = destBranchNo;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageCode() {
            return messageCode;
        }

        public void setMessageCode(String messageCode) {
            this.messageCode = messageCode;
        }

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getProgramId() {
            return programId;
        }

        public void setProgramId(String programId) {
            this.programId = programId;
        }

        public String getReversalTranType() {
            return reversalTranType;
        }

        public void setReversalTranType(String reversalTranType) {
            this.reversalTranType = reversalTranType;
        }

        public String getSceneId() {
            return sceneId;
        }

        public void setSceneId(String sceneId) {
            this.sceneId = sceneId;
        }

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
        }

        public String getServerId() {
            return serverId;
        }

        public void setServerId(String serverId) {
            this.serverId = serverId;
        }

        public String getServiceCode() {
            return serviceCode;
        }

        public void setServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
        }

        public String getSourceBranchNo() {
            return sourceBranchNo;
        }

        public void setSourceBranchNo(String sourceBranchNo) {
            this.sourceBranchNo = sourceBranchNo;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getTranCode() {
            return tranCode;
        }

        public void setTranCode(String tranCode) {
            this.tranCode = tranCode;
        }

        public String getTranDate() {
            return tranDate;
        }

        public void setTranDate(String tranDate) {
            this.tranDate = tranDate;
        }

        public String getTranMode() {
            return tranMode;
        }

        public void setTranMode(String tranMode) {
            this.tranMode = tranMode;
        }

        public String getTranTimestamp() {
            return tranTimestamp;
        }

        public void setTranTimestamp(String tranTimestamp) {
            this.tranTimestamp = tranTimestamp;
        }

        public String getTranType() {
            return tranType;
        }

        public void setTranType(String tranType) {
            this.tranType = tranType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserLang() {
            return userLang;
        }

        public void setUserLang(String userLang) {
            this.userLang = userLang;
        }

        public String getWsId() {
            return wsId;
        }

        public void setWsId(String wsId) {
            this.wsId = wsId;
        }
    }
}
