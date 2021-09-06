package com.hdu.encryption;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @ClassName：EncryptionMain
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/5 11:00 下午
 * @Versiion：1.0
 */
public class EncryptionMain {
    private static String password = "1232323232324";
    private static final String defaultCharset = "UTF-8";
    private static final String KEY_AES = "AES";
    private static final String KEY_MD5 = "MD5";
    private static MessageDigest md5Digest;

    static {
        try {
            md5Digest = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {

        }
    }


    public static void main(String[] args) throws Exception {
        String input = "fsajfopawefmpafew";
        System.out.println("Input md5:" + md5Encryption(input));
        System.out.println("Input sha256:" + sha256(input));

        SecretKeySpec secretKeySpec = new SecretKeySpec("sssfff".getBytes(), "HmacMD5");
        System.out.println("Input hmac:" + hmacSha256(input, secretKeySpec));


        String encryptString = encrypt(input.getBytes(), password);
        System.out.println("Input des:" + encryptString);
        System.out.println("Input des decrypt:" + decrypt(encryptString, password));



        String des3Key = "fajwiefjiwaehfiuwaehfuiwahefuiwaefohawhefuiwaehfuoawefwa";
        String en3EncryptString = encryptThreeDESECB(input, des3Key);
        System.out.println("Input 3des:" + en3EncryptString);
        System.out.println("Input 3des decrypt:" +decryptThreeDESECB(en3EncryptString, des3Key) );


        String aesKey = "afjawefawjefiawejf0aiwjef0iawf";
        String aesEncryptString = encryptAes(input, aesKey);
        System.out.println("Input aes:" + aesEncryptString);
        System.out.println("Input aes aesEncrypt:" + decryptAes(aesEncryptString, aesKey));


        String rsaString = RsaUtil.encryptByPublicKey()
    }

    public static String md5Encryption(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = null;
        messageDigest = MessageDigest.getInstance("MD5");

        byte[] bytes = messageDigest.digest(input.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static String sha256(String text) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = messageDigest.digest(text.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static String hmacSha256(String text, SecretKeySpec sk) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            mac.init(sk);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] rawHmac = mac.doFinal(text.getBytes());
        return new String(Base64.encodeBase64(rawHmac));
    }


    public static String encrypt(byte[] dataSource, String password){
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            //正式执行加密操作
            return Base64.encodeBase64String(cipher.doFinal(dataSource));
        } catch (Throwable e) {
            e.printStackTrace();
        } return null;
    }
    // 解密
    public static String decrypt(String src, String password) throws Exception{
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
        // 真正开始解密操作
        return new String(cipher.doFinal(Base64.decodeBase64(src)));

    }

    public static String encryptThreeDESECB(String src, String key) {
        try{
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            byte[] b = cipher.doFinal(src.getBytes("UTF-8"));

            String ss = new String(Base64.encodeBase64(b));
            ss = ss.replaceAll("\\+", "-");
            ss = ss.replaceAll("/", "_");
            return ss;
        } catch(Exception ex){
            ex.printStackTrace();
            return src;
        }
    }

    public static String decryptThreeDESECB(String src, String key) {
        try{
            src = src.replaceAll("-", "+");
            src = src.replaceAll("_", "/");
            byte[] bytesrc = Base64.decodeBase64(src.getBytes("UTF-8"));
            // --解密的key
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            // --Chipher对象解密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            byte[] retByte = cipher.doFinal(bytesrc);

            return new String(retByte, "UTF-8");
        } catch(Exception ex){
            ex.printStackTrace();
            return src;
        }
    }

    public static String encryptAes(String data, String key) {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }

    public static String decryptAes(String data, String key) {
        return doAES(data, key, Cipher.DECRYPT_MODE);

    }

    private static String doAES(String data, String key, int mode) {
        try {
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = Base64.decodeBase64(data.getBytes());
            }
            SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(defaultCharset)), KEY_AES);
            Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(Base64.encodeBase64(result));
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
        }
        return null;
    }
}