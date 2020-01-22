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
package com.open.cloud.common.utils;

import java.util.List;

/**
 * @author Leijian
 */
public interface BaseMapper<T> {
    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 删除
     *
     * @param entity
     * @return
     */
    int deleteSelective(T entity);

    /**
     * 插入实体类
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 批量插入实体类
     *
     * @param entity
     * @return
     */
    int insertBatch(List<T> entity);

    /**
     * 选择性插入实体类
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(String id);

    /**
     * 查询所有信息
     *
     * @return
     */
    List<T> selectAll();

    /**
     * @param entity
     * @return
     */
    List<T> selectSelective(T entity);

    /**
     * 选择性更新
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据主键更新
     *
     * @param entity
     * @return
     */
    int updateByPrimaryKey(T entity);

}
