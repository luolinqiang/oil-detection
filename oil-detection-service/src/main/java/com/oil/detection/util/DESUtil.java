package com.oil.detection.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-11-12
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
public class DESUtil {

    private final static String ivs = "12345678";

    public static String encrypt(String val, String key) throws Exception{
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        //key的长度不能够小于8位字节
        Key secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());//向量
        AlgorithmParameterSpec paramSpec = iv;
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
        byte[] bytes = cipher.doFinal(val.getBytes());
        Base64 base64 = new Base64();
        return base64.encodeToString(bytes);
    }

    public static String decrypt(String val, String key) throws Exception{
        try{
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            //key 的长度不能够小于8 位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
            Base64 base64 = new Base64();
            byte[] decodeParam = base64.decode(val);
            byte[] bt = cipher.doFinal(decodeParam);
            return new String(bt);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
