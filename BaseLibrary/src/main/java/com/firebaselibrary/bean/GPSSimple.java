package com.firebaselibrary.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class GPSSimple implements Serializable {

    /**
     * 经度
     */
    private double x;
    /**
     * 纬度
     */
    private double y;
    /**
     * 纬度
     */
    private double speed;
    /**
     * 时间
     * 格式
     * 20180910133120
     */
    private Date time;

    public GPSSimple(double x, double y, double speed, Date time) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.time = time;
    }

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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNoSpeedStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return x + "," + y + "," + format.format(time);
    }

    public String getHasSpeedStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return x + "," + y + "," + format.format(time) + "," + speed;
    }


}
