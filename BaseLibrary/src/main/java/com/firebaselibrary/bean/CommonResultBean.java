package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/4/11.
 */

public class CommonResultBean {
    private boolean isSuccess;
    private Object result;
    private String exceptionString;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }
}
