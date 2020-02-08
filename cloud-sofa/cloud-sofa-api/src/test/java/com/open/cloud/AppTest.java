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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.open.cloud.api.model.RbAcctTransferIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest 
{
    public static void main(String[] args) {
        RbAcctTransferIn rbAcctTransferIn = new RbAcctTransferIn();
        rbAcctTransferIn.setBaseAcctNo("123456");
        log.info("{}",rbAcctTransferIn);
        BaseAcctNo baseAcctNo = new BaseAcctNo();
        log.info("{}",baseAcctNo);
        BeanUtils.copyProperties(rbAcctTransferIn,baseAcctNo);
        log.info("{}",baseAcctNo);
        JSONObject json = JSON.parseObject(JSON.toJSONString(rbAcctTransferIn));

        JSONObject headJSON = json.getJSONObject("head");
    }
}
