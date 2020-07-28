package common.map.api.listener;

import org.creation.common.geometry.GeoLocation;

import java.util.List;

/**
 * Created by chasen on 2018/3/27.
 */

public interface IGetPoiListener {
    void getPoi(List<GeoLocation> list);
}
