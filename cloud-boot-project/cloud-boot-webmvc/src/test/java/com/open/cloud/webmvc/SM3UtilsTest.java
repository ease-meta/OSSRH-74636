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
package com.open.cloud.webmvc;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SM3UtilsTest {

    public static final String KEY = "1234567812345678";

    public static final String CHARSET_UTF8 = StandardCharsets.UTF_8.name();

    @Test
    public void testGenerateHash() throws Exception {
        String data = "测试SM3杂凑算法";
        // 生成消息摘要  消息摘要=SM3（原文）
        byte[] signByte = SM3Utils.hash(data.getBytes(CHARSET_UTF8));
        String signBase64String = Base64.toBase64String(signByte);
        System.out.println("\n消息摘要 Base64 String：" + signBase64String);

        // 生成数字签名  数字签名 = SM3（原文+密钥）
        String digitalSign = Base64.toBase64String(SM3Utils.hash((data + KEY)
                .getBytes(CHARSET_UTF8)));
        System.out.println("\n数字签名 Base64 String：" + digitalSign);
    }

    @Test
    public void testHashAndVerify() {
        try {
            String data = "SM3UtilsTest";
            byte[] hash = SM3Utils.hash(data.getBytes(CHARSET_UTF8));
            System.out.println("hash:" + Arrays.toString(hash));
            String hashBase64String = Base64.toBase64String(hash);
            System.out.println("SM3 Base64 String:\n" + hashBase64String);
            System.out.println("hash:\n" + Arrays.toString(Base64.decode(hashBase64String)));
            boolean flag = SM3Utils.verify(data.getBytes(CHARSET_UTF8), hash);
            System.out.println("验证结果：" + flag);
            System.out.println("======");
            Assert.assertEquals(true, flag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}