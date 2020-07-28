package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.GPSVM;

/**
 * Created by chasen on 2018/9/10.
 */

public interface GpsModel {
    void uploadLocations(Context context, ILoadingResultListener<Integer> listener, GPSVM gps);
}
