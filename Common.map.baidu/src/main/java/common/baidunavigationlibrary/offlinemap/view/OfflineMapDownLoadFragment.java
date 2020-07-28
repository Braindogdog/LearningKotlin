package common.baidunavigationlibrary.offlinemap.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
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
import common.baselibrary.baseview.CommomDialog;
import common.baselibrary.irecyclerview.IRecyclerView;
import common.baselibrary.irecyclerview.itemdecoration.GetGroupMessageListener;
import common.baselibrary.irecyclerview.itemdecoration.GroupItemDecoration;

/**
 * Created by chasen on 2018/4/13.
 */

public class OfflineMapDownLoadFragment extends BaseFragment {

    private IRecyclerView iRecyclerView;
    private String city;
    private MKOfflineMap mOffline;
    private List<CityBean> cityList;
    private OfflineMapDownLoadMapAdapter downLoadMapAdapter;
    private CommomDialog commomDialog;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_download_map;
    }

    @Override
    protected void initWidget(View view) {
        iRecyclerView = view.findViewById(R.id.irecyclerview);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {
        commomDialog = new CommomDialog(context)
                .setContent("确定要删除此城市的离线地图吗？");
        city = getArguments().getString("city");
        mOffline = new MKOfflineMap();
        OfflineMapActivity offlineMapActivity = (OfflineMapActivity) context;
        mOffline.init(offlineMapActivity);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        downLoadMapAdapter = new OfflineMapDownLoadMapAdapter(new OnDownLoadMapClickListener() {
            @Override
            public void clickView(Object object) {

            }

            @Override
            public void clickDownLoad(Object object) {
                MKOLSearchRecord searchRecord = (MKOLSearchRecord) object;
                mOffline.start(searchRecord.cityID);
            }

            @Override
            public void clickDelete(final Object object) {
                commomDialog.setOnSubmitListener(new CommomDialog.OnSubmitListener() {
                    @Override
                    public void onClick() {
                        MKOLUpdateElement updateElement = (MKOLUpdateElement) object;
                        mOffline.remove(updateElement.cityID);
                        refresh();
                        OfflineMapActivity activity = (OfflineMapActivity) context;
                        activity.setNeedUpdateCityList(true);
                        commomDialog.dismiss();
                    }
                })
                        .show();
            }
        }, city);
        initData();
        initDecoration();
        iRecyclerView.setAdapter(downLoadMapAdapter);
        downLoadMapAdapter.setList(cityList);
    }

    private void initData() {
        cityList = new ArrayList<>();
        StringBuffer loadingCity = new StringBuffer("");
        StringBuffer downloadedCity = new StringBuffer("");
        ArrayList<MKOLUpdateElement> records2 = mOffline.getAllUpdateInfo();
        if (!EmptyUtil.isEmpty(records2)) {
            for (MKOLUpdateElement updateElement : records2) {
                CityBean cityBean = new CityBean();
                cityBean.setMkolUpdateElement(updateElement);
                cityBean.setGroupName(CityBean.HAVE_DOWNLOAD_CITY);
                cityList.add(cityBean);
                if (updateElement.ratio == 100)
                    downloadedCity.append(updateElement.cityName);
                else
                    loadingCity.append(updateElement.cityName);
            }
        }
        ArrayList<MKOLSearchRecord> records1 = mOffline.getHotCityList();
        if (!EmptyUtil.isEmpty(records1)) {
            for (MKOLSearchRecord searchRecord : records1) {
                if (downloadedCity.toString().contains(searchRecord.cityName)) {
                    continue;
                }
                if (loadingCity.toString().contains(searchRecord.cityName)) {
                    continue;
                }
                CityBean cityBean = new CityBean();
                cityBean.setMkolSearchRecord(searchRecord);
                cityBean.setGroupName(CityBean.RECOMMEND_CITY);
                cityList.add(cityBean);
            }
        }
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

    public static OfflineMapDownLoadFragment getInstance(String city) {
        Bundle args = new Bundle();
        args.putString("city", city);
        OfflineMapDownLoadFragment fgm = new OfflineMapDownLoadFragment();
        fgm.setArguments(args);
        return fgm;
    }

    public void refresh() {
        initData();
        downLoadMapAdapter.setList(cityList);
    }

    @Override
    public void onDestroy() {
        mOffline.destroy();
        super.onDestroy();
    }

}
