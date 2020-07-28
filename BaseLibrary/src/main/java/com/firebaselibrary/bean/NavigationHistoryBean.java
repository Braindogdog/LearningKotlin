package com.firebaselibrary.bean;

import org.creation.common.geometry.GeoLocation;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by chasen on 2018/3/26.
 */

@Entity
public class NavigationHistoryBean {

    @Id
    private Long histroyid;
    @Transient
    private GeoLocation startPoint;
    @Transient
    private GeoLocation endPoint;

    @Generated(hash = 332978376)
    public NavigationHistoryBean(Long histroyid) {
        this.histroyid = histroyid;
    }

    @Generated(hash = 275087086)
    public NavigationHistoryBean() {
    }

    public GeoLocation getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(GeoLocation startPoint) {
        this.startPoint = startPoint;
    }

    public GeoLocation getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(GeoLocation endPoint) {
        this.endPoint = endPoint;
    }

    public Long getHistroyid() {
        return this.histroyid;
    }

    public void setHistroyid(Long histroyid) {
        this.histroyid = histroyid;
    }
}
