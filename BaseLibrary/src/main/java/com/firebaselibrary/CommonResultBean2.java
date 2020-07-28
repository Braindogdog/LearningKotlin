package com.firebaselibrary;

/**
 * Created by chasen on 2018/4/11.
 */

public class CommonResultBean2<T> {
    private boolean isSuccess;
    private T result;
    private String exceptionString;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }
}
