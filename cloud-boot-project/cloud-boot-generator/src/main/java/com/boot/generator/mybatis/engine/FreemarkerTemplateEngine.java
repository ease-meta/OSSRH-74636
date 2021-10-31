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
package com.boot.generator.mybatis.engine;

import com.boot.generator.mybatis.config.ConstVal;
import com.boot.generator.mybatis.config.builder.ConfigBuilder;
import com.boot.generator.mybatis.util.StringPool;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Freemarker 模板引擎实现文件输出
 *
 * @author nieqiurong
 * @since 2018-01-11
 */
public class FreemarkerTemplateEngine extends AbstractTemplateEngine {
    private Configuration configuration;

    @Override
    public @Nonnull
    FreemarkerTemplateEngine init(@Nonnull ConfigBuilder configBuilder) {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, StringPool.SLASH);
        return this;
    }

    @Override
    public void writer(@Nonnull Map<String, Object> objectMap, @Nonnull String templatePath,
                       @Nonnull File outputFile) throws Exception {
        Template template = configuration.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
        }
    }

    @Override
    public @Nonnull
    String templateFilePath(@Nonnull String filePath) {
        return filePath + ".ftl";
    }
}
