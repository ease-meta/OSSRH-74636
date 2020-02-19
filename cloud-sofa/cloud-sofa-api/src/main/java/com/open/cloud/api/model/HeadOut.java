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

import java.util.ArrayList;
import java.util.List;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/14 19:10
 **/
@Data
public class HeadOut extends BaseData {

    private String       runDate;
    private String       downFilePath;
    private String       retStatus;

    /**
     * 返回结果集
     */
    private List<Result> ret;

    public HeadOut(String retStatus, Result result) {
        this.retStatus = retStatus;
        this.ret = new ArrayList<>();
        this.ret.add(result);
    }
}
