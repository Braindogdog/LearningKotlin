package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.search.LivingVideo;
import com.firebaselibrary.bean.search.LivingVideoRecord;
import com.firebaselibrary.bean.search.query.LiveArgs;
import com.krx.ydzh.commoncore.net.callback.OnResultBootListCallBack;
import com.krx.ydzh.commoncore.net.callback.OnResultBootObjectCallBack;

import java.util.List;

/**
 * 直播
 * Created by zhangmh on 2020/5/29.
 */
public interface LiveModel {

    /**
     * 开始录制
     */
    void startRecord(Context context, OnResultBootObjectCallBack<Boolean> callback, String videoId);

    /**
     * 停止录制
     */
    void stopRecord(Context context, OnResultBootObjectCallBack<Boolean> callback, String videoId);

    /**
     * 开启直播
     */
    void startLive(Context context, OnResultBootObjectCallBack<Boolean> callback, LiveArgs args);

    /**
     * 直播列表
     */
    void listLive(Context context, OnResultBootListCallBack<List<LivingVideo>> callback, LiveArgs args);

    /**
     * 直播录像列表
     */
    void listRecord(Context context, OnResultBootListCallBack<List<LivingVideoRecord>> callback, LiveArgs args);
}
