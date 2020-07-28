package common.baidunavigationlibrary.offlinemap.view;

import android.support.annotation.NonNull;

import java.util.List;

import common.baidunavigationlibrary.offlinemap.model.CityBean;
import common.baselibrary.irecyclerview.expandable.adapter.BaseExpandableAdapter;
import common.baselibrary.irecyclerview.expandable.viewholder.AbstractAdapterItem;

/**
 * Created by chasen on 2018/4/16.
 */

public class OfflineMapCityAdapter extends BaseExpandableAdapter {
    private String city;
    private OnDownLoadMapClickListener onDownLoadMapClickListener;

    public OfflineMapCityAdapter(List data, String city, OnDownLoadMapClickListener onDownLoadMapClickListener) {
        super(data);
        this.city = city;
        this.onDownLoadMapClickListener = onDownLoadMapClickListener;
    }

    @NonNull
    @Override
    public AbstractAdapterItem<Object> getItemView(Object type) {
        boolean isChildren = (Boolean) type;
        if (isChildren) {
            return new CityChildItem(city, onDownLoadMapClickListener);
        } else {
            return new CityNomalItem(city, onDownLoadMapClickListener);
        }
    }

    @Override
    public Object getItemViewType(Object t) {
        CityBean cityBean = (CityBean) t;
        return cityBean.isChildren();
    }
}
