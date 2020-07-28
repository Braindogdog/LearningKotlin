package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/5/25.
 */

public class FireCaseBean {
    private String id;
    private String level;
    private String state;
    private String description;
    private boolean incepttype;
    private String doTime;
    private String callTime;
    private StationAddressBean address;
    private IdNameBean caseType1;
    private IdNameBean caseType2;
    private IdNameBean caseType3;
    private IdNameBean division;
    private IdNameBean secondUnit;
    private IdNameBean thirdUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIncepttype() {
        return incepttype;
    }

    public void setIncepttype(boolean incepttype) {
        this.incepttype = incepttype;
    }

    public String getDoTime() {
        return doTime;
    }

    public void setDoTime(String doTime) {
        this.doTime = doTime;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public StationAddressBean getAddress() {
        return address;
    }

    public void setAddress(StationAddressBean address) {
        this.address = address;
    }

    public IdNameBean getCaseType1() {
        return caseType1;
    }

    public void setCaseType1(IdNameBean caseType1) {
        this.caseType1 = caseType1;
    }

    public IdNameBean getCaseType2() {
        return caseType2;
    }

    public void setCaseType2(IdNameBean caseType2) {
        this.caseType2 = caseType2;
    }

    public IdNameBean getCaseType3() {
        return caseType3;
    }

    public void setCaseType3(IdNameBean caseType3) {
        this.caseType3 = caseType3;
    }

    public IdNameBean getDivision() {
        return division;
    }

    public void setDivision(IdNameBean division) {
        this.division = division;
    }

    public IdNameBean getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(IdNameBean secondUnit) {
        this.secondUnit = secondUnit;
    }

    public IdNameBean getThirdUnit() {
        return thirdUnit;
    }

    public void setThirdUnit(IdNameBean thirdUnit) {
        this.thirdUnit = thirdUnit;
    }
}
