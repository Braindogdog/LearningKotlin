package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/5/21.
 */

public class ExpertBean {
    private String id;
    private String name;
    private String sex;
    private String duty;
    private String workPhone;
    private IDBean unit;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public IDBean getUnit() {
        return unit;
    }

    public void setUnit(IDBean unit) {
        this.unit = unit;
    }
}
