package common.map.api.listener;


import org.creation.common.geometry.GeoPoint;


/**
 * Created by zhangmh on 2018/3/12.
 */
public interface ILongClickMapListener {
    /**
     * 长按地图
     * @param location
     */
    void longClick(GeoPoint location);
}
