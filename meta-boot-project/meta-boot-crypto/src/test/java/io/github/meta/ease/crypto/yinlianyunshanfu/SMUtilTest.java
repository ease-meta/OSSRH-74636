package io.github.meta.ease.crypto.yinlianyunshanfu;

import cn.hutool.crypto.KeyUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

/**
 * BASE64格式SM2公钥：MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAErcnRUoIqvfjJKMgZjiDQ8dHdoHh/NVR2RmIacinRL90VNidbFFSMz1SV2N/S+N1AdQ0ai8zm8xMYKQwZGc205A==
 * BASE64格式SM2私钥：MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg7e5O/6dcwRnkbSPE62wSHLa74NKr/PDETu8V5YpitkKgCgYIKoEcz1UBgi2hRANCAAStydFSgiq9+MkoyBmOINDx0d2geH81VHZGYhpyKdEv3RU2J1sUVIzPVJXY39L43UB1DRqLzObzExgpDBkZzbTk
 * 16进制的SM2公钥：04adc9d152822abdf8c928c8198e20d0f1d1dda0787f35547646621a7229d12fdd1536275b14548ccf5495d8dfd2f8dd40750d1a8bcce6f31318290c1919cdb4e4
 * 16进制的SM2私钥：edee4effa75cc119e46d23c4eb6c121cb6bbe0d2abfcf0c44eef15e58a62b642
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
class SMUtilTest {
    String hexPublicKey = "04adc9d152822abdf8c928c8198e20d0f1d1dda0787f35547646621a7229d12fdd1536275b14548ccf5495d8dfd2f8dd40750d1a8bcce6f31318290c1919cdb4e4";

    String hexPrivateKey = "edee4effa75cc119e46d23c4eb6c121cb6bbe0d2abfcf0c44eef15e58a62b642";

    String plainText = "123456";

    String sm4Key = "1234567890123456";

    @Test
    void sm2Encrypt() {
        SMUtil smUtil = new SMUtil();
        byte[] bytes = smUtil.sm2Encrypt(hexPublicKey, plainText);
        String s = smUtil.sm2Decrypt(hexPrivateKey, bytes);
        Assertions.assertEquals(plainText, s);
    }

    @Test
    void sm2Decrypt() {
        SMUtil smUtil = new SMUtil();
        byte[] bytes = smUtil.sm2Encrypt(hexPublicKey, plainText);
        String s = smUtil.sm2Decrypt(hexPrivateKey, bytes);
        Assertions.assertEquals(plainText, s);
    }

    @Test
    void sm2Sign() {
        SMUtil smUtil = new SMUtil();
        byte[] bytes = smUtil.sm2Sign(hexPrivateKey, plainText);
        boolean b = smUtil.sm2SignValidate(hexPublicKey, bytes, plainText);
        Assertions.assertTrue(b);
    }

    @Test
    void sm2SignValidate() {
        SMUtil smUtil = new SMUtil();
        byte[] bytes = smUtil.sm2Sign(hexPrivateKey, plainText);
        boolean b = smUtil.sm2SignValidate(hexPublicKey, bytes, plainText);
        Assertions.assertTrue(b);
    }

    @Test
    void sm4Encrypt() {
        SecretKey sm4 = KeyUtil.generateKey("SM4");
        SMUtil smUtil = new SMUtil();

        byte[] bytes = smUtil.sm4Encrypt(sm4Key, plainText);
        String s = smUtil.sm4Decrypt(sm4Key, bytes);
        Assertions.assertEquals(plainText, s);

    }

    @Test
    void sm4Decrypt() {
        SMUtil smUtil = new SMUtil();
        byte[] bytes = smUtil.sm4Encrypt(sm4Key, plainText);
        String s = smUtil.sm4Decrypt(sm4Key, bytes);
        Assertions.assertEquals(plainText, s);
    }
}