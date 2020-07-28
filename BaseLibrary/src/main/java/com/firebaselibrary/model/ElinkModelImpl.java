package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.search.query.ElinkGroupArg;
import com.firebaselibrary.constant.UrlConstant;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.net.HttpUtils;
import com.krx.ydzh.commoncore.net.callback.OnResultStringCallBack;

/**
 * elink
 * Created by zhangmh on 2020/6/4.
 */
public class ElinkModelImpl implements ElinkModel {



    @Override
    public void addGroup(Context context, OnResultStringCallBack callback, ElinkGroupArg args) {
        new HttpUtils().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.ELINK_ADD_GROUP, args, callback);

    }


    private String getBaseUrl() {
//        return "http://192.168.0.104:8081/pmweb/";
        return ConfigManager.getInstance().getNetConfig(UrlConstant.API_COMMAND_RES_BASE_URL);
    }

}
