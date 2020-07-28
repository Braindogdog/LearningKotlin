package com.firebaselibrary.bean.search.query;

import com.firebaselibrary.bean.search.LiveTargetTypeEnum;

import java.io.Serializable;

/**
 * 开启录像
 * @author zhangmh
 * @date 2020/5/28 17:42
 */
public class LiveArgs implements Serializable {


    private String targetId;

    /**
     * 类型
     */
    private LiveTargetTypeEnum targetType;

    private String userId;

    private String userName;

    private String unitId;

    private String unitName;

    public LiveArgs() {
    }

    public LiveArgs(String targetId, LiveTargetTypeEnum targetType, String userId, String userName, String unitId, String unitName) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.userId = userId;
        this.userName = userName;
        this.unitId = unitId;
        this.unitName = unitName;
    }
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
}
