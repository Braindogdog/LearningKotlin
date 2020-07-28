package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.SettingsManager;
import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.Dictionary;
import com.firebaselibrary.bean.DictionaryQuery;
import com.firebaselibrary.bean.SimpleDictionary;
import com.firebaselibrary.bean.VersionBean;
import com.firebaselibrary.constant.UrlConstant;
import com.google.gson.reflect.TypeToken;
import com.krx.ydzh.commoncore.config.ConfigManager;

import java.util.HashMap;
import java.util.List;

import common.baselibrary.baseutil.GsonUtil;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;
import common.networkrequestlibrary.util.NewDBResult;

/**
 * Created by chasen on 2018/5/9.
 */

public class AppConfigureModelImpl implements AppConfigureModel {

    private Context context;
    private String baseUrl;

    public AppConfigureModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void checkUpdate(final ILoadingResultListener<VersionBean> listener) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/version/new")
                .addParam("division", SettingsManager.getDivision(context))
                .addParam("appId", SettingsManager.getAppId(context))
                .addParam("loginid", SettingsManager.getUserId(context))
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<VersionBean> versionBeanTResponse = new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<VersionBean>>() {
//                                }.getType());
//
//                        if (versionBeanTResponse.getIsSuccess()) {
//                            listener.success(versionBeanTResponse.getResult());
//                        } else {
//                            listener.error(versionBeanTResponse.getExceptionString());
//                        }
                        try {
                            CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                            if (resultBean.isSuccess()) {
                                VersionBean version = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), VersionBean.class);
                                listener.success(version);
                            } else {
                                listener.error(resultBean.getExceptionString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.success(new VersionBean());
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
    public void getSystemConfigure() {

    }

    @Override
    public void uploadMobileError() {

    }

    @Override
    public void getDictionary(final ILoadingResultListener<Dictionary> listener, String code, String scope) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("scope", scope);
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.CASE_BASE_URL) + "/emergency/api/v1/dictionary/one")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }
                        NewDBResult<Dictionary> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<NewDBResult<Dictionary>>() {
                                }.getType());
                        listener.success(resRsp.getResult());
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getDictionaryList(final ILoadingResultListener<List<Dictionary>> listener) {
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.CASE_BASE_URL) + "/emergency/api/v1/dictionary/list/all")
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }
                        NewDBResult<List<Dictionary>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<NewDBResult<List<Dictionary>>>() {
                                }.getType());
                        listener.success(resRsp.getResult());
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void searchDictionary(final ILoadingResultListener<List<SimpleDictionary>> listener, DictionaryQuery args) {
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.CASE_BASE_URL) + "/emergency/api/v1/dictionary/list/search")
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {

                        if (listener == null) {
                            return;
                        }
                        NewDBResult<List<SimpleDictionary>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<NewDBResult<List<SimpleDictionary>>>() {
                                }.getType());
                        listener.success(resRsp.getResult());
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).postWithJson(GsonUtil.obj2String(args));
    }
}
