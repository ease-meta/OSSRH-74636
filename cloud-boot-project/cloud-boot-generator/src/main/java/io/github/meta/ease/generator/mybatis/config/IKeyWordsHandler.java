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
package io.github.meta.ease.generator.mybatis.config;


import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * 关键字处理接口
 *
 * @author nieqiurong 2020/5/7.
 * @since 3.3.2
 */
public interface IKeyWordsHandler {

    /**
     * 获取关键字
     *
     * @return 关键字集合
     */
    @Nonnull
    Collection<String> getKeyWords();

    /**
     * 格式化关键字格式
     *
     * @return 格式
     */
    @Nonnull
    String formatStyle();

    /**
     * 是否为关键字
     *
     * @param columnName 字段名称
     * @return 是否为关键字
     */
    boolean isKeyWords(@Nonnull String columnName);

    /**
     * 格式化字段
     *
     * @param columnName 字段名称
     * @return 格式化字段
     */
    @Nonnull
    default String formatColumn(@Nonnull String columnName) {
        return String.format(formatStyle(), columnName);
    }

}
