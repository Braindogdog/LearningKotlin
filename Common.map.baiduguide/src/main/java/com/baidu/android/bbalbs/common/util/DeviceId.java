//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baidu.android.bbalbs.common.util;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.android.bbalbs.common.security.AESUtil;
import com.baidu.android.bbalbs.common.security.Base64;
import com.baidu.android.bbalbs.common.security.MD5Util;
import com.baidu.android.bbalbs.common.security.SHA1Util;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DeviceId {
    private static final String TAG = "DeviceId";
    private static final boolean DEBUG = false;
    private static final String KEY_DEVICE_ID = "com.baidu.deviceid";
    private static final String KEY_DEVICE_ID_V2 = "com.baidu.deviceid.v2";
    private static final String AES_KEY = "30212102dicudiab";
    private static final String OLD_EXT_DIR = "baidu";
    private static final String EXT_DIR = "backups/.SystemConfig";
    private static final String EXT_FILE = ".cuid";
    private static final String EXT_FILE_V2 = ".cuid2";
    private final Context mContext;
    private int mSaveMask = 0;
    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private PublicKey mPublicKey;
    private static final int STORAGE_SYSTEM_SETTING_V1 = 1;
    private static final int STORAGE_SYSTEM_SETTING_V2 = 2;
    private static final int STORAGE_SDCARD_V1 = 4;
    private static final int STORAGE_SDCARD_V2 = 8;
    private static final int STORAGE_SELF_FILE = 16;
    private static final String SELF_CUID_FILE = "libcuid.so";
    private static DeviceId.CUIDInfo sCachedCuidInfo;
    private static final String KEY_IMEI = "bd_setting_i";
    private static final String DEFAULT_TM_DEVICEID = "";
    private static final int SDK_ANDROID_M = 23;
    private static final String ACTION_GLAXY_CUID = "com.baidu.intent.action.GALAXY";
    private static final String META_KEY_GLAXY_DATA = "galaxy_data";
    private static final String META_KEY_GALAXY_SF = "galaxy_sf";

    private DeviceId(Context var1) {
        this.mContext = var1.getApplicationContext();
        this.initPublicKey();
    }

    private static String byte2hex(byte[] var0) {
        if(var0 == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        } else {
            String var1 = "";
            String var2 = "";

            for(int var3 = 0; var3 < var0.length; ++var3) {
                var2 = Integer.toHexString(var0[var3] & 255);
                if(var2.length() == 1) {
                    var1 = var1 + "0" + var2;
                } else {
                    var1 = var1 + var2;
                }
            }

            return var1.toLowerCase();
        }
    }

    private String[] formatAndroidSigArray(Signature[] var1) {
        String[] var2 = new String[var1.length];

        for(int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = byte2hex(SHA1Util.sha1(var1[var3].toByteArray()));
        }

        return var2;
    }

    private static byte[] decryptByPublicKey(byte[] var0, PublicKey var1) throws Exception {
        Cipher var2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        var2.init(2, var1);
        return var2.doFinal(var0);
    }

    private void initPublicKey() {
        ByteArrayInputStream var1 = null;

        try {
            var1 = new ByteArrayInputStream(CuidCertStore.getCertBytes());
            CertificateFactory var2 = CertificateFactory.getInstance("X.509");
            Certificate var3 = var2.generateCertificate(var1);
            this.mPublicKey = var3.getPublicKey();
        } catch (Exception var12) {
            ;
        } finally {
            if(var1 != null) {
                try {
                    var1.close();
                } catch (Exception var11) {
                    handleThrowable(var11);
                }
            }

        }

    }

    private List<DeviceId.CUIDBuddyInfo> collectBuddyInfos(Intent var1, boolean var2) {
        ArrayList var3 = new ArrayList();
        PackageManager var4 = this.mContext.getPackageManager();
        List var5 = var4.queryBroadcastReceivers(var1, 0);
        if(var5 != null) {
            Iterator var6 = var5.iterator();

            label71:
            while(true) {
                ResolveInfo var7;
                do {
                    do {
                        if(!var6.hasNext()) {
                            break label71;
                        }

                        var7 = (ResolveInfo)var6.next();
                    } while(var7.activityInfo == null);
                } while(var7.activityInfo.applicationInfo == null);

                try {
                    ActivityInfo var8 = var4.getReceiverInfo(new ComponentName(var7.activityInfo.packageName, var7.activityInfo.name), 128);
                    Bundle var9 = var8.metaData;
                    if(var9 != null) {
                        String var10 = var9.getString("galaxy_data");
                        if(!TextUtils.isEmpty(var10)) {
                            byte[] var11 = Base64.decode(var10.getBytes("utf-8"));
                            String var12 = new String(var11);
                            JSONObject var13 = new JSONObject(var12);
                            DeviceId.CUIDBuddyInfo var14 = new DeviceId.CUIDBuddyInfo();
                            var14.priority = var13.getInt("priority");
                            var14.appInfo = var7.activityInfo.applicationInfo;
                            if(this.mContext.getPackageName().equals(var7.activityInfo.applicationInfo.packageName)) {
                                var14.isSelf = true;
                            }

                            if(var2) {
                                String var15 = var9.getString("galaxy_sf");
                                if(!TextUtils.isEmpty(var15)) {
                                    PackageInfo var16 = var4.getPackageInfo(var7.activityInfo.applicationInfo.packageName, 64);
                                    JSONArray var17 = var13.getJSONArray("sigs");
                                    String[] var18 = new String[var17.length()];

                                    for(int var19 = 0; var19 < var18.length; ++var19) {
                                        var18[var19] = var17.getString(var19);
                                    }

                                    String[] var24 = this.formatAndroidSigArray(var16.signatures);
                                    if(this.isSigsEqual(var18, var24)) {
                                        byte[] var20 = decryptByPublicKey(Base64.decode(var15.getBytes()), this.mPublicKey);
                                        byte[] var21 = SHA1Util.sha1(var11);
                                        boolean var22 = var20 != null && Arrays.equals(var20, var21);
                                        if(var22) {
                                            var14.sigMatched = true;
                                        }
                                    }
                                }
                            }

                            var3.add(var14);
                        }
                    }
                } catch (Exception var23) {
                    ;
                }
            }
        }

        Collections.sort(var3, new Comparator<DeviceId.CUIDBuddyInfo>() {
            public int compare(DeviceId.CUIDBuddyInfo var1, DeviceId.CUIDBuddyInfo var2) {
                int var3 = var2.priority - var1.priority;
                if(var3 == 0) {
                    if(var1.isSelf && var2.isSelf) {
                        return 0;
                    }

                    if(var1.isSelf) {
                        return -1;
                    }

                    if(var2.isSelf) {
                        return 1;
                    }
                }

                return var3;
            }
        });
        return var3;
    }

    private boolean isSigsEqual(String[] var1, String[] var2) {
        if(var1 != null && var2 != null && var1.length == var2.length) {
            HashSet var3 = new HashSet();
            String[] var4 = var1;
            int var5 = var1.length;

            int var6;
            for(var6 = 0; var6 < var5; ++var6) {
                String var7 = var4[var6];
                var3.add(var7);
            }

            HashSet var9 = new HashSet();
            String[] var10 = var2;
            var6 = var2.length;

            for(int var11 = 0; var11 < var6; ++var11) {
                String var8 = var10[var11];
                var9.add(var8);
            }

            return var3.equals(var9);
        } else {
            return false;
        }
    }

    private boolean writeToCuidFile(String var1) {
        FileOutputStream var2 = null;

        try {
            var2 = this.mContext.openFileOutput("libcuid.so", Context.MODE_WORLD_READABLE);
            var2.write(var1.getBytes());
            var2.flush();
            boolean var3 = true;
            return var3;
        } catch (Exception var13) {
            handleThrowable(var13);
        } finally {
            if(var2 != null) {
                try {
                    var2.close();
                } catch (Exception var12) {
                    handleThrowable(var12);
                }
            }

        }

        return false;
    }

    private String getSystemSettingValue(String var1) {
        try {
            return System.getString(this.mContext.getContentResolver(), var1);
        } catch (Exception var3) {
            handleThrowable(var3);
            return null;
        }
    }

    private boolean tryPutSystemSettingValue(String var1, String var2) {
        try {
            return System.putString(this.mContext.getContentResolver(), var1, var2);
        } catch (Exception var4) {
            handleThrowable(var4);
            return false;
        }
    }

    private static void handleThrowable(Throwable var0) {
    }

    private static void writeToFile(File var0, byte[] var1) {
        FileOutputStream var2 = null;

        try {
            var2 = new FileOutputStream(var0);
            var2.write(var1);
            var2.flush();
        } catch (IOException var14) {
            handleThrowable(var14);
        } catch (SecurityException var15) {
            handleThrowable(var15);
        } finally {
            if(var2 != null) {
                try {
                    var2.close();
                } catch (IOException var13) {
                    handleThrowable(var13);
                }
            }

        }

    }

    private static String getFileContent(File var0) {
        FileReader var1 = null;

        try {
            var1 = new FileReader(var0);
            char[] var2 = new char[8192];
            CharArrayWriter var3 = new CharArrayWriter();

            int var4;
            while((var4 = var1.read(var2)) > 0) {
                var3.write(var2, 0, var4);
            }

            String var5 = var3.toString();
            return var5;
        } catch (Exception var15) {
            handleThrowable(var15);
        } finally {
            if(var1 != null) {
                try {
                    var1.close();
                } catch (Exception var14) {
                    handleThrowable(var14);
                }
            }

        }

        return null;
    }

    public static String getCUID(Context var0) {
        return getOrCreateCUIDInfo(var0).getFinalCUID();
    }

    private static DeviceId.CUIDInfo getOrCreateCUIDInfo(Context var0) {
        if(sCachedCuidInfo == null) {
            Class var1 = DeviceId.CUIDInfo.class;
            synchronized(DeviceId.CUIDInfo.class) {
                if(sCachedCuidInfo == null) {
                    long var2 = SystemClock.uptimeMillis();
                    sCachedCuidInfo = (new DeviceId(var0)).getCUIDInfo();
                    long var4 = SystemClock.uptimeMillis();
                }
            }
        }

        return sCachedCuidInfo;
    }

    public static String getDeviceID(Context var0) {
        return getOrCreateCUIDInfo(var0).deviceId;
    }

    public static String getIMEI(Context var0) {
        return getOrCreateCUIDInfo(var0).imei;
    }

    public static String getAndroidId(Context var0) {
        String var2 = "";
        var2 = Secure.getString(var0.getContentResolver(), "android_id");
        if(TextUtils.isEmpty(var2)) {
            var2 = "";
        }

        return var2;
    }

    private DeviceId.CUIDInfo getCUIDInfo() {
        DeviceId.CUIDInfo var1 = null;
        String var2 = null;
        List var3 = this.collectBuddyInfos((new Intent("com.baidu.intent.action.GALAXY")).setPackage(this.mContext.getPackageName()), true);
        boolean var4;
        if(var3 != null && var3.size() != 0) {
            DeviceId.CUIDBuddyInfo var13 = (DeviceId.CUIDBuddyInfo)var3.get(0);
            var4 = var13.sigMatched;
            if(!var13.sigMatched) {
                for(int var6 = 0; var6 < 3; ++var6) {
                    Log.w("DeviceId", "galaxy config err, In the release version of the signature should be matched");
                }
            }
        } else {
            var4 = false;

            for(int var5 = 0; var5 < 3; ++var5) {
                Log.w("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
        }

        File var14 = new File(this.mContext.getFilesDir(), "libcuid.so");
        if(var14.exists()) {
            var1 = DeviceId.CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(var14)));
        }

        if(var1 == null) {
            this.mSaveMask |= 16;
            List var15 = this.collectBuddyInfos(new Intent("com.baidu.intent.action.GALAXY"), var4);
            if(var15 != null) {
                String var7 = "files";
                File var8 = this.mContext.getFilesDir();
                if(!var7.equals(var8.getName())) {
                    Log.e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + var8.getAbsolutePath());
                    var7 = var8.getName();
                }

                Iterator var9 = var15.iterator();

                while(var9.hasNext()) {
                    DeviceId.CUIDBuddyInfo var10 = (DeviceId.CUIDBuddyInfo)var9.next();
                    if(!var10.isSelf) {
                        File var11 = new File(new File(var10.appInfo.dataDir, var7), "libcuid.so");
                        if(var11.exists()) {
                            var1 = DeviceId.CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(var11)));
                            if(var1 != null) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(var1 == null) {
            var1 = DeviceId.CUIDInfo.createFromJson(decryptCUIDInfo(this.getSystemSettingValue("com.baidu.deviceid.v2")));
        }

        boolean var16 = this.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
        if(var1 == null && var16) {
            this.mSaveMask |= 2;
            var1 = this.getCuidInfoFromExternalV2();
        }

        if(var1 == null) {
            this.mSaveMask |= 8;
            var1 = this.getCUidInfoFromSystemSettingV1();
        }

        boolean var17 = false;
        if(var1 == null && var16) {
            this.mSaveMask |= 1;
            var2 = this.getIMEIFromSystem("");
            var17 = true;
            var1 = this.getExternalV1DeviceId(var2);
        }

        String var18;
        if(var1 == null) {
            this.mSaveMask |= 4;
            if(!var17) {
                var2 = this.getIMEIFromSystem("");
            }

            var1 = new DeviceId.CUIDInfo();
            var18 = getAndroidId(this.mContext);
            String var19;
            if(VERSION.SDK_INT < 23) {
                String var21 = UUID.randomUUID().toString();
                var19 = var2 + var18 + var21;
            } else {
                var19 = "com.baidu" + var18;
            }

            var1.deviceId = MD5Util.toMd5(var19.getBytes(), true);
            var1.imei = var2;
        }

        var18 = null;
        File var20 = new File(this.mContext.getFilesDir(), "libcuid.so");
        if((this.mSaveMask & 16) != 0 || !var20.exists()) {
            if(TextUtils.isEmpty(var18)) {
                var18 = encryptCUIDInfo(var1.toPersitString());
            }

            this.writeToCuidFile(var18);
        }

        boolean var22 = this.hasWriteSettingsPermission();
        if(var22 && ((this.mSaveMask & 2) != 0 || TextUtils.isEmpty(this.getSystemSettingValue("com.baidu.deviceid.v2")))) {
            if(TextUtils.isEmpty(var18)) {
                var18 = encryptCUIDInfo(var1.toPersitString());
            }

            this.tryPutSystemSettingValue("com.baidu.deviceid.v2", var18);
        }

        boolean var23 = this.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        File var12;
        if(var23) {
            var12 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
            if((this.mSaveMask & 8) != 0 || !var12.exists()) {
                if(TextUtils.isEmpty(var18)) {
                    var18 = encryptCUIDInfo(var1.toPersitString());
                }

                setExternalV2DeviceId(var18);
            }
        }

        if(var22 && ((this.mSaveMask & 1) != 0 || TextUtils.isEmpty(this.getSystemSettingValue("com.baidu.deviceid")))) {
            this.tryPutSystemSettingValue("com.baidu.deviceid", var1.deviceId);
            this.tryPutSystemSettingValue("bd_setting_i", var1.imei);
        }

        if(var22 && !TextUtils.isEmpty(var1.imei)) {
            var12 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            if((this.mSaveMask & 2) != 0 || !var12.exists()) {
                setExternalDeviceId(var1.imei, var1.deviceId);
            }
        }

        return var1;
    }

    private boolean hasWriteSettingsPermission() {
        return this.checkSelfPermission("android.permission.WRITE_SETTINGS");
    }

    private boolean hasReadImeiPermission() {
        return this.checkSelfPermission("android.permission.READ_PHONE_STATE");
    }

    private boolean checkSelfPermission(String var1) {
        return this.mContext.checkPermission(var1, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED;
    }

    private DeviceId.CUIDInfo getCUidInfoFromSystemSettingV1() {
        String var1 = this.getSystemSettingValue("com.baidu.deviceid");
        String var2 = this.getSystemSettingValue("bd_setting_i");
        if(TextUtils.isEmpty(var2)) {
            var2 = this.getIMEIFromSystem("");
            if(!TextUtils.isEmpty(var2)) {
                this.tryPutSystemSettingValue("bd_setting_i", var2);
            }
        }

        if(TextUtils.isEmpty(var1)) {
            String var3 = getAndroidId(this.mContext);
            String var4 = MD5Util.toMd5(("com.baidu" + var2 + var3).getBytes(), true);
            var1 = this.getSystemSettingValue(var4);
        }

        if(!TextUtils.isEmpty(var1)) {
            DeviceId.CUIDInfo var5 = new DeviceId.CUIDInfo();
            var5.deviceId = var1;
            var5.imei = var2;
            return var5;
        } else {
            return null;
        }
    }

    private DeviceId.CUIDInfo getCuidInfoFromExternalV2() {
        File var1 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if(var1.exists()) {
            String var2 = getFileContent(var1);
            if(!TextUtils.isEmpty(var2)) {
                try {
                    return DeviceId.CUIDInfo.createFromJson(new String(AESUtil.decrypt("30212102dicudiab", "30212102dicudiab", Base64.decode(var2.getBytes()))));
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }
        }

        return null;
    }

    private DeviceId.CUIDInfo getExternalV1DeviceId(String var1) {
        boolean var2 = VERSION.SDK_INT < 23;
        if(var2 && TextUtils.isEmpty(var1)) {
            return null;
        } else {
            boolean var3 = false;
            String var4 = "";
            File var5 = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
            if(!var5.exists()) {
                var5 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
                var3 = true;
            }

            try {
                FileReader var6 = new FileReader(var5);
                BufferedReader var7 = new BufferedReader(var6);
                StringBuilder var8 = new StringBuilder();
                String var9 = null;

                while((var9 = var7.readLine()) != null) {
                    var8.append(var9);
                    var8.append("\r\n");
                }

                var7.close();
                String var10 = new String(AESUtil.decrypt("30212102dicudiab", "30212102dicudiab", Base64.decode(var8.toString().getBytes())));
                String[] var11 = var10.split("=");
                if(var11 != null && var11.length == 2) {
                    if(var2 && var1.equals(var11[0])) {
                        var4 = var11[1];
                    } else if(!var2) {
                        if(TextUtils.isEmpty(var1)) {
                            var1 = var11[1];
                        }

                        var4 = var11[1];
                    }
                }

                if(!var3) {
                    setExternalDeviceId(var1, var4);
                }
            } catch (FileNotFoundException var12) {
                ;
            } catch (IOException var13) {
                ;
            } catch (Exception var14) {
                ;
            }

            if(!TextUtils.isEmpty(var4)) {
                DeviceId.CUIDInfo var15 = new DeviceId.CUIDInfo();
                var15.deviceId = var4;
                var15.imei = var1;
                return var15;
            } else {
                return null;
            }
        }
    }

    private static String encryptCUIDInfo(String var0) {
        if(TextUtils.isEmpty(var0)) {
            return null;
        } else {
            try {
                return Base64.encode(AESUtil.encrypt("30212102dicudiab", "30212102dicudiab", var0.getBytes()), "utf-8");
            } catch (UnsupportedEncodingException var2) {
                handleThrowable(var2);
            } catch (Exception var3) {
                handleThrowable(var3);
            }

            return "";
        }
    }

    private static String decryptCUIDInfo(String var0) {
        if(TextUtils.isEmpty(var0)) {
            return null;
        } else {
            try {
                return new String(AESUtil.decrypt("30212102dicudiab", "30212102dicudiab", Base64.decode(var0.getBytes())));
            } catch (Exception var2) {
                handleThrowable(var2);
                return "";
            }
        }
    }

    private static void setExternalV2DeviceId(String var0) {
        File var1 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File var2 = new File(var1, ".cuid2");

        try {
            if(var1.exists() && !var1.isDirectory()) {
                Random var3 = new Random();
                File var4 = null;
                File var5 = var1.getParentFile();
                String var6 = var1.getName();

                do {
                    var4 = new File(var5, var6 + var3.nextInt() + ".tmp");
                } while(var4.exists());

                var1.renameTo(var4);
                var4.delete();
            }

            var1.mkdirs();
            FileWriter var9 = new FileWriter(var2, false);
            var9.write(var0);
            var9.flush();
            var9.close();
        } catch (IOException var7) {
            ;
        } catch (Exception var8) {
            ;
        }

    }

    private static void setExternalDeviceId(String var0, String var1) {
        if(!TextUtils.isEmpty(var0)) {
            StringBuilder var2 = new StringBuilder();
            var2.append(var0);
            var2.append("=");
            var2.append(var1);
            File var3 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
            File var4 = new File(var3, ".cuid");

            try {
                if(var3.exists() && !var3.isDirectory()) {
                    Random var5 = new Random();
                    File var6 = null;
                    File var7 = var3.getParentFile();
                    String var8 = var3.getName();

                    do {
                        var6 = new File(var7, var8 + var5.nextInt() + ".tmp");
                    } while(var6.exists());

                    var3.renameTo(var6);
                    var6.delete();
                }

                var3.mkdirs();
                FileWriter var11 = new FileWriter(var4, false);
                String var12 = Base64.encode(AESUtil.encrypt("30212102dicudiab", "30212102dicudiab", var2.toString().getBytes()), "utf-8");
                var11.write(var12);
                var11.flush();
                var11.close();
            } catch (IOException var9) {
                ;
            } catch (Exception var10) {
                ;
            }

        }
    }

    @SuppressLint("MissingPermission")
    private String getIMEIFromSystem(String var1) {
        String var2 = null;

        try {
            TelephonyManager var3 = (TelephonyManager)this.mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if(var3 != null) {
                var2 = var3.getDeviceId();
            }
        } catch (Exception var4) {
            Log.e("DeviceId", "Read IMEI failed", var4);
        }

        var2 = imeiCheck(var2);
        if(TextUtils.isEmpty(var2)) {
            var2 = var1;
        }

        return var2;
    }

    private static String imeiCheck(String var0) {
        return null != var0 && var0.contains(":")?"":var0;
    }

    private static class CUIDInfo {
        public String deviceId;
        public String imei;
        public int version;
        private static final int VERSION = 2;

        private CUIDInfo() {
            this.version = 2;
        }

        public static DeviceId.CUIDInfo createFromJson(String var0) {
            if(TextUtils.isEmpty(var0)) {
                return null;
            } else {
                try {
                    JSONObject var1 = new JSONObject(var0);
                    String var2 = var1.getString("deviceid");
                    String var3 = var1.getString("imei");
                    int var4 = var1.getInt("ver");
                    if(!TextUtils.isEmpty(var2) && var3 != null) {
                        DeviceId.CUIDInfo var5 = new DeviceId.CUIDInfo();
                        var5.deviceId = var2;
                        var5.imei = var3;
                        var5.version = var4;
                        return var5;
                    }
                } catch (JSONException var6) {
                    DeviceId.handleThrowable(var6);
                }

                return null;
            }
        }

        public String toPersitString() {
            try {
                return (new JSONObject()).put("deviceid", this.deviceId).put("imei", this.imei).put("ver", this.version).toString();
            } catch (JSONException var2) {
                DeviceId.handleThrowable(var2);
                return null;
            }
        }

        public String getFinalCUID() {
            String var1 = this.imei;
            if(TextUtils.isEmpty(var1)) {
                var1 = "0";
            }

            StringBuffer var2 = new StringBuffer(var1);
            var1 = var2.reverse().toString();
            String var3 = this.deviceId + "|" + var1;
            return var3;
        }
    }

    private static class CUIDBuddyInfo {
        public ApplicationInfo appInfo;
        public int priority;
        public boolean sigMatched;
        public boolean isSelf;

        private CUIDBuddyInfo() {
            this.priority = 0;
            this.sigMatched = false;
            this.isSelf = false;
        }
    }
}
