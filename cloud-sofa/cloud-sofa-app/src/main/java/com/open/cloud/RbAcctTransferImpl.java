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
package com.open.cloud;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.open.cloud.api.IRbAcctTransfer;
import com.open.cloud.api.model.HeadOut;
import com.open.cloud.api.model.RbAcctTransferIn;
import com.open.cloud.api.model.RbAcctTransferOut;
import com.open.cloud.api.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SofaService(bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class RbAcctTransferImpl implements IRbAcctTransfer {


    @Override
    public RbAcctTransferOut rbAcctTransfer(RbAcctTransferIn in) {
        log.info("{}", in);
        log.info("{}", in.getHead());
        Result ret = new Result("123", "456");
        String retStatus = "OK";
        HeadOut headOut = new HeadOut(retStatus, ret);
        RbAcctTransferOut rbAcctTransferOut = new RbAcctTransferOut();
        rbAcctTransferOut.setAcctCcy("CNY");
        rbAcctTransferOut.setHead(headOut);
        log.info("{}",rbAcctTransferOut.getHead());
        log.info("{}",rbAcctTransferOut);
        return rbAcctTransferOut;
    }
}
