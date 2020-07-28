//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baidu.android.bbalbs.common.security;

public class RC4 {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;
    private int x = 0;
    private int y = 0;
    private byte[] workingKey = null;

    public RC4(String var1) {
        this.workingKey = var1.getBytes();
    }

    private void processBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) {
        if(var2 + var3 > var1.length) {
            throw new RuntimeException("input buffer too short");
        } else if(var5 + var3 > var4.length) {
            throw new RuntimeException("output buffer too short");
        } else {
            for(int var6 = 0; var6 < var3; ++var6) {
                this.x = this.x + 1 & 255;
                this.y = this.engineState[this.x] + this.y & 255;
                byte var7 = this.engineState[this.x];
                this.engineState[this.x] = this.engineState[this.y];
                this.engineState[this.y] = var7;
                var4[var6 + var5] = (byte)(var1[var6 + var2] ^ this.engineState[this.engineState[this.x] + this.engineState[this.y] & 255]);
            }

        }
    }

    private void setKey(byte[] var1) {
        this.x = 0;
        this.y = 0;
        if(this.engineState == null) {
            this.engineState = new byte[256];
        }

        int var2;
        for(var2 = 0; var2 < 256; ++var2) {
            this.engineState[var2] = (byte)var2;
        }

        var2 = 0;
        int var3 = 0;

        for(int var4 = 0; var4 < 256; ++var4) {
            var3 = (var1[var2] & 255) + this.engineState[var4] + var3 & 255;
            byte var5 = this.engineState[var4];
            this.engineState[var4] = this.engineState[var3];
            this.engineState[var3] = var5;
            var2 = (var2 + 1) % var1.length;
        }

    }

    private void reset() {
        this.setKey(this.workingKey);
    }

    public byte[] encrypt(byte[] var1) {
        this.reset();
        byte[] var2 = new byte[var1.length];
        this.processBytes(var1, 0, var1.length, var2, 0);
        return var2;
    }

    public byte[] decrypt(byte[] var1) {
        this.reset();
        byte[] var2 = new byte[var1.length];
        this.processBytes(var1, 0, var1.length, var2, 0);
        return var2;
    }
}
