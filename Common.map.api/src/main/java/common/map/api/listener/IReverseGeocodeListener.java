package common.map.api.listener;

import org.creation.common.geometry.GeoLocation;


/**
 * Created by chasen on 2018/3/27.
 * 逆地理编码返回结果监听
 */

public interface IReverseGeocodeListener {
    void result(GeoLocation location);
}
