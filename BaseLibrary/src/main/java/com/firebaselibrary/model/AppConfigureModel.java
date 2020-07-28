package com.firebaselibrary.model;

import com.firebaselibrary.bean.Dictionary;
import com.firebaselibrary.bean.DictionaryQuery;
import com.firebaselibrary.bean.SimpleDictionary;
import com.firebaselibrary.bean.VersionBean;

import java.util.List;

/**
 * Created by chasen on 2018/5/9.
 */

public interface AppConfigureModel {
    //检查更新
    void checkUpdate(ILoadingResultListener<VersionBean> listener);

    //获取配置信息
    void getSystemConfigure();

    //上传终端崩溃信息
    void uploadMobileError();

    //获取字典详情
    void getDictionary(ILoadingResultListener<Dictionary> listener,String code, String scope);

    //获取字典列表呢
    void getDictionaryList(ILoadingResultListener<List<Dictionary>> listener);

    //搜索字典
    void searchDictionary(ILoadingResultListener<List<SimpleDictionary>> listener, DictionaryQuery args);

}
