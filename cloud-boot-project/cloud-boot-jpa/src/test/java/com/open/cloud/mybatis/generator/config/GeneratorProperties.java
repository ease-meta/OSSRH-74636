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
