//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baidu.android.bbalbs.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SHA1Util {
    private SHA1Util() {
    }

    public static byte[] sha1(byte[] var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("SHA-1");
            return var1.digest(var0);
        } catch (NoSuchAlgorithmException var2) {
            throw new RuntimeException(var2);
        }
    }
}
