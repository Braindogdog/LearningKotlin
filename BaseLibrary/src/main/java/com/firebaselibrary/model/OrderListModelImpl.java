package com.firebaselibrary.model;

import android.content.Context;

import com.krx.ydzh.commoncore.basequery.Conditions;
import com.krx.ydzh.commoncore.basequery.ConstantsBase;
import com.krx.ydzh.commoncore.basequery.CreOperType;
import com.krx.ydzh.commoncore.basequery.CreParamsList;
import com.krx.ydzh.commoncore.basequery.CreParamsObject;
import com.krx.ydzh.commoncore.basequery.CreSortType;
import com.krx.ydzh.commoncore.basequery.CreTableType;
import com.krx.ydzh.commoncore.basequery.CreWorkType;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.net.HttpUtils;
import com.krx.ydzh.commoncore.net.callback.OnResultCallBack;

import java.util.Map;

/**
 * created by Liang
 * on2020/6/3
 */
public class OrderListModelImpl implements OrderListModel {
    @Override
    public void getOrderList(Context context, String unitId, OnResultCallBack callback) {
        CreParamsList creParams = new CreParamsList("yjzh", "order", CreTableType.Table)
                .setOrder("SENDTIME")
                .setSort(CreSortType.ASC)
                .setPage(1,10)
                .addConditions(new Conditions("receiverUnitId", CreOperType.EQ, unitId));
        HttpUtils.getInstance().setBaseUrl(ConfigManager.getInstance().getNetConfig(com.firebaselibrary.constant.UrlConstant.API_BASE_CONSTANT) + "/")
                .creGetRequest(ConstantsBase.BASE_GETLIST, creParams.buildRequestParam(), callback);

    }

    @Override
    public void getTopList(Context context, String unitId, OnResultCallBack callback) {
        CreParamsList creParams = new CreParamsList("yjzh", "order_top",CreTableType.Table)
                .setOrder("OPERATIONTIME")
                .setSort(CreSortType.DESC)
                .setPage(1,10)
                .addConditions(new Conditions("receiverUnitId", CreOperType.EQ, unitId))
                .addConditions(new Conditions("isTop", CreOperType.EQ, "1"));

        HttpUtils.getInstance().setBaseUrl(ConfigManager.getInstance().getNetConfig(com.firebaselibrary.constant.UrlConstant.API_BASE_CONSTANT) + "/")
                .creGetRequest(ConstantsBase.BASE_GETLIST, creParams.buildRequestParam(), callback);
    }

    @Override
    public void getCollectList(Context context, String unitId, OnResultCallBack callback) {

        CreParamsList creParams = new CreParamsList("yjzh", "order_top", CreTableType.Table)
                .setOrder("OPERATIONTIME")
                .setSort(CreSortType.DESC)
                .setPage(1,10)
                .addConditions(new Conditions("receiverUnitId", CreOperType.EQ, unitId))
                .addConditions(new Conditions("isTop", CreOperType.EQ, "0"));
        HttpUtils.getInstance().setBaseUrl(ConfigManager.getInstance().getNetConfig(com.firebaselibrary.constant.UrlConstant.API_BASE_CONSTANT) + "/")
                .creGetRequest(ConstantsBase.BASE_GETLIST, creParams.buildRequestParam(), callback);
    }

    @Override
    public void setCollection(Context context, Map<String, Object> params, OnResultCallBack callback) {
        CreParamsObject creParams = new CreParamsObject("yjzh", "order_top", CreWorkType.Save, params);
        HttpUtils.getInstance().setBaseUrl(ConfigManager.getInstance().getNetConfig(com.firebaselibrary.constant.UrlConstant.API_BASE_CONSTANT) + "/")
                .creJsonRequest(ConstantsBase.BASE_EXECUTE, creParams, null, callback);
    }
}
