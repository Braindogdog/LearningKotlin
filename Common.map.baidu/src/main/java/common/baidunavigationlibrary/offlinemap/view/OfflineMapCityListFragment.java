package common.baidunavigationlibrary.offlinemap.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;

import java.util.ArrayList;
import java.util.List;

import common.baidunavigationlibrary.R;
import common.baidunavigationlibrary.offlinemap.model.CityBean;
import common.baselibrary.baseutil.DensityUtil;
import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseview.BaseFragment;
import common.baselibrary.irecyclerview.IRecyclerView;
import common.baselibrary.irecyclerview.itemdecoration.GetGroupMessageListener;
import common.baselibrary.irecyclerview.itemdecoration.GroupItemDecoration;

/**
 * Created by chasen on 2018/4/13.
 */

public class OfflineMapCityListFragment extends BaseFragment {
    private IRecyclerView iRecyclerView;
    private EditText et_search;
    private ImageView iv_search;
    private String city;

    private MKOfflineMap mOffline = null;
    private OfflineMapCityAdapter mAdapter;
    private List<CityBean> cityList;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_city_list;
    }

    @Override
    protected void initWidget(View view) {
        iRecyclerView = view.findViewById(R.id.irecyclerview);
        et_search = view.findViewById(R.id.et_search);
        iv_search = view.findViewById(R.id.iv_search);
    }

    @Override
    protected void setListener() {
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!EmptyUtil.isEmpty(et_search.getText().toString())) {
                    searchCity(et_search.getText().toString());
                }
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!EmptyUtil.isEmpty(charSequence.toString())) {
                    searchCity(charSequence.toString());
                } else {
                    refresh();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void init() {
        city = getArguments().getString("city");
        mOffline = new MKOfflineMap();
        OfflineMapActivity offlineMapActivity = (OfflineMapActivity) context;
        mOffline.init(offlineMapActivity);
        initRecyclerView();
    }

    private void initData() {
        cityList = new ArrayList<>();
        //先获取已下载信息
        StringBuffer loadingCity = new StringBuffer("");
        StringBuffer downloadedCity = new StringBuffer("");
        ArrayList<MKOLUpdateElement> recordsUpdate = mOffline.getAllUpdateInfo();
        if (!EmptyUtil.isEmpty(recordsUpdate)) {
            for (MKOLUpdateElement updateElement : recordsUpdate) {
                if (updateElement.ratio == 100)
                    downloadedCity.append(updateElement.cityName);
                else
                    loadingCity.append(updateElement.cityName);
            }
        }
        ArrayList<MKOLSearchRecord> records1 = mOffline.getHotCityList();
        if (!EmptyUtil.isEmpty(records1)) {
            for (MKOLSearchRecord searchRecord : records1) {
                CityBean cityBean = new CityBean();
                cityBean.setMkolSearchRecord(searchRecord);
                cityBean.setGroupName(CityBean.HOT_CITY);
                if (downloadedCity.toString().contains(searchRecord.cityName)) {
                    cityBean.setComplete(true);
                }
                if (loadingCity.toString().contains(searchRecord.cityName)) {
                    cityBean.setLoading(true);
                }
                cityList.add(cityBean);
            }
        }
        ArrayList<MKOLSearchRecord> allRecord = mOffline.getOfflineCityList();
        ArrayList<MKOLSearchRecord> records2 = new ArrayList<>();
        if (allRecord == null) {
            allRecord = new ArrayList<>();
        }
        //过滤掉外国地图信息
        for (MKOLSearchRecord record : allRecord) {
            //9000 是台湾省
            if (record.cityID < 10000) {
                records2.add(record);
            }
        }
        if (!EmptyUtil.isEmpty(records2)) {
            for (MKOLSearchRecord searchRecord : records2) {
                CityBean cityBean = new CityBean();
                cityBean.setMkolSearchRecord(searchRecord);
                if (downloadedCity.toString().contains(searchRecord.cityName)) {
                    cityBean.setComplete(true);
                }
                if (loadingCity.toString().contains(searchRecord.cityName)) {
                    cityBean.setLoading(true);
                }
                cityBean.setGroupName(CityBean.ALL_CITY);
                if (!EmptyUtil.isEmpty(searchRecord.childCities)) {
                    List<CityBean> list = new ArrayList<>();
                    for (MKOLSearchRecord searchRecordChild : searchRecord.childCities) {
                        CityBean bean = new CityBean();
                        bean.setMkolSearchRecord(searchRecordChild);
                        bean.setGroupName(CityBean.ALL_CITY);
                        if (downloadedCity.toString().contains(searchRecordChild.cityName)) {
                            bean.setComplete(true);
                        }
                        if (loadingCity.toString().contains(searchRecordChild.cityName)) {
                            bean.setLoading(true);
                        }
                        bean.setChildren(true);
                        list.add(bean);
                    }
                    cityBean.setListChild(list);
                }
                cityList.add(cityBean);
            }
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        iRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        initData();
        mAdapter = new OfflineMapCityAdapter(cityList, city, new OnDownLoadMapClickListener() {
            @Override
            public void clickView(Object object) {

            }

            @Override
            public void clickDownLoad(Object object) {
                MKOLSearchRecord searchRecord = (MKOLSearchRecord) object;
                mOffline.start(searchRecord.cityID);
                OfflineMapActivity activity = (OfflineMapActivity) context;
                activity.changeToMapDownload();
            }

            @Override
            public void clickDelete(Object object) {
            }
        });
        iRecyclerView.setAdapter(mAdapter);
        //添加悬浮布局
        initDecoration();
    }

    /**
     * 添加悬浮布局
     */
    private void initDecoration() {
        GroupItemDecoration decoration = GroupItemDecoration.Builder
                .init(new GetGroupMessageListener() {
                    @Override
                    public String getGroupName(int position) {
                        //获取组名，用于判断是否是同一组
                        if (cityList.size() > position && position >= 0) {
                            return cityList.get(position).getGroupName();
                        }
                        return null;
                    }

                    @Override
                    public View getGroupView(int position) {
                        //获取自定定义的组View
                        if (cityList.size() > position && position >= 0) {
                            View view = getLayoutInflater().inflate(R.layout.item_map_group, null, false);
                            TextView tv_name = view.findViewById(R.id.tv_name);
                            tv_name.setText(cityList.get(position).getGroupName());
                            return view;
                        } else {
                            return null;
                        }
                    }
                })
                //设置高度
                .setGroupHeight(DensityUtil.dp2px(context, 32))
                .build();
        iRecyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onDestroy() {
        mOffline.destroy();
        super.onDestroy();
    }

    public void refresh() {
        initData();
        mAdapter.updateData(cityList);
    }

    private void searchCity(String city) {
        if (cityList == null)
            cityList = new ArrayList<>();
        StringBuffer loadingCity = new StringBuffer("");
        StringBuffer downloadedCity = new StringBuffer("");
        ArrayList<MKOLUpdateElement> recordsUpdate = mOffline.getAllUpdateInfo();
        if (!EmptyUtil.isEmpty(recordsUpdate)) {
            for (MKOLUpdateElement updateElement : recordsUpdate) {
                if (updateElement.ratio == 100)
                    downloadedCity.append(updateElement.cityName);
                else
                    loadingCity.append(updateElement.cityName);
            }
        }
        ArrayList<MKOLSearchRecord> records = mOffline.searchCity(city);
        if (!EmptyUtil.isEmpty(records)) {
            cityList.clear();
            for (MKOLSearchRecord searchRecord : records) {
                CityBean cityBean = new CityBean();
                cityBean.setMkolSearchRecord(searchRecord);
                if (downloadedCity.toString().contains(searchRecord.cityName)) {
                    cityBean.setComplete(true);
                }
                if (loadingCity.toString().contains(searchRecord.cityName)) {
                    cityBean.setLoading(true);
                }
                cityBean.setGroupName(CityBean.ALL_CITY);
                if (!EmptyUtil.isEmpty(searchRecord.childCities)) {
                    List<CityBean> list = new ArrayList<>();
                    for (MKOLSearchRecord searchRecordChild : searchRecord.childCities) {
                        CityBean bean = new CityBean();
                        bean.setMkolSearchRecord(searchRecordChild);
                        bean.setGroupName(CityBean.ALL_CITY);
                        if (downloadedCity.toString().contains(searchRecordChild.cityName)) {
                            bean.setComplete(true);
                        }
                        if (loadingCity.toString().contains(searchRecordChild.cityName)) {
                            bean.setLoading(true);
                        }
                        bean.setChildren(true);
                        list.add(bean);
                    }
                    cityBean.setListChild(list);
                }
                cityList.add(cityBean);
            }
            mAdapter.updateData(cityList);
        }
    }

    public static OfflineMapCityListFragment getInstance(String city) {
        Bundle args = new Bundle();
        args.putString("city", city);
        OfflineMapCityListFragment fgm = new OfflineMapCityListFragment();
        fgm.setArguments(args);
        return fgm;
    }
}
