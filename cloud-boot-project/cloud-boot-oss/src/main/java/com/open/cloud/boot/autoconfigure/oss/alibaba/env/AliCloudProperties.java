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
package com.open.cloud.boot.autoconfigure.oss.alibaba.env;

import com.alibaba.cloud.context.AliCloudConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.open.cloud.boot.autoconfigure.oss.alibaba.env.AliCloudProperties.PROPERTY_PREFIX;

/**
 * The type Ali cloud properties.
 */
@ConfigurationProperties(prefix = PROPERTY_PREFIX)
public class AliCloudProperties implements AliCloudConfiguration {

    /**
     * The prefix of the property of {@link AliCloudProperties}.
     */
    public static final String PROPERTY_PREFIX = "alibaba.cloud";

    /**
     * The the property of {@link #getAccessKey()}.
     */
    public static final String ACCESS_KEY_PROPERTY = PROPERTY_PREFIX + ".access-key";

    /**
     * The prefix of the property of {@link #getSecretKey()}.
     */
    public static final String SECRET_KEY_PROPERTY = PROPERTY_PREFIX + ".secret-key";

    /**
     * alibaba cloud access key.
     */
    private String accessKey;

    /**
     * alibaba cloud secret key.
     */
    private String secretKey;

    @Override
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * Sets access key.
     *
     * @param accessKey the access key
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Sets secret key.
     *
     * @param secretKey the secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}