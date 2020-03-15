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
package com.open.cloud.common.base;

import java.time.LocalDateTime;

/**
 * @author Leijian
 * @date 2020/2/8
 */
public class ResponseTool<T> {

    public static <T> Response<T> response(int code, String message) {
        return Response.<T> builder().ret(extracted(code, message)).build();
    }

    public static <T> Response<T> response(int code, String message, T object) {
        return Response.<T> builder().ret(extracted(code, message)).result(object).build();
    }

    public static <T> Response<T> success(T object) {
        return Response.<T> builder().ret(extracted(200, "S")).result(object).build();
    }

    public static <T> Response<T> success() {
        return Response.<T> builder().ret(extracted(200, "S")).build();
    }

    public static <T> Response<T> response(int code) {
        return Response.<T> builder().ret(extracted(code)).build();
    }

    public static <T> Response<T> response(int code, T object) {
        return Response.<T> builder().ret(extracted(code)).result(object).build();
    }

    private static Ret extracted(int code) {
        return Ret.builder().retCode(code).retTime(LocalDateTime.now()).build();
    }

    private static Ret extracted(int code, String message) {
        return Ret.builder().retCode(code).retMsg(message).retTime(LocalDateTime.now()).build();
    }
}
