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
package com.open.cloud.boot.autoconfigure.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.open.cloud.boot.autoconfigure.oss.alibaba.AliCloudAuthorizationMode;
import com.open.cloud.boot.autoconfigure.oss.alibaba.AliOssFile;
import com.open.cloud.boot.autoconfigure.oss.alibaba.env.AliCloudProperties;
import com.open.cloud.boot.autoconfigure.oss.alibaba.env.AliOssProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * The type Oss auto configuration.
 *
 * @author Leijian
 * @date 2021 /1/18
 */
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

    /**
     * The type Oss configuration.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnMissingBean({FileBase.class})
    @Import({OssAutoConfiguration.AliOss.class})
    protected static class OssConfiguration {

    }

    /**
     * The type Ali oss.
     */
    @ConditionalOnClass(value = com.aliyun.oss.OSS.class)
    @ConditionalOnProperty(name = "com.open.oss.type", havingValue = "com.open.cloud.boot.autoconfigure.oss.alibaba.AliOssFile")
    @EnableConfigurationProperties({AliCloudProperties.class, AliOssProperties.class})
    static class AliOss {

        /**
         * Oss client oss.
         *
         * @param aliCloudProperties the ali cloud properties
         * @param ossProperties      the oss properties
         * @return the oss
         */
        @ConditionalOnMissingBean
        @Bean(destroyMethod = "shutdown")
        public OSS ossClient(AliCloudProperties aliCloudProperties, AliOssProperties ossProperties) {
            if (ossProperties.getAuthorizationMode() == AliCloudAuthorizationMode.AK_SK) {
                Assert.isTrue(StringUtils.hasText(ossProperties.getEndpoint()),
                        "Oss endpoint can't be empty.");
                Assert.isTrue(StringUtils.hasText(aliCloudProperties.getAccessKey()),
                        "${alibaba.cloud.access-key} can't be empty.");
                Assert.isTrue(StringUtils.hasText(aliCloudProperties.getSecretKey()),
                        "${alibaba.cloud.secret-key} can't be empty.");
                return new OSSClientBuilder().build(ossProperties.getEndpoint(),
                        aliCloudProperties.getAccessKey(), aliCloudProperties.getSecretKey(),
                        ossProperties.getConfig());
            } else if (ossProperties.getAuthorizationMode() == AliCloudAuthorizationMode.STS) {
                Assert.isTrue(StringUtils.hasText(ossProperties.getEndpoint()),
                        "Oss endpoint can't be empty.");
                Assert.isTrue(StringUtils.hasText(ossProperties.getSts().getAccessKey()),
                        "Access key can't be empty.");
                Assert.isTrue(StringUtils.hasText(ossProperties.getSts().getSecretKey()),
                        "Secret key can't be empty.");
                Assert.isTrue(StringUtils.hasText(ossProperties.getSts().getSecurityToken()),
                        "Security Token can't be empty.");
                return new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties
                        .getSts().getAccessKey(), ossProperties.getSts().getSecretKey(), ossProperties
                        .getSts().getSecurityToken(), ossProperties.getConfig());
            } else {
                throw new IllegalArgumentException("Unknown auth mode.");
            }
        }

        /**
         * File base file base.
         *
         * @param oss the oss
         * @return the file base
         */
        @Bean(destroyMethod = "close")
        public FileBase fileBase(OSS oss) {
            return new AliOssFile(oss);
        }
    }
}
