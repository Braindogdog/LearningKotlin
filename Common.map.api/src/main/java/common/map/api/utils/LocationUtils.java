package common.map.api.utils;

import org.creation.common.geometry.GeoPoint;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmh on 2018/3/12.
 * 地图点转化
 */
public class LocationUtils {


    //public static final String BAIDU_LBS_TYPE = "bd09ll";

    public static double pi = 3.1415926535897932384626;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

    /**
     * 84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
     *
     * @param lon
     * @param lat
     * @return
     */
    public static Gps gps84_To_Gcj02(double lon, double lat) {
        if (!checkLocation(lon, lat)) {
            return null;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLon, mgLat);
    }


    /**
     * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return
     */
    public static Gps gcj02_To_Gps84(double lon, double lat) {
        Gps gps = transform(lon, lat);
        double longitude = lon * 2 - gps.getWgLon();
        double latitude = lat * 2 - gps.getWgLat();
        return new Gps(longitude, latitude);
    }

    public static Gps gps84_To_Bd09(double lon, double lat) {

        Gps gps = gps84_To_Gcj02(lon, lat);

        if (gps == null) {
            return new Gps(lon, lat);
        } else {
            return gcj02_To_Bd09(gps.getWgLon(), gps.getWgLat());
        }

    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param gg_lat
     * @param gg_lon
     */
    public static Gps gcj02_To_Bd09(double gg_lon, double gg_lat) {
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new Gps(bd_lon, bd_lat);
    }

    /**
     * * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标 * * @param
     * bd_lat * @param bd_lon * @return
     */
    public static Gps bd09_To_Gcj02(double bd_lon, double bd_lat) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new Gps(gg_lon, gg_lat);
    }

    /**
     * (BD-09)-->84
     *
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static Gps bd09_To_Gps84(double bd_lon, double bd_lat) {

        Gps gcj02 = LocationUtils.bd09_To_Gcj02(bd_lon, bd_lat);
        Gps map84 = LocationUtils.gcj02_To_Gps84(gcj02.getWgLon(), gcj02.getWgLat());
        return map84;

    }

    public static Gps transform(double lon, double lat) {
        if (!checkLocation(lon, lat)) {
            return new Gps(lon, lat);
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLon, mgLat);
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static class Gps {

        private double wgLat;
        private double wgLon;

        public Gps(double wgLon, double wgLat) {
            setWgLon(wgLon);
            setWgLat(wgLat);
        }

        public double getWgLat() {
            return getDoubleFractionDigits(wgLat, 6);
//            return wgLat;
        }

        public void setWgLat(double wgLat) {
            this.wgLat = wgLat;
        }

        public double getWgLon() {
            return getDoubleFractionDigits(wgLon, 6);
//            return wgLon;
        }

        public void setWgLon(double wgLon) {
            this.wgLon = wgLon;
        }

        @Override
        public String toString() {
            return wgLat + "," + wgLon;
        }
    }

    public static double getDoubleFractionDigits(double num, int digit) {
        DecimalFormat formater = new DecimalFormat();
        //保留几位小数
        formater.setMaximumFractionDigits(digit);
        //模式  四舍五入
        formater.setRoundingMode(RoundingMode.UP);
        return Double.parseDouble(formater.format(num));
    }

    /**
     * WKT模式的数据 ->  点集合
     */
    public static List<GeoPoint> geometryFromWkt(String wkt) {
        int hindex = wkt.indexOf("(");
        wkt = wkt.substring(hindex);
        wkt = wkt.replace("(", "").replace(")", "");
        List<GeoPoint> pointsArr = new ArrayList<GeoPoint>();
        String[] pointsString = wkt.split(",");
        for (int i = 0; i < pointsString.length; i++) {
            String[] p = pointsString[i].split(" ");
            if (p[0].equals("")) {
                pointsArr.add(new GeoPoint(Double.valueOf(p[1]), Double.valueOf(p[2])));
            } else {
                pointsArr.add(new GeoPoint(Double.valueOf(p[0]), Double.valueOf(p[1])));
            }
        }
        return pointsArr;
    }

    /**
     * 判断是否是有效定位
     */
    public static boolean checkLocation(double x, double y) {
        return x > 73 && x < 136 && y > 18 && y < 54;
    }

    /**
     * 判断是否是有效定位
     */
    public static boolean checkLocation(GeoPoint location) {
        if (location != null) {
            return checkLocation(location.getX(), location.getY());
        } else {
            return false;
        }
    }

}
