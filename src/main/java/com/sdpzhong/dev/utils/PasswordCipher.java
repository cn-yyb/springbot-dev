package com.sdpzhong.dev.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * 密码密钥生成
 */
@Slf4j
public class PasswordCipher {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int DERIVED_KEY_LENGTH = 256; // 密钥长度
    private static final int ITERATIONS = 100000; // 迭代次数，增加安全性
    private static final int SALT_LENGTH = 16; // 盐的长度，通常是8到16字节

    /**
     * 生成密钥
     *
     * @param password 原密码
     * @param salt     密码盐
     * @return String
     */
    public static String hashPassword(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DERIVED_KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 生成密码盐
     *
     * @return byte[]
     */
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 密钥比对
     *
     * @param password     密码
     * @param hashPassword 加密后的密码 （Base64）
     * @param salt         密码盐（Base64）
     * @return boolean
     */
    public static boolean comparePassword(String password, String hashPassword, String salt) {
        // 生成新的密钥和原密钥进行比对
        String encryptPassword = hashPassword(password, Base64.getDecoder().decode(salt));

        return Objects.equals(encryptPassword, hashPassword);
    }

    // test
    public static void main(String[] args) {
        try {
            String password = "123456";
            byte[] salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);
            System.out.println("Hashed Password: " + hashedPassword + " ,Salt: " + Base64.getEncoder().encodeToString(salt));
            // hashedPassword和salt一起存储到数据库中, 校验时将密码和盐一起算出hashedPassword进行比对
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
