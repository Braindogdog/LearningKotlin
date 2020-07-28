package common.mediaselectlibrary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
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

public class ShowImageAdapter extends BaseAdapter<ImageMessage, ShowImageAdapter.MyViewHolder> {

    private Context context;

    public ShowImageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    protected int getItemResID() {
        return R.layout.item_show_image;
    }

    @Override
    protected void mBindViewHolder(MyViewHolder holder, ImageMessage data, int position) {
        if (!EmptyUtil.isEmpty(data)) {
            if (data.getType() == ImageMessage.PATH) {
                File file = new File(data.getPath());
                Glide.with(context)
                        .load(file)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.icon_load_error))
                        .into(holder.riv_image);
            } else if (data.getType() == ImageMessage.URL) {
                Glide.with(context)
                        .load(data.getThums())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.icon_load_error))
                        .into(holder.riv_image);
            } else if (data.getType() == ImageMessage.RESID) {
                Glide.with(context)
                        .load(data.getResId())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.icon_load_error))
                        .into(holder.riv_image);
            }
        }
    }

    @Override
    protected MyViewHolder mCreateViewHolder(View view) {
        return new MyViewHolder(view);
    }

    class MyViewHolder extends IViewHolder {
        RatioImageView riv_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            riv_image = itemView.findViewById(R.id.riv_image);
        }
    }
}
