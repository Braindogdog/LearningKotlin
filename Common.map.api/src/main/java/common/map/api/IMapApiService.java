package common.map.api;

import android.content.Context;

import org.creation.common.geometry.GeoEnvelope;
import org.creation.common.geometry.GeoLocation;
import org.creation.common.geometry.GeoPoint;

import java.util.List;

import common.map.api.entity.CreCircle;
import common.map.api.entity.CrePoint;
import common.map.api.entity.CrePolygon;
import common.map.api.entity.CrePolyline;
import common.map.api.listener.IGeocodeListener;
import common.map.api.listener.IGetPoiListener;
import common.map.api.listener.IGetRoutePlanResultListener;
import common.map.api.listener.IMapChangeListener;
import common.map.api.listener.IMapLoadedListener;
import common.map.api.listener.IMeasureResultListener;
import common.map.api.listener.IOnLocationListener;
import common.map.api.listener.IReverseGeocodeListener;

/**
 * Created by zhangmh on 2018/3/12.
 * 地图service
 */
public interface IMapApiService {

    /**
     * 初始化
     * Application初始化时调用
     */
    void initMapSDK(Context context);

    /**
     * 初始化
     *
     * @param mapDir 地图相关数据本地存储地址
     *               例如：离线地图包、
     */
    void initMapSDK(Context context, String mapDir);

    /**
     * 地图初始化操作
     */
    void initMapApi(Context context,int type, IMapLoadedListener loadedListener);

    /**
     * 切换影像地图
     * @param context
     * @param loadedListener
     */
    void initImageMapApi(Context context,IMapLoadedListener loadedListener);
    //添加点
    void addCrePoint(String layerId, CrePoint crePoint);

    void addCrePointList(String layerId, List<CrePoint> crePointList);

    //添加圆
    void addCreCircle(String layerId, CreCircle creCircle);

    void addCreCircle(String layerId, List<CreCircle> creCircleList);

    //添加线
    void addCrePolyline(String layerId, CrePolyline crePolyline);

    void addCrePolyline(String layerId, List<CrePolyline> crePolylineList);

    //添加图形
    void addCrePolygon(String layerId, CrePolygon crePolygon);

    void addCrePolygon(String layerId, List<CrePolygon> crePolygonList);

    /**
     * 清除地图图标
     *           i = 0   清除地图所有图标
     *          i = 1  仅清除地图上的资源图标
     *
     */
    void clearMap(int i);


    /**
     * 清除特定图层
     */
    void removeLayer(String layerId);

    /**
     * 地图状态改变监听
     *
     * @param changeListener
     */
    void setMapChangListener(IMapChangeListener changeListener);


    /**
     * 设置中心点
     *
     * @param centerPoint 中心点坐标
     * @param zoom        地图当前级别,zoom不需要的时候，写 0
     */
    void setCenter(GeoPoint centerPoint, float zoom);


    /**
     * 获取中心点坐标
     */
    GeoPoint getCenter();

    /**
     * 获取当前zoom
     */
    float getCurrentZoom();

    /**
     * 设置zoom
     */
    void setCurrentZoom(float zoom);

    /**
     * 获取屏幕显示的坐标范围
     */
    GeoEnvelope getGeoEnvelope();

    /**
     * 设置定位图层
     *
     * @param locationListener
     */
    void setLocationLayer(IOnLocationListener locationListener);

    /**
     * 开启定位
     *
     * @return true 开启定位
     * false 没有定位图层或者正在定位
     */
    boolean startLocation();

    /**
     * 停止定位
     *
     * @return
     */
    void stopLocation();


    /**
     * 画热点图
     *
     * @param heatPoints
     */
    void drawHeatMap(List<GeoPoint> heatPoints);

    /**
     * 将集合内的点 自动缩放到地图的视野范围内
     *
     * @param crePoints  点集合
     * @param isBaiduLoc 坐标是否是百度地图
     */
    void setAutoZoom(String layerId, List<CrePoint> crePoints, boolean isBaiduLoc);

    /**
     * 将集合内的点 自动缩放到地图的视野范围内
     * @param points
     */
    void setAutoZoom(List<GeoPoint> points);

    /**
     * 设置为在线地图
     * arcgis专用
     */
    void setOnlineMap(String division, String zxyxServerUrl, String zxyxPOIServerUrl);

    /**
     * 设置为离线地图
     * arcgis专用
     */
    void setOfflineMap();

    /**
     * 地理编码
     *
     * @param address
     * @param listener
     */
    void geocoding(String address, IGeocodeListener listener, String cityName);

    /**
     * 逆地理编码
     *
     * @param point
     * @param listener
     */
    void reverseGeocode(GeoPoint point, IReverseGeocodeListener listener);

    /**
     * 获取热点信息
     *
     * @param listener
     * @param address
     */
    void getPoi(IGetPoiListener listener, String address, String cityName);

    /**
     * 获取周边热点信息
     *
     * @param listener
     * @param point
     */
    void getPoi(IGetPoiListener listener, GeoPoint point);

    /**
     * 路径规划
     *
     * @param startPoint
     * @param endPoint
     */
    void routePlan(GeoLocation startPoint, GeoLocation endPoint, IGetRoutePlanResultListener routePlanResultListener);

    /**
     * 地图量算
     * @param type
     */
    void measureMap(int type, IMeasureResultListener listener);

    /**
     * 绘制点线面
     * @param type
     */
    void DrawInMap(int type);

    /**
     * 界面onResume时调用
     */
    void onResume();

    /**
     * 销毁
     */
    void onDestroy();


}
