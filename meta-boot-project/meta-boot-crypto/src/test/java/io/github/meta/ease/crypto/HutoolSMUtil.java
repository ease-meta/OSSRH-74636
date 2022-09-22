package io.github.meta.ease.crypto;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.DSAPublicKeySpec;

public class HutoolSMUtil {
    public static final String KEY_MAC = "HmacMD5";

    @BeforeEach
    public void SM() {
        BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
        BouncyCastlePQCProvider bouncyCastlePQCProvider = new BouncyCastlePQCProvider();

        Security.addProvider(bouncyCastleProvider);
        Security.addProvider(bouncyCastlePQCProvider);

        Provider[] providers = Security.getProviders();
        System.out.println("-----Provider 列表如下：-----");
        for (Provider provider : providers) {
            System.out.println(provider.getName());
        }

        System.out.println("-----支持的算法如下：-----");

        for (Provider provider : providers) {
            for (Provider.Service service : provider.getServices()) {
                System.out.println(service.getType() + ":" + service.getAlgorithm() + ":" + service.getProvider().getName());
            }
        }
    }

    private static final String DSA = "DSA";
    private static final String keyspecFile = "keyspec.text";

    @Test
    public void genenatePublicKey() throws Exception {
        writeKeySpec();
        readKeySpec();
    }

    private void writeKeySpec() throws Exception {
        File file=new File(keyspecFile);
        file.deleteOnExit();
        file.createNewFile();

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(DSA);
        keyGen.initialize(512, new SecureRandom());
        KeyPair keyPair=keyGen.generateKeyPair();

        KeyFactory factory=KeyFactory.getInstance(DSA);
        DSAPublicKeySpec keySpec=factory.getKeySpec(keyPair.getPublic(), DSAPublicKeySpec.class);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(keySpec.getY());
        oos.writeObject(keySpec.getP());
        oos.writeObject(keySpec.getQ());
        oos.writeObject(keySpec.getG());
        oos.flush();
        oos.close();
    }

    private void readKeySpec() throws Exception {
        KeyFactory factory=KeyFactory.getInstance(DSA);
        FileInputStream fis = new FileInputStream(keyspecFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        DSAPublicKeySpec keySpec = new DSAPublicKeySpec(
                (BigInteger) ois.readObject(),
                (BigInteger) ois.readObject(),
                (BigInteger) ois.readObject(),
                (BigInteger) ois.readObject());
        ois.close();
        PublicKey puk=factory.generatePublic(keySpec);
        System.out.println("Got private key:\n"+puk);
    }
    @Test
    public void testSign() throws Exception{
        KeyPairGenerator keyPairGen=KeyPairGenerator.getInstance("SM2");
        keyPairGen.initialize(1024);
        KeyPair keyPair= keyPairGen.generateKeyPair();
        PublicKey puk=keyPair.getPublic();
        PrivateKey pik=keyPair.getPrivate();

        String data="Hello, Java.";
        Signature signature=Signature.getInstance("SHA1withDSA");

        // private key sign
        signature.initSign(pik);
        signature.update(data.getBytes());
        byte[] signinfo=signature.sign();

        // public key resolve sign
        signature.initVerify(puk);
        boolean ok=signature.verify(signinfo);
        System.out.println(ok);

        signature.update(data.getBytes());
        ok=signature.verify(signinfo);
        System.out.println(ok);

    }
    @Test
    public void sm3MsgDigestTest() throws NoSuchAlgorithmException, InvalidKeyException {

        String text = "wangjing";
        //Hmac
        //初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HMACSM3");
        //产生密钥
        SecretKey key = keyGenerator.generateKey();
        //还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key.getEncoded(), "HMACSM3");
        //实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化Mac
        mac.init(secretKey);
        byte[] bytes = mac.doFinal(text.getBytes());
        String digestMacHex = byte2hex(bytes);
        System.out.println("HMACSM3加密后：" + digestMacHex);
        //SM3 哈希算法
        MessageDigest sm3MsgDigest = MessageDigest.getInstance("SM3");
        String digestHex = byte2hex(sm3MsgDigest.digest(text.getBytes()));
        System.out.println("SM3加密后：" + digestHex);

        Signature sha256WithSM2 = Signature.getInstance("sha256WithSM2");
    }

    // 转字符串
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    @Test
    public void sm2Test() throws NoSuchAlgorithmException {
        String KEY_MAC = "HmacMD5";

        String text = "wangjing";

        //SmUtil.sm2(Base64.decode("123456"),Base64.decode("123456"));

        //使用随机生成的密钥对加密或解密
        System.out.println("使用随机生成的密钥对加密或解密====开始");
        SM2 sm2 = SmUtil.sm2();
        //String privateKeyBase64 = sm2.getPrivateKeyBase64();
       // String publicKeyBase64 = sm2.getPublicKeyBase64();
       // System.out.println(publicKeyBase64);
       // System.out.println(privateKeyBase64);

        String s = sm2.encryptBase64("123456", KeyType.PublicKey);
        String s1 = sm2.decryptStr(s, KeyType.PrivateKey);
        System.out.println(s);
        String digestHex = SmUtil.sm3(text);
        String signHex = sm2.signHex(digestHex);
        String encodeBase64 = Base64.encode(signHex);
        boolean b = sm2.verifyHex(digestHex, Base64.decodeStr(encodeBase64));
        // 公钥加密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        System.out.println("公钥加密：" + encryptStr);
        //私钥解密
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println("私钥解密：" + decryptStr);
        System.out.println("使用随机生成的密钥对加密或解密====结束");


        //使用自定义密钥对加密或解密
        System.out.println("使用自定义密钥对加密或解密====开始");

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        SM2 sm22 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密
        String encryptStr2 = sm22.encryptBcd(text, KeyType.PublicKey);
        System.out.println("公钥加密：" + encryptStr2);
        //私钥解密
        String decryptStr2 = StrUtil.utf8Str(sm22.decryptFromBcd(encryptStr2, KeyType.PrivateKey));
        System.out.println("私钥解密：" + decryptStr2);
        System.out.println("使用自定义密钥对加密或解密====结束");

    }

    @Test
    public void sm3Test() {
        String text = "wangjing";
        String digestHex = SmUtil.sm3("aaaaa");
        System.out.println("加密后：" + digestHex);
    }

    @Test
    public void sm4Test() {
        String text = "wangjing";
        SymmetricCrypto sm4 = SmUtil.sm4();

        String encryptHex = sm4.encryptHex(text);
        System.out.println("加密后：" + encryptHex);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("解密后：" + decryptStr);
    }
}