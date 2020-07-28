package common.baidunavigation.entity;

/**
 * Created by zhangmh on 2018/5/22.
 * <p>
 * 坐标类型
 */

public interface CoordinateType {
    //国测局坐标
    int GCJ02 = 0;
    //百度墨卡托
    int BD09_MC = 1;
    //标准84坐标
    int WGS84 = 2;
    //百度自己的09坐标
    int BD09LL = 3;
}
