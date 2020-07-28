package common.map.api.listener;

import org.creation.common.geometry.GeoLocation;

/**
 * Created by chasen on 2018/3/27.
 * 地理编码结果监听
 */

public interface IGeocodeListener {
    void result(GeoLocation location);
}
