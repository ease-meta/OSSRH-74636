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
/**
 * <p>Title: Ret.java</p>
 * <p>Description: </p>
 *
 * @author leijian
 * @date 2019年1月10日
 * @version 1.0
 */
package com.open.cloud.common.base;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.open.cloud.common.utils.MessageUtils;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author leijian
 * @date 2019年1月10日
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Ret {

    /**
     * 返回码
     */
    private Integer       retCode;
    /**
     * 返回信息
     */
    private String        retMsg;

    /**
     * 当前时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime retTime;

    public Ret(Integer retCode, String retMsg, LocalDateTime retTime) {
        this.retCode = retCode;
        this.retMsg = MessageUtils.message(retMsg);
        this.retTime = retTime;

    }
}
