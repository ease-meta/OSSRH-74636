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
package io.github.meta.ease.mybatis;

import io.github.meta.ease.domain.BasePO;

import java.util.List;

/**
 * Mbatis持久化业务领域
 *
 * @author leijian
 * @date 2021/9/27 9:19
 */
public class MbatisDaoAction extends BaseDaoAction {

    @Override
    public int insert(BasePO entity) {
        return 0;
    }

    @Override
    public int delete(BasePO entity) {
        return 0;
    }

    @Override
    public int update(BasePO entity, BasePO whereEntity) {
        return 0;
    }

    @Override
    public Long selectCount(BasePO entity) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(BasePO record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(BasePO record) {
        return 0;
    }

    @Override
    public BasePO selectByPrimaryKey(BasePO record, Object... pkValue) {
        return null;
    }

    @Override
    public List selectList(BasePO record, Object... pkValue) {
        return null;
    }
}
