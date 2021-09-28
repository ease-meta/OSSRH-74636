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
import com.boot.generator.mybatis.config.rules.DateType;
import com.boot.generator.mybatis.config.rules.DbColumnType;
import com.boot.generator.mybatis.config.rules.IColumnType;

import static com.boot.generator.mybatis.config.converts.TypeConverts.contains;
import static com.boot.generator.mybatis.config.converts.TypeConverts.containsAny;
import static com.boot.generator.mybatis.config.rules.DbColumnType.BIG_DECIMAL;
import static com.boot.generator.mybatis.config.rules.DbColumnType.BOOLEAN;
import static com.boot.generator.mybatis.config.rules.DbColumnType.BYTE_ARRAY;
import static com.boot.generator.mybatis.config.rules.DbColumnType.CLOB;
import static com.boot.generator.mybatis.config.rules.DbColumnType.DATE_SQL;
import static com.boot.generator.mybatis.config.rules.DbColumnType.DOUBLE;
import static com.boot.generator.mybatis.config.rules.DbColumnType.FLOAT;
import static com.boot.generator.mybatis.config.rules.DbColumnType.INTEGER;
import static com.boot.generator.mybatis.config.rules.DbColumnType.LOCAL_DATE;
import static com.boot.generator.mybatis.config.rules.DbColumnType.LOCAL_DATE_TIME;
import static com.boot.generator.mybatis.config.rules.DbColumnType.LOCAL_TIME;
import static com.boot.generator.mybatis.config.rules.DbColumnType.LONG;
import static com.boot.generator.mybatis.config.rules.DbColumnType.STRING;
import static com.boot.generator.mybatis.config.rules.DbColumnType.TIME;
import static com.boot.generator.mybatis.config.rules.DbColumnType.TIMESTAMP;

/**
 * KingbaseES 字段类型转换
 *
 * @author kingbase, hanchunlin
 * @since 2019-10-12
 */
public class KingbaseESTypeConvert implements ITypeConvert {
    public static final KingbaseESTypeConvert INSTANCE = new KingbaseESTypeConvert();

    /**
     * @param globalConfig 全局配置
     * @param fieldType    字段类型
     * @return 返回对应的字段类型
     */
    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        return TypeConverts.use(fieldType)
                .test(containsAny("char", "text", "json", "enum").then(STRING))
                .test(contains("bigint").then(LONG))
                .test(contains("int").then(INTEGER))
                .test(containsAny("date", "time").then(p -> toDateType(globalConfig, p)))
                .test(containsAny("bit", "boolean").then(BOOLEAN))
                .test(containsAny("decimal", "numeric").then(BIG_DECIMAL))
                .test(contains("clob").then(CLOB))
                .test(contains("blob").then(BYTE_ARRAY))
                .test(contains("float").then(FLOAT))
                .test(contains("double").then(DOUBLE))
                .or(STRING);
    }

    /**
     * 转换为日期类型
     *
     * @param config 配置信息
     * @param type   类型
     * @return 返回对应的列类型
     */
    private IColumnType toDateType(GlobalConfig config, String type) {
        DateType dateType = config.getDateType();
        if (dateType == DateType.SQL_PACK) {
            switch (type) {
                case "date":
                    return DATE_SQL;
                case "time":
                    return TIME;
                default:
                    return TIMESTAMP;
            }
        } else if (dateType == DateType.TIME_PACK) {
            switch (type) {
                case "date":
                    return LOCAL_DATE;
                case "time":
                    return LOCAL_TIME;
                default:
                    return LOCAL_DATE_TIME;
            }
        }
        return DbColumnType.DATE;
    }

}
