package common.baidunavigation.entity;

/**
 * Created by zhangmh on 2018/5/22.
 * 导航起止点
 */

public class NaviPoint {
    private double longitude, latitude;
    private String name, description;
    private int coType;

    /**
     * 经纬度默认为百度09坐标
     */
    public NaviPoint(double longitude, double latitude, String name, String description) {
        this(longitude,latitude,name,description, CoordinateType.WGS84);
    }


    /**
     * @param coType
     *        coType 使用 CoordinateType里面的值比如 CoordinateType.BD09LL
     */
    public NaviPoint(double longitude, double latitude, String name, String description, int coType) {
        this.longitude = longitude;
        this.latitude = latitude;
        if (name == null) {
            this.name = "";
        } else {
            this.name = new String(name);
        }

        if (description == null) {
            this.description = "";
        } else {
            this.description = new String(description);
        }
        this.coType = coType;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCoType() {
        return coType;
    }
}
