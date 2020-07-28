package com.firebaselibrary.bean;

/**
 * Created by chasen on 2018/5/22.
 */

public class StationAddressBean {
    private String id;
    private String srid;
    private String description;
    private PointBean point;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrid() {
        return srid;
    }

    public void setSrid(String srid) {
        this.srid = srid;
    }

    public PointBean getPoint() {
        return point;
    }

    public void setPoint(PointBean point) {
        this.point = point;
    }
}
