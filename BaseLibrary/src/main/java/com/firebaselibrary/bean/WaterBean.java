package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/5/22.
 */

public class WaterBean {
    private String id;
    private String name;
    private IdNameBean stype;
    private IdNameBean unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdNameBean getStype() {
        return stype;
    }

    public void setStype(IdNameBean stype) {
        this.stype = stype;
    }

    public IdNameBean getUnit() {
        return unit;
    }

    public void setUnit(IdNameBean unit) {
        this.unit = unit;
    }
}
