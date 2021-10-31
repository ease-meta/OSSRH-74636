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
package com.open.cloud.web;

import lombok.Getter;
import lombok.Setter;

public class Result<T> {

    @Setter
    @Getter
    private int code;
    @Setter
    @Getter
    private String message;
    @Setter
    @Getter
    private T data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultMsgEnum.SUCCESS.getCode());
        result.setMessage(ResultMsgEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> error(int code, String message) {
        return new Result(code, message);
    }
}
