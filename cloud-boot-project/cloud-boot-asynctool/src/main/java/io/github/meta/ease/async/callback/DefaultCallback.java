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
package io.github.meta.ease.async.callback;

import io.github.meta.ease.async.worker.WorkResult;

/**
 * 默认回调类，如果不设置的话，会默认给这个回调
 *
 * @author wuweifeng wrote on 2019-11-19.
 */
public class DefaultCallback<T, V> implements ICallback<T, V> {
    @Override
    public void begin() {

    }

    @Override
    public void result(boolean success, T param, WorkResult<V> workResult) {

    }

}
