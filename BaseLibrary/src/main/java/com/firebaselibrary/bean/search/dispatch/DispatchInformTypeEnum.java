package com.firebaselibrary.bean.search.dispatch;

/**
 * 通知类型
 *
 * @author zhangmh
 * @date 2020/3/23 11:58
 */
public enum DispatchInformTypeEnum {

    /**
     * 任务通知
     */
    cmd("cmd"),

    /**
     * 短信通知
     */
    sms("sms");

    private String value;

    DispatchInformTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
