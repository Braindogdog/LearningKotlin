package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/5/21.
 */

public class CarBean {
    private String id;
    private String name;
    private String water;
    private String froth;
    private String dryDust;
    private String state;
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

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getFroth() {
        return froth;
    }

    public void setFroth(String froth) {
        this.froth = froth;
    }

    public String getDryDust() {
        return dryDust;
    }

    public void setDryDust(String dryDust) {
        this.dryDust = dryDust;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public IdNameBean getUnit() {
        return unit;
    }

    public void setUnit(IdNameBean unit) {
        this.unit = unit;
    }
}
