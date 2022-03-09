package io.github.meta.ease.alibaba.provider.examples;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/3/3 14:48
 */
@Data
public class User {

    private Long id;

    private String name;

    /**
     * SM2签名和验签
     *
     * @param args
     */
    public static void main(String[] args) {
        String content = "我是Hanley.";
        final SM2 sm2 = SmUtil.sm2();
        byte[] sign = sm2.sign(content.getBytes(StandardCharsets.UTF_8));
        // true
        boolean verify = sm2.verify(content.getBytes(StandardCharsets.UTF_8), sign);
        System.out.println(verify);
    }

    /**
     * SM2签名和验签
     *
     * @param args
     */
    public static void main03(String[] args) {
        String content = "我是Hanley.";
        final SM2 sm2 = SmUtil.sm2();
        String sign = sm2.signHex(HexUtil.encodeHexStr(content));

        // true
        boolean verify = sm2.verifyHex(HexUtil.encodeHexStr(content), sign);
        System.out.println(verify);
    }

    /**
     * 使用自定义密钥对加密或解密
     *
     * @param args
     */
    public static void main02(String[] args) {
        String text = "我是一段测试aaaa";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println(encryptStr);
        System.out.println(decryptStr);
    }

    /**
     * 使用随机生成的密钥对加密或解密
     *
     * @param args
     */
    public static void main01(String[] args) {
        String text = "我是一段测试aaaa";

        SM2 sm2 = SmUtil.sm2();
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println(encryptStr);
        System.out.println(decryptStr);
    }
}
