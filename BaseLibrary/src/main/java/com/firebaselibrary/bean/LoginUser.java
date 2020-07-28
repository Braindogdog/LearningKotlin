/**
 *
 */
package com.firebaselibrary.bean;

import java.io.Serializable;
import java.util.Set;

/**
 * @登录单位
 */
@SuppressWarnings("serial")
public class LoginUser implements Serializable {
    /**
     * @编号
     */
    private String id;
    /**
     * @姓名
     */
    private String name = "未知用户";
    /**
     * @单位编号
     */
    private String unitId;
    /**
     * @单位名称
     */
    private String unitName = "未知单位";
    /**
     * @城市编号
     */
    private String divisionId;
    /**
     * @城市名称
     */
    private String divisionName = "未知地区";
    /**
     * @权限
     */
    private Set<String> sysfunc;

    /**
     * @return the divisionId
     */
    public String getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the divisionId to set
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return the divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName the divisionName to set
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unitId
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @param unitId the unitId to set
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * @return the unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * @return the sysfunc
     */
    public Set<String> getSysfunc() {
        return sysfunc;
    }

    /**
     * @param sysfunc the sysfunc to set
     */
    public void setSysfunc(Set<String> sysfunc) {
        this.sysfunc = sysfunc;
    }


}
