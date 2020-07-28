package common.baidunavigationlibrary;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;

import org.creation.common.geometry.GeoEnvelope;
import org.creation.common.geometry.GeoLocation;
import org.creation.common.geometry.GeoPoint;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.baselibrary.baseutil.EmptyUtil;
import common.map.api.IMapApiService;
import common.map.api.MapConstants;
import common.map.api.entity.CreCircle;
import common.map.api.entity.CrePoint;
import common.map.api.entity.CrePolygon;
import common.map.api.entity.CrePolyline;
import common.map.api.entity.routeplan.CreDrivingRouteLine;
import common.map.api.entity.routeplan.CreRouteNode;
import common.map.api.listener.IGeocodeListener;
import common.map.api.listener.IGeometryClickListener;
import common.map.api.listener.IGetPoiListener;
import common.map.api.listener.IGetRoutePlanResultListener;
import common.map.api.listener.IMapChangeListener;
import common.map.api.listener.IMapLoadedListener;
import common.map.api.listener.IMeasureResultListener;
import common.map.api.listener.IOnLocationListener;
import common.map.api.listener.IReverseGeocodeListener;
import common.map.api.utils.LocationUtils;

//import com.baidu.lbsapi.BMapManager;
//import com.baidu.lbsapi.MKGeneralListener;
//import com.baidu.lbsapi.model.BaiduPanoData;
//import com.baidu.lbsapi.panoramaview.PanoramaRequest;
//import common.baidunavigationlibrary.panaroma.PanoViewActivity;

/**
 * Created by MHshachang on 2018/3/27.
 * 百度地图实现
 */
public class BaiduMapApiServiceImpl implements IMapApiService, OnGetRoutePlanResultListener {

    private BaiduMap baiduMap;
    private MapView vMapView;
    private HeatMap heatMap;
    private double currentMapX, currentMapY;
    private int zoomDefault;
    private int minLevel, maxLevel;
    private boolean openLoc;
    private Context context;
    private LatLng centerPoint;

    private LatLng rt, lb;
    private BaiduMap.OnMarkerClickListener onMarkerClickListener;
    private IOnLocationListener locationListener;
    //地图改变
    private IMapChangeListener changeListener;
    private GeoPoint currentCenter;

    private Map<Integer, BitmapDescriptor> bgBmMap = new ArrayMap<>();

    public LocationClient mLocationClient;
    public BDLocationListener myListener;
    private int[] zoom;
    private float currentZoom;
    private GeoEnvelope mGeoEnvelope;
    private ArrayMap<String, List<Overlay>> layers;
    private GeoCoder mGeoCodeSearch;
    private GeoCoder mReverseGeoCodeSearch;
    private PoiSearch mPoiSearch;
    private IGetRoutePlanResultListener routePlanResultListener;
    private RoutePlanSearch routePlanSearch;
    //图形集合
    private HashMap<String, BitmapDescriptor> bitmapList;
    private IGeometryClickListener geometryClickListener;
//    public BMapManager mBMapManager = null;


    public BaiduMapApiServiceImpl(View mapView, double currentMapX, double currentMapY, boolean openLoc) {
        this(mapView, currentMapX, currentMapY, 15, openLoc);
    }

    public BaiduMapApiServiceImpl(View mapView, double currentMapX, double currentMapY, int zoomDefault, boolean openLoc) {
        this(mapView, currentMapX, currentMapY, zoomDefault, 7, 21, openLoc);
    }

    public BaiduMapApiServiceImpl(View vMapView, double currentMapX, double currentMapY, int zoomDefault, int minLevel, int maxLevel, boolean openLoc) {
        this.vMapView = (MapView) vMapView;
        this.currentMapX = currentMapX;
        this.currentMapY = currentMapY;
        this.zoomDefault = zoomDefault;
        this.currentZoom = zoomDefault;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.openLoc = openLoc;
        layers = new ArrayMap<>();
    }

    public BaiduMapApiServiceImpl() {
    }

    public BaiduMapApiServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void initMapSDK(Context applicationContext) {
        SDKInitializer.initialize(applicationContext);
    }

    @Override
    public void initMapSDK(Context applicationContext, String mapDir) {
        File file = new File(mapDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String ditu = file.getAbsolutePath();
        SDKInitializer.initialize(ditu, applicationContext);
    }

    @Override
    public void initMapApi(final Context context,int type, final IMapLoadedListener loadedListener) {
        this.context = context;
//        initEngineManager(context.getApplicationContext());
        baiduMap = vMapView.getMap();
        // 隐藏缩放控件
        vMapView.showZoomControls(false);
        // 删除百度地图logo
        vMapView.removeViewAt(1);

        if (openLoc) {
            // 开启定位图层
            baiduMap.setMyLocationEnabled(true);
        }
        baiduMap.setMaxAndMinZoomLevel(maxLevel, minLevel);

        if (LocationUtils.checkLocation(currentMapX, currentMapY)) {
            LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(currentMapX, currentMapY);
            centerPoint = new LatLng(gps.getWgLat(), gps.getWgLon());
            MapStatus mMapStatus = new MapStatus.Builder().zoom(zoomDefault)
                    .target(centerPoint).build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
                    .newMapStatus(mMapStatus);
            baiduMap.setMapStatus(mMapStatusUpdate);
        } else {
            Log.e("initMapApi中心点坐标错误", "经度：" + currentMapX + "  纬度：" + currentMapY);
        }

        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

                LatLng latLng = mapStatus.target;
                LocationUtils.Gps centerGps = LocationUtils.bd09_To_Gps84(latLng.longitude, latLng.latitude);
                currentCenter = new GeoPoint(centerGps.getWgLon(), centerGps.getWgLat());
                currentZoom = mapStatus.zoom;

                int screenX = mapStatus.targetScreen.x;
                int screenY = mapStatus.targetScreen.y;
                Projection pro = baiduMap.getProjection();
                if (pro != null) {

                    if (mGeoEnvelope == null) {
                        mGeoEnvelope = new GeoEnvelope();
                    }

                    rt = pro.fromScreenLocation(new android.graphics.Point(2 * screenX, 0));
                    lb = pro.fromScreenLocation(new android.graphics.Point(0, 2 * screenY));

                    LocationUtils.Gps miGps = LocationUtils.bd09_To_Gps84(lb.longitude, lb.latitude);
                    LocationUtils.Gps maGps = LocationUtils.bd09_To_Gps84(rt.longitude, rt.latitude);


                    mGeoEnvelope.setMax(new GeoPoint(maGps.getWgLon(), maGps.getWgLat()));
                    mGeoEnvelope.setMin(new GeoPoint(miGps.getWgLon(), miGps.getWgLat()));
                    mGeoEnvelope.setCenter(new GeoPoint(currentCenter.getX(), currentCenter.getY()));

                    if (changeListener != null) {
                        changeListener.mapChanged(miGps.getWgLon(), miGps.getWgLat(), maGps.getWgLon(), maGps.getWgLat());
                    }
                }
            }
        });

        baiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                if (loadedListener != null)
                    loadedListener.loaded();
            }
        });

        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getExtraInfo() != null && marker.getExtraInfo().getString(MapConstants.BUNDLE_ID) != null) {
                    if (geometryClickListener != null) {
                        geometryClickListener.onClick(marker.getExtraInfo());
                    }
                }
                return false;
            }
        });
//        baiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
//            @Override
//            public void onMapLongClick(final LatLng latLng) {
//                Observable.create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                        PanoramaRequest request = PanoramaRequest.getInstance(context);
//                        BaiduPanoData locationPanoData = request.getPanoramaInfoByLatLon(latLng.longitude, latLng.latitude);
//                        //开发者可以判断是否有外景(街景)
//                        if (locationPanoData.hasStreetPano()) {
//                            String url = "http://pcsv1.map.bdimg.com/scape/?qt=pdata&pos=0_0&z=0&sid=" + locationPanoData.getPid();
//                            emitter.onNext(url);
//                        }
//                    }
//                }).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<String>() {
//                            @Override
//                            public void accept(String url) throws Exception {
//                                removeLayer("panorama");
//                                MarkerOptions option = new MarkerOptions()
//                                        .position(latLng)
//                                        .icon(BitmapDescriptorFactory
//                                                .fromResource(R.drawable.icon_markc));
//                                //在地图上添加Marker，并显示
//                                final Overlay overlay = baiduMap.addOverlay(option);
//                                saveToOverLayer("panorama",overlay);
//                                View view = LayoutInflater.from(context).inflate(R.layout.pano_overlay, null);
//                                ImageView pic = (ImageView) view.findViewById(R.id.panoImageView);
//                                ImageView iv_close = (ImageView) view.findViewById(R.id.iv_close);
//                                Glide.with(context).load(url).into(pic);
//                                InfoWindow mInfoWindow = new InfoWindow(view, latLng, -57);
//                                //显示InfoWindow
//                                baiduMap.showInfoWindow(mInfoWindow);
//                                pic.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        overlay.remove();
//                                        baiduMap.hideInfoWindow();
//                                        Intent intent = new Intent(context, PanoViewActivity.class);
//                                        intent.putExtra("latitude", latLng.latitude);
//                                        intent.putExtra("longitude", latLng.longitude);
//                                        context.startActivity(intent);
//                                    }
//                                });
//                                iv_close.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        overlay.remove();
//                                        baiduMap.hideInfoWindow();
//                                    }
//                                });
//                            }
//                        });
//            }
//        });
    }

    @Override
    public void initImageMapApi(Context context, IMapLoadedListener loadedListener) {

    }

//    public void initEngineManager(Context context) {
//        if (mBMapManager == null) {
//            mBMapManager = new BMapManager(context);
//        }
//        if (!mBMapManager.init(new MyGeneralListener())) {
//
//        }
//    }

//    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
//    class MyGeneralListener implements MKGeneralListener {
//
//        @Override
//        public void onGetPermissionState(int iError) {
//        }
//    }

    @Override
    public void addCrePoint(String layerId, CrePoint crePoint) {
        if (crePoint == null || crePoint.getPoint() == null || !LocationUtils.checkLocation(crePoint.getPoint().getX(), crePoint.getPoint().getY())) {
            return;
        }
        // 构建MarkerOption，用于在地图上添加Marker
        MarkerOptions markerOptions = new MarkerOptions();
        if (crePoint.getExtra() != null) {
            markerOptions.extraInfo(crePoint.getExtra());
        }
        OverlayOptions option = null;
        LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(crePoint.getPoint().getX(), crePoint.getPoint().getY());
        LatLng centerLaln = new LatLng(gps.getWgLat(), gps.getWgLon());
//        if (!TextUtils.isEmpty(crePoint.getText())) {
//            BitmapDescriptor bit = BitmapDescriptorFactory.fromView(getMarkView(crePoint));
//            option = markerOptions.position(centerLaln)
//                    .icon(bit);
//        } else {//如果名字为空，则不需要显示名字
//            option = markerOptions.position(centerLaln).icon(getBitmapDes(crePoint.getResId()));
//        }

        if (crePoint.getView() != null) {
            BitmapDescriptor bit = BitmapDescriptorFactory.fromView(crePoint.getView());
            option = markerOptions.position(centerLaln)
                    .icon(bit);
        } else if (!TextUtils.isEmpty(crePoint.getText())) {
            BitmapDescriptor bit = BitmapDescriptorFactory.fromView(getDefaultPointView(crePoint.getText(), crePoint.getResId()));
            option = markerOptions.position(centerLaln)
                    .icon(bit);
        } else {//如果名字为空，则不需要显示名字
            option = markerOptions.position(centerLaln).icon(getBitmapDes(crePoint.getResId()));
        }


        Overlay overlay = baiduMap.addOverlay(option);
        saveToOverLayer(layerId, overlay);

    }

    @Override
    public void addCrePointList(String layerId, List<CrePoint> crePointList) {
        for (CrePoint point : crePointList) {
            addCrePoint(layerId, point);
        }
    }

    @Override
    public void addCreCircle(String layerId, CreCircle creCircle) {
        if (creCircle == null || creCircle.getCenter() == null || !LocationUtils.checkLocation(creCircle.getCenter().getX(), creCircle.getCenter().getY())) {
            return;
        }
        LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(creCircle.getCenter().getX(), creCircle.getCenter().getY());
        LatLng centerLatlng = new LatLng(gps.getWgLat(), gps.getWgLon());
        CircleOptions circleOptions = new CircleOptions();
        if (creCircle.getExtra() != null) {
            circleOptions.extraInfo(creCircle.getExtra());
        }
        OverlayOptions ooCircle = circleOptions.fillColor(creCircle.getFillColor())
                .center(centerLatlng)
                .stroke(new Stroke(creCircle.getStrokeSize(), creCircle.getStrokeColor()))
                .radius(creCircle.getRadius());
        Overlay overlay = baiduMap.addOverlay(ooCircle);
        saveToOverLayer(layerId, overlay);
    }

    @Override
    public void addCreCircle(String layerId, List<CreCircle> creCircleList) {
        for (CreCircle circle : creCircleList) {
            addCreCircle(layerId, circle);
        }
    }

    @Override
    public void addCrePolyline(String layerId, CrePolyline crePolyline) {
        if (crePolyline == null || crePolyline.getPoints() == null) {
            return;
        }
        List<org.creation.common.geometry.GeoPoint> locations = crePolyline.getPoints();
        List<LatLng> points = new ArrayList<LatLng>();
        for (GeoPoint loc : locations) {
            if (LocationUtils.checkLocation(loc.getX(), loc.getY())) {
                LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(loc.getX(), loc.getY());
                points.add(new LatLng(gps.getWgLat(), gps.getWgLon()));
            }
        }
        if (points.size() > 0) {
            PolylineOptions polylineOptions = new PolylineOptions();
            if (crePolyline.getExtra() != null) {
                polylineOptions.extraInfo(crePolyline.getExtra());
            }
            polylineOptions
                    .width(crePolyline.getLineSize())
                    .points(points);
            OverlayOptions ooPolyline = null;
            if (crePolyline.hasResId()) {
                ooPolyline = polylineOptions
                        .dottedLine(true)
                        .customTexture(getBitmap(crePolyline.getLineResId()));

            } else {
                ooPolyline = polylineOptions
                        .color(crePolyline.getLineColor());
            }

            Overlay overlay = baiduMap.addOverlay(ooPolyline);
            saveToOverLayer(layerId, overlay);
        }
    }

    @Override
    public void addCrePolyline(String layerId, List<CrePolyline> crePolylineList) {
        for (CrePolyline polyline : crePolylineList) {
            addCrePolyline(layerId, polyline);
        }
    }

    private BitmapDescriptor getBitmap(String resId) {
        if (bitmapList == null) {
            bitmapList = new HashMap<>();
        }
        if (bitmapList.containsKey(resId)) {
            return bitmapList.get(resId);
        } else {
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromAsset(resId);
            bitmapList.put(resId, bitmapDescriptor);
            return bitmapDescriptor;
        }
    }

    @Override
    public void addCrePolygon(String layerId, CrePolygon crePolygon) {
        if (crePolygon == null) {
            Log.d("map", "crePolygon == null");
            return;
        }
        List<GeoPoint> locations = crePolygon.getRings();
        if (locations == null) {
            Log.d("map", "crePolygon.getPoints() == null");
            return;
        }
        List<LatLng> points = new ArrayList<LatLng>();
        for (GeoPoint loc : locations) {
            if (loc != null && LocationUtils.checkLocation(loc.getX(), loc.getY())) {
                LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(loc.getX(), loc.getY());
                points.add(new LatLng(gps.getWgLat(), gps.getWgLon()));
            }
        }
        PolygonOptions polygonOptions = new PolygonOptions();
        if (crePolygon.getExtra() != null) {
            polygonOptions.extraInfo(crePolygon.getExtra());
        }
        OverlayOptions ooPolygon = polygonOptions.points(points)
                .stroke(new Stroke(crePolygon.getStrokeSize(), crePolygon.getStrokeColor())).fillColor(crePolygon.getFillColor());
        Overlay overlay = baiduMap.addOverlay(ooPolygon);
        saveToOverLayer(layerId, overlay);
    }

    @Override
    public void addCrePolygon(String layerId, List<CrePolygon> crePolygonList) {
        for (CrePolygon crePolygon : crePolygonList) {
            addCrePolygon(layerId, crePolygon);
        }
    }

    @Override
    public void clearMap(int i) {
        if (baiduMap != null) {
            baiduMap.clear();
        }
    }

    private void saveToOverLayer(String layerId, Overlay overlay) {
        if (layers.containsKey(layerId)) {
            layers.get(layerId).add(overlay);
        } else {
            List<Overlay> overlayList = new ArrayList<>();
            overlayList.add(overlay);
            layers.put(layerId, overlayList);
        }
    }

    private BitmapDescriptor getBitmapDes(int resouseId) {
        if (bgBmMap.containsKey(resouseId)) {
            return bgBmMap.get(resouseId);
        } else {
            BitmapDescriptor bm = BitmapDescriptorFactory.fromResource(resouseId);
            bgBmMap.put(resouseId, bm);
            return bm;
        }
    }

    @Override
    public void removeLayer(String layerId) {
        if (layers.size() <= 1) {
            clearMap(0);
        } else if (layers.containsKey(layerId)) {
            List<Overlay> overlayList = layers.get(layerId);
            for (Overlay overlay : overlayList) {
                overlay.remove();
            }
        }
    }

    @Override
    public void setMapChangListener(final IMapChangeListener changeListener) {
        this.changeListener = changeListener;
    }


    public MapView getvMapView() {
        return vMapView;
    }

    public View getDefaultPointView(String name, int resourceId) {
        View view = View.inflate(context, R.layout.view_mark, null);
        TextView vMarkTitle = (TextView) view.findViewById(R.id.vId);
        ImageView vMarkerImage = (ImageView) view.findViewById(R.id.vIcon);

        vMarkTitle.setText(name);
//        vMarkTitle.setBackgroundResource(R.drawable.bg_own);
        vMarkerImage.setBackgroundResource(resourceId);
        return view;
    }

//    private View getMarkView(CrePoint point) {
//        View view = View.inflate(context, R.layout.view_mark, null);
//        TextView vMarkTitle = (TextView) view.findViewById(R.id.vMarkTitle);
//        ImageView vMarkerImage = (ImageView) view.findViewById(R.id.vMarkerImage);
//
//        vMarkTitle.setTextColor(Color.BLACK);
//        vMarkTitle.setText(point.getText());
//        vMarkTitle.setBackgroundResource(R.drawable.bg_own);
//        vMarkerImage.setBackgroundResource(point.getResId());
//
//        return view;
//    }

    private void initLocationClient() {
        myListener = new MyLocationListener();

        mLocationClient = new LocationClient(context); // 声明LocationClient类
        mLocationClient.registerLocationListener(myListener); // 注册监听函数

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(0);// 设置发起定位请求的间隔时间为100ms
        option.setOpenGps(true);
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(false);// 返回的定位结果包含手机机头的方向
        mLocationClient.setLocOption(option);
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || location.getLatitude() < 10 || location.getLongitude() < 10) {
//                locationListener.getLocation(new BaseLocation(0,0));
                return;
            }
            //altitude;
            double altitude = location.getAltitude();
            float direction = location.getDirection();
            float radius = location.getRadius();


            if(baiduMap!=null) {
                //可能之前把图层隐藏了，现在需要显示出来
                resetLocationMarker();

                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(location.getDirection()).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                baiduMap.setMyLocationData(locData);

                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                baiduMap.animateMapStatus(u);
            }

//            LocationUtils.Gps loc = LocationUtils.bd09_To_Gps84(location.getLongitude(), location.getLatitude());
//            locationListener.getLocation(new GeoPoint(loc.getWgLon(), loc.getWgLat()), location.getCity());
            locationListener.getLocation(new GeoPoint(location.getLongitude(),location.getLatitude()), location.getCity());

            stopLocation();
        }
    }

    //    隐藏定位图层
    public void hideLocationMarker() {
        baiduMap.setMyLocationEnabled(false);
    }

    //显示定位图层
    public void resetLocationMarker() {
        baiduMap.setMyLocationEnabled(true);
    }

    @Override
    public void setCenter(GeoPoint centerPoint, float zoom) {
        if (LocationUtils.checkLocation(centerPoint.getX(), centerPoint.getY())) {
            {
                LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(centerPoint.getX(), centerPoint.getY());
                setCenterAndZoom(new LatLng(gps.getWgLat(), gps.getWgLon()), zoom);
            }
        }
    }

    @Override
    public GeoPoint getCenter() {
        if (currentCenter == null) {
            LatLng center = baiduMap.getMapStatus().target;
            LocationUtils.Gps centerGps = LocationUtils.bd09_To_Gps84(center.longitude, center.latitude);
            currentCenter = new GeoPoint(centerGps.getWgLon(), centerGps.getWgLat());
        }
        return currentCenter;
    }

    @Override
    public float getCurrentZoom() {
        return currentZoom;
    }

    @Override
    public void setCurrentZoom(float zoom) {
        if (baiduMap == null)
            return;
        baiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(zoom));
        currentZoom = zoom;
    }

    @Override
    public GeoEnvelope getGeoEnvelope() {

        if (mGeoEnvelope == null && baiduMap != null) {
            int screenX = baiduMap.getMapStatus().targetScreen.x;
            int screenY = baiduMap.getMapStatus().targetScreen.y;
            Projection pro = baiduMap.getProjection();
            if (pro != null) {
                if (mGeoEnvelope == null) {
                    mGeoEnvelope = new GeoEnvelope();
                }

                rt = pro.fromScreenLocation(new android.graphics.Point(2 * screenX, 0));
                lb = pro.fromScreenLocation(new android.graphics.Point(0, 2 * screenY));

                LocationUtils.Gps miGps = LocationUtils.bd09_To_Gps84(lb.longitude, lb.latitude);
                LocationUtils.Gps maGps = LocationUtils.bd09_To_Gps84(rt.longitude, rt.latitude);
                mGeoEnvelope.setMax(new GeoPoint(maGps.getWgLon(), maGps.getWgLat()));
                mGeoEnvelope.setMin(new GeoPoint(miGps.getWgLon(), miGps.getWgLat()));

                LatLng centerLatlng = baiduMap.getMapStatus().target;
                LocationUtils.Gps centerGps = LocationUtils.bd09_To_Gps84(centerLatlng.longitude, centerLatlng.latitude);
                mGeoEnvelope.setCenter(new GeoPoint(centerGps.getWgLon(), centerGps.getWgLat()));

            }
        }
        return mGeoEnvelope;
    }

    private void setCenterAndZoom(LatLng latLng, float zoom) {
        MapStatus mMapStatus = null;
        if (zoom >= 7 && zoom <= 21 && LocationUtils.checkLocation(latLng.longitude, latLng.latitude)) {
            mMapStatus = new MapStatus.Builder().zoom(zoom).target(latLng).build();
        } else if (LocationUtils.checkLocation(latLng.longitude, latLng.latitude)) {
            mMapStatus = new MapStatus.Builder().target(latLng).build();
        } else if (zoom >= 7 && zoom <= 21) {
            mMapStatus = new MapStatus.Builder().zoom(zoom).build();
        }

        if (mMapStatus != null) {
            // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            // 改变地图状态
            baiduMap.setMapStatus(mMapStatusUpdate);
        }
    }

    @Override
    public void setLocationLayer(IOnLocationListener locationListener) {
        initLocationClient();
        this.locationListener = locationListener;
    }

    @Override
    public boolean startLocation() {
        if (mLocationClient == null) {
            return false;
        }
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        mLocationClient.requestLocation();
        return true;
    }

    @Override
    public void stopLocation() {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }

    @Override
    public void drawHeatMap(List<GeoPoint> heatPoints) {
        if (baiduMap != null) {
            //清除热点
            if (heatMap != null)
                heatMap.removeHeatMap();

            //添加热点
            List<LatLng> heatList = new ArrayList<LatLng>();
            LocationUtils.Gps gps = null;
            LatLng latlng = null;
            for (GeoPoint loc : heatPoints) {
                if (loc != null && LocationUtils.checkLocation(loc.getX(), loc.getY())) {
                    gps = LocationUtils.gps84_To_Bd09(loc.getX(), loc.getY());
                    latlng = new LatLng(gps.getWgLat(), gps.getWgLon());
                    heatList.add(latlng);
                }
            }

            heatMap = new HeatMap.Builder().data(heatList).radius(50).build();
            baiduMap.addHeatMap(heatMap);
        }
    }

    @Override
    public void setAutoZoom(String layerId, List<CrePoint> crePoints, boolean isBaiduLoc) {
        List<LatLng> latLngList = new ArrayList<>();
        for (CrePoint point : crePoints) {
            if (point != null && LocationUtils.checkLocation(point.getPoint())) {
                LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(point.getPoint().getX(), point.getPoint().getY());
                LatLng latLng = new LatLng(gps.getWgLat(), gps.getWgLon());
                latLngList.add(latLng);
            }
        }
        setAutoZoom(latLngList, isBaiduLoc);
    }

    @Override
    public void setAutoZoom(List<GeoPoint> points) {
        List<LatLng> latLngList = new ArrayList<>();
        for (GeoPoint point : points) {
            if (point != null && LocationUtils.checkLocation(point)) {
                LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(point.getX(), point.getY());
                LatLng latLng = new LatLng(gps.getWgLat(), gps.getWgLon());
                latLngList.add(latLng);
            }
        }
        setAutoZoom(latLngList, false);
    }

    private void setAutoZoom(List<LatLng> latLngs, boolean isBaiduLoc) {
        if (vMapView == null || baiduMap == null)
            return;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng point : latLngs) {
            builder = builder.include(point);
        }
        LatLngBounds latlngBounds = builder.build();
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds, vMapView.getWidth(), vMapView.getHeight());
        baiduMap.animateMapStatus(u);
    }

    @Override
    public void setOnlineMap(String division, String zxyxServerUrl, String zxyxPOIServerUrl) {

    }

    @Override
    public void setOfflineMap() {

    }

    @Override
    public void geocoding(final String address, final IGeocodeListener listener, String cityName) {
        if (mGeoCodeSearch == null)
            mGeoCodeSearch = GeoCoder.newInstance();
        mGeoCodeSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            public void onGetGeoCodeResult(GeoCodeResult result) {

                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    listener.result(new GeoLocation());
                } else {
                    LatLng latLng = result.getLocation();
                    LocationUtils.Gps gps = LocationUtils.bd09_To_Gps84(latLng.longitude, latLng.latitude);
                    listener.result(new GeoLocation(gps.getWgLon(), gps.getWgLat(), address));
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            }
        });
        mGeoCodeSearch.geocode(new GeoCodeOption()
                .city(cityName)
                .address(address));
    }

    @Override
    public void reverseGeocode(final GeoPoint location, final IReverseGeocodeListener listener) {
        if (mReverseGeoCodeSearch == null)
            mReverseGeoCodeSearch = GeoCoder.newInstance();
        mReverseGeoCodeSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            public void onGetGeoCodeResult(GeoCodeResult result) {
            }

            @Override

            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    listener.result(new GeoLocation());
                } else {
                    String address = result.getSematicDescription();
//                    if (EmptyUtil.isEmpty(result.getPoiList())) {·
//                        address = result.getSematicDescription();
//                    } else {
//                        PoiInfo info = result.getPoiList().get(0);
//                        address = info.name + "-" + info.address;
//
//                    }
                    listener.result(new GeoLocation(location.getX(), location.getY(), address));
                }
            }
        });
        if (location != null) {
            LocationUtils.Gps gps = LocationUtils.gps84_To_Bd09(location.getX(), location.getY());
            mReverseGeoCodeSearch.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(new LatLng(gps.getWgLat(), gps.getWgLon())));
        }

    }

    @Override
    public void getPoi(final IGetPoiListener listener, String address, String cityName) {
        if (mPoiSearch == null)
            mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    listener.getPoi(null);
                } else {
                    List<PoiInfo> list = poiResult.getAllPoi();
                    List<GeoLocation> listPoint = new ArrayList<>();
                    for (PoiInfo info : list) {
                        if (EmptyUtil.isEmpty(info.name) || EmptyUtil.isEmpty(info.location))
                            break;
                        LocationUtils.Gps gps = LocationUtils.bd09_To_Gps84(info.location.longitude, info.location.latitude);
                        GeoLocation point = new GeoLocation(gps.getWgLon(), gps.getWgLat(), info.name + "-" + info.address);
                        listPoint.add(point);
                    }
                    listener.getPoi(listPoint);
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city(cityName)
                .cityLimit(false)
                .keyword(address)
                .pageCapacity(10)
                .pageNum(0));
    }

    @Override
    public void getPoi(final IGetPoiListener listener, GeoPoint location) {
        if (mReverseGeoCodeSearch == null)
            mReverseGeoCodeSearch = GeoCoder.newInstance();
        mReverseGeoCodeSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            public void onGetGeoCodeResult(GeoCodeResult result) {
            }

            @Override

            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    listener.getPoi(null);
                } else {
                    List<PoiInfo> list = result.getPoiList();
                    List<GeoLocation> listPoint = new ArrayList<>();
                    for (PoiInfo info : list) {
                        if (EmptyUtil.isEmpty(info.address) || EmptyUtil.isEmpty(info.location))
                            break;
                        GeoLocation point = new GeoLocation(info.location.longitude, info.location.latitude, info.address);
                        listPoint.add(point);
                    }
                    listener.getPoi(listPoint);
                }
            }
        });
        mReverseGeoCodeSearch.reverseGeoCode(new ReverseGeoCodeOption()
                .location(new LatLng(location.getY(), location.getX())));
    }

    @Override
    public void onResume() {
        vMapView.onResume();
    }

    @Override
    public void onDestroy() {
        if (vMapView != null) {
            vMapView.removeAllViews();
            vMapView.onDestroy();
        }
        if (mGeoCodeSearch != null)
            mGeoCodeSearch.destroy();
        if (mReverseGeoCodeSearch != null)
            mReverseGeoCodeSearch.destroy();
        if (mPoiSearch != null)
            mPoiSearch.destroy();
    }


    private Map<String, Object> getZoom(List<CrePoint> pointList) {
        int defZoom = (int) baiduMap.getMapStatus().zoom;
        Map<String, Object> map = new HashMap<>();
        if (zoom == null) {
            //                10      11   12    13   14    15   16   17  18   19  20  21
            zoom = new int[]{20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5};
        }

        if (pointList == null || pointList.size() == 0) {
            return null;
        }

        double maxX = 0;
        double maxY = 0;
        double minX = 0;
        double minY = 0;

        for (CrePoint cp : pointList) {
            if (cp != null && LocationUtils.checkLocation(cp.getPoint())) {
                maxX = checkMax(maxX, cp.getPoint().getX());
                minX = checkMin(minX, cp.getPoint().getX());
                maxY = checkMax(maxY, cp.getPoint().getY());
                minY = checkMin(minY, cp.getPoint().getY());
            }
        }

        double centerX = (maxX + minX) / 2;
        double centerY = (maxY + minY) / 2;
        map.put("centerLoc", new GeoPoint(centerX, centerY));

//        地图横向距离大约为比例尺的10倍，地图纵向距离大约为比例尺的15倍。（具体倍数是多少根据项目中地图所占位置确定）
        int distanceX = (int) DistanceUtil.getDistance(new LatLng(maxY, maxX), new LatLng(maxY, minX)) / 10;
        int distanceY = (int) DistanceUtil.getDistance(new LatLng(maxY, maxX), new LatLng(minY, maxX)) / 15;


        int distance = distanceX > distanceY ? distanceX : distanceY;
        for (int i = 10; i <= 21; i++) {
            if (zoom[i - 10] <= distance) {
                map.put("zoom", i - 1);
                return map;
            }
        }
        map.put("zoom", defZoom);
        return map;
    }

    private double checkMax(double oldParams, double newParams) {
        if (oldParams == 0) {
            return newParams;
        } else {
            return oldParams > newParams ? oldParams : newParams;
        }
    }

    private double checkMin(double oldParams, double newParams) {
        if (oldParams == 0) {
            return newParams;
        } else {
            return oldParams < newParams ? oldParams : newParams;
        }
    }

    @Override
    public void routePlan(GeoLocation startPoint, GeoLocation endPoint, IGetRoutePlanResultListener routePlanResultListener) {
        this.routePlanResultListener = routePlanResultListener;
        if (routePlanSearch == null) {
            routePlanSearch = RoutePlanSearch.newInstance();
            routePlanSearch.setOnGetRoutePlanResultListener(this);
        }
        LocationUtils.Gps startGps = LocationUtils.gps84_To_Bd09(startPoint.getPoint().getX(), startPoint.getPoint().getY());
        LocationUtils.Gps endGps = LocationUtils.gps84_To_Bd09(endPoint.getPoint().getX(), endPoint.getPoint().getY());
        PlanNode stNode = PlanNode.withLocation(new com.baidu.mapapi.model.LatLng(startGps.getWgLat(), startGps.getWgLon()));
        PlanNode enNode = PlanNode.withLocation(new com.baidu.mapapi.model.LatLng(endGps.getWgLat(), endGps.getWgLon()));
        //驾车导航
        routePlanSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode).to(enNode));
    }

    @Override
    public void measureMap(int type, IMeasureResultListener listener) {

    }

    @Override
    public void DrawInMap(int type) {

    }


    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        //先不做
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
        //先不做
    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
        //先不做
    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            if (routePlanResultListener != null) {
                routePlanResultListener.onRoutePlanResultFailed(context.getString(R.string.route_plan_result_err_1));
            }
        }
        if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            if (routePlanResultListener != null) {
                routePlanResultListener.onRoutePlanResultFailed(context.getString(R.string.route_plan_result_err_1));
            }
            return;
        }

        if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
            if (drivingRouteResult.getRouteLines().size() > 0) {
                List<DrivingRouteLine> listRoutelines = new ArrayList<>();
                if (drivingRouteResult.getRouteLines().size() > 3) {
                    listRoutelines.add(drivingRouteResult.getRouteLines().get(0));
                    listRoutelines.add(drivingRouteResult.getRouteLines().get(1));
                    listRoutelines.add(drivingRouteResult.getRouteLines().get(2));
                } else {
                    listRoutelines = drivingRouteResult.getRouteLines();
                }
                if (routePlanResultListener != null) {
                    routePlanResultListener.onRoutePlanResultSuccess(parseToCreDrivingRouteLine(listRoutelines));
                }
            } else {
                if (routePlanResultListener != null) {
                    routePlanResultListener.onRoutePlanResultFailed(context.getString(R.string.route_plan_result_err_2));
                }
            }
        } else {
            if (routePlanResultListener != null) {
                routePlanResultListener.onRoutePlanResultFailed(context.getString(R.string.route_plan_result_err_2));
            }
        }


    }

    /**
     * 百度地图驾车路线
     * 转为
     * cre驾车路线
     */
    private List<CreDrivingRouteLine> parseToCreDrivingRouteLine(List<DrivingRouteLine> listRoutelines) {
        List<CreDrivingRouteLine> creDrivingRouteLineList = new ArrayList<>();
        CreDrivingRouteLine creDrivingRouteLine = null;
        for (DrivingRouteLine line : listRoutelines) {
            creDrivingRouteLine = new CreDrivingRouteLine();
            RouteNode startNode = line.getStarting();
            if (startNode != null && startNode.getLocation() != null) {
                LocationUtils.Gps gpsStart = LocationUtils.bd09_To_Gps84(startNode.getLocation().longitude, startNode.getLocation().latitude);
                creDrivingRouteLine.setStartNode(new CreRouteNode(new GeoPoint(gpsStart.getWgLon(), gpsStart.getWgLat())));
            }
            RouteNode endNode = line.getTerminal();
            if (endNode != null && endNode.getLocation() != null) {
                LocationUtils.Gps gpsEnd = LocationUtils.bd09_To_Gps84(endNode.getLocation().longitude, endNode.getLocation().latitude);
                creDrivingRouteLine.setEndNode(new CreRouteNode(new GeoPoint(gpsEnd.getWgLon(), gpsEnd.getWgLat())));
            }

            List<DrivingRouteLine.DrivingStep> routeStepList = line.getAllStep();
            List<GeoPoint> ways = new ArrayList<>();
            if (routeStepList != null) {
                for (DrivingRouteLine.DrivingStep step : routeStepList) {
                    if (step != null && step.getWayPoints() != null) {
                        List<LatLng> wayPoints = step.getWayPoints();
                        for (LatLng latLng : wayPoints) {
                            LocationUtils.Gps gpsNode = LocationUtils.bd09_To_Gps84(latLng.longitude, latLng.latitude);
                            GeoPoint point = new GeoPoint(gpsNode.getWgLon(), gpsNode.getWgLat());
                            ways.add(point);
                        }
                    }
                }
            }

            creDrivingRouteLine.setDistance(line.getDistance());
            creDrivingRouteLine.setCongestionDistance(line.getCongestionDistance());
            creDrivingRouteLine.setDuration(line.getDuration());
            creDrivingRouteLine.setLightNum(line.getLightNum());


            creDrivingRouteLine.setWayList(ways);
            creDrivingRouteLineList.add(creDrivingRouteLine);
        }
        return creDrivingRouteLineList;
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
        //先不做
    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
        //先不做
    }

    public IGeometryClickListener getGeometryClickListener() {
        return geometryClickListener;
    }

    public void setGeometryClickListener(IGeometryClickListener geometryClickListener) {
        this.geometryClickListener = geometryClickListener;
    }
}
