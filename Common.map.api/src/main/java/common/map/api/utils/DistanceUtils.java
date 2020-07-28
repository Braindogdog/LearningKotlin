package common.map.api.utils;


/**
 * Created by zhangmh on 2018/3/12.
 * 距离计算
 */
public class DistanceUtils {

    // 计算距离常量
    private static final double EARTH_RADIUS = 6378137;


    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double getDistance(double x1, double y1, double x2, double y2) {

        double radLat1 = rad(y1);
        double radLat2 = rad(y2);
        double a = radLat1 - radLat2;
        double b = rad(x1) - rad(x2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;

    }


    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
