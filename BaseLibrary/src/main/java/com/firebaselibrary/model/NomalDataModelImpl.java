package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.firebaselibrary.SettingsManager;
import com.firebaselibrary.bean.CarBean;
import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.ExpertBean;
import com.firebaselibrary.bean.LeaderBean;

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

public class NomalDataModelImpl implements NomalDataModel {

    private Context context;
    private String baseUrl;

    public NomalDataModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getExpertList(final ILoadingResultListener<List<ExpertBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
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
                .url(baseUrl + "/expert/list")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<List<ExpertBean>> expert = new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<ExpertBean>>>() {
//                                }.getType());
//
//                        if (expert.getIsSuccess()) {
//                            listener.success(expert.getResult());
//                        } else {
//                            listener.error(expert.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<ExpertBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), ExpertBean.class);
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
    public void getExpert(final ILoadingResultListener<ExpertBean> listener, String id) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/expert/get")
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

//                        IBaseResult<ExpertBean> versionBeanTResponse = new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<ExpertBean>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            ExpertBean expertBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), ExpertBean.class);
                            listener.success(expertBean);
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
    public void getLeaderList(final ILoadingResultListener<List<LeaderBean>> listener, String unitID, String pageIndex, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        if (!EmptyUtil.isEmpty(unitID))
            params.put("unit", unitID);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/unit/leader")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<List<LeaderBean>> versionBeanTResponse = new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<LeaderBean>>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<LeaderBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), LeaderBean.class);
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
    public void getCarList(final ILoadingResultListener<List<CarBean>> listener, String unitID, String pageIndex, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        if (!EmptyUtil.isEmpty(unitID))
            params.put("unit", unitID);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/unit/vehicle")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<List<CarBean>> versionBeanTResponse = new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<CarBean>>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<CarBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), CarBean.class);
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
}
