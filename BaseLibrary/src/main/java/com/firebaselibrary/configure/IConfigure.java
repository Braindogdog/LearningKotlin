package com.firebaselibrary.configure;

import android.view.View;

import common.map.api.IMapApiService;

/**
 * Created by chasen on 2018/4/9.
 * 配置方法定义接口
 */

public interface IConfigure {

    void initIOC();

    IMapApiService getMapService(View mapView, double currentMapX, double currentMapY, boolean openLoc);


}
