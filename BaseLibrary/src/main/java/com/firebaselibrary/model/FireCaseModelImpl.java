package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebaselibrary.SettingsManager;
import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.FireCaseBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.baselibrary.baseutil.GsonUtil;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;

/**
 * Created by chasen on 2018/4/4.
 */

public class FireCaseModelImpl implements FireCaseModel {

    private Context context;
    private String baseUrl;

    public FireCaseModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getFireCaseList(final ILoadingResultListener<List<FireCaseBean>> listener, @NonNull String pageIndex, @NonNull String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/case/list")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }

//                        IBaseResult<List<FireCaseBean>> fireCase =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<List<FireCaseBean>>>() {
//                                }.getType());
//
//                        if (fireCase.getIsSuccess()) {
//                            listener.success(fireCase.getResult());
//                        } else {
//                            listener.error(fireCase.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<FireCaseBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), FireCaseBean.class);
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
                .post();
    }

    @Override
    public void getFireCaseByID(final ILoadingResultListener<FireCaseBean> listener, @NonNull String id) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/case/get")
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

//                        IBaseResult<FireCaseBean> fireCase =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<FireCaseBean>>() {
//                                }.getType());
//
//                        if (fireCase.getIsSuccess()) {
//                            listener.success(fireCase.getResult());
//                        } else {
//                            listener.error(fireCase.getExceptionString());
//                        }

                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            FireCaseBean fireCaseBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), FireCaseBean.class);
                            listener.success(fireCaseBean);
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
