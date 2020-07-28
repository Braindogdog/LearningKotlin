package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.firebaselibrary.SettingsManager;
import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.KeyUnitSimpleBean;
import com.firebaselibrary.bean.StationBean;
import com.firebaselibrary.bean.WaterBean;

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

public class CollectDataModelImpl implements CollectDataModel {
    private Context context;
    private String baseUrl;

    public CollectDataModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getKeyUnitList(final ILoadingResultListener<List<KeyUnitSimpleBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        if (!EmptyUtil.isEmpty(name))
            params.put("name", name);
        if (!EmptyUtil.isEmpty(unit))
            params.put("unit", unit);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/unit/importance")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<List<KeyUnitSimpleBean>> versionBeanTResponse =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<KeyUnitSimpleBean>>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<KeyUnitSimpleBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), KeyUnitSimpleBean.class);
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
    public void getKeyUnit() {

    }

    @Override
    public void createKeyUnit() {

    }

    @Override
    public void getWaterList(final ILoadingResultListener<List<WaterBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        if (!EmptyUtil.isEmpty(name))
            params.put("name", name);
        if (!EmptyUtil.isEmpty(unit))
            params.put("unit", unit);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/water/list")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
//                        IBaseResult<List<WaterBean>> versionBeanTResponse =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<WaterBean>>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<WaterBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), WaterBean.class);
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
    public void getWater(final ILoadingResultListener<WaterBean> listener, String id) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/water/get")
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

//                        IBaseResult<WaterBean> versionBeanTResponse =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<WaterBean>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            WaterBean waterBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), WaterBean.class);
                            listener.success(waterBean);
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
    public void createWater() {

    }

    @Override
    public void getFireStationList(final ILoadingResultListener<List<StationBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        if (!EmptyUtil.isEmpty(name))
            params.put("name", name);
        if (!EmptyUtil.isEmpty(unit))
            params.put("unit", unit);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/station/list")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<List<StationBean>> versionBeanTResponse =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<StationBean>>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<StationBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), StationBean.class);
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
    public void getFireStation(final ILoadingResultListener<StationBean> listener, String id) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/station/get")
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

//                        IBaseResult<StationBean> versionBeanTResponse =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<StationBean>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            StationBean stationBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), StationBean.class);
                            listener.success(stationBean);
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
    public void createFireStation() {

    }
}
