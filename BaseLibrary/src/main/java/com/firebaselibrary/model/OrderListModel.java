package com.firebaselibrary.model;

import android.content.Context;

import com.krx.ydzh.commoncore.net.callback.OnResultCallBack;

import java.util.Map;

/**
 * created by Liang
 * on2020/6/3
 */
public interface OrderListModel {

    void getOrderList(Context context,String unitId, OnResultCallBack callback);
    void getTopList(Context context,String unitId, OnResultCallBack callback);
    void getCollectList(Context context,String unitId, OnResultCallBack callback);
    void setCollection(Context context, Map<String, Object> params, OnResultCallBack callback);
}
