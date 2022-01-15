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
 * 持久化业务领域
 *
 * @author leijian
 * @date 2021/9/27 9:19
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
public interface BaseMapper<T> {

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    <T extends BasePO> int insert(T entity);

    /**
     * 根据 entity 条件，删除记录
     */
    <T extends BasePO> int delete(T entity);

    /**
     * 根据 entity 条件，更新记录
     */
    <T extends BasePO> int update(T entity, T whereEntity);

    /**
     * 根据 Wrapper 条件，查询总记录数
     */
    <T extends BasePO> Long selectCount(T entity);

    <T extends BasePO> int updateByPrimaryKey(T record);

    /**
     * 根据主键删除记录
     *
     * @param record dbmodel bean，必须定义了@TablePk主键
     * @param <T>    记录mapper泛型
     * @return
     */
    <T extends BasePO> int deleteByPrimaryKey(T record);

    <T extends BasePO> T selectByPrimaryKey(T record, Object... pkValue);

    <T extends BasePO> List<T> selectList(T record, Object... pkValue);
}
