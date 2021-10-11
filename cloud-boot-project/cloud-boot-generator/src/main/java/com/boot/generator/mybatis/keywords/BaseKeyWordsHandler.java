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
package com.boot.generator.mybatis.keywords;

import com.boot.generator.mybatis.config.IKeyWordsHandler;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * 基类关键字处理
 *
 * @author nieqiurong 2020/5/8.
 * @since 3.3.2
 */
public abstract class BaseKeyWordsHandler implements IKeyWordsHandler {

    public Set<String> keyWords;

    public BaseKeyWordsHandler(@Nonnull List<String> keyWords) {
        this.keyWords = new HashSet<>(keyWords);
    }

    public BaseKeyWordsHandler(@Nonnull Set<String> keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public @Nonnull
    Collection<String> getKeyWords() {
        return keyWords;
    }

    @Override
    public boolean isKeyWords(@Nonnull String columnName) {
        return getKeyWords().contains(columnName.toUpperCase(Locale.ENGLISH));
    }
}
