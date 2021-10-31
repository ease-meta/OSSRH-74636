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
package com.boot.generator.mybatis.test;

import com.boot.generator.mybatis.FastAutoGenerator;
import com.boot.generator.mybatis.config.*;
import com.boot.generator.mybatis.config.rules.DateType;
import com.boot.generator.mybatis.engine.FreemarkerTemplateEngine;
import com.open.cloud.logging.BizLogger;
import com.open.cloud.logging.BizLoggerFactory;

import java.io.File;
import java.util.Collections;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/25 19:42
 */
public class FastAutoGeneratorOracleMain {

    private static final BizLogger logger = BizLoggerFactory
            .getLogger(FastAutoGeneratorOracleMain.class);

    public static void main(String[] args) {
        //TODO 1.生成扩展类；2.entity父类;3.mapper父类；4.xml格式重新定义；5.
        FastAutoGenerator.create(new DataSourceConfig.Builder()
                        .url("jdbc:mariadb://127.0.0.1:3306/botj?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true")
                        .username("root")
                        .password("123456"))
                .globalConfig((GlobalConfig.Builder builder) -> {
                    builder.author("leijian")
                            .fileOverride()
                            .outputDir(System.getProperty("user.dir") + File.separator + "cloud-boot-project\\cloud-boot-generator\\src\\test\\java\\")
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent("")
                            .moduleName("")
                            .entity("com.open.cloud.mybatis.demo.entity")
                            .mapper("com.open.cloud.mybatis.demo.mapper")
                            .xml("mapper.xml")
                            .other("_ext")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + File.separator + "cloud-boot-project\\cloud-boot-mybatis\\src\\test\\resources\\mapper\\"))
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig((TemplateConfig.Builder builder) -> {
                    builder.disable(TemplateType.SERVICE, TemplateType.SERVICEIMPL, TemplateType.CONTROLLER)
                            .build();
                })
                .injectionConfig(builder -> builder.beforeOutputFile((tableInfo, objectMap) -> {
                    System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                }))
                .strategyConfig(builder -> {
                    //builder.addTablePrefix("SYS_USER").build();
                    //builder.entityBuilder().enableChainModel().superClass(BasePO.class);
                    //builder.mapperBuilder().enableBaseResultMap().enableBaseColumnList().superClass(BaseDaoAction.class);
                })
                .execute();
    }
}
