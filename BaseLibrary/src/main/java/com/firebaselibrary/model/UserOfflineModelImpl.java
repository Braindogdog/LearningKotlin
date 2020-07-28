package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.LoginUser;
import com.firebaselibrary.bean.UserBean;

import java.util.List;

import common.baselibrary.baseutil.GsonUtil;

/**
 * Created by chasen on 2018/4/11.
 */

public class UserOfflineModelImpl implements UserModel {
    private Context context;
    private String baseUrl;

    public UserOfflineModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void userLogin(String userName, String password, final ILoadingResultListener<LoginUser> listener) {
        String result = "{\"isSuccess\":true,\"result\":{\"id\":\"admin1\",\"name\":\"测试1\",\"unitId\":\"e23a1005fe7b45c5ade1d029d8e3ed\",\"unitName\":\"江西省公安消防总队\",\"divisionId\":\"360000\",\"divisionName\":\"江西省\",\"sysrole\":[]},\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            LoginUser loginUser = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), LoginUser.class);
            listener.success(loginUser);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void resetPassword() {

    }

    @Override
    public void changeHeader() {

    }

    @Override
    public void getUserList(final ILoadingResultListener<List<UserBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"id\":\"admin1\",\"name\":\"测试1\",\"unitId\":\"e23a1005fe7b45c5ade1d029d8e3ed\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},{\"id\":\"admin2\",\"name\":\"测试2\",\"unitId\":\"fdd5eced27624f7da0c2cfb7f\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},{\"id\":\"admin3\",\"name\":\"测试3\",\"unitId\":\"e23a1005fe7b45c5ade1d029d8e3ed\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},{\"id\":\"admin4\",\"name\":\"测试4\",\"unitId\":\"e23a1005fe7b45c5ade1d029d8e3ed\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},{\"id\":\"admin5\",\"name\":\"测试5\",\"unitId\":\"35d6383badb14e08ad0f64ce0a133e\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},{\"id\":\"admin6\",\"name\":\"测试6\",\"unitId\":\"3a3b796eed404f25a8891d9f069a09\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},{\"id\":\"admin7\",\"name\":\"测试7\",\"unitId\":\"e224927ddb514724b14083b389ccf9\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},{\"id\":\"admin8\",\"name\":\"测试8\",\"unitId\":\"d6087d87a1dd4f11b6c0bcc9350b49\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<UserBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), UserBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getUser(final ILoadingResultListener<UserBean> listener, String id) {
        String result = "{\"isSuccess\":true,\"result\":{\"id\":\"admin1\",\"name\":\"测试1\",\"unitId\":\"e23a1005fe7b45c5ade1d029d8e3ed\",\"divisionName\":\"未知地区\",\"sysrole\":[{\"disused\":false,\"timestamp\":\"Jun 20, 2018 9:56:55 AM\"}]},\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            UserBean detailBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), UserBean.class);
            listener.success(detailBean);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }
}
