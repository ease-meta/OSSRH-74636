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
package com.open.cloud.mybatis.generator.test;

import com.open.cloud.mybatis.generator.config.DataSourceConfig;
import com.open.cloud.mybatis.generator.config.GeneratorConfig;
import com.open.cloud.mybatis.generator.config.GlobalConfig;
import com.open.cloud.mybatis.generator.service.CodeGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/27 22:20
 */
public class AutoGeneratorTest {

    private static final Logger logger = LoggerFactory.getLogger(AutoGeneratorTest.class);

    public static void main(String[] args) throws Exception {
        //DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "ENS_CIF", "ENS_CIF").build();
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(
                "jdbc:mariadb://127.0.0.1:3306/botj?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true",
                "root", "123456").build();
        GlobalConfig globalConfig = GlobalConfig.GlobalConfigBuilder.GlobalConfig()
                .withAuthor("leijian")
                //��Ŀ��·��������������
                .withOutputDir("").withFileOverride(true).build();
        GeneratorConfig generatorConfig = GeneratorConfig.GeneratorConfigBuilder.aGeneratorConfig()
                .withCrateAllTable(true).build();

        CodeGeneratorService.CodeGeneratorServiceBuilder.aCodeGeneratorService()
                .withDataSourceConfig(dataSourceConfig).withGlobalConfig(globalConfig)
                .withGeneratorConfig(generatorConfig).build().createCode();

    }
}
