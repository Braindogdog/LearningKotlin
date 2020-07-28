package common.map.api.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by zhangmh on 2018/3/12.
 * 距离计算
 */

public class Utils {

    /**
     * 判断是否已经开启GPS
     * @param context
     * @return
     */
    public static final boolean isOpenGPS(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 开启GPS
     * @param context
     * @return
     */
    public static final void openGPS(Context context) {
//        Intent GPSIntent = new Intent();
//        GPSIntent.setClassName("com.android.settings",
//                "com.android.settings.widget.SettingsAppWidgetProvider");
//        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
//        GPSIntent.setData(Uri.parse("custom:3"));
//        try {
//            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }


        Intent intent = new Intent(
                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent); // 设置完成后返回到原来的界面
    }

    /**
     * 判断service是否已经开启
     * @param context
     * @param serviceFullName service全称
     * @return
     */
    public static boolean isServiceStart(Context context,String serviceFullName) {
        ActivityManager myManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(Integer.MAX_VALUE);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals(serviceFullName)) {
                return true;
            }
        }
        return false;
    }
	
	
	

	
	
	
	
}
