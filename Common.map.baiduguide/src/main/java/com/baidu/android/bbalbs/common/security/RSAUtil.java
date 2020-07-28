//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baidu.android.bbalbs.common.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

public final class RSAUtil {
    public static final String ALGORITHM_RSA = "RSA";
    public static final String PUBLIC_KEY = "PublicKey";
    public static final String PRIVATE_KEY = "PrivateKey";
    public static final int BYTE_IN_BITS = 8;
    public static final int PT_MAXLEN_OFFSET = 11;

    private RSAUtil() {
    }

    public static Map<String, Object> generateKey(int var0) throws NoSuchAlgorithmException {
        KeyPairGenerator var1 = KeyPairGenerator.getInstance("RSA");
        var1.initialize(var0);
        KeyPair var2 = var1.generateKeyPair();
        RSAPublicKey var3 = (RSAPublicKey)var2.getPublic();
        RSAPrivateKey var4 = (RSAPrivateKey)var2.getPrivate();
        HashMap var5 = new HashMap(2);
        var5.put("PublicKey", var3);
        var5.put("PrivateKey", var4);
        return var5;
    }

    public static String getPublicKey(Map<String, Object> var0) throws UnsupportedEncodingException {
        Key var1 = (Key)var0.get("PublicKey");
        return Base64.encode(var1.getEncoded(), "utf-8");
    }

    public static String getPrivateKey(Map<String, Object> var0) throws Exception {
        Key var1 = (Key)var0.get("PrivateKey");
        return Base64.encode(var1.getEncoded(), "utf-8");
    }

    public static byte[] decryptByPrivateKey(byte[] var0, String var1) throws Exception {
        byte[] var2 = Base64.decode(var1.getBytes());
        PKCS8EncodedKeySpec var3 = new PKCS8EncodedKeySpec(var2);
        KeyFactory var4 = KeyFactory.getInstance("RSA");
        PrivateKey var5 = var4.generatePrivate(var3);
        Cipher var6 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        var6.init(2, var5);
        return var6.doFinal(var0);
    }

    public static byte[] decryptLongByPrivateKey(byte[] var0, String var1, int var2) throws Exception {
        byte[] var3 = Base64.decode(var1.getBytes());
        PKCS8EncodedKeySpec var4 = new PKCS8EncodedKeySpec(var3);
        KeyFactory var5 = KeyFactory.getInstance("RSA");
        PrivateKey var6 = var5.generatePrivate(var4);
        Cipher var7 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        var7.init(2, var6);
        int var8 = var2 / 8;
        StringBuilder var9 = new StringBuilder();
        int var10 = var0.length;
        int var11 = 0;

        while(var11 < var10) {
            int var12 = var10 - var11;
            int var13 = var8 < var12?var8:var12;
            byte[] var14 = new byte[var13];
            System.arraycopy(var0, var11, var14, 0, var13);
            var11 += var13;
            var9.append(new String(var7.doFinal(var14)));
        }

        return var9.toString().getBytes();
    }

    public static byte[] decryptByPublicKey(byte[] var0, String var1) throws Exception {
        byte[] var2 = Base64.decode(var1.getBytes());
        X509EncodedKeySpec var3 = new X509EncodedKeySpec(var2);
        KeyFactory var4 = KeyFactory.getInstance("RSA");
        PublicKey var5 = var4.generatePublic(var3);
        Cipher var6 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        var6.init(2, var5);
        return var6.doFinal(var0);
    }

    public static byte[] encryptByPublicKey(byte[] var0, String var1) throws Exception {
        byte[] var2 = Base64.decode(var1.getBytes());
        X509EncodedKeySpec var3 = new X509EncodedKeySpec(var2);
        KeyFactory var4 = KeyFactory.getInstance("RSA");
        PublicKey var5 = var4.generatePublic(var3);
        Cipher var6 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        var6.init(1, var5);
        return var6.doFinal(var0);
    }

    public static byte[] encryptLongByPublicKey(byte[] var0, String var1, int var2) throws Exception {
        byte[] var3 = Base64.decode(var1.getBytes());
        X509EncodedKeySpec var4 = new X509EncodedKeySpec(var3);
        KeyFactory var5 = KeyFactory.getInstance("RSA");
        PublicKey var6 = var5.generatePublic(var4);
        Cipher var7 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        var7.init(1, var6);
        int var8 = var2 / 8;
        int var9 = var8 - 11;
        int var10 = var0.length;
        byte[] var11 = new byte[(var10 + var9 - 1) / var9 * var8];
        int var12 = 0;

        for(int var13 = 0; var12 < var10; var13 += var8) {
            int var14 = var10 - var12;
            int var15 = var9 < var14?var9:var14;
            byte[] var16 = new byte[var15];
            System.arraycopy(var0, var12, var16, 0, var15);
            var12 += var15;
            System.arraycopy(var7.doFinal(var16), 0, var11, var13, var8);
        }

        return var11;
    }

    public static byte[] encryptByPrivateKey(byte[] var0, String var1) throws Exception {
        byte[] var2 = Base64.decode(var1.getBytes());
        PKCS8EncodedKeySpec var3 = new PKCS8EncodedKeySpec(var2);
        KeyFactory var4 = KeyFactory.getInstance("RSA");
        PrivateKey var5 = var4.generatePrivate(var3);
        Cipher var6 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        var6.init(1, var5);
        return var6.doFinal(var0);
    }

    public static RSAPublicKey generateRSAPublicKey(BigInteger var0, BigInteger var1) throws Exception {
        KeyFactory var2 = null;

        try {
            var2 = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException var6) {
            throw new Exception(var6.getMessage());
        }

        RSAPublicKeySpec var3 = new RSAPublicKeySpec(var0, var1);

        try {
            return (RSAPublicKey)var2.generatePublic(var3);
        } catch (InvalidKeySpecException var5) {
            throw new Exception(var5.getMessage());
        }
    }

    public static RSAPrivateKey generateRSAPrivateKey(BigInteger var0, BigInteger var1) throws Exception {
        KeyFactory var2 = null;

        try {
            var2 = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException var6) {
            throw new Exception(var6.getMessage());
        }

        RSAPrivateKeySpec var3 = new RSAPrivateKeySpec(var0, var1);

        try {
            return (RSAPrivateKey)var2.generatePrivate(var3);
        } catch (InvalidKeySpecException var5) {
            throw new Exception(var5.getMessage());
        }
    }
}
