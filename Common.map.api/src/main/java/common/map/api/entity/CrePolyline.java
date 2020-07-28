package common.map.api.entity;

import android.os.Bundle;
import android.text.TextUtils;

import org.creation.common.geometry.GeoPolyline;

import common.map.api.listener.IGeometryClickListener;

/**
 * Created by zhangmh on 2018/3/12.
 * 线
 */
public class CrePolyline extends GeoPolyline {
    //线条样式有两种 图片（lineResId）或者 线条（lineColor ）
    //如果存在图片会优先取图片

    //线条图片
    private String lineResId;
    //线条颜色
    private int lineColor = 0xAA00FF00;
    //线条宽度
    private int lineSize = 5;
    //附带信息，一般用于点击后所带返回值
    private Bundle extra;
    //点击监听
    private IGeometryClickListener click;

    public String getLineResId() {
        return lineResId;
    }

    public void setLineResId(String lineResId) {
        this.lineResId = lineResId;
    }

    /**
     * 是否有图片
     * @return
     */
    public boolean hasResId(){
        return !TextUtils.isEmpty(lineResId);
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineSize() {
        return lineSize;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
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
