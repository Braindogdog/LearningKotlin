package com.firebaselibrary.bean.fireunit;

import com.firebaselibrary.bean.PointBean;

/**
 * Created by chasen on 2018/5/21.
 */

public class FireUnitAddressBean {
    private String id;
    private String srid;
    private PointBean point;

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
