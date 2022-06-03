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
package io.github.meta.ease.core.test;

import org.openjdk.jol.info.ClassLayout;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试String占用的内存空间
 */
public class TestString {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 一个空 String 所占空间为：

        // 对象头（8 字节）+ 引用 (4 字节 ) + char 数组（16 字节）+ 1个 int（4字节）+ 1个long（8字节）= 40 字节
        // String所占空间：char（2字节）个数*2+（8 字节）+ 引用 (4 字节 ) + char 数组（16 字节）+ 1个 int（4字节）+
        // 1个long（8字节）
        System.out.println(8 + 4 + 16 + 4 + 8 + 2 * 1);
        /*
         * String[] strContainer = new String[4000000]; for(int i = 0; i < 4000000; i++){
         * strContainer[i] = UUIDUtils.getUUID(); System.out.println(i); }
         */
        String str = "";
        System.out.println("\"搞java\".length():" + (str.length()));
        System.out.println("\"搞java\".getBytes().length:" + (str.getBytes().length));
        System.out.println("\"搞java\".getBytes(\"GBK\").length:" + (str.getBytes("GBK").length));
        System.out.println("\"搞java\".getBytes(\"UTF-8\").length:"
                + (str.getBytes(StandardCharsets.UTF_8).length));
        /**
         * "搞java".length():5 "搞java".getBytes().length:6 "搞java".getBytes("GBK").length:6
         * "搞java".getBytes("UTF-8").length:7
         */
        System.out.println(ClassLayout.parseInstance(str).toPrintable());
        // 防止程序退出
        /* while (true) {
         }*/
    }

}
