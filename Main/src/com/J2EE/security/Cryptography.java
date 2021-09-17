package com.J2EE.security;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;


public class Cryptography {
    private Cryptography() {
    }

    public static String hashPBKDF2WithHmacSHA1(String plainText, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return DatatypeConverter.printHexBinary(hash);
    }

    public static String hashPBKDF2WithHmacSHA256(String plainText, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return DatatypeConverter.printHexBinary(hash);
    }

    public static String hashPBKDF2WithHmacSHA512(String plainText, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(), 65536, 512);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return DatatypeConverter.printHexBinary(hash);
    }

    @SuppressWarnings("JDK9+")
    public static String hashSHA3_256(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA3_256");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }

    @SuppressWarnings("JDK9+")
    public static String hashSHA3_512(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA3_512");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }

    public static String hashSHA2_256(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }

    public static String hashSHA2_512(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }


    public static String hashMD5(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static int getRandomInt(int maximum) {
        Random random = new Random();
        return random.nextInt(maximum);
    }

    public static double getRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }

    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    @SuppressWarnings("Decrypt By decryptBase64()")
    public static String encryptBase64URL(byte[] bytes) {
        return Base64.getEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String encryptBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decryptBase64(String encrypted) {
        return Base64.getDecoder().decode(encrypted);
    }

    public static String byte2String(byte[] bytes) {
        return new String(bytes);
    }

    public static byte[] string2Byte(String plainText) {
        return plainText.getBytes();
    }

    public static String decryptAES256(String encryptedText, String secret, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
    }

    public static String encryptAES256(String plainText, String secret, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    @Deprecated
    public static String encryptAES(String plainText, String secret) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] bytes = secret.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        bytes = messageDigest.digest(bytes);
        bytes = Arrays.copyOf(bytes, 16);
        SecretKeySpec secretKey = new SecretKeySpec(bytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
    }

    @Deprecated
    public static String decryptAES(String encryptedText, String secret) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] bytes = secret.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        bytes = messageDigest.digest(bytes);
        bytes = Arrays.copyOf(bytes, 16);
        SecretKeySpec secretKey = new SecretKeySpec(bytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
    }

    @SuppressWarnings("Optimize minimum secret lengh 16 character")
    public static void encryptAESFile(String clearFile, String encryptFile, String secret) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        FileInputStream fileInputStream = new FileInputStream(clearFile);
        FileOutputStream fileOutputStream = new FileOutputStream(encryptFile);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
        int aByte;
        byte[] bytes = new byte[8];
        while ((aByte = fileInputStream.read(bytes)) != -1) {
            cipherOutputStream.write(bytes, 0, aByte);
        }
        cipherOutputStream.flush();
        cipherOutputStream.close();
        fileInputStream.close();
    }

    @SuppressWarnings("Optimize minimum secret lengh 16 character")
    public static void decryptAESFile(String encryptedFile, String clearFile, String secret) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        FileInputStream fileInputStream = new FileInputStream(encryptedFile);
        FileOutputStream fileOutputStream = new FileOutputStream(clearFile);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipher);
        int aByte;
        byte[] bytes = new byte[8];
        while ((aByte = cipherInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, aByte);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        cipherInputStream.close();
    }

    public static Map<String, Key> getRSAKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        Map<String, Key> keys = new HashMap<>();
        keys.put("+", publicKey);
        keys.put("-", privateKey);
        return keys;
    }

    public static byte[] encryptRSA(String plainText, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String decryptRSA(byte[] encryptedByte, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(encryptedByte));
    }
}