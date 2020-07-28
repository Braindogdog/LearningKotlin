package common.map.api.listener;

import java.util.List;

import common.map.api.entity.routeplan.CreDrivingRouteLine;

/**
 * Created by zhangmh on 2018/5/29.
 * 路径规划监听
 */

public interface IGetRoutePlanResultListener {

    void onRoutePlanResultFailed(String errMessage);

    void onRoutePlanResultSuccess(List<CreDrivingRouteLine> listRoutelines);

}
