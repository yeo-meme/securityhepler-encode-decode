package com.nalazoocare.securityhelper;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-07-10
 */
public class DES3 {

    //key
    // DES 는  16Byte
    // 3DES는 24 Byte

    //ECB
    //CBC 방식 2가지가 있음

    private final static String secertKey = "nalazoo@2020##$$good$$##";
    //Vector
    private final static String iv = "";

    //Encryption and decryption unified encoding to use
    private final static String encoding = "utf-8";


    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secertKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
        deskey = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return Base64.encodeToString(encryptData, Base64.DEFAULT);
    }

    /**
     * 3DES decryption
     *
     * @param encryptText encrypted text
     * @return
     */
    public static String decode(String encryptText) {
        if (TextUtils.isEmpty(encryptText)) return "";
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secertKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            Key deskey = keyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            byte[] decryptData = cipher.doFinal(Base64.decode(encryptText, Base64.DEFAULT));
            Log.d("meme","decode :" + encoding);

            return new String(decryptData, encoding);
        } catch (Exception e) {
            Log.d("meme","decode exception :" + e);
            e.printStackTrace();
        }
        return "";
    }
}