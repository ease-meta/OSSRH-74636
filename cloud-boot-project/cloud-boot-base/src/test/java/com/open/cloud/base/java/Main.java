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
package com.open.cloud.base.java;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.crypto.symmetric.SM4;
import lombok.SneakyThrows;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * @author Leijian
 * @date 2020/8/12
 */
public class Main {

	@SneakyThrows
	@Test
	public void testSM3() throws NoSuchProviderException, NoSuchAlgorithmException {
		Security.addProvider(new BouncyCastleProvider());
		byte[] message = "123".getBytes("UTF-8");
		System.out.println(new String(message));
		MessageDigest digest = MessageDigest.getInstance("SM3");
		byte[] result = digest.digest(message);
		//System.out.println(new String(result));
		System.out.println(HexUtil.encodeHexStr(result));
		System.out.println(SM3.create().digestHex("123", "utf-8"));
		SM4 sm4 = new SM4();
		System.out.println(sm4.decryptStr(sm4.encrypt("123", "utf-8")));
	}
}
