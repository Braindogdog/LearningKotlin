package common.baidunavigationlibrary.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.baidu.mapapi.map.MapView;

import org.creation.common.geometry.GeoLocation;
import org.creation.common.geometry.GeoPoint;

import java.util.List;

import common.baidunavigationlibrary.BaiduMapApiServiceImpl;
import common.baidunavigationlibrary.R;
import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseutil.ToastUtil;
import common.baselibrary.baseview.BaseActivity;
import common.baselibrary.baseview.CommonTitleBar;
import common.baselibrary.irecyclerview.IRecyclerView;
import common.baselibrary.irecyclerview.OnItemClickListener;
import common.map.api.IMapApiService;
import common.map.api.listener.IGetPoiListener;
import common.map.api.listener.IMapChangeListener;
import common.map.api.listener.IOnLocationListener;
import common.map.api.listener.IReverseGeocodeListener;

/**
 * Created by chasen on 2018/7/16.
 * 选择地址
 */

public class CommonSelectAddressActiviy extends BaseActivity implements IGetPoiListener, IReverseGeocodeListener {
    private MapView mapView;
    private ImageView iv_location;
    private ImageView iv_iconhead, iv_sha;
    private EditText et_address;
    private IMapApiService mapApiService;
    private GeoPoint currentLocation;
    private String city = "北京";
    private IRecyclerView irecyclerview_address;
    private AddressAdapter addressAdapter;
    private boolean needSearch = true;
    private ImageView iv_close;
    private GeoLocation location;
    private double centerX;
    private double centerY;

    public static final String ADDRESS = "address";
    public static final String POINT = "point";

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void getIntentData(Intent intent) {
        location = (GeoLocation) intent.getSerializableExtra("location");
        if (location != null) {
            GeoPoint point = location.getPoint();
            if (point != null) {
                centerX = point.getX();
                centerY = point.getY();
            }
        }
    }

    @Override
    protected void initWidget() {
        irecyclerview_address = findViewById(R.id.irecyclerview_address);
        mapView = findViewById(R.id.mapView);
        iv_location = findViewById(R.id.iv_location);
        iv_iconhead = findViewById(R.id.iv_iconhead);
        et_address = findViewById(R.id.et_address);
        iv_sha = findViewById(R.id.iv_sha);
        iv_close = findViewById(R.id.iv_close);
    }

    @Override
    protected void init() {
        addressAdapter = new AddressAdapter();
        irecyclerview_address.setLayoutManager(new LinearLayoutManager(context));
        irecyclerview_address.setIAdapter(addressAdapter);
        titleBar.setRightTitle("确定");
        mapApiService = new BaiduMapApiServiceImpl(mapView, centerX, centerY, true);
        mapApiService.initMapApi(context,1000, null);
        mapApiService.setLocationLayer(new IOnLocationListener() {
            @Override
            public void getLocation(GeoPoint location, String cityName) {
                city = cityName;
                if (centerX == 0 || centerY == 0) {
                    mapApiService.setCenter(location, 15);
                }
            }
        });
        mapApiService.setMapChangListener(new IMapChangeListener() {
            @Override
            public void mapChanged(double xmin, double ymin, double xmax, double ymax) {
                iv_iconhead.startAnimation(AnimationUtils.loadAnimation(
                        context, R.anim.bm_loc_pin));
                iv_sha.startAnimation(AnimationUtils.loadAnimation(
                        context, R.anim.bm_loc_pin_sha));
                currentLocation = mapApiService.getCenter();
                mapApiService.reverseGeocode(currentLocation, CommonSelectAddressActiviy.this);
            }
        });
        mapApiService.startLocation();
    }

    @Override
    protected void setListener() {
        titleBar.setOnTitleRightClickListener(new CommonTitleBar.onTitleRightClickListener() {
            @Override
            public void onClick() {
                String address = et_address.getText().toString();
                if (EmptyUtil.isEmpty(address)) {
                    ToastUtil.showShort(context, "请填写地址信息");
                    return;
                }
                if (currentLocation == null)
                    currentLocation = mapApiService.getCenter();
                Intent intent = new Intent();
                intent.putExtra(ADDRESS, address);
                intent.putExtra(POINT, currentLocation);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        iv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapApiService.startLocation();
            }
        });
        et_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!EmptyUtil.isEmpty(s)) {
                    if (needSearch) {
                        mapApiService.getPoi(CommonSelectAddressActiviy.this, s.toString(), city);
                    }else
                        needSearch = true;
                } else {
                    irecyclerview_address.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v, Object object) {
                irecyclerview_address.setVisibility(View.GONE);
                iv_close.setVisibility(View.GONE);
                GeoLocation location = (GeoLocation) object;
                currentLocation = location.getPoint();
                mapApiService.setCenter(location.getPoint(), 15);
                needSearch = false;
                et_address.setText(location.getDescription());
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irecyclerview_address.setVisibility(View.GONE);
                iv_close.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void getPoi(List<GeoLocation> list) {
        if (!EmptyUtil.isEmpty(list)) {
            irecyclerview_address.setVisibility(View.VISIBLE);
            iv_close.setVisibility(View.VISIBLE);
            addressAdapter.setList(list);
        }
    }

    @Override
    public void result(GeoLocation location) {
        needSearch = false;
        et_address.setText(location.getDescription());
    }

    @Override
    public void onResume() {
        super.onResume();
        mapApiService.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapApiService.onDestroy();
    }

    public static Intent getIntent(Context context, GeoLocation location){
        Intent intent = new Intent(context, CommonSelectAddressActiviy.class);
        intent.putExtra("location", location);
        return intent;
    }

    public static void startActivityForResult(Activity activity, int requestCode, GeoLocation location) {
        Intent intent = new Intent(activity, CommonSelectAddressActiviy.class);
        intent.putExtra("location", location);
        activity.startActivityForResult(intent, requestCode);
    }

}
