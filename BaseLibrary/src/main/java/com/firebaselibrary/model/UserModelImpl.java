package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseutil.GsonUtil;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;

import com.firebaselibrary.SettingsManager;
import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.LoginUser;
import com.firebaselibrary.bean.UserBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chasen on 2018/4/11.
 */

public class UserModelImpl implements UserModel {
    private Context context;
    private String baseUrl;

    public UserModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void userLogin(String userName, String password, final ILoadingResultListener<LoginUser> listener) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/user/login")
                .addParam("division", SettingsManager.getDivision(context))
                .addParam("appId", SettingsManager.getAppId(context))
                .addParam("loginid", userName)
                .addParam("pwd", password)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
//                        IBaseResult<LoginUser> userIBaseResult =new Gson().fromJson(result,
//                                new TypeToken<IBaseResult<LoginUser>>() {
//                                }.getType());
//
//                        if (userIBaseResult.getIsSuccess()) {
//                            listener.success(userIBaseResult.getResult());
//                        } else {
//                            listener.error(userIBaseResult.getExceptionString());
//                        }
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            LoginUser loginUser = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), LoginUser.class);
                            listener.success(loginUser);
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
    public void resetPassword() {

    }

    @Override
    public void changeHeader() {

    }

    @Override
    public void getUserList(final ILoadingResultListener<List<UserBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("division", SettingsManager.getDivision(context));
        params.put("appId", SettingsManager.getAppId(context));
        params.put("loginid", SettingsManager.getUserId(context));
        if (!EmptyUtil.isEmpty(name))
            params.put("name", name);
        if (!EmptyUtil.isEmpty(unit))
            params.put("unit", unit);
        new HttpUtil.Builder(context)
                .url(baseUrl + "/user/list")
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            List<UserBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), UserBean.class);
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
    public void getUser(final ILoadingResultListener<UserBean> listener, String id) {
        new HttpUtil.Builder(context)
                .url(baseUrl + "/user/get")
                .addParam("division", SettingsManager.getDivision(context))
                .addParam("appId", SettingsManager.getAppId(context))
                .addParam("loginid", SettingsManager.getUserId(context))
                .addParam("id", id)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                        if (resultBean.isSuccess()) {
                            UserBean detailBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), UserBean.class);
                            listener.success(detailBean);
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
