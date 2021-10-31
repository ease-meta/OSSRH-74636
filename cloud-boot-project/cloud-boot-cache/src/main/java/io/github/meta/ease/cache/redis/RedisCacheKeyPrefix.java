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
package io.github.meta.ease.cache.redis;

import io.github.meta.ease.cache.CacheConstant;
import org.springframework.data.redis.cache.CacheKeyPrefix;

/**
 * @author Leijian
 * @date 2020/4/22
 */
public class RedisCacheKeyPrefix implements CacheKeyPrefix {

    private String delimiter;

    public RedisCacheKeyPrefix() {

    }

    public RedisCacheKeyPrefix(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public String compute(String cacheName) {
        return delimiter != null ? cacheName.concat(delimiter) : cacheName
                .concat(CacheConstant.COLON);
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
