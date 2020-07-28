package common.map.api.entity.routeplan;

import org.creation.common.geometry.GeoPoint;

/**
 * Created by zhangmh on 2018/5/29.
 * 路径规划结果—路线上的点
 */

public class CreRouteNode {
    //点
    private GeoPoint point;

    public CreRouteNode() {
    }

    public CreRouteNode(GeoPoint point) {
        this.point = point;
    }

    public GeoPoint getPoint() {
        return point;
    }

    public void setPoint(GeoPoint point) {
        this.point = point;
    }
}
