package com.firebaselibrary.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.firebaselibrary.bean.KeyUnitSimpleBean;
import com.firebaselibrary.bean.StationBean;
import com.firebaselibrary.bean.WaterBean;

import java.util.List;

/**
 * Created by chasen on 2018/5/21.
 */

public interface CollectDataModel {
    //获取重点单位列表
    void getKeyUnitList(ILoadingResultListener<List<KeyUnitSimpleBean>> listener, @Nullable String name, @Nullable String unit, @NonNull String pageIndex, @NonNull String pageSize);

    //获取重点单位
    void getKeyUnit();

    //创建重点单位
    void createKeyUnit();

    //获取水源列表
    void getWaterList(ILoadingResultListener<List<WaterBean>> listener, @Nullable String name, @Nullable String unit, @NonNull String pageIndex, @NonNull String pageSize);

    //获取水源
    void getWater(ILoadingResultListener<WaterBean> listener, String id);

    //创建水源
    void createWater();

    //获取微型消防站列表
    void getFireStationList(ILoadingResultListener<List<StationBean>> listener, @Nullable String name, @Nullable String unit, @NonNull String pageIndex, @NonNull String pageSize);

    //获取微型消防站
    void getFireStation(ILoadingResultListener<StationBean> listener, String id);

    //创建微型消防站
    void createFireStation();

}
