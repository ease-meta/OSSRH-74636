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
package com.open.cloud.flow.api;


import com.open.cloud.domain.api.BaseRequest;
import com.open.cloud.domain.api.BaseResponse;

import java.lang.reflect.ParameterizedType;

public interface IProcess<T extends BaseRequest, R extends BaseResponse> {

    R process(T request);

    /**
     * @param request
     * @return R
     */
    default R asyncProcess(T request) {
        return null;
    }

    default Class<? extends BaseRequest> getRequestModel() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    default Class<? extends BaseResponse> getResponseModel() {
        return (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * 服务前处理
     *
     * @param request
     */
    default void beforeProcess(T request) {

    }

    /**
     * 服务后处理
     *
     * @param request
     * @param response
     */
    default void afterProcess(T request, R response) {

    }
}
