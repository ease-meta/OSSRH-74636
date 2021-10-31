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
package com.open.cloud.core.thread;

public class DateUtils {

    /**
     * 每秒毫秒数
     */
    public static final int MILLISECONDS_PER_SECONDE = 1000;
    /**
     * 每分毫秒数 60*1000
     */
    public static final int MILLISECONDS_PER_MINUTE = 60000;
    /**
     * 每小时毫秒数 36*60*1000
     */
    public static final int MILLISECONDS_PER_HOUR = 3600000;
    /**
     * 每天毫秒数 24*60*60*1000;
     */
    public static final long MILLISECONDS_PER_DAY = 86400000;

    /**
     * 普通时间的格式
     */
    public static final String DATE_FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 毫秒级时间的格式
     */
    public static final String DATE_FORMAT_MILLS_TIME = "yyyy-MM-dd HH:mm:ss.SSS";

}
