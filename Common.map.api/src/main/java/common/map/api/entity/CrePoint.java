package common.map.api.entity;

import android.os.Bundle;
import android.view.View;

import org.creation.common.geometry.GeoMarker;
import org.creation.common.geometry.GeoPoint;

import java.util.List;

import common.map.api.listener.IGeometryClickListener;

/**
 * Created by zhangmh on 2018/3/12.
 * 点
 */
public class CrePoint extends GeoMarker {
    //图片id
    private int resId;
    //图片集合，用于显示动态效果
    private List<Integer> backGif;
    //附带信息，一般用于点击后所带返回值
    private Bundle extra;
    //点击监听
    private IGeometryClickListener click;
    //view
    private View view;

    public CrePoint() {
    }

    public CrePoint(GeoPoint geoPoint) {
        setPoint(geoPoint);
    }

    public CrePoint (double x, double y){
        setPoint(new GeoPoint(x,y));
    }


    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public List<Integer> getBackGif() {
        return backGif;
    }

    public void setBackGif(List<Integer> backGif) {
        this.backGif = backGif;
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

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
