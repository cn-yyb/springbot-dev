package com.sdpzhong.dev.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Author: zhongqing
 * @Description: 密码加密
 * @Date: 2024-07-15 19:03
 **/


/**
 * AES 加
 */
public class PasswordAESEncryption {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;

    public static void main(String[] args) throws Exception {
        String passwordToEncrypt = "myPassword123!";

        // Generate encryption key
        SecretKey key = generateKey();

        // Encrypt the password
        String encryptedPassword = encrypt(key, passwordToEncrypt);
        System.out.println("Encrypted Password: " + encryptedPassword);

        // Decrypt the password
        String decryptedPassword = decrypt(key, encryptedPassword);
        System.out.println("Decrypted Password: " + decryptedPassword);
    }

    /**
     * 生成密钥
     *
     * @return
     * @throws Exception
     */
    private static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE, new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
    }

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    private static String encrypt(SecretKey key, String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * 解密
     *
     * @param key
     * @param encryptedData
     * @return
     * @throws Exception
     */
    private static String decrypt(SecretKey key, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }
}
