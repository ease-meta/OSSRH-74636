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
package io.github.meta.ease.generator.mybatis.config.builder;

import io.github.meta.ease.generator.mybatis.ITemplate;
import io.github.meta.ease.generator.mybatis.config.ConstVal;
import io.github.meta.ease.generator.mybatis.config.StrategyConfig;
import io.github.meta.ease.generator.mybatis.config.po.TableInfo;
import io.github.meta.ease.generator.mybatis.function.ConverterFileName;
import io.github.meta.ease.generator.mybatis.util.ClassUtils;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Service属性配置
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
public class Service implements ITemplate {

    private Service() {
    }

    /**
     * 自定义继承的Service类全称，带包名
     */
    private String superServiceClass = ConstVal.SUPER_SERVICE_CLASS;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    private String superServiceImplClass = ConstVal.SUPER_SERVICE_IMPL_CLASS;

    @Nonnull
    public String getSuperServiceClass() {
        return superServiceClass;
    }

    @Nonnull
    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    /**
     * 转换输出Service文件名称
     *
     * @since 3.5.0
     */
    private ConverterFileName converterServiceFileName = (entityName -> "I" + entityName + ConstVal.SERVICE);

    /**
     * 转换输出ServiceImpl文件名称
     *
     * @since 3.5.0
     */
    private ConverterFileName converterServiceImplFileName = (entityName -> entityName + ConstVal.SERVICE_IMPL);

    @Nonnull
    public ConverterFileName getConverterServiceFileName() {
        return converterServiceFileName;
    }

    @Nonnull
    public ConverterFileName getConverterServiceImplFileName() {
        return converterServiceImplFileName;
    }

    @Override
    @Nonnull
    public Map<String, Object> renderData(@Nonnull TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superServiceClassPackage", this.superServiceClass);
        data.put("superServiceClass", ClassUtils.getSimpleName(this.superServiceClass));
        data.put("superServiceImplClassPackage", this.superServiceImplClass);
        data.put("superServiceImplClass", ClassUtils.getSimpleName(this.superServiceImplClass));
        return data;
    }

    public static class Builder extends BaseBuilder {

        private final Service service = new Service();

        public Builder(@Nonnull StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        /**
         * Service接口父类
         *
         * @param clazz 类
         * @return this
         */
        public Builder superServiceClass(@Nonnull Class<?> clazz) {
            return superServiceClass(clazz.getName());
        }

        /**
         * Service接口父类
         *
         * @param superServiceClass 类名
         * @return this
         */
        public Builder superServiceClass(@Nonnull String superServiceClass) {
            this.service.superServiceClass = superServiceClass;
            return this;
        }

        /**
         * Service实现类父类
         *
         * @param clazz 类
         * @return this
         */
        public Builder superServiceImplClass(@Nonnull Class<?> clazz) {
            return superServiceImplClass(clazz.getName());
        }

        /**
         * Service实现类父类
         *
         * @param superServiceImplClass 类名
         * @return this
         */
        public Builder superServiceImplClass(@Nonnull String superServiceImplClass) {
            this.service.superServiceImplClass = superServiceImplClass;
            return this;
        }

        /**
         * 转换输出service接口文件名称
         *
         * @param converter 　转换处理
         * @return this
         * @since 3.5.0
         */
        public Builder convertServiceFileName(@Nonnull ConverterFileName converter) {
            this.service.converterServiceFileName = converter;
            return this;
        }

        /**
         * 转换输出service实现类文件名称
         *
         * @param converter 　转换处理
         * @return this
         * @since 3.5.0
         */
        public Builder convertServiceImplFileName(@Nonnull ConverterFileName converter) {
            this.service.converterServiceImplFileName = converter;
            return this;
        }

        /**
         * 格式化service接口文件名称
         *
         * @param format 　格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatServiceFileName(@Nonnull String format) {
            return convertServiceFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 格式化service实现类文件名称
         *
         * @param format 　格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatServiceImplFileName(@Nonnull String format) {
            return convertServiceImplFileName((entityName) -> String.format(format, entityName));
        }

        @Nonnull
        public Service get() {
            return this.service;
        }
    }
}
