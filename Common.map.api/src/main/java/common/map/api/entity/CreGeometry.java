package common.map.api.entity;


import android.os.Bundle;

import common.map.api.listener.IGeometryClickListener;

/**
 * Created by zhangmh on 2018/3/12.
 * 图形（子类包括 点、线、面、圆 CreCircle）
 */
public class CreGeometry {
    private String id;
    private Bundle extra;
    private IGeometryClickListener click;

    public CreGeometry() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bundle getExtra() {
        return extra;
    }

    public void setExtra(Bundle extra) {
        this.extra = extra;
    }

    public IGeometryClickListener getClick() {
        return click;
    }

    public void setClick(IGeometryClickListener click) {
        this.click = click;
    }
}
