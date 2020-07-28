package com.firebaselibrary.bean;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

import common.baselibrary.baseutil.EmptyUtil;

@SuppressWarnings("serial")
public class GPSVM implements Serializable {

    /**
     * 设备id
     */
    private String diviceId;
    /**
     * gps集合
     */
    private List<GPSSimple> gps;
    /**
     * 方向
     */
    private float direction;
    /**
     * 海拔
     */
    private float altitude;

    /**
     * 精度
     */
    private int accuracy;

    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }




    private boolean needSpeed;

    public boolean isNeedSpeed() {
        return needSpeed;
    }

    public void setNeedSpeed(boolean needSpeed) {
        this.needSpeed = needSpeed;
    }

    public String getDiviceId() {
        return diviceId;
    }

    public void setDiviceId(String diviceId) {
        this.diviceId = diviceId;
    }

    public List<GPSSimple> getGps() {
        return gps;
    }

    public void setGps(List<GPSSimple> gps) {
        this.gps = gps;
    }

    public String getUploadString() {
        if (EmptyUtil.isEmpty(diviceId))
            return "";
        if (EmptyUtil.isEmpty(gps))
            return "{" + diviceId + "[]}";
        StringBuilder builder = new StringBuilder("{" + diviceId + "[");
        for (GPSSimple simple : gps) {
            if (needSpeed)
                builder.append("\'" + simple.getHasSpeedStr());
            else
                builder.append("\'" + simple.getNoSpeedStr());

            builder.append(","+direction);
            builder.append(","+accuracy);
            builder.append(","+altitude+"\',");
        }
        String result = builder.substring(0, builder.lastIndexOf(","));
        result += "]"+appId+"}";
        Log.e("LocationService", "initLocationOption:"+result);
        return result;
    }

}
