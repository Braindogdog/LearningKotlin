package common.baidunavigation.listener;

/**
 * Created by zhangmh on 2018/5/22.
 * <p>
 * 算路监听,进入导航界面前需要先算路，看能否导航
 */

public interface OnRouteplanListener {


    /**
     * 算路开始
     */
    void onRoutePlanStart();

    /**
     * 算路成功
     */
    void onRoutePlanSuccess();

    /**
     * 算路失败
     */
    void onRoutePlanFailed();

    /**
     * 从此处开启导航页面
     * return true 表示已经跳转到导航页面
     *        false 没有处理跳转事件，需要程序自己跳转
     */
    boolean onRoutePlanToNavi();

}
