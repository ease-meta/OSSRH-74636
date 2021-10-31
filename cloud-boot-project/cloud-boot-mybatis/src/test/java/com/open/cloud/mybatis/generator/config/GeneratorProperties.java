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
package com.open.cloud.mybatis.generator.config;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/27 23:00
 */
public class GeneratorProperties {
    /*  generatorProperties.isCreateCurrentPath = this.isCreateCurrentPath;
      generatorProperties.parentClassPackage = this.parentClassPackage;
      generatorProperties.tableName = this.tableName;
      generatorProperties.skipAttributes = this.skipAttributes;
      generatorProperties.entityDescription = this.entityDescription;
      generatorProperties.basePackage = this.basePackage;
      generatorProperties.isDeleteTablePrefix = this.isDeleteTablePrefix;
      generatorProperties.dbType = this.dbType;
      generatorProperties.iscreateMapperExt = this.iscreateMapperExt;
      generatorProperties.basedir = this.basedir;
      generatorProperties.isCrateAllTable = this.isCrateAllTable;
      generatorProperties.entityRelativePackage = this.entityRelativePackage;
      generatorProperties.entityPackage = this.entityPackage;
      generatorProperties.tablePrefix = this.tablePrefix;
      generatorProperties.entityParentClass = this.entityParentClass;
      generatorProperties.mapperPackage = this.mapperPackage;
      generatorProperties.author = this.author;
      generatorProperties.dbName = this.dbName;
      generatorProperties.tablePkSize = this.tablePkSize;*/
    private String tableName;

    private String entityDescription;

    private int tablePkSize;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityDescription() {
        return entityDescription;
    }

    public void setEntityDescription(String entityDescription) {
        this.entityDescription = entityDescription;
    }

    public int getTablePkSize() {
        return tablePkSize;
    }

    public void setTablePkSize(int tablePkSize) {
        this.tablePkSize = tablePkSize;
    }
}
