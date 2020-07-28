package common.baidunavigationlibrary.offlinemap.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;

import java.util.ArrayList;
import java.util.List;

import common.baidunavigationlibrary.R;
import common.baselibrary.baseview.BaseActivity;

/**
 * 离线地图下载
 */
public class OfflineMapActivity extends BaseActivity implements MKOfflineMapListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String city;
    private OfflineMapTabAdapter tabAdapter;
    private boolean needUpdateCityList;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_offline_map;
    }

    @Override
    protected void getIntentData(Intent intent) {
        city = intent.getStringExtra("city");
    }

    @Override
    protected void initWidget() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    protected void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1 && needUpdateCityList) {
                    tabAdapter.updateCityList();
                    needUpdateCityList = false;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void init() {
        List<TabInfo> listTabInfo = new ArrayList<>();
        listTabInfo.add(new TabInfo(TabInfo.DOWNLOAD, "下载管理"));
        listTabInfo.add(new TabInfo(TabInfo.CITY_LIST, "城市列表"));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabAdapter = new OfflineMapTabAdapter(getSupportFragmentManager(), listTabInfo, city);
        viewPager.setAdapter(tabAdapter);
    }

    class TabInfo {
        public static final int DOWNLOAD = 1;
        public static final int CITY_LIST = 2;
        public int id;
        public String title;

        public TabInfo(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }

    public static void startActivity(Context context, String city) {
        Intent intent = new Intent(context, OfflineMapActivity.class);
        intent.putExtra("city", city);
        context.startActivity(intent);
    }

    @Override
    public void onGetOfflineMapState(int type, int state) {
        switch (type) {
            case MKOfflineMap.TYPE_DOWNLOAD_UPDATE: {
                tabAdapter.updateDownload();
                needUpdateCityList = true;
            }
            break;
            case MKOfflineMap.TYPE_NEW_OFFLINE:
                // 有新离线地图安装
                break;
            case MKOfflineMap.TYPE_VER_UPDATE:
                // 版本更新提示
                break;
            default:
                break;
        }
    }

    public void changeToMapDownload() {
        viewPager.setCurrentItem(0);
    }

    public void setNeedUpdateCityList(boolean needUpdateCityList) {
        this.needUpdateCityList = needUpdateCityList;
    }
}
