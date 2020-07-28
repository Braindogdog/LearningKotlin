package com.firebaselibrary.configure;

import android.content.Context;
import android.view.View;

import common.map.api.IMapApiService;

/**
 * Created by chasen on 2018/4/9.
 * 配置文件工具类，app中所有涉及到不同版本配置的方法都集中到这里实现
 */

public class ConfigureUtil {
    private static IConfigure iConfigure;

    public static void init(Context context, String versionName) {
        if ("JiangXi".equals(versionName)) {
            iConfigure = new JiangXiConfigure(context);
        } else if ("Offline".equals(versionName)) {
            iConfigure = new OfflineConfigure(context);
        } else {
            iConfigure = new JiangXiConfigure(context);
        }
    }

    public static void initIOC(){
        iConfigure.initIOC();
    }

    public static IMapApiService getMapService(View mapView, double currentMapX, double currentMapY, boolean openLoc) {
        return iConfigure.getMapService(mapView, currentMapX, currentMapY, openLoc);
    }

}
