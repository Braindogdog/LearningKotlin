package common.map.api.entity;

import android.os.Bundle;

import org.creation.common.geometry.GeoPoint;
import org.creation.common.geometry.IGeoObject;

import common.map.api.listener.IGeometryClickListener;

/**
 * Created by zhangmh on 2018/3/12.
 * 圆
 */

public class CreCircle extends IGeoObject {

    //边的宽度
    private int strokeSize = 5;
    //边的颜色
    private int strokeColor = 0x00bcb4;
    //填充颜色
    private int fillColor = 0x00bcb4;
    //中心点
    private GeoPoint center;
    //半径
    private int radius;
    //附带信息，一般用于点击后所带返回值
    private Bundle extra;
    //点击监听
    private IGeometryClickListener click;



    public CreCircle() {
    }

    /**
     * @param center 中心点
     * @param radius 半径
     */
    public CreCircle(GeoPoint center, int radius) {
        this.center = center;
        this.radius = radius;
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

    public GeoPoint getCenter() {
        return center;
    }

    public void setCenter(GeoPoint center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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
