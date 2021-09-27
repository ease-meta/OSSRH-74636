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
    //生成某一张表
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
        //生成某一张表
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
