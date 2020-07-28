package com.firebaselibrary.bean.fireunit;

import com.firebaselibrary.bean.IDBean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chasen on 2018/5/21.
 */

@Entity
public class FireUnitBean {
    @Convert(converter = FireUnitAddressConvert.class, columnType = String.class)
    private FireUnitAddressBean address;
    @Convert(converter = IDBeanConvent.class, columnType = String.class)
    private IDBean parent;
    private String name;
    @Id
    private String id;

    @Generated(hash = 1972819007)
    public FireUnitBean(FireUnitAddressBean address, IDBean parent, String name,
            String id) {
        this.address = address;
        this.parent = parent;
        this.name = name;
        this.id = id;
    }

    @Generated(hash = 1567353007)
    public FireUnitBean() {
    }

    public FireUnitAddressBean getAddress() {
        return address;
    }

    public void setAddress(FireUnitAddressBean address) {
        this.address = address;
    }

    public IDBean getParent() {
        return parent;
    }

    public void setParent(IDBean parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
