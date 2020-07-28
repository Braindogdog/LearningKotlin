package common.baidunavigation.listener;

import android.graphics.Bitmap;

/**
 * Created by zhangmh on 2018/5/22.
 * <p>
 * 导航路程数据有更新
 */

public interface OnRouteUpdateListener {

    /**
     * 图标变换（左转、直行、右转、掉头等）
     */
    void onRoadTurnIconUpdate(Bitmap tureIcon);

    /**
     * 多少米后转向TURN_DISTANCE
     */
    void onRoadTurnDistanceUpdate(String tureDistance);

    /**
     * 导航结束
     * 在 用户结束导航 或 到达目的地的时候 调用
     */
    void onRoadNaviGuideEnd();



}
