package com.firebaselibrary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chasen on 2018/4/13.
 */

@Entity
public class NavigationAddressBean {
    @Id()
    private Long id;
    private double x;
    private double y;
    private String address;
    private int type;//1:普通搜索记录，2：起点 3：终点
    private long histroyid;
    @Generated(hash = 2043557573)
    public NavigationAddressBean(Long id, double x, double y, String address,
            int type, long histroyid) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.address = address;
        this.type = type;
        this.histroyid = histroyid;
    }
    @Generated(hash = 129032357)
    public NavigationAddressBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getX() {
        return this.x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return this.y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getHistroyid() {
        return this.histroyid;
    }
    public void setHistroyid(long histroyid) {
        this.histroyid = histroyid;
    }

}
