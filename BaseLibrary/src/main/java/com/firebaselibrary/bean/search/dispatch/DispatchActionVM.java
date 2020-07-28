package com.firebaselibrary.bean.search.dispatch;

import com.firebaselibrary.bean.query.DispatchTypeEnum;
import org.creation.common.models.BaseQuery;
import java.io.Serializable;
import java.util.List;

/**
 * 调度
 *
 * @author zhangmh
 * @date 2020/3/23 10:43
 */
public class DispatchActionVM<T extends BaseDispatchBean> extends BaseQuery implements Serializable {

    /**
     * 事件 id
     */
    private String eventId;

    /**
     * 是否调度
     * 如果是true,则必填 informType
     */
    private boolean dispatched;

    /**
     * 通知类型
     * 任务通知cmd、短信通知sms
     * 如果 dispatched = true ,则必填此项
     */
    private DispatchInformTypeEnum informType;

    /**
     * 匹配预案
     */
    private boolean matchingScheme;

    /**
     * 预案 id
     */
    private String schemeId;

    /**
     * 创建人
     */
    private String createId;
    private String createName;
    private String createUnitid;
    private String createUnitname;
    /**
     * 职责
     */
    private String duty;
    /**
     * 派遣的资源类型
     */
    private DispatchTypeEnum dispatchType;

    private List<T> datas;


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public boolean isDispatched() {
        return dispatched;
    }

    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }

    public boolean isMatchingScheme() {
        return matchingScheme;
    }

    public void setMatchingScheme(boolean matchingScheme) {
        this.matchingScheme = matchingScheme;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateUnitid() {
        return createUnitid;
    }

    public void setCreateUnitid(String createUnitid) {
        this.createUnitid = createUnitid;
    }

    public String getCreateUnitname() {
        return createUnitname;
    }

    public void setCreateUnitname(String createUnitname) {
        this.createUnitname = createUnitname;
    }

    public DispatchTypeEnum getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(DispatchTypeEnum dispatchType) {
        this.dispatchType = dispatchType;
    }

    public DispatchInformTypeEnum getInformType() {
        return informType;
    }

    public void setInformType(DispatchInformTypeEnum informType) {
        this.informType = informType;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
