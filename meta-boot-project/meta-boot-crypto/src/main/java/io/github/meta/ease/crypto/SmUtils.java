package io.github.meta.ease.crypto;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

public class SmUtils {
    private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");

    private static ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());

    private static ECParameterSpec ecParameterSpec = new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());

    private static final int RS_LEN = 32;

    static {
        BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
        BouncyCastlePQCProvider bouncyCastlePQCProvider = new BouncyCastlePQCProvider();

        Security.addProvider(bouncyCastleProvider);
        Security.addProvider(bouncyCastlePQCProvider);
    }

    public static byte[] signSm3WithSm2(byte[] msg, byte[] userId, PrivateKey privateKey) {
        return rsAsn1ToPlainByteArray(signSm3WithSm2Asn1Rs(msg, userId, privateKey));
    }

    public static byte[] signSm3WithSm2(byte[] msg, PrivateKey privateKey) {
        return rsAsn1ToPlainByteArray(signSm3WithSm2Asn1Rs(msg, privateKey));
    }

    public static byte[] signSm3WithSm2Asn1Rs(byte[] msg, PrivateKey privateKey) {
        try {
            Signature signer = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), "BC");
            signer.initSign(privateKey, new SecureRandom());
            signer.update(msg, 0, msg.length);
            byte[] sig = signer.sign();
            return sig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] signSm3WithSm2Asn1Rs(byte[] msg, byte[] userId, PrivateKey privateKey) {
        try {
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
            Signature signer = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), "BC");
            signer.setParameter(parameterSpec);
            signer.initSign(privateKey, new SecureRandom());
            signer.update(msg, 0, msg.length);
            byte[] sig = signer.sign();
            return sig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifySm3WithSm2(byte[] msg, byte[] userId, byte[] rs, PublicKey publicKey) {
        return verifySm3WithSm2Asn1Rs(msg, userId, rsPlainByteArrayToAsn1(rs), publicKey);
    }

    public static boolean verifySm3WithSm2(byte[] msg, byte[] rs, PublicKey publicKey) {
        return verifySm3WithSm2Asn1Rs(msg, rsPlainByteArrayToAsn1(rs), publicKey);
    }

    public static boolean verifySm3WithSm2Asn1Rs(byte[] msg, byte[] rs, PublicKey publicKey) {
        try {
            Signature verifier = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), (Provider) new BouncyCastleProvider());
            verifier.initVerify(publicKey);
            verifier.update(msg, 0, msg.length);
            return verifier.verify(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifySm3WithSm2Asn1Rs(byte[] msg, byte[] userId, byte[] rs, PublicKey publicKey) {
        try {
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
            Signature verifier = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), (Provider) new BouncyCastleProvider());
            verifier.setParameter((AlgorithmParameterSpec) parameterSpec);
            verifier.initVerify(publicKey);
            verifier.update(msg, 0, msg.length);
            return verifier.verify(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] changeC1C2C3ToC1C3C2(byte[] c1c2c3) {
        int c1Len = (x9ECParameters.getCurve().getFieldSize() + 7) / 8 * 2 + 1;
        int c3Len = 32;
        byte[] result = new byte[c1c2c3.length];
        System.arraycopy(c1c2c3, 0, result, 0, c1Len);
        System.arraycopy(c1c2c3, c1c2c3.length - 32, result, c1Len, 32);
        System.arraycopy(c1c2c3, c1Len, result, c1Len + 32, c1c2c3.length - c1Len - 32);
        return result;
    }

    private static byte[] changeC1C3C2ToC1C2C3(byte[] c1c3c2) {
        int c1Len = (x9ECParameters.getCurve().getFieldSize() + 7) / 8 * 2 + 1;
        int c3Len = 32;
        byte[] result = new byte[c1c3c2.length];
        System.arraycopy(c1c3c2, 0, result, 0, c1Len);
        System.arraycopy(c1c3c2, c1Len + 32, result, c1Len, c1c3c2.length - c1Len - 32);
        System.arraycopy(c1c3c2, c1Len, result, c1c3c2.length - 32, 32);
        return result;
    }

    public static byte[] sm2Decrypt(byte[] data, PrivateKey key) {
        return sm2DecryptOld(changeC1C3C2ToC1C2C3(data), key);
    }

    public static byte[] sm2Encrypt(byte[] data, PublicKey key) {
        return changeC1C2C3ToC1C3C2(sm2EncryptOld(data, key));
    }

    public static byte[] sm2EncryptOld(byte[] data, PublicKey key) {
        BCECPublicKey localECPublicKey = (BCECPublicKey) key;
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(localECPublicKey.getQ(), ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, (CipherParameters) new ParametersWithRandom((CipherParameters) ecPublicKeyParameters, new SecureRandom()));
        try {
            return sm2Engine.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sm2DecryptOld(byte[] data, PrivateKey key) {
        BCECPrivateKey localECPrivateKey = (BCECPrivateKey) key;
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(localECPrivateKey.getD(), ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, (CipherParameters) ecPrivateKeyParameters);
        try {
            return sm2Engine.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sm3(byte[] bytes) {
        SM3Digest sm3 = new SM3Digest();
        sm3.update(bytes, 0, bytes.length);
        byte[] result = new byte[sm3.getDigestSize()];
        sm3.doFinal(result, 0);
        return result;
    }

    private static byte[] bigIntToFixexLengthBytes(BigInteger rOrS) {
        byte[] rs = rOrS.toByteArray();
        if (rs.length == 32) {
            return rs;
        }
        if (rs.length == 33 && rs[0] == 0) {
            return Arrays.copyOfRange(rs, 1, 33);
        }
        if (rs.length < 32) {
            byte[] result = new byte[32];
            Arrays.fill(result, (byte) 0);
            System.arraycopy(rs, 0, result, 32 - rs.length, rs.length);
            return result;
        }
        throw new RuntimeException("err rs: " + Hex.toHexString(rs));
    }

    private static byte[] rsAsn1ToPlainByteArray(byte[] rsDer) {
        ASN1Sequence seq = ASN1Sequence.getInstance(rsDer);
        byte[] r = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(0)).getValue());
        byte[] s = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(1)).getValue());
        byte[] result = new byte[64];
        System.arraycopy(r, 0, result, 0, r.length);
        System.arraycopy(s, 0, result, 32, s.length);
        return result;
    }

    private static byte[] rsPlainByteArrayToAsn1(byte[] sign) {
        if (sign.length != 64) {
            throw new RuntimeException("err rs. ");
        }
        BigInteger r = new BigInteger(1, Arrays.copyOfRange(sign, 0, 32));
        BigInteger s = new BigInteger(1, Arrays.copyOfRange(sign, 32, 64));
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add((ASN1Encodable) new ASN1Integer(r));
        v.add((ASN1Encodable) new ASN1Integer(s));
        try {
            return (new DERSequence(v)).getEncoded("DER");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("EC", "BC");
            kpGen.initialize(ecParameterSpec, new SecureRandom());
            return kpGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BCECPrivateKey getPrivatekeyFromD(BigInteger d) {
        ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(d, ecParameterSpec);
        return new BCECPrivateKey("EC", ecPrivateKeySpec, BouncyCastleProvider.CONFIGURATION);
    }

    public static BCECPublicKey getPublickeyFromXY(BigInteger x, BigInteger y) {
        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y), ecParameterSpec);
        return new BCECPublicKey("EC", ecPublicKeySpec, BouncyCastleProvider.CONFIGURATION);
    }

    public static BCECPublicKey getPublickeyFromq(String publickey) {
        byte[] publickeyByte = Hex.decode(publickey);
        int baseLength = 32;
        int startIndex = 0;
        if (publickeyByte.length > baseLength * 2) {
            startIndex = publickeyByte.length - baseLength * 2;
        }
        BigInteger x = new BigInteger(1, Arrays.copyOfRange(publickeyByte, startIndex, startIndex + baseLength));
        BigInteger y = new BigInteger(1, Arrays.copyOfRange(publickeyByte, startIndex + baseLength, publickeyByte.length));
        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y), ecParameterSpec);
        return new BCECPublicKey("EC", ecPublicKeySpec, BouncyCastleProvider.CONFIGURATION);
    }

    public static PublicKey getPublickeyFromX509File(File file) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
            FileInputStream in = new FileInputStream(file);
            X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
            return x509.getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] fileToByte(File file) {
        byte[] result = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            result = baos.toByteArray();
            fis.close();
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void byteToFile(byte[] fileByte, String filePath) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(filePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileByte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String encryptSM2(String text, String publicKey) {
        BCECPublicKey bCECPublicKey = getPublickeyFromq(publicKey);
        byte[] enByte = new byte[0];
        try {
            enByte = sm2EncryptOld(text.getBytes("UTF8"), (PublicKey) bCECPublicKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String enStr = Hex.toHexString(enByte);
        return enStr;
    }

    public static String decryptSM2(String text, String privateKey) {
        BCECPrivateKey bcecPrivateKey = getPrivatekeyFromD(new BigInteger(privateKey, 16));
        byte[] deByte = sm2DecryptOld(Hex.decode(text), (PrivateKey) bcecPrivateKey);
        String deStr = null;
        try {
            deStr = new String(deByte, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return deStr;
    }

    public static void getSM2RandomKey() {
        KeyPair kp = generateKeyPair();
        String privateKey = Hex.toHexString(((BCECPrivateKey) kp.getPrivate()).getD().toByteArray());
        String publicKey = Hex.toHexString(((BCECPublicKey) kp.getPublic()).getQ().getEncoded(false));
        File privateKeyFile = new File("privateKey.pri");
        File publicKeyFile = new File("publicKey.pub");
        FileWriter fw1 = null;
        FileWriter fw2 = null;
        try {
            fw1 = new FileWriter(privateKeyFile);
            fw1.write(privateKey);
            fw2 = new FileWriter(publicKeyFile);
            fw2.write(publicKey);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw1 != null) {
                try {
                    fw1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw2 != null) {
                try {
                    fw2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
