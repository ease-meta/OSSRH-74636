package com.open.cloud.mybatis.generator.service;

import com.open.cloud.mybatis.generator.entity.GeneratorProperties;
import com.open.cloud.mybatis.generator.mapper.CodeGeneratorMapper;
import com.open.cloud.mybatis.generator.util.PbUtils;
import com.open.cloud.mybatis.generator.util.TemplateHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configurable
public class CodeGeneratorService {

    public static final String TRUE = "true";

    @Autowired
    private CodeGeneratorMapper codeDao;

    @Autowired
    GeneratorProperties generatorProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorService.class);
    private static Map<String, Object> validationMap = new HashMap<>();

    List<Map<String, Object>> entityList = new ArrayList<>();
    private List<String> skipAttributesList = new ArrayList<>();
    List<Map<String, Object>> allTables = new ArrayList<>();

    /**
     * 生成代码入口 需判断一次生成全部表还是指定一张表
     */

    public void createCode(Class clz) throws Exception {
        //获取当前类的包名
        String packageName = clz.getPackage().getName();
        //获取当前类的路径
        String path = clz.getResource("/").getPath();
        path = path.substring(1, path.indexOf("/target"));
        generatorProperties.setBasedir(path);
        generatorProperties.setBasePackage(packageName);
        String isCrateAllTable = generatorProperties.getIsCrateAllTable();

        if (generatorProperties.getSkipAttributes() != null) {
            for (String attribute : generatorProperties.getSkipAttributes().split(",")) {
                skipAttributesList.add(attribute);
            }
        }

        //生成所有表
        if (!PbUtils.isEmpty(isCrateAllTable) && TRUE.equals(isCrateAllTable.toLowerCase())) {
            List<Map<String, Object>> listTables = getTablesList();
            allTables = listTables;
            if (listTables.size() > 0) {
                for (Map<String, Object> listTable : listTables) {
                    generatorProperties.setTableName((String) listTable.get("TABLE_NAME"));
                    String tableComment = (String) listTable.get("TABLE_COMMENT");
                    generatorProperties.setEntityDescription(tableComment);
                    createCodeFolw();
                }
            } else {
                LOGGER.info("数据库不存在,请确认generator.properties配置文件的generator.dbName配置是否正确");
            }
        } else {

            String[] tableNames = generatorProperties.getTableName().split(",");

            for (String tableName : tableNames) {
                generatorProperties.setTableName(tableName);
                String tableComment = getTableComment();
                generatorProperties.setEntityDescription(tableComment);
                createCodeFolw();
            }
        }


        LOGGER.info("生成代码成功，请刷新该模块");
        System.exit(0);
    }

    /**
     * 生成对应文件
     */
    public void createCodeFolw() throws Exception {

        List<String> tableKeys = getTableKeys();
        generatorProperties.setTablePkSize(tableKeys.size());
        Map<String, Object> modelDate = getTemplateData(getTableComumnModel(tableKeys));
        if (!modelDate.isEmpty()) {
//            //生成entity
            createEntity(modelDate);
//            //生成Mapper.Xml
            createMapperXml(modelDate);
            //生成Mapper_ext.Xml
            String iscreateMapperExt = generatorProperties.getIsCreateMapperExt();
            if (!PbUtils.isEmpty(iscreateMapperExt) && TRUE.equals(iscreateMapperExt.toLowerCase())) {
                createMapperExt(modelDate);
            }
            entityList.clear();
            LOGGER.info(generatorProperties.getTableName() + "表对应的JavaBean和Mapper文件生成");

        } else {
            LOGGER.info("generator.properties配置文件的dbName或者tableName不存在");
        }
    }


    /**
     * 生成mybatis mapper文件
     */
    public void createMapperXml(Map<String, Object> modelDate) throws Exception {
        String ftlName = "/MybatisMapper.ftl";
        // 生成文件的路径和名称
        String fileName = generatorProperties.getBasedir() + "/src/main/resources/" + generatorProperties.getMapperPackage() + "/" + PbUtils
                .convertToCamelCase(generatorProperties.getTableName()) + "Mapper.xml";
        TemplateHelp.creatTemplate(modelDate, ftlName, fileName);
    }

    /**
     * 生成mybatis mapper_ext文件
     */
    public void createMapperExt(Map<String, Object> modelDate) throws Exception {
        String ftlName = "/MybatisMapper_ext.ftl";
        // 生成文件的路径和名称
        String fileName = generatorProperties.getBasedir() + "/src/main/resources/" + generatorProperties.getMapperPackage() + "/" + PbUtils
                .convertToCamelCase(generatorProperties.getTableName()) + "Mapper_ext.xml";
        TemplateHelp.creatTemplate(modelDate, ftlName, fileName);
    }


    /**
     * 生成JavaBean 实体类
     */
    public void createEntity(Map<String, Object> modelDate) throws Exception {
        String ftlName = "/entity.ftl";
        // 生成文件的路径和名称
        String fileName =
                generatorProperties.getBasedir() + "/src/main/java/" + generatorProperties.getBasePackage().replace(".", "/") + "/" + generatorProperties.getEntityPackage() + "/"
                        + PbUtils.convertToCamelCase(generatorProperties.getTableName()) + ".java";
        TemplateHelp.creatTemplate(modelDate, ftlName, fileName);
    }

    /**
     * 获取所有的表名称及描述
     */
    public List<Map<String, Object>> getTablesList() throws Exception {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("dbName", generatorProperties.getDbName());
            params.put("dbType", generatorProperties.getDbType());//数据库类型
            maps = codeDao.getTablesList(params);
        } catch (Exception e) {
            LOGGER.error("Err=[{}]", e);
        }
        return maps;
    }

    /**
     * 获取表的描述信息
     */
    public String getTableComment() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tableName", generatorProperties.getTableName());// 表名称
        params.put("dbName", generatorProperties.getDbName());// 数据库名称
        params.put("dbType", generatorProperties.getDbType());//数据库类型
        String tableComment = codeDao.getTableComment(params);
        return tableComment;
    }

    /**
     * 获取表得主键
     */
    public List<String> getTableKeys() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tableName", generatorProperties.getTableName());// 表名称
        params.put("dbName", generatorProperties.getDbName());// 数据库名称
        params.put("dbType", generatorProperties.getDbType());//数据库类型
        List<String> tableKeys = codeDao.getTableKeys(params);
        return tableKeys;

    }

    /**
     * 获取数据表的列信息
     */
    protected List<Map> getListMap() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tableName", generatorProperties.getTableName());// 表名称
        params.put("dbName", generatorProperties.getDbName());// 数据库名称
        params.put("dbType", generatorProperties.getDbType());//数据库类型
        List<Map> maps = codeDao.getListMap(params);
        return maps;
    }

    /**
     * 处理数据表列信息
     */
    public List<Map<String, Object>> getTableComumnModel(List<String> tableKeys) {
        List<Map> list = getListMap();
        List<Map<String, Object>> clList = new ArrayList<Map<String, Object>>();
        if (list != null && list.size() > 0) {
            int pkIndex = 1;
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> oMap = new HashMap<String, Object>();
                // 遍历list
                Map<String, Object> map = list.get(i);
                // 遍历map
                for (String key : map.keySet()) {
                    // 列名称
                    if ("COLUMNNAME".equals(key)) {
                        String columnName = map.get(key).toString();//列名称
                        String reStr = PbUtils.strRelplacetoLowerCase(columnName);// 列名称，首字母小写，去下划线
                        oMap.put("columnNameL", columnName);// 列名称
                        oMap.put("columnName", reStr);// 列名称，首字母小写，去下划线
                        // 自动判断大小写
                        if ("_".equals(map.get(key).toString().substring(1, 2))) {
                            oMap.put("UpUmnName", reStr);// 列名称，首字母小写，去下划线
                        } else {
                            oMap.put("UpUmnName", PbUtils.fristStrToUpperCase(reStr));// 列名称，首字母大写，去下划线
                        }
                        //主键标识  用于model
                        String cloumsTop = null;
                        //主键标识  用于xml
                        String pkFlag = "N";
                        //判断是否为主键
                        if (tableKeys.size() > 0 && (tableKeys.contains(columnName) || tableKeys.contains(columnName.toLowerCase()))) {
                            cloumsTop = "@TablePk(index = " + pkIndex + ")";
                            pkIndex++;
                            pkFlag = "Y";
                        }
                        oMap.put("cloumsTop", cloumsTop);
                        oMap.put("pkFlag", pkFlag);
                    }
                    // 注释
                    if (map.get("COLUMNCOMMENT") == null) {
                        oMap.put("columnComment", " ");
                    }
                    if ("COLUMNCOMMENT".equals(key)) {
                        oMap.put("columnComment", map.get(key) == null ? " " : map.get(key));// 注释
                    }
                    // 列类型
                    if ("COLUMNTYPE".equals(key)) {
                        String columnType = map.get("COLUMNTYPE").toString();// 列类型
                        String dataScale = "0";
                        if (columnType.contains(",")) {
                            String dataScaleTemp = columnType.split(",")[1];
                            dataScale = dataScaleTemp.substring(0, dataScaleTemp.length() - 1);
                        }
                        if (columnType.equals("NUMBER") && map.get("DATA_SCALE") != null) {
                            dataScale = map.get("DATA_SCALE").toString();
                        }
                        oMap.put("dbType", columnType);
                        oMap.put("javaType", PbUtils.convertJavaType(columnType, dataScale));
                        oMap.put("jdbcType", PbUtils.convertJdbcType(columnType));
                    }
                    if (oMap.get("columnComment") != null && "" != oMap.get("columnComment")) {
                        validationMap.put((String) oMap.get("columnName"), oMap.get("columnComment"));
                    }

                }
                if (!skipAttributesList.contains(oMap.get("columnName").toString())) {
                    entityList.add(oMap);
                }
                clList.add(oMap);// 添加到集合
            }
        }
        return clList;
    }

    /**
     * 组装模板文件中所需的变量信息
     */
    public Map<String, Object> getTemplateData(List<Map<String, Object>> clList) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (clList != null && clList.size() > 0) {
            String className = PbUtils.convertToCamelCase(generatorProperties.getTableName());
            data.put("className", className);// 类名称
            data.put("objectName", PbUtils.fristStrToLowerCase(className));// 类名首字母小写
            data.put("parentClassPackage", generatorProperties.getParentClassPackage()); //父类的包名
            data.put("mouldName", generatorProperties.getBasePackage());// 基本包名称
            data.put("entityPackage", generatorProperties.getEntityPackage());//entity包名称
            data.put("functionComment", generatorProperties.getEntityDescription() == null ? " " : generatorProperties.getEntityDescription());// 功能说明
            data.put("tableName", generatorProperties.getTableName().toUpperCase());// 表名称
            //根据数据库表反向生成的所有字段集合
            data.put("cloums", clList);// 属性
            //根据数据库表反向生成的所有字段集合中去掉skipAttributes
            data.put("entityList", entityList);// 属性
            data.put("author", generatorProperties.getAuthor());// 作者
            data.put("date", PbUtils.getCurrentDateTime());// 日期
            data.put("entityParentClass", generatorProperties.getEntityParentClass());// entity父类
            if (generatorProperties.getTablePkSize() > 0) {
                data.put("tablePkSize", "Y");//存在主键
            } else {
                data.put("tablePkSize", "N");//不存在主键
            }
        }
        return data;
    }
}

