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
package com.open.cloud.common.utils;

import com.open.cloud.common.exception.BaseException;
import org.springframework.boot.convert.ApplicationConversionService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Leijian
 */
public class ConvertUtils {
    public static <T> T convert(Object value, Class<T> type) {
        if (value == null) {
            return null;
        }
        return (T) ApplicationConversionService.getSharedInstance().convert(value, type);
    }

    public static <T> T tryConvert(Object value, Class<T> type) {
        try {
            return convert(value, type);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T deepClone(T obj) {
        try {
            try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
                try (ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
                    out.writeObject(obj);
                    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(
                        byteOut.toByteArray())) {
                        ObjectInputStream in = new ObjectInputStream(byteIn);
                        return (T) in.readObject();
                    }
                }
            }
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }
}
