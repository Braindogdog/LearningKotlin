package com.firebaselibrary.model;

import android.support.annotation.NonNull;

import com.firebaselibrary.bean.FireCaseBean;

import java.util.List;

/**
 * Created by chasen on 2018/4/4.
 * 警情数据获取model
 */

public interface FireCaseModel {
    void getFireCaseList(ILoadingResultListener<List<FireCaseBean>> listener, @NonNull String pageIndex, @NonNull String pageSize);

    void getFireCaseByID(ILoadingResultListener<FireCaseBean> listener, @NonNull String id);
}
