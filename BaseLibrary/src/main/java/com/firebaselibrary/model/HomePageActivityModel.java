package com.firebaselibrary.model;

/**
 * Created by chasen on 2018/4/4.
 * 首页activity数据操作
 */

public interface HomePageActivityModel {
    //获取消防单位列表
    void getFireUnitList();

    //获取重点单位列表
    void getKeyUnitList();

    //获取水源列表
    void getWaterList();

    //获取微型消防站列表
    void getFireStationList();

}
