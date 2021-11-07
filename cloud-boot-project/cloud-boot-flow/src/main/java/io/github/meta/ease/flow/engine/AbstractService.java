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
package io.github.meta.ease.flow.engine;

import io.github.meta.ease.domain.api.BaseRequest;
import io.github.meta.ease.domain.api.BaseResponse;
import io.github.meta.ease.flow.engine.base.BusinessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService<T extends BaseRequest, R extends BaseResponse> implements BusinessEngine<T, R> {

    private transient final Logger logger = LoggerFactory.getLogger(AbstractService.class);


    abstract void executeSystemCheck(T req);


    abstract void idempotenCheck(T req);


    void initPlatformId(T req) {

    }
}
