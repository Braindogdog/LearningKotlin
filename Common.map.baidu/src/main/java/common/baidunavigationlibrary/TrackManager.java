package common.baidunavigationlibrary;

import android.content.Context;
import android.os.Handler;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmh on 2018/5/7.
 */

public class TrackManager {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    //    private Polyline mPolyline;
    private Marker mMoveMarker;
    private Handler mHandler;
    private Context context;
    private List<LatLng> polylines;
    private boolean isMoving;


    // 通过设置间隔时间和距离可以控制速度和图标移动的距离
    private static final int TIME_INTERVAL = 40;
    private static final double DISTANCE = 0.00005;

    public void init(MapView mMapView, Context context, Handler mHandler) {
        this.mMapView = mMapView;
        this.context = context;
        this.mHandler = mHandler;
        mBaiduMap = mMapView.getMap();
        polylines = new ArrayList<>();
        for (int index = 0; index < latlngs.length; index++) {
            polylines.add(latlngs[index]);
        }

        polylines.add(latlngs[0]);

    }

    public void stopMove() {
        isMoving = false;
    }


    /**
     * 循环进行移动逻辑
     */
    public void startMove() {
        isMoving = true;
        OverlayOptions markerOptions;
        markerOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_move_car)).position(polylines.get(0))
                .rotate((float) getAngle(0));
        mMoveMarker = (Marker) mBaiduMap.addOverlay(markerOptions);

        new Thread() {
            public void run() {
                lable:
                while (isMoving) {
                    for (int i = 0; i < latlngs.length - 1; i++) {
                        final LatLng startPoint = latlngs[i];
                        final LatLng endPoint = latlngs[i + 1];
                        mMoveMarker
                                .setPosition(startPoint);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // refresh marker's rotate
                                if (mMapView == null) {
                                    return;
                                }
                                mMoveMarker.setRotate((float) getAngle(startPoint,
                                        endPoint));
                            }
                        });
                        double slope = getSlope(startPoint, endPoint);
                        // 是不是正向的标示
                        boolean isReverse = (startPoint.latitude > endPoint.latitude);

                        double intercept = getInterception(slope, startPoint);

                        double xMoveDistance = isReverse ? getXMoveDistance(slope) :
                                -1 * getXMoveDistance(slope);


                        for (double j = startPoint.latitude; !((j > endPoint.latitude) ^ isReverse);
                             j = j - xMoveDistance) {
                            LatLng latLng = null;
                            if (slope == Double.MAX_VALUE) {
                                latLng = new LatLng(j, startPoint.longitude);
                            } else {
                                latLng = new LatLng(j, (j - intercept) / slope);
                            }

                            final LatLng finalLatLng = latLng;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (mMapView == null) {
                                        return;
                                    }
                                    mMoveMarker.setPosition(finalLatLng);
                                }
                            });
                            try {
                                Thread.sleep(TIME_INTERVAL);
                                if (!isMoving) {
                                    continue lable;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    isMoving = false;
                }
            }

        }.start();
    }

    private static final LatLng[] latlngs = new LatLng[]{
//            new LatLng(39.946168, 116.362012),
//            new LatLng(39.947993, 116.362156),
//            new LatLng(39.949155, 116.362875),
//            new LatLng(39.95004, 116.363953),
            new LatLng(39.952805, 116.372433),
            new LatLng(39.954409, 116.376601),
            new LatLng(39.954852, 116.379332),
            new LatLng(39.955349, 116.384003),
            new LatLng(39.95817, 116.3858),
            new LatLng(39.96453, 116.385871),
            new LatLng(39.969064, 116.386231),
            new LatLng(39.969286, 116.391118),
//            new LatLng(40.057225, 116.307164),
//            new LatLng(40.056134, 116.307546),
//            new LatLng(40.055879, 116.307636),
//            new LatLng(40.055826, 116.307697),
    };


    /**
     * 根据点获取图标转的角度
     */
    private double getAngle(int startIndex) {
        if ((startIndex + 1) >= polylines.size()) {
            throw new RuntimeException("index out of bonds");
        }
        LatLng startPoint = polylines.get(startIndex);
        LatLng endPoint = polylines.get(startIndex + 1);
        return getAngle(startPoint, endPoint);
    }

    /**
     * 根据两点算取图标转的角度
     */
    private double getAngle(LatLng fromPoint, LatLng toPoint) {
        double slope = getSlope(fromPoint, toPoint);
        if (slope == Double.MAX_VALUE) {
            if (toPoint.latitude > fromPoint.latitude) {
                return 0;
            } else {
                return 180;
            }
        }
        float deltAngle = 0;
        if ((toPoint.latitude - fromPoint.latitude) * slope < 0) {
            deltAngle = 180;
        }
        double radio = Math.atan(slope);
        double angle = 180 * (radio / Math.PI) + deltAngle - 90;
        return angle;
    }

    /**
     * 根据点和斜率算取截距
     */
    private double getInterception(double slope, LatLng point) {

        double interception = point.latitude - slope * point.longitude;
        return interception;
    }

    /**
     * 算斜率
     */
    private double getSlope(LatLng fromPoint, LatLng toPoint) {
        if (toPoint.longitude == fromPoint.longitude) {
            return Double.MAX_VALUE;
        }
        double slope = ((toPoint.latitude - fromPoint.latitude) / (toPoint.longitude - fromPoint.longitude));
        return slope;

    }


    /**
     * 计算x方向每次移动的距离
     */
    private double getXMoveDistance(double slope) {
        if (slope == Double.MAX_VALUE) {
            return DISTANCE;
        }
        return Math.abs((DISTANCE * slope) / Math.sqrt(1 + slope * slope));
    }


}
