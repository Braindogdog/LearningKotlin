package com.firebaselibrary.model;

import android.support.annotation.Nullable;

import com.firebaselibrary.bean.CarBean;
import com.firebaselibrary.bean.ExpertBean;
import com.firebaselibrary.bean.LeaderBean;

import java.util.List;

/**
 * Created by chasen on 2018/5/21.
 */

public interface NomalDataModel {
    //获取专家列表,name和unit用来搜索，可以为空
    void getExpertList(ILoadingResultListener<List<ExpertBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize);

    //获取专家详细信息
    void getExpert(ILoadingResultListener<ExpertBean> listener, String id);

    //获取领导列表
    void getLeaderList(ILoadingResultListener<List<LeaderBean>> listener, String unitID, String pageIndex, String pageSize);

    //获取车辆信息
    void getCarList(ILoadingResultListener<List<CarBean>> listener, String unitID, String pageIndex, String pageSize);
}
