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

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class SM4UtilsTest {

    @Test
    public void testCustomKeySM4ECB() throws Exception {
        String charset = StandardCharsets.UTF_8.name();
        // SM4密钥长度分组长度128bit，因此密匙长度为16
        String myKey = "1234567812345678";
        String data = "SM4UtilsTest";
        byte[] myKeyBytes = myKey.getBytes(charset);
        byte[] encryptedBytes = SM4Utils.encrypt_ECB_Padding(myKeyBytes, data.getBytes(charset));
        String encryptedHexString = ByteUtils.toHexString(encryptedBytes);
        System.out.println("ECB加密后的数据HexString：" + encryptedHexString);
        byte[] decryptedBytes = SM4Utils.decrypt_ECB_Padding(myKeyBytes,
                ByteUtils.fromHexString(encryptedHexString));
        System.out.println("ECB解密后的数据：" + new String(decryptedBytes, charset));
    }

    @Test
    public void testCustomKeySM4CBC() throws Exception {
        String charset = StandardCharsets.UTF_8.name();
        // SM4密钥长度分组长度128bit，因此密匙长度为16
        String myKey = "1234567812345678";
        String myIv = "8765432187654321";
        String data = "SM4UtilsTest";
        byte[] myKeyBytes = myKey.getBytes(charset);
        byte[] myIvBytes = myIv.getBytes(charset);
        byte[] encryptedBytes = SM4Utils.encrypt_CBC_Padding(myKeyBytes, myIvBytes,
                data.getBytes(charset));
        String encryptedHexString = ByteUtils.toHexString(encryptedBytes);
        System.out.println("CBC加密后的数据HexString：" + encryptedHexString);
        byte[] decryptedBytes = SM4Utils.decrypt_CBC_Padding(myKeyBytes, myIvBytes,
                ByteUtils.fromHexString(encryptedHexString));
        System.out.println("CBC解密后的数据：" + new String(decryptedBytes, charset));
    }

}