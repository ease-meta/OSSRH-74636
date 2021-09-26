package com.open.cloud.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.open.cloud.logging.BizLogger;
import com.open.cloud.logging.BizLoggerFactory;

import java.util.Collections;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/25 19:42
 */
public class FastAutoGeneratorMain {

    private static final BizLogger logger = BizLoggerFactory.getLogger(FastAutoGeneratorMain.class);

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mariadb://127.0.0.1:3306/botj?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true", "root", "123456")
                .globalConfig((GlobalConfig.Builder builder) -> {
                    builder.author("leijian")
                            .fileOverride()
                            .outputDir("D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-project\\cloud-boot-mybatis\\src\\test\\java\\")
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent("")
                            .moduleName("")
                            .entity("com.open.cloud.mybatis.entity")
                            .mapper("com.open.cloud.mybatis.mapper")
                            .xml("mapper.xml")
                            .other("_ext")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-project\\cloud-boot-mybatis\\src\\test\\resources\\mapper\\"))
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
                    builder.entityBuilder().enableActiveRecord().enableChainModel().superClass(com.open.cloud.core.po.BasePO.class);
                    builder.mapperBuilder().superClass(BaseDo.class);
                })
                .execute();
    }
}
