package common.mediaselectlibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseview.BaseAdapter;
import common.baselibrary.baseview.RatioImageView;
import common.baselibrary.baseview.RatioRelativeLayout;
import common.baselibrary.irecyclerview.IViewHolder;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.ImageMessage;

/**
 * Created by chasen on 2018/4/19.
 */

public class SelectImageAdapter extends BaseAdapter<ImageMessage, SelectImageAdapter.MyViewHolder> {

    private Context context;

    public SelectImageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    protected int getItemResID() {
        return R.layout.item_select_image;
    }

    @Override
    protected void mBindViewHolder(MyViewHolder holder, ImageMessage data, int position) {
        if (position == getItemCount() - 1) {
            holder.rv_select_image.setVisibility(View.VISIBLE);
            holder.riv_image.setVisibility(View.GONE);
        } else {
            holder.rv_select_image.setVisibility(View.GONE);
            holder.riv_image.setVisibility(View.VISIBLE);
            if (!EmptyUtil.isEmpty(data)) {
                if (data.getType() == ImageMessage.PATH) {
                    File file = new File(data.getPath());
                    Glide.with(context)
                            .load(file)
                            .apply(new RequestOptions()
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.icon_load_error))
                            .into(holder.riv_image);
                } else {
                    Glide.with(context)
                            .load(data.getPath())
                            .apply(new RequestOptions()
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.icon_load_error))
                            .into(holder.riv_image);
                }
            }
        }
    }

    @Override
    protected MyViewHolder mCreateViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    public void append(List datas) {
        if (null == listDatas)
            listDatas = new ArrayList<>();
        int positionStart = listDatas.size();
        int itemCount = datas.size();
        listDatas.addAll(datas);
        listDatas.add(new ImageMessage());
        if (positionStart > 0 && itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends IViewHolder {
        RatioImageView riv_image;
        RatioRelativeLayout rv_select_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            riv_image = itemView.findViewById(R.id.riv_image);
            rv_select_image = itemView.findViewById(R.id.rv_select_image);
        }
    }
}
