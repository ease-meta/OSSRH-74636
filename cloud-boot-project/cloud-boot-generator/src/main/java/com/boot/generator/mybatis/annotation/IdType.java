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
package com.boot.generator.mybatis.annotation;

import lombok.Getter;

/**
 * ����ID����ö����
 *
 * @author hubin
 * @since 2015-11-10
 */
@Getter
public enum IdType {
    /**
     * ���ݿ�ID����
     * <p>��������ȷ�����ݿ������� ID���� ������Ч</p>
     */
    AUTO(0),
    /**
     * ������Ϊδ������������(ע������ڸ���ȫ��,ȫ����Լ���� INPUT)
     */
    NONE(1),
    /**
     * �û�����ID
     * <p>�����Ϳ���ͨ���Լ�ע���Զ�������������</p>
     */
    INPUT(2),

    /* ����3�����͡�ֻ�е��������ID Ϊ�գ����Զ���䡣 */
    /**
     * ����ID (��������Ϊnumber��string��,
     * Ĭ��ʵ���� {@link com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator}(ѩ���㷨)
     *
     * @since 3.3.0
     */
    ASSIGN_ID(3),
    /**
     * ����UUID (��������Ϊ string)
     * Ĭ��ʵ���� {@link com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator}(UUID.replace("-",""))
     */
    ASSIGN_UUID(4);

    private final int key;

    IdType(int key) {
        this.key = key;
    }
}
