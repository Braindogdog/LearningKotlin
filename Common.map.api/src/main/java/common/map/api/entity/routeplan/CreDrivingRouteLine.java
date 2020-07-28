package common.map.api.entity.routeplan;

import org.creation.common.geometry.GeoPoint;

import java.util.List;

/**
 * Created by zhangmh on 2018/5/29.
 * <p>
 * 驾车路径规划结果
 */

public class CreDrivingRouteLine {

    private CreRouteNode startNode;

    private CreRouteNode endNode;
    //路程点（包含路径规划的起点和终点）
    private List<GeoPoint> wayList;
    //距离（米）
    private int distance;
    //拥挤距离（米）
    private int congestionDistance;
    //耗时（秒）
    private int duration;
    //红绿灯（个）
    private int lightNum;


    public CreRouteNode getStartNode() {
        return startNode;
    }

    public void setStartNode(CreRouteNode startNode) {
        this.startNode = startNode;
    }

    public CreRouteNode getEndNode() {
        return endNode;
    }

    public void setEndNode(CreRouteNode endNode) {
        this.endNode = endNode;
    }

    public List<GeoPoint> getWayList() {
        return wayList;
    }

    public void setWayList(List<GeoPoint> wayList) {
        this.wayList = wayList;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCongestionDistance() {
        return congestionDistance;
    }

    public void setCongestionDistance(int congestionDistance) {
        this.congestionDistance = congestionDistance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLightNum() {
        return lightNum;
    }

    public void setLightNum(int lightNum) {
        this.lightNum = lightNum;
    }
}
