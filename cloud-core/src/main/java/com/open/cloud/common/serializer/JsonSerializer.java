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
package com.open.cloud.common.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.open.cloud.common.config.CoreProperties;
import com.open.cloud.common.exception.BaseException;
import com.open.cloud.common.utils.PropertyUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public class JsonSerializer implements Serializer {
    private static ObjectMapper objectMapper = new ObjectMapper()
                                                 .setSerializationInclusion(
                                                     JsonInclude.Include.NON_NULL)
                                                 .enable(MapperFeature.USE_ANNOTATIONS)
                                                 .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                                                 .disable(
                                                     SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                                                 .disable(
                                                     DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                                                 .setTimeZone(
                                                     TimeZone.getTimeZone(PropertyUtils
                                                         .getPropertyCache(
                                                             CoreProperties.SpringJacksonTimeZone,
                                                             "GMT+8")))
                                                 .setDateFormat(
                                                     new SimpleDateFormat(
                                                         PropertyUtils
                                                             .getPropertyCache(
                                                                 CoreProperties.SpringJacksonDateFormat,
                                                                 "yyyy-MM-dd HH:mm:ss")));

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public String name() {
        return "Json";
    }

    @Override
    public byte[] serialize(Object obj) throws IOException {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new BaseException("序列化失败，对象：" + obj, e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, T classOfT) throws IOException {
        try {
            return (T) objectMapper.readValue(data, new TypeReference<Object>() {
                @Override
                public Type getType() {
                    return classOfT.getClass();
                }
            });
        } catch (Exception e) {
            throw new BaseException("反序列化失败，类型：" + classOfT + "，JSON：", e);
        }
    }
}