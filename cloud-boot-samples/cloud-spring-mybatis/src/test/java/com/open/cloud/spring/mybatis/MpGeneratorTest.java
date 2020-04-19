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
/*
package com.open.cloud.spring.mybatis;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

*/
/**
 * <p>
 * 通过junit test 生成代码
 * 演示：自定义代码模板
 * 默认不会覆盖已有文件，如果需要覆盖，配置GlobalConfig.setFileOverride(true)
 * </p>
 *
 * @author yuxiaobin
 * @date 2018/11/29
 *//*

public class MpGeneratorTest {

	@Test
	public void generateCode() {
		String str = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java";
		generate(str,"mysql", "stria_flow");
	}

	private void generate(String str,String moduleName, String... tableNamesInclude) {
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(str);
		gc.setAuthor("Leijian");
		gc.setOpen(false);
		//默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
		gc.setFileOverride(true);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setActiveRecord(true);
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://10.7.25.205:3306/workflow?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		if (Objects.nonNull(moduleName)) {
			pc.setModuleName(moduleName);
		}
		pc.setParent("com.open.cloud.spring.mybatis");
		mpg.setPackageInfo(pc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		//strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
		//strategy.setEntityLombokModel(true);
		//strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
		strategy.setInclude(tableNamesInclude);
		//strategy.setSuperEntityColumns("id");
		//strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		strategy.setEntityTableFieldAnnotationEnable(false);
		strategy.setEntityBuilderModel(true);
		strategy.setRestControllerStyle(true);
		strategy.setEntityBooleanColumnRemoveIsPrefix(true);
		//strategy.entityTableFieldAnnotationEnable(true);
		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		//mpg.setTemplateEngine(new FreemarkerTemplateEngine());

		//configCustomizedCodeTemplate(mpg);
		//configInjection(mpg);

		mpg.execute();
	}

	*/
/**
 * 自定义模板
 *
 * @param mpg
 *//*

	private void configCustomizedCodeTemplate(AutoGenerator mpg) {
		//配置 自定义模板
		TemplateConfig templateConfig = new TemplateConfig()
				.setEntity("templates/MyEntityTemplate.java")//指定Entity生成使用自定义模板
				.setXml(null);//不生成xml
		mpg.setTemplate(templateConfig);
	}

	*/
/**
 * 配置自定义参数/属性
 *
 * @param mpg
 *//*

	private void configInjection(AutoGenerator mpg) {
		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
                */
/*
                自定义属性注入: 模板配置：abc=${cfg.abc}
                 *//*

			}
		};
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/MyEntityTemplate.java.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 指定模板生，自定义生成文件到哪个地方
//                return "D:/abc";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
	}
}
*/
