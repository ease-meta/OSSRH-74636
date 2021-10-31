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
package io.github.meta.ease.generator.mybatis.fill;

import io.github.meta.ease.generator.mybatis.IFill;
import io.github.meta.ease.generator.mybatis.annotation.FieldFill;

import javax.annotation.Nonnull;

/**
 * 属性填充
 *
 * @author nieqiurong
 * @since 3.5.0 2020/11/30.
 */
public class Property implements IFill {

    private final String propertyName;

    private final FieldFill fieldFill;

    public Property(@Nonnull String propertyName, @Nonnull FieldFill fieldFill) {
        this.propertyName = propertyName;
        this.fieldFill = fieldFill;
    }

    public Property(@Nonnull String propertyName) {
        this.propertyName = propertyName;
        this.fieldFill = FieldFill.DEFAULT;
    }

    @Override
    public @Nonnull
    String getName() {
        return this.propertyName;
    }

    @Override
    public @Nonnull
    FieldFill getFieldFill() {
        return this.fieldFill;
    }
}
