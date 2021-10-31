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

public class GeneratorConfig {

    // String basedir;
    // private String author;
    // private boolean fileOverride;
    // private boolean crateAllTable;
    // private String  createMapperExt;

    private String entityPackage;
    private String entityParentClass;

    private String mapperPackage;
    //����ĳһ�ű�
    private String tableName;

    private String parentClassPackage;

    private boolean crateAllTable;

    private boolean createMapperExt;

    public boolean isCrateAllTable() {
        return crateAllTable;
    }

    public boolean isCreateMapperExt() {
        return createMapperExt;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public String getEntityParentClass() {
        return entityParentClass;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public String getTableName() {
        return tableName;
    }

    public String getParentClassPackage() {
        return parentClassPackage;
    }

    public static final class GeneratorConfigBuilder {
        private String entityPackage;
        private String entityParentClass;
        private String mapperPackage;
        //����ĳһ�ű�
        private String tableName;
        private String parentClassPackage;
        private boolean crateAllTable;
        private boolean createMapperExt;

        private GeneratorConfigBuilder() {
        }

        public static GeneratorConfigBuilder aGeneratorConfig() {
            return new GeneratorConfigBuilder();
        }

        public GeneratorConfigBuilder withEntityPackage(String entityPackage) {
            this.entityPackage = entityPackage;
            return this;
        }

        public GeneratorConfigBuilder withEntityParentClass(String entityParentClass) {
            this.entityParentClass = entityParentClass;
            return this;
        }

        public GeneratorConfigBuilder withMapperPackage(String mapperPackage) {
            this.mapperPackage = mapperPackage;
            return this;
        }

        public GeneratorConfigBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public GeneratorConfigBuilder withParentClassPackage(String parentClassPackage) {
            this.parentClassPackage = parentClassPackage;
            return this;
        }

        public GeneratorConfigBuilder withCrateAllTable(boolean crateAllTable) {
            this.crateAllTable = crateAllTable;
            return this;
        }

        public GeneratorConfigBuilder withCreateMapperExt(boolean createMapperExt) {
            this.createMapperExt = createMapperExt;
            return this;
        }

        public GeneratorConfig build() {
            GeneratorConfig generatorConfig = new GeneratorConfig();
            generatorConfig.parentClassPackage = this.parentClassPackage;
            generatorConfig.mapperPackage = this.mapperPackage;
            generatorConfig.entityPackage = this.entityPackage;
            generatorConfig.tableName = this.tableName;
            generatorConfig.entityParentClass = this.entityParentClass;
            generatorConfig.crateAllTable = this.crateAllTable;
            generatorConfig.createMapperExt = this.createMapperExt;
            return generatorConfig;
        }
    }
}
