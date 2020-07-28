package com.firebaselibrary.bean.search;

import lombok.Data;

import java.io.Serializable;

/**
 * 手机gps
 * @author zhangmh
 * @date 2020/5/24 0:52
 */
@Data
public class DeviceGps implements Serializable {

    /**
     * 设备id
     */
    private String deviceid;
    /**
     * 名称
     * 绑定账号
     */
    private String account;
    /**
     * 名称
     * 登录人员名称
     */
    private String name;

    /**
     * 经度
     */
    private double x;
    /**
     * 纬度
     */
    private double y;

}
