package common.baidunavigationlibrary.factoryImpl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import org.creation.common.geometry.GeoPoint;

import common.baidunavigationlibrary.CommonLocationService;
import common.map.api.factory.IUpdateLocationFactory;
import common.map.api.utils.Utils;

/**
 * Created by MHshachang on 2017/4/11.
 */
public class BaiduUpdateLocationImpl implements IUpdateLocationFactory, ServiceConnection,CommonLocationService.OnGetLocationListener {
    private OnUpdateLocationListener onUpdateLocationListener;

    @Override
    public boolean startLocationService(Context context) {
        if (!Utils.isOpenGPS(context)) {
            Utils.openGPS(context);
            return false;
        } else {
            if (!Utils.isServiceStart(context, CommonLocationService.class.getName())) {
                context.getApplicationContext().bindService(new Intent(context.getApplicationContext(), CommonLocationService.class), this, Context.BIND_AUTO_CREATE);
            }
            return true;
        }
    }

    @Override
    public void stopLocationService(Context context) {
        if (Utils.isServiceStart(context, CommonLocationService.class.getName())) {
            context.getApplicationContext().unbindService(this);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        CommonLocationService.LocationBinder binder = (CommonLocationService.LocationBinder) service;
        binder.getMyService().setGetLocationListener(this);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e("tag", "onServiceDisconnected");
    }

    @Override
    public void getLocation(GeoPoint loc, float speed) {
        if(onUpdateLocationListener != null){
            onUpdateLocationListener.updateLocation(loc,speed);
        }
    }

    public interface OnUpdateLocationListener {
        //定位信息
        void updateLocation(GeoPoint loc, float speed);
    }

    public void setOnUpdateLocationListener(OnUpdateLocationListener onUpdateLocationListener) {
        this.onUpdateLocationListener = onUpdateLocationListener;
    }

    private static class SingletonHolder {
        private static BaiduUpdateLocationImpl instance = new BaiduUpdateLocationImpl();
    }

    private BaiduUpdateLocationImpl() {
    }

    public static BaiduUpdateLocationImpl getInstance() {
        return SingletonHolder.instance;
    }
}
