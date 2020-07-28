//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.baidu.android.bbalbs.common.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.android.bbalbs.common.security.MD5Util;
import java.util.Iterator;
import java.util.List;

public final class Util {
    private Util() {
    }

    public static String toMd5(byte[] var0, boolean var1) {
        return MD5Util.toMd5(var0, var1);
    }

    public static String toHexString(byte[] var0, String var1, boolean var2) {
        return MD5Util.toHexString(var0, var1, var2);
    }

    public static boolean hasOtherServiceRuninMyPid(Context var0, String var1) {
        ActivityManager var2 = (ActivityManager)var0.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List var3 = var2.getRunningServices(100);
        Iterator var4 = var3.iterator();

        RunningServiceInfo var5;
        do {
            if(!var4.hasNext()) {
                return false;
            }

            var5 = (RunningServiceInfo)var4.next();
        } while(var5.pid != Process.myPid() || TextUtils.equals(var5.service.getClassName(), var1));

        return true;
    }
}
