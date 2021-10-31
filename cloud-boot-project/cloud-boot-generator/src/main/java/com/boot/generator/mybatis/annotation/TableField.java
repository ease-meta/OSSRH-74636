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

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.lang.annotation.*;

/**
 * ���ֶα�ʶ
 *
 * @author hubin sjy tantan
 * @since 2016-09-09
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface TableField {

    /**
     * ���ݿ��ֶ�ֵ
     * <p>
     * ����Ҫ���ø�ֵ�����:
     * <li> �� {@link com.baomidou.mybatisplus.core.MybatisConfiguration#mapUnderscoreToCamelCase} Ϊ true ʱ,
     * (mp��Ĭ����true,mybatisĬ����false), ���ݿ��ֶ�ֵ.replace("_","").toUpperCase() == ʵ��������.toUpperCase() </li>
     * <li> �� {@link com.baomidou.mybatisplus.core.MybatisConfiguration#mapUnderscoreToCamelCase} Ϊ false ʱ,
     * ���ݿ��ֶ�ֵ.toUpperCase() == ʵ��������.toUpperCase() </li>
     */
    String value() default "";

    /**
     * �Ƿ�Ϊ���ݿ���ֶ�
     * <p>
     * Ĭ�� true ���ڣ�false ������
     */
    boolean exist() default true;

    /**
     * �ֶ� where ʵ���ѯ�Ƚ�����
     * <p>
     * Ĭ�� {@link SqlCondition#EQUAL}
     */
    String condition() default "";

    /**
     * �ֶ� update set ����ע��, ��ע������ el ע��ʹ��
     * <p>
     * ��1��@TableField(.. , update="%s+1") ���� %s �����Ϊ�ֶ�
     * ��� SQL Ϊ��update �� set �ֶ�=�ֶ�+1 where ...
     * <p>
     * ��2��@TableField(.. , update="now()") ʹ�����ݿ�ʱ��
     * ��� SQL Ϊ��update �� set �ֶ�=now() where ...
     */
    String update() default "";

    /**
     * �ֶ���֤����֮ insert: ��insert����ʱ�����ֶ�ƴ��insert���ʱ�Ĳ���
     * <p>
     * IGNORED: ֱ��ƴ�� insert into table_a(column) values (#{columnProperty});
     * NOT_NULL: insert into table_a(<if test="columnProperty != null">column</if>) values (<if test="columnProperty != null">#{columnProperty}</if>)
     * NOT_EMPTY: insert into table_a(<if test="columnProperty != null and columnProperty!=''">column</if>) values (<if test="columnProperty != null and columnProperty!=''">#{columnProperty}</if>)
     * NOT_EMPTY �����Ե��Ƿ� CharSequence ���͵��ֶ���Ч������ NOT_NULL
     *
     * @since 3.1.2
     */
    // FieldStrategy insertStrategy() default FieldStrategy.DEFAULT;

    /**
     * �ֶ���֤����֮ update: �����²���ʱ�����ֶ�ƴ��set���ʱ�Ĳ���
     * <p>
     * IGNORED: ֱ��ƴ�� update table_a set column=#{columnProperty}, ����Ϊnull/��string���ᱻset��ȥ
     * NOT_NULL: update table_a set <if test="columnProperty != null">column=#{columnProperty}</if>
     * NOT_EMPTY: update table_a set <if test="columnProperty != null and columnProperty!=''">column=#{columnProperty}</if>
     * NOT_EMPTY �����Ե��Ƿ� CharSequence ���͵��ֶ���Ч������ NOT_NULL
     *
     * @since 3.1.2
     */
    // FieldStrategy updateStrategy() default FieldStrategy.DEFAULT;

    /**
     * �ֶ���֤����֮ where: ��ʾ���ֶ���ƴ��where����ʱ�Ĳ���
     * <p>
     * IGNORED: ֱ��ƴ�� column=#{columnProperty}
     * NOT_NULL: <if test="columnProperty != null">column=#{columnProperty}</if>
     * NOT_EMPTY: <if test="columnProperty != null and columnProperty!=''">column=#{columnProperty}</if>
     * NOT_EMPTY �����Ե��Ƿ� CharSequence ���͵��ֶ���Ч������ NOT_NULL
     *
     * @since 3.1.2
     */
    // FieldStrategy whereStrategy() default FieldStrategy.DEFAULT;

    /**
     * �ֶ��Զ�������
     * <p>
     * �ڶ�Ӧģʽ�½������ insertStrategy �� updateStrategy ������,���ڶ��Ը��ֶα���ֵ
     */
    FieldFill fill() default FieldFill.DEFAULT;

    /**
     * �Ƿ���� select ��ѯ
     * <p>
     * ���ֶο�����Ϊ false ������ select ��ѯ��Χ
     */
    boolean select() default true;

    /**
     * �Ƿ񱣳�ʹ��ȫ�ֵ� columnFormat ��ֵ
     * <p>
     * ֻ��Ч�� ��������ȫ�ֵ� columnFormat Ҳ���������� {@link #value()} ��ֵ
     * ����� false , ȫ�ֵ� columnFormat ����Ч
     *
     * @since 3.1.1
     */
    boolean keepGlobalFormat() default false;

    /**
     * {@link ResultMapping#property} and {@link ParameterMapping#property}
     *
     * @since 3.4.4
     */
    String property() default "";

    /**
     * JDBC���� (��Ĭ��ֵ������ᰴ�ո�ֵ��Ч),
     * ֻ��Ч�� mp �Զ�ע��� method,
     * ������� {@link TableName#autoResultMap()} һ��ʹ��
     * <p>
     * {@link ResultMapping#jdbcType} and {@link ParameterMapping#jdbcType}
     *
     * @since 3.1.2
     */
    JdbcType jdbcType() default JdbcType.UNDEFINED;

    /**
     * ���ʹ����� (��Ĭ��ֵ������ᰴ�ո�ֵ��Ч),
     * ֻ��Ч�� mp �Զ�ע��� method,
     * ������� {@link TableName#autoResultMap()} һ��ʹ��
     * <p>
     * {@link ResultMapping#typeHandler} and {@link ParameterMapping#typeHandler}
     *
     * @since 3.1.2
     */
    Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;

    /**
     * ֻ��ʹ���� {@link #typeHandler()} ʱ�ж��Ƿ���׷�� javaType
     * <p>
     * һ������²��Ƽ�ʹ��
     * {@link ParameterMapping#javaType}
     *
     * @since 3.4.0 @2020-07-23
     */
    boolean javaType() default false;

    /**
     * ָ��С���������λ��,
     * ֻ��Ч�� mp �Զ�ע��� method,
     * ������� {@link TableName#autoResultMap()} һ��ʹ��
     * <p>
     * {@link ParameterMapping#numericScale}
     *
     * @since 3.1.2
     */
    String numericScale() default "";
}
