package com.firebaselibrary.model;

import android.support.annotation.Nullable;

import com.firebaselibrary.bean.LoginUser;
import com.firebaselibrary.bean.UserBean;

import java.util.List;

/**
 * Created by chasen on 2018/4/11.
 */

public interface UserModel {
    void userLogin(String userName, String password, ILoadingResultListener<LoginUser> listener);

    void resetPassword();

    void changeHeader();

    void getUserList(ILoadingResultListener<List<UserBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize);

    void getUser(ILoadingResultListener<UserBean> listener, String id);
}
