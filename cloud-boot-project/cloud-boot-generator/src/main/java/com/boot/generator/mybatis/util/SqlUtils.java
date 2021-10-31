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
package com.boot.generator.mybatis.util;

import com.boot.generator.mybatis.annotation.SqlLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.boot.generator.mybatis.util.StringPool.PERCENT;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/28 21:18
 */
public class SqlUtils {

    private static final Logger logger = LoggerFactory.getLogger(SqlUtils.class);

    /**
     * ��%����like
     *
     * @param str ԭ�ַ���
     * @return like ��ֵ
     */
    public static String concatLike(Object str, SqlLike type) {
        switch (type) {
            case LEFT:
                return PERCENT + str;
            case RIGHT:
                return str + PERCENT;
            default:
                return PERCENT + str + PERCENT;
        }
    }
}
