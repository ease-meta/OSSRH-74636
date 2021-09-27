package com.open.cloud.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.open.cloud.core.po.BasePO;
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

    private static final BizLogger logger = BizLoggerFactory.getLogger(FastAutoGeneratorOracleMain.class);

    public static void main(String[] args) {
        //TODO 1.生成扩展类；2.entity父类;3.mapper父类；4.xml格式重新定义；5.
        FastAutoGenerator.create("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "ENS_CIF", "ENS_CIF")
                .globalConfig((GlobalConfig.Builder builder) -> {
                    builder.author("leijian")
                            .fileOverride()
                            .outputDir(System.getProperty("user.dir")+ File.separator+"cloud-boot-project\\cloud-boot-mybatis\\src\\test\\java\\")
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
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+ File.separator+"cloud-boot-project\\cloud-boot-mybatis\\src\\test\\resources\\mapper\\"))
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
                    builder.entityBuilder().enableChainModel().superClass(BasePO.class);
                    builder.mapperBuilder().enableBaseResultMap().enableBaseColumnList().superClass(BaseDaoAction.class);
                })
                .execute();
    }
}
