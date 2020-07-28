package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.firebaselibrary.SettingsManager;
import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.SimpUnit;
import com.firebaselibrary.bean.UnitQueryArg;
import com.firebaselibrary.bean.fireunit.FireUnitBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseutil.GsonUtil;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;

/**
 * Created by chasen on 2018/5/21.
 */

public class FireUnitModelImpl implements FireUnitModel {

    private Context context;
    private String baseUrl;

    public FireUnitModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getFireUnitList(final ILoadingResultListener<List<FireUnitBean>> listener, @Nullable String shortName, @Nullable String parent, String pageIndex, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        if (!EmptyUtil.isEmpty(shortName))
            params.put("shortName", shortName);
        if (!EmptyUtil.isEmpty(parent))
            params.put("parent", parent);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/unit/list")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<List<FireUnitBean>> fireUnit =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<FireUnitBean>>>() {
//                                }.getType());
//
//                        if (fireUnit.getIsSuccess()) {
//                            listener.success(fireUnit.getResult());
//                        } else {
//                            listener.error(fireUnit.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<FireUnitBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), FireUnitBean.class);
                            listener.success(list);
                        } else {
                            listener.error(resultBean.getExceptionString());
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                })
                .get();
    }

    @Override
    public void getFireUnit(final ILoadingResultListener<FireUnitBean> listener, String id) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/unit/get")
                .addParam("division", SettingsManager.getDivision(context))
                .addParam("appId", SettingsManager.getAppId(context))
                .addParam("loginid", SettingsManager.getUserId(context))
                .addParam("id", id)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<FireUnitBean> fireUnit =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<FireUnitBean>>() {
//                                }.getType());
//
//                        if (fireUnit.getIsSuccess()) {
//                            listener.success(fireUnit.getResult());
//                        } else {
//                            listener.error(fireUnit.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            FireUnitBean fireUnitBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), FireUnitBean.class);
                            listener.success(fireUnitBean);
                        } else {
                            listener.error(resultBean.getExceptionString());
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                })
                .get();
    }

    @Override
    public void getUnitList(ILoadingResultListener<List<SimpUnit>> listener, UnitQueryArg args) {

    }
}
