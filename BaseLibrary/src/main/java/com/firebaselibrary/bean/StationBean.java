package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/5/22.
 */

public class StationBean {
    private String id;
    private String name;
    private String leader;
    private String leaderTele;
    private String observer;
    private String observerTele;
    private String personCount;
    private String equip;
    private IdNameBean unit;
    private StationAddressBean location;

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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeaderTele() {
        return leaderTele;
    }

    public void setLeaderTele(String leaderTele) {
        this.leaderTele = leaderTele;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getObserverTele() {
        return observerTele;
    }

    public void setObserverTele(String observerTele) {
        this.observerTele = observerTele;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public IdNameBean getUnit() {
        return unit;
    }

    public void setUnit(IdNameBean unit) {
        this.unit = unit;
    }

    public StationAddressBean getLocation() {
        return location;
    }

    public void setLocation(StationAddressBean location) {
        this.location = location;
    }
}
