package common.map.api.listener;


import org.creation.common.geometry.GeoPoint;

/**
 * Created by zhangmh on 2018/3/12.
 * 定位结果
 */
public interface IOnLocationListener {
    /**
     * 定位结果
     * @return
     */
    void getLocation(GeoPoint location, String cityName);
}
