package common.baselibrary.baseutil;

import android.Manifest;
import android.app.*;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by chasen on 2018/3/26.
 */

public class SystemUtil {
    private static final int NETWORK_TYPE_NONE = 0;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_MOBILE = 2;

    public static Bundle getApplicationMetaData(Context context) {

        Bundle metaData = null;
        try {
            metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            metaData = new Bundle();
        }

        return metaData;
    }

    public static int getNetworkType(Context context) {

        int type = NETWORK_TYPE_NONE;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null) {
            return type;
        }

        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_TYPE_WIFI;
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            return NETWORK_TYPE_MOBILE;
        }

        return type;
    }

    public static final boolean isNetUrl(String url) {
        boolean result = false;
        if (url.indexOf("http://") >= 0) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void hideSoftInputFromWindow(Activity activity) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }


    public static void showSoftInputFromWindow(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    public static String getPackageName(Context context) {
        String packageName = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            packageName = info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return packageName;
    }

    public static int getVersionCode(Context context) {

        int versionCode = 0;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return versionCode;
    }

    public static String getDeviceId(Activity context) {
        PermissionUtils utils = new PermissionUtils(context);
        utils.checkPermission(Manifest.permission.READ_PHONE_STATE);
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String deviceId = tm.getDeviceId();

        if (EmptyUtil.isEmpty(deviceId)) {

            return getSerialNumber();
        } else {
            return deviceId;
        }
    }

    public static String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }

    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static void exit() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void callPhone(Context context, String number) {
        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + number));
        phoneIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(phoneIntent);
    }

    public static void sendSMS(Context context, String phoneNumber, String message) {

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", message);
        context.startActivity(intent);

    }

    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }


    public static boolean isServiceWork(Context mContext, String serviceName) {

        boolean isWork = false;
        android.app.ActivityManager myAM = (android.app.ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(100);

        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }


    public static String getSystemMetrics(Context context) {
        String sysMetrics = null;

        int screen_w = 0;
        int screen_h = 0;

        int ver = Build.VERSION.SDK_INT;

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm
                .getDefaultDisplay();
        display.getMetrics(dm);
        screen_w = dm.widthPixels;
        if (ver < 13) {
            screen_h = dm.heightPixels;

            int temp;
            if (screen_w > screen_h) {
                temp = screen_w;
                screen_w = screen_h;
                screen_h = temp;
            }

            if (screen_w >= 480 && screen_h >= 800) {
                sysMetrics = "h";
            } else {
                sysMetrics = "l";
            }

        } else if (ver >= 13) {
            String dpi = null;
            @SuppressWarnings("rawtypes")
            Class c;
            try {
                c = Class.forName("android.view.Display");
                @SuppressWarnings("unchecked")
                Method method = c.getMethod("getRealMetrics",
                        DisplayMetrics.class);
                method.invoke(display, dm);

                int temp;
                if (dm.widthPixels > dm.heightPixels) {
                    temp = dm.widthPixels;
                    dm.widthPixels = dm.heightPixels;
                    dm.heightPixels = temp;
                }
                if (dm.widthPixels >= 480 && dm.heightPixels >= 800) {
                    sysMetrics = "h";
                } else {
                    sysMetrics = "l";
                }

                // dpi = dm.widthPixels + "_" + dm.heightPixels;
                // sysMetrics = dpi;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sysMetrics;
    }

}
