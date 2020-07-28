package com.firebaselibrary.bean.query;

/**
 * @author Zyq
 * @date 2020/3/6
 */

import org.creation.common.models.BaseQuery;

/**
 * 调度的资源
 *
 * @author zhangmh
 * @date 2020/2/21 14:38
 */
public class DispatchQuery extends BaseQuery {

    /**
     * 任务id
     */
    private String cmdId;

    /**
     * 事件id
     */
    private String eventId;

    /**
     * 派遣的资源类型
     */
    private String dispatchType;

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * G 派遣的资源类型
     */
    public String getDispatchType() {
        return this.dispatchType;
    }

    /**
     *  S 派遣的资源类型
     */
    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }
}
