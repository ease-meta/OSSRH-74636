/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.boot.generator.mybatis.fill;


import com.boot.generator.mybatis.IFill;
import com.boot.generator.mybatis.annotation.FieldFill;

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
