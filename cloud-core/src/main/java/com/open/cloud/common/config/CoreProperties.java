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
package com.open.cloud.common.config;

import com.open.cloud.common.base.Environment;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Data
public class CoreProperties {
    /**
     * 枚举:dev,test,prd
     */
    @Value("${bsf.env:dev}")
    private Environment  env;

    public static String Project                     = "Core";
    public static String SpringApplicationName       = "spring.application.name";
    public static String BsfEnv                      = "bsf.env";
    public static String SpringJacksonDateFormat     = "spring.jackson.date-format";
    public static String SpringJacksonTimeZone       = "spring.jackson.time-zone";
    public static String ServerTomcatMaxThreads      = "server.tomcat.max-threads";
    public static String ServerTomcatMaxConnections  = "server.tomcat.max-connections";
    public static String ServerTomcatMinSpaceThreads = "server.tomcat.min-spare-threads";
    public static String ServeCompressionEnabled     = "server.compression.enabled";
    public static String ServeCompressionMimeTypes   = "server.compression.mime-types";
    public static String LoggingFile                 = "logging.file";
    public static String LoggingFileMaxHistory       = "logging.file.max-history";
    public static String LoggingFileMaxSize          = "logging.file.max-size";
    public static String BsfLoggingFileTotalSize     = "bsf.logging.file.total-size";
    public static String BsfContextRestartText       = "bsf.context.restart.text";
    public static String BsfContextRestartEnabled    = "bsf.context.restart.enabled";
    public static String BsfContextRestartTimeSpan   = "bsf.context.restart.timespan";
    public static String BsfEnabled                  = "bsf.enabled";
    public static String BsfCollectHookEnabled       = "bsf.collect.hook.enabled";
}
