package com.firebaselibrary.model;

/**
 * Created by chasen on 2018/4/11.
 */

public interface ILoadingResultListener<T> {
    void success(T result);

    void error(String error);

    void progress(double progress);
}
