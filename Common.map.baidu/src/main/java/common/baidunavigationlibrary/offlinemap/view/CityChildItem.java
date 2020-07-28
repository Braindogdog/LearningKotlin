package common.baidunavigationlibrary.offlinemap.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;

import common.baidunavigationlibrary.R;
import common.baidunavigationlibrary.offlinemap.model.CityBean;
import common.baselibrary.irecyclerview.expandable.viewholder.AbstractExpandableAdapterItem;

/**
 * Created by chasen on 2018/4/16.
 */

public class CityChildItem extends AbstractExpandableAdapterItem {
    private String city;
    private TextView tv_city_name;
    private TextView tv_size;
    private TextView tv_tips;
    private ImageView iv_download;
    private ProgressBar progressbar;
    private OnDownLoadMapClickListener onDownLoadMapClickListener;

    public CityChildItem(String city, OnDownLoadMapClickListener onDownLoadMapClickListener) {
        this.city = city;
        this.onDownLoadMapClickListener = onDownLoadMapClickListener;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_map_download_child;
    }

    @Override
    public void onBindViews(View root) {
        tv_city_name = root.findViewById(R.id.tv_city_name);
        tv_size = root.findViewById(R.id.tv_size);
        tv_tips = root.findViewById(R.id.tv_tips);
        iv_download = root.findViewById(R.id.iv_download);
        progressbar = root.findViewById(R.id.progressbar);
    }

    @Override
    public void onSetViews() {

    }

    @Override
    public void onExpansionToggled(boolean expanded) {

    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);
        final CityBean cityBean = (CityBean) model;
        MKOLSearchRecord searchRecord = cityBean.getMkolSearchRecord();
        tv_city_name.setText(searchRecord.cityName);
        tv_size.setText(formatDataSize(searchRecord.dataSize));
        String tips = "";
        if (searchRecord.cityName.equals(city))
            tips += "(当前城市)";
        if (cityBean.isComplete()) {
            progressbar.setVisibility(View.GONE);
            iv_download.setVisibility(View.VISIBLE);
            iv_download.setImageResource(R.drawable.complete);
            tips += "  已下载";
        } else {
            if (cityBean.isLoading()) {
                tips += "  下载中...";
                progressbar.setVisibility(View.VISIBLE);
                iv_download.setVisibility(View.GONE);
            } else {
                progressbar.setVisibility(View.GONE);
                iv_download.setVisibility(View.VISIBLE);
                iv_download.setImageResource(R.drawable.selector_download);
            }
        }
        tv_tips.setText(tips);
        iv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cityBean.isComplete() && !cityBean.isLoading()) {
                    onDownLoadMapClickListener.clickDownLoad(cityBean.getMkolSearchRecord());
                }
            }
        });
    }

    public String formatDataSize(long size) {
        String ret = "";
        if (size < (1024 * 1024)) {
            ret = String.format("%dK", size / 1024);
        } else {
            ret = String.format("%.1fM", size / (1024 * 1024.0));
        }
        return ret;
    }
}
