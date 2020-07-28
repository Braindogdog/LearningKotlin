package common.map.api.entity;

import android.os.Bundle;

import org.creation.common.geometry.GeoPolygon;

import common.map.api.listener.IGeometryClickListener;

/**
 * Created by zhangmh on 2018/3/12.
 * 多边形
 */
public class CrePolygon extends GeoPolygon {
    //边的宽度
    private int strokeSize = 5;
    //边的颜色
    private int strokeColor = 0xAA00FF00;
    //填充颜色
    private int fillColor = 0xAAFFFF00;

    //附带信息，一般用于点击后所带返回值
    private Bundle extra;
    //点击监听
    private IGeometryClickListener click;

    public CrePolygon() {
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
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
