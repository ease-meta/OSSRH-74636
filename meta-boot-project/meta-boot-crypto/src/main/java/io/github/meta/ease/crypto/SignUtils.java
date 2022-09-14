package io.github.meta.ease.crypto;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SignUtils {
    
    public static String sign(String text, String privateKey) {
        byte[] textBytes, userBytes;
        BigInteger d = new BigInteger(privateKey, 16);
        BCECPrivateKey bcecPrivateKey = SmUtils.getPrivatekeyFromD(d);
        try {
            textBytes = text.getBytes("UTF-8");
            userBytes = CommonContent.sign_UserId.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            textBytes = text.getBytes();
            userBytes = CommonContent.sign_UserId.getBytes();
        }
        byte[] sig = SmUtils.signSm3WithSm2Asn1Rs(textBytes, userBytes, (PrivateKey) bcecPrivateKey);
        String sing = Hex.toHexString(sig);
        return sing;
    }

    public static boolean design(String text, String sign, String publicKey) {
        byte[] textBytes, userBytes;
        BCECPublicKey bCECPublicKey = SmUtils.getPublickeyFromq(publicKey);
        try {
            textBytes = text.getBytes("UTF-8");
            userBytes = CommonContent.sign_UserId.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            textBytes = text.getBytes();
            userBytes = CommonContent.sign_UserId.getBytes();
        }
        return SmUtils.verifySm3WithSm2Asn1Rs(textBytes, userBytes, Hex.decode(sign), (PublicKey) bCECPublicKey);
    }
}
