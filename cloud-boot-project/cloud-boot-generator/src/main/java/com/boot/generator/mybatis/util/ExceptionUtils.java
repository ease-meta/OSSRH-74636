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
package com.boot.generator.mybatis.util;

import com.boot.generator.mybatis.exceptions.MybatisPlusException;

/**
 * �쳣����������
 *
 * @author HCL
 * @since 2018-07-24
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * ����һ���µ��쳣��ͳһ����������ͳһ����
     *
     * @param msg ��Ϣ
     * @param t   �쳣��Ϣ
     * @return �����쳣
     */
    public static MybatisPlusException mpe(String msg, Throwable t, Object... params) {
        return new MybatisPlusException(String.format(msg, params), t);
    }

    /**
     * ���صķ���
     *
     * @param msg ��Ϣ
     * @return �����쳣
     */
    public static MybatisPlusException mpe(String msg, Object... params) {
        return new MybatisPlusException(String.format(msg, params));
    }

    /**
     * ���صķ���
     *
     * @param t �쳣
     * @return �����쳣
     */
    public static MybatisPlusException mpe(Throwable t) {
        return new MybatisPlusException(t);
    }

    public static void throwMpe(boolean condition, String msg, Object... params) {
        if (condition) {
            throw mpe(msg, params);
        }
    }
}
