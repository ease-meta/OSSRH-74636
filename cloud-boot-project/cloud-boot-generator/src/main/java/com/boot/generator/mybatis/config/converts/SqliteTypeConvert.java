/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.boot.generator.mybatis.config.converts;

import com.boot.generator.mybatis.config.GlobalConfig;
import com.boot.generator.mybatis.config.ITypeConvert;
import com.boot.generator.mybatis.config.rules.DbColumnType;
import com.boot.generator.mybatis.config.rules.IColumnType;

import static com.boot.generator.mybatis.config.converts.MySqlTypeConvert.toDateType;
import static com.boot.generator.mybatis.config.converts.TypeConverts.contains;
import static com.boot.generator.mybatis.config.converts.TypeConverts.containsAny;
import static com.boot.generator.mybatis.config.rules.DbColumnType.BIG_DECIMAL;
import static com.boot.generator.mybatis.config.rules.DbColumnType.BLOB;
import static com.boot.generator.mybatis.config.rules.DbColumnType.BOOLEAN;
import static com.boot.generator.mybatis.config.rules.DbColumnType.CLOB;
import static com.boot.generator.mybatis.config.rules.DbColumnType.DOUBLE;
import static com.boot.generator.mybatis.config.rules.DbColumnType.FLOAT;
import static com.boot.generator.mybatis.config.rules.DbColumnType.INTEGER;
import static com.boot.generator.mybatis.config.rules.DbColumnType.LONG;
import static com.boot.generator.mybatis.config.rules.DbColumnType.STRING;

/**
 * SQLite 字段类型转换
 *
 * @author chen_wj, hanchunlin
 * @since 2019-05-08
 */
public class SqliteTypeConvert implements ITypeConvert {
    public static final SqliteTypeConvert INSTANCE = new SqliteTypeConvert();

    /**
     * @inheritDoc
     * @see MySqlTypeConvert#toDateType(GlobalConfig, String)
     */
    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        return TypeConverts.use(fieldType)
                .test(contains("bigint").then(LONG))
                .test(containsAny("tinyint(1)", "boolean").then(BOOLEAN))
                .test(contains("int").then(INTEGER))
                .test(containsAny("text", "char", "enum").then(STRING))
                .test(containsAny("decimal", "numeric").then(BIG_DECIMAL))
                .test(contains("clob").then(CLOB))
                .test(contains("blob").then(BLOB))
                .test(contains("float").then(FLOAT))
                .test(contains("double").then(DOUBLE))
                .test(containsAny("date", "time", "year").then(t -> toDateType(config, t)))
                .or(DbColumnType.STRING);
    }

}
