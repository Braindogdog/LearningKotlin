package common.baidunavigationlibrary.offlinemap.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;

import common.baidunavigationlibrary.R;
import common.baidunavigationlibrary.offlinemap.model.CityBean;
import common.baselibrary.baseview.BaseAdapter;
import common.baselibrary.baseview.numberprogressbar.NumberProgressBar;
import common.baselibrary.irecyclerview.IViewHolder;

/**
 * Created by chasen on 2018/4/16.
 */

public class OfflineMapDownLoadMapAdapter extends BaseAdapter {
    private OnDownLoadMapClickListener onDownLoadMapClickListener;
    private String city;

    public OfflineMapDownLoadMapAdapter(OnDownLoadMapClickListener onDownLoadMapClickListener, String city) {
        this.onDownLoadMapClickListener = onDownLoadMapClickListener;
        this.city = city;
    }

    @NonNull
    @Override
    protected int getItemResID() {
        return R.layout.item_map_download;
    }

    @Override
    protected void mBindViewHolder(IViewHolder holder, Object object, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        final CityBean bean = (CityBean) object;
        String tips = "";
        if (CityBean.RECOMMEND_CITY.equals(bean.getGroupName())) {
            MKOLSearchRecord searchRecord = bean.getMkolSearchRecord();
            viewHolder.tv_city_name.setText(searchRecord.cityName);
            viewHolder.tv_size.setText(formatDataSize(searchRecord.dataSize));
            viewHolder.progressbar.setVisibility(View.GONE);
            viewHolder.downLoadProgressbar.setVisibility(View.GONE);
            viewHolder.iv_download.setVisibility(View.VISIBLE);
            viewHolder.iv_download.setImageResource(R.drawable.download);
            if (searchRecord.cityName.equals(city)) {
                tips += "(当前城市)";
            }
        } else if (CityBean.HAVE_DOWNLOAD_CITY.equals(bean.getGroupName())) {
            MKOLUpdateElement updateElement = bean.getMkolUpdateElement();
            viewHolder.tv_city_name.setText(updateElement.cityName);
            viewHolder.tv_size.setText(formatDataSize(updateElement.size));
            if (updateElement.cityName.equals(city)) {
                tips += "(当前城市)";
            }
            if (updateElement.ratio != 100) {
                tips += "  下载中...";
                viewHolder.progressbar.setVisibility(View.VISIBLE);
                viewHolder.downLoadProgressbar.setVisibility(View.VISIBLE);
                viewHolder.iv_download.setVisibility(View.GONE);
                viewHolder.downLoadProgressbar.setProgress(updateElement.ratio);
            } else {
                tips += "  已下载";
                viewHolder.progressbar.setVisibility(View.GONE);
                viewHolder.downLoadProgressbar.setVisibility(View.GONE);
                viewHolder.iv_download.setVisibility(View.VISIBLE);
                viewHolder.iv_download.setImageResource(R.drawable.complete);
            }
        }
        viewHolder.tv_tips.setText(tips);
        viewHolder.iv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CityBean.RECOMMEND_CITY.equals(bean.getGroupName()) && !bean.isComplete()) {
                    viewHolder.progressbar.setVisibility(View.VISIBLE);
                    viewHolder.iv_download.setVisibility(View.GONE);
                    onDownLoadMapClickListener.clickDownLoad(bean.getMkolSearchRecord());
                }
            }
        });
        viewHolder.re_download.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (CityBean.HAVE_DOWNLOAD_CITY.equals(bean.getGroupName())) {
                    onDownLoadMapClickListener.clickDelete(bean.getMkolUpdateElement());
                }
                return true;
            }
        });
    }

    @Override
    protected IViewHolder mCreateViewHolder(View view) {
        return new MyViewHolder(view);
    }

    class MyViewHolder extends IViewHolder {
        public TextView tv_city_name;
        public TextView tv_size;
        public TextView tv_tips;
        public ImageView iv_download;
        public ProgressBar progressbar;
        public NumberProgressBar downLoadProgressbar;
        public RelativeLayout re_download;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_city_name = itemView.findViewById(R.id.tv_city_name);
            tv_size = itemView.findViewById(R.id.tv_size);
            tv_tips = itemView.findViewById(R.id.tv_tips);
            iv_download = itemView.findViewById(R.id.iv_download);
            progressbar = itemView.findViewById(R.id.progressbar);
            downLoadProgressbar = itemView.findViewById(R.id.number_progress);
            re_download = itemView.findViewById(R.id.re_download);
        }
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
