package com.firebaselibrary.bean.search;

import java.io.Serializable;

/**
 * @author zhangmh
 * @date 2020/6/2 22:02
 */
public class LivingVideo implements Serializable {


    private String targetId;

    private LiveTargetTypeEnum targetType;

    private String userId;

    private String userName;

    private String unitId;

    private String unitName;

    /**
     * 创建时间
     */
    private long time;


    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public LiveTargetTypeEnum getTargetType() {
        return targetType;
    }

    public void setTargetType(LiveTargetTypeEnum targetType) {
        this.targetType = targetType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
