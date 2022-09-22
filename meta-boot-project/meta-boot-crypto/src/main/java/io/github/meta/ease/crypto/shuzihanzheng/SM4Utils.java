package io.github.meta.ease.crypto.shuzihanzheng;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.InputStream;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

public class SM4Utils {
  private String secretKey = "";
  
  private boolean hexString = true;
  
  private static int SM4keySize = 128;
  
  static {
    if (Security.getProvider("BC") == null) {
      Security.addProvider((Provider)new BouncyCastleProvider());
    }
  }
  
  public String getSecretKey() {
    return this.secretKey;
  }
  
  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }
  
  public boolean isHexString() {
    return this.hexString;
  }
  
  public void setHexString(boolean hexString) {
    this.hexString = hexString;
  }
  
  public byte[] encryptByte_ECB(byte[] plainByte) {
    try {
      byte[] keyBytes;
      if (this.hexString) {
        keyBytes = Hex.decode(this.secretKey);
      } else {
        keyBytes = this.secretKey.getBytes();
      } 
      byte[] encrypted = sm4En(keyBytes, plainByte);
      return encrypted;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public byte[] decryptByte_ECB(byte[] cipherByte) {
    try {
      byte[] keyBytes;
      if (this.hexString) {
        keyBytes = Hex.decode(this.secretKey);
      } else {
        keyBytes = this.secretKey.getBytes();
      } 
      byte[] decrypted = sm4De(keyBytes, cipherByte);
      return decrypted;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public String encryptFile(File file) {
    byte[] fileBytes = FileUtils.fileToByte(file);
    byte[] enFileBytes = encryptByte_ECB(fileBytes);
    return Hex.toHexString(enFileBytes);
  }
  
  public String encryptFile(InputStream fis) {
    byte[] fileBytes = FileUtils.fileToByte(fis);
    byte[] enFileBytes = encryptByte_ECB(fileBytes);
    return Hex.toHexString(enFileBytes);
  }
  
  public byte[] decryptFile(String enFile) {
    byte[] fileByte = decryptByte_ECB(Hex.decode(enFile));
    return fileByte;
  }
  
  public String getSM4RandomKey() {
    String key = null;
    try {
      KeyGenerator kg = KeyGenerator.getInstance("SM4", "BC");
      kg.init(SM4keySize, new SecureRandom());
      byte[] keyBytes = kg.generateKey().getEncoded();
      key = Hex.toHexString(keyBytes);
    } catch (Exception exception) {}
    return key;
  }
  
  public static byte[] sm4En(byte[] key, byte[] text) {
    byte[] result = null;
    try {
      Cipher cipher = getCipher(key, 1);
      result = cipher.doFinal(text);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return result;
  }
  
  public static byte[] sm4De(byte[] key, byte[] text) {
    byte[] result = null;
    try {
      Cipher cipher = getCipher(key, 2);
      result = cipher.doFinal(text);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return result;
  }
  
  public static Cipher getCipher(byte[] key, int mode) throws Exception {
    Cipher cipher = Cipher.getInstance("SM4/ECB/PKCS5Padding", "BC");
    Key sm4Key = new SecretKeySpec(key, "SM4");
    cipher.init(mode, sm4Key);
    return cipher;
  }
}
