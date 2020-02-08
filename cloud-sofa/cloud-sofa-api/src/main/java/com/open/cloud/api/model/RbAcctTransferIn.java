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

import java.math.BigDecimal;

@Data
public class RbAcctTransferIn extends SuperBaseRequest  {
    /**
     * 交易参考号<br>
     * REFERENCE<br>
     * seqNo:27<br>
     * dataType:String<br>
     * length:50<br>
     * cons:
     */
    private String reference;

    /**
     * 账户所属账务系统<br>
     * ACCT_SYSTEM<br>
     * seqNo:27<br>
     * dataType:String<br>
     * length:3<br>
     * cons:
     * 1.新核心 2.老核心
     */
    private String acctSystem;

    /**
     * 客户号<br>
     * CLIENT_NO<br>
     * seqNo:21<br>
     * dataType:String<br>
     * length:12<br>
     * cons:
     */
    private String clientNo;

    /**
     * 主账号<br>
     * BASE_ACCT_NO<br>
     * seqNo:1<br>
     * dataType:String<br>
     * length:50<br>
     * cons:
     */
    private String baseAcctNo;

    /**
     * 账户币种<br>
     * ACCT_CCY<br>
     * seqNo:2<br>
     * dataType:String<br>
     * length:3<br>
     * cons:
     */
    private String acctCcy;

    /**
     * 产品代码<br>
     * PROD_TYPE<br>
     * seqNo:3<br>
     * dataType:String<br>
     * length:3<br>
     * cons:渠道交易时不是必输
     */
    private String prodType;

    /**
     * 账户序列号<br>
     * ACCT_SEQ_NO<br>
     * seqNo:4<br>
     * dataType:String<br>
     * length:8<br>
     * cons:为空默认取1
     */
    private String acctSeqNo;

    /**
     * 卡二磁道信息<br>
     * TRACK2<br>
     * seqNo:5<br>
     * dataType:String<br>
     * length:37<br>
     * cons:
     */
    private String track2;

    /**
     * 卡三磁道信息<br>
     * TRACK3<br>
     * seqNo:6<br>
     * dataType:String<br>
     * length:104<br>
     * cons:
     */
    private String track3;

    /**
     * 支取方式<br>
     * WITHDRAWAL_TYPE<br>
     * seqNo:19<br>
     * dataType:String<br>
     * length:1<br>
     * cons:S凭印鉴支取
     * P凭密码支取
     * W无密码无印鉴支取
     * B凭印鉴和密码支取
     * O凭证件支取
     */
    private String withdrawalType;


    /**
     * 密码<br>
     * PASSWORD<br>
     * seqNo:20<br>
     * dataType:String<br>
     * length:50<br>
     * cons:密码支取时需要且必输
     */
    private String password;


    /**
     * 到账方式<br>
     * TRAN_METHOD<br>
     * seqNo:36<br>
     * dataType:String<br>
     * length:2<br>
     * cons:
     */
    private String tranMethod;

    /**
     * 存折携带标记<br>
     * PB_FLAG<br>
     * seqNo:10<br>
     * dataType:String<br>
     * length:1<br>
     * cons:
     */
    private String pbFlag;

    /**
     * 交易金额<br>
     * TRAN_AMT<br>
     * seqNo:11<br>
     * dataType:Double<br>
     * length:17<br>
     * length:2<br>
     * cons:
     */
    private BigDecimal tranAmt;

    /**
     * 交易币种<br>
     * TRAN_CCY<br>
     * seqNo:12<br>
     * dataType:String<br>
     * length:3<br>
     * cons:
     */
    private String tranCcy;

    /**
     * 交款单位<br>
     * PAY_UNIT<br>
     * seqNo:13<br>
     * dataType:String<br>
     * length:50<br>
     * cons:
     */
    private String payUnit;

}