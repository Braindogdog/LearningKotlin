package com.firebaselibrary.bean;

import org.greenrobot.greendao.annotation.Entity;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 通信保障
 */
@Entity
public class AreaBean {
    @Id
    private long code;
    private String value;
    @Generated(hash = 1598343157)
    public AreaBean(long code, String value) {
        this.code = code;
        this.value = value;
    }
    @Generated(hash = 1823161578)
    public AreaBean() {
    }
    public String getPickerViewText(){
        return value;
    }
    public long getCode() {
        return this.code;
    }
    public void setCode(long code) {
        this.code = code;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
