package com.open.cloud.base.java;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.crypto.symmetric.SM4;
import lombok.SneakyThrows;
import org.bouncycastle.crypto.engines.SM4Engine;
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
		System.out.println(SM3.create().digestHex("123","utf-8"));
		SM4 sm4 = new SM4();
		System.out.println(sm4.decryptStr(sm4.encrypt("123","utf-8")));
	}
}
