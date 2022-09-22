package io.github.meta.ease.crypto.yinlianyunshanfu;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

/**

 *
 * @author Leijian
 */
public class KeyGenUtil {
    /**
     * 生成SM2公私钥对
     *
     * @return
     */
    public static KeyPair generateSm2KeyPair() {
        try {
            final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
            //获取一个椭圆曲线类型的密钥对生成器
            final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            //使用sm2的算法区域初始化密钥生成器
            kpg.initialize(sm2Spec, random);
            KeyPair keyPair = kpg.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new RuntimeException("生成密钥对失败");
        }
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateSm2KeyPair();
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("BASE64格式SM2公钥：" + Base64.encodeBase64String(publicKey.getEncoded()));
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("BASE64格式SM2私钥：" + Base64.encodeBase64String(privateKey.getEncoded()));
        if (publicKey instanceof BCECPublicKey) {
            String publicKeyHex = Hex.toHexString(((BCECPublicKey) publicKey).getQ().getEncoded(false));
            System.out.println("16进制的SM2公钥：" + publicKeyHex);
        }
        if (privateKey instanceof BCECPrivateKey) {
            String privateKeyHex = ((BCECPrivateKey) privateKey).getD().toString(16);
            System.out.println("16进制的SM2私钥：" + privateKeyHex);
        }
    }
}
