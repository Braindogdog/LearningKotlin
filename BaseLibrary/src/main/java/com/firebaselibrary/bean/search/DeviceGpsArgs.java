package com.firebaselibrary.bean.search;

import org.creation.common.models.BaseQuery;

import lombok.Data;


/**
 * 设备分布查询
 * @author zhangmh
 * @date 2020/5/24 0:58
 */
public class DeviceGpsArgs extends BaseQuery {

    /**
     * 中心点x
     */
    private double x;

    /**
     * 中心点y
     */
    private double y;

    /**
     * 半径
     */
    private int radius;


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
