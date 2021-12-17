package net.taus.data.marketplace.api.security.auth.provider;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Crypto {
  // 16 bytes //hex = 6b 54 39 31 6a 4a 6f 70 49 74 72 6e 34 70 6f 70
  static final String initVector = "kT91jJopItrn4pop";

  public static String encrypt(String value, String key) throws Exception {
    IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
    SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
    byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
    return Base64.encodeBase64String(encrypted);
  }
}