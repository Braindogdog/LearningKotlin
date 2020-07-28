package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.GPSVM;
import com.firebaselibrary.constant.UrlConstant;
import com.krx.ydzh.commoncore.config.ConfigManager;

import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;

/**
 * Created by chasen on 2018/9/10.
 */

public class GpsModelOfflineImpl implements GpsModel {

    @Override
    public void uploadLocations(Context context, final ILoadingResultListener<Integer> listener, GPSVM gps) {
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.API_GPS_UP_URL)+"/api/v1" + "/gps/up")
//                .url("http://192.168.110.126:8082/gps/api/v1/gps/up")
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }
                        if ("1".equals(result.trim())) {
                            listener.success(1);
                        } else {
                            listener.error("上传失败");
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                })
                .postWithText(gps.getUploadString());
    }
}
