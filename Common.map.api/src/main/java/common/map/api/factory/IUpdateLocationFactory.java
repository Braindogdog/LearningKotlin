package common.map.api.factory;

import android.content.Context;

/**
 * Created by zhangmh on 2018/3/12.
 * 定位服务
 */

public interface IUpdateLocationFactory {

    /**
     * 开启定位服务
     */
    boolean startLocationService(Context context);

    /**
     * 停止定位服务
     */
    void stopLocationService(Context context);

}
