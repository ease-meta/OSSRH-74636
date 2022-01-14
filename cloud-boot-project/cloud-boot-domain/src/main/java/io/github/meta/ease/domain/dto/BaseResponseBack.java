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
package io.github.meta.ease.domain.dto;

import lombok.Data;

@Data
public class BaseResponseBack<T> extends BaseDTO {

    public static final String SUCCESS_CODE = "000000";

    public static final String FAIL_CODE = "999999";

    private Boolean result;

    /**
     * 000000 ：成功， 999999 ；异常，其余code单定
     */
    private String code;

    private String message;

    private T data;

    public BaseResponseBack() {
    }

    public BaseResponseBack(boolean result, String code, String message, T data) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponseBack(boolean result, String code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public BaseResponseBack(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponseBack(String message) {
        this.message = message;
    }

    public static BaseResponseBack<?> success() {
        return new BaseResponseBack<>(Boolean.TRUE, "000000", "操作成功!");
    }

    public static BaseResponseBack<?> success(String code, String message) {
        return new BaseResponseBack<>(Boolean.TRUE, code, message);
    }

    public static <T> BaseResponseBack<T> success(T data) {
        return new BaseResponseBack<>(Boolean.TRUE, "000000", "操作成功", data);
    }

    public static BaseResponseBack<?> fail() {
        return new BaseResponseBack<>(Boolean.FALSE, "999999", "操作失败!");
    }

    public static BaseResponseBack<?> fail(String code, String message) {
        return new BaseResponseBack<>(Boolean.FALSE, code, message);
    }

    public static BaseResponseBack<?> fail(String message) {
        return new BaseResponseBack<>(Boolean.FALSE, "999999", message);
    }
}
