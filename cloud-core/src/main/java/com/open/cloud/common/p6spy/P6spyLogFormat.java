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
package com.open.cloud.common.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang.StringUtils;

/**
 * @author Leijian
 * @date   2020/2/8
 */
public class P6spyLogFormat implements MessageFormattingStrategy {

    /**
    * @Description //生成美观SQL
    * @author leijian
    * @date 2019/9/12 10:21
    * @param connectionId, now, elapsed, category, prepared, sql, url
    * @return java.lang.String
    **/
    @Override
    public String formatMessage(final int connectionId, final String now, final long elapsed, final String category, final String prepared, final String sql, final String url) {

        return StringUtils.isNotEmpty(sql) ? new StringBuilder().append(" Execute SQL：").append(sql.replaceAll("[\\s]+", " ")).toString() : null;
    }
}
