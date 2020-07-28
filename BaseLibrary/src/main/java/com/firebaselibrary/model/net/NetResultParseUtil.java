package com.firebaselibrary.model.net;


import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.model.ILoadingResultListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.baselibrary.baseutil.GsonUtil;

/**
 * Created by zhangmh on 2018/5/30.
 * <p>
 * 网络请求结果处理工具
 * 解析json
 */

public class NetResultParseUtil<T> {

    public void parseToBean(String jsonReuslt, ILoadingResultListener<T> listener) {
        if (listener == null) {
            return;
        }
        CommonResultBean commonResultBean = GsonUtil.json2Bean(jsonReuslt, CommonResultBean.class);
        if (commonResultBean.isSuccess()) {
            T t = new Gson().fromJson(GsonUtil.obj2String(commonResultBean.getResult()),
                    new TypeToken<T>() {
                    }.getType());
            listener.success(t);
        } else {
            listener.error(commonResultBean.getExceptionString());
        }
    }


}
