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
package com.open.cloud.dynamic.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author leijian
 * @version 1.0
 * @Description TODO
 * @date 2019/9/24 16:42
 **/
@Slf4j
public final class LogUpdateFactory {

    /**
     * @param logMappings
     */
    static void updateLogMappings(HashMap<String, String> logMappings) {

        if (CollectionUtils.isEmpty(logMappings)) {
            return;
        }
        try {
            Iterator<Map.Entry<String, String>> iterator = logMappings.entrySet().iterator();
            ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory
                    .getILoggerFactory();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                loggerContext.getLogger(key.trim()).setLevel(
                        ch.qos.logback.classic.Level.toLevel(value));
                log.info("set {} level to {}", key, value);
            }
        } catch (Throwable th) {
            log.error("Invalid root level to updateLogMappings");
        }
    }

    static void updateRootlevel(String rootlevel) {
        if (StringUtils.isEmpty(rootlevel)) {
            return;
        }
        try {
            ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory
                    .getILoggerFactory();
            loggerContext.getLogger("root").setLevel(
                    ch.qos.logback.classic.Level.toLevel(rootlevel));
            log.info("set root level to {}", rootlevel);
        } catch (Throwable th) {
            log.error("Invalid root level {} to setLogLevel, {}", rootlevel, th);
        }

    }
}
