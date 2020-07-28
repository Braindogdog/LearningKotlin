package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.search.query.ElinkGroupArg;
import com.krx.ydzh.commoncore.net.callback.OnResultCreObjectCallBack;
import com.krx.ydzh.commoncore.net.callback.OnResultStringCallBack;

/**
 * elink 讨论组
 * Created by zhangmh on 2020/6/4.
 */
public interface ElinkModel {

    /**
     * 创建讨论组
     */
    void addGroup(Context context, OnResultStringCallBack callback, ElinkGroupArg args);


}
