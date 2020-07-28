package common.map.api.entity;

/**
 * Created by Chasen on 2019/4/18
 */
public class BaseLocation {
    public double X;
    public double Y;

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public BaseLocation() {

    }

    public BaseLocation(double x, double y) {

        X = x;
        Y = y;
    }
}
