package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.search.LivingVideo;
import com.firebaselibrary.bean.search.LivingVideoRecord;
import com.firebaselibrary.bean.search.query.LiveArgs;
import com.firebaselibrary.constant.UrlConstant;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.net.HttpUtils;
import com.krx.ydzh.commoncore.net.callback.OnResultBootListCallBack;
import com.krx.ydzh.commoncore.net.callback.OnResultBootObjectCallBack;

import java.util.List;

/**
 * 直播
 * Created by zhangmh on 2020/5/29.
 */
public class LiveModelImpl implements LiveModel {


    @Override
    public void startRecord(Context context, OnResultBootObjectCallBack<Boolean> callback, String videoId) {
        new HttpUtils().setBaseUrl(getBaseUrl())
                .getRequest(UrlConstant.LIVE_RECORD_START + videoId, callback);
    }

    @Override
    public void stopRecord(Context context, OnResultBootObjectCallBack<Boolean> callback, String videoId) {
        new HttpUtils().setBaseUrl(getBaseUrl())
                .getRequest(UrlConstant.LIVE_RECORD_STOP + videoId, callback);
    }

    @Override
    public void startLive(Context context, OnResultBootObjectCallBack<Boolean> callback, LiveArgs args) {
        new HttpUtils().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.LIVE_START, args, callback);
    }

    @Override
    public void listLive(Context context, OnResultBootListCallBack<List<LivingVideo>> callback, LiveArgs args) {
        new HttpUtils().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.LIVE_LIST, args, callback);
    }

    @Override
    public void listRecord(Context context, OnResultBootListCallBack<List<LivingVideoRecord>> callback, LiveArgs args) {
        new HttpUtils().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.LIVE_RECORD_LIST, args, callback);
    }


    private String getBaseUrl() {
//        return "http://192.168.110.173:8081/emergency/";
//        return "http://192.168.0.102:8081/emergency/";
        return ConfigManager.getInstance().getNetConfig(UrlConstant.API_EMERGENCY_SERVER_URL);

    }
}
