package common.baidunavigationlibrary;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.creation.common.geometry.GeoPoint;

import common.map.api.utils.LocationUtils;

/**
 * Created by MHshachang on 2016/11/28.
 */
public class CommonLocationService extends Service {
    private static int LOCATION_INTERVAL = 1000 * 30;
    private LocationClient locationClient = null;
    private BDLocationListener locationListener;
    private OnGetLocationListener getLocationListener;
    private LocationClientOption option;
    private Context mContext;

    //静止，2分钟
    private static final int INTERVAL_STATIC = 1000 * 60 * 2;
    //步行 半分钟
    private static final int INTERVAL_WALK = 1000 * 30;
    //开车 10秒  实际测试间隔 20秒
    private static final int INTERVAL_DRIVE = 1000 * 10;
    //开快车  5秒   没有测试过
    private static final int INTERVAL_DRIVE_FAST = 1000 * 5;

    //上一次定到位置的时间
    private long lastTime;
    //与上次定到位置的时间间隔
    private static final int INTERVAL_LAST_LOCATION = 1000 * 60 * 5;
    //    上次算出来的时间间隔
    private int lastReckonInterval = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public interface OnGetLocationListener {
        //定位信息
        void getLocation(GeoPoint loc, float speed);
    }

    public void setGetLocationListener(OnGetLocationListener getLocationListener) {
        this.getLocationListener = getLocationListener;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void initLocation() {
        locationListener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {

                if (bdLocation == null || !LocationUtils.checkLocation(bdLocation.getLongitude(), bdLocation.getLatitude())) {
                    if (System.currentTimeMillis() - lastTime > INTERVAL_LAST_LOCATION) {
                        stopLocation();
                        startLocation();
                    }
                    return;
                }
                if (System.currentTimeMillis() - lastTime > 3 * 1000) {
                    lastTime = System.currentTimeMillis();
                    if (getLocationListener != null) {
                        LocationUtils.Gps gps = LocationUtils.bd09_To_Gps84(bdLocation.getLatitude(), bdLocation.getLongitude());
                        getLocationListener.getLocation(new GeoPoint(gps.getWgLon(), gps.getWgLat()), bdLocation.getSpeed());
                        setIntervalBySpeed(bdLocation.getSpeed());
                    }
                }
            }
        };

        startLocation();
    }

    private void setIntervalBySpeed(float speed) {

        if (speed < 3) {//基本静止   3km/h = 0.83m/s  那么2分钟100米
            resetOption(INTERVAL_STATIC);
        } else if (speed < 12) {//步行   11.88km/h = 3.3m/s  那么30秒100米
            resetOption(INTERVAL_WALK);
        } else if (speed < 70) {//开慢车    70km/h = 20m/s  那么5秒100米
            resetOption(INTERVAL_DRIVE);
        } else {//开快车
            resetOption(INTERVAL_DRIVE_FAST);
        }
    }

    private void resetOption(int intervalTime) {
        if (LOCATION_INTERVAL != intervalTime) {
            if (LOCATION_INTERVAL < intervalTime && lastReckonInterval != intervalTime) {
                lastReckonInterval = intervalTime;
                return;
            }
            LOCATION_INTERVAL = intervalTime;
            locationClient.stop();
            option.setScanSpan(LOCATION_INTERVAL);
            locationClient.setLocOption(option);
            locationClient.start();
        }
    }


    private void startLocation() {
        lastTime = System.currentTimeMillis();

        locationClient = new LocationClient(mContext);

        locationClient.registerLocationListener(locationListener);

        option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setCoorType("bd09ll");
        //设置定时定位的时间间隔。单位毫秒
        option.setScanSpan(LOCATION_INTERVAL);
        // 开启GPS定位
        option.setOpenGps(true);
        option.SetIgnoreCacheException(true);
        locationClient.setLocOption(option);
        locationClient.start();
    }

    private void stopLocation() {
        if (locationClient != null && locationClient.isStarted()) {
            locationClient.unRegisterLocationListener(locationListener);
            locationClient.stop();
            locationClient = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocation();
    }

    public class LocationBinder extends android.os.Binder {

        public CommonLocationService getMyService() {
            mContext = CommonLocationService.this;
            initLocation();
            return CommonLocationService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocationBinder();
    }
}
