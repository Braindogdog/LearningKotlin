package com.firebaselibrary.model;

import android.support.annotation.Nullable;

import com.firebaselibrary.bean.SimpUnit;
import com.firebaselibrary.bean.UnitQueryArg;
import com.firebaselibrary.bean.fireunit.FireUnitBean;

import java.util.List;

/**
 * Created by chasen on 2018/5/21.
 */

public interface FireUnitModel {
    //获取消防单位列表
    void getFireUnitList(ILoadingResultListener<List<FireUnitBean>> listener, @Nullable String shortName, @Nullable String parent, String pageIndex, String pageSize);

    //获取消防单位
    void getFireUnit(ILoadingResultListener<FireUnitBean> listener, String id);

    //获取消防单位列表
    void getUnitList(ILoadingResultListener<List<SimpUnit>> listener, UnitQueryArg args);
}
