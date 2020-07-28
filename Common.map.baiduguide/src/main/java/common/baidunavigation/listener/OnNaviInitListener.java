package common.baidunavigation.listener;

/**
 * Created by zhangmh on 2018/5/22.
 * 导航监听
 */

public interface OnNaviInitListener {

    void onAuthResult(int status, String msg);

    void initStart();

    void initSuccess();

    void initFailed();
}
