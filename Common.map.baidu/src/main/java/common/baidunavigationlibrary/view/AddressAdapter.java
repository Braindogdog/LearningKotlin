package common.baidunavigationlibrary.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import org.creation.common.geometry.GeoLocation;

import common.baidunavigationlibrary.R;
import common.baselibrary.baseview.BaseAdapter;
import common.baselibrary.irecyclerview.IViewHolder;

/**
 * Created by chasen on 2018/8/22.
 */

public class AddressAdapter extends BaseAdapter<GeoLocation, AddressAdapter.MyHolder> {
    @NonNull
    @Override
    protected int getItemResID() {
        return R.layout.item_address;
    }

    @Override
    protected void mBindViewHolder(MyHolder holder, GeoLocation data, int position) {
        holder.tv_address.setText(data.getDescription());
    }

    @Override
    protected MyHolder mCreateViewHolder(View view) {
        return new MyHolder(view);
    }

    class MyHolder extends IViewHolder {
        TextView tv_address;

        public MyHolder(View itemView) {
            super(itemView);
            tv_address = itemView.findViewById(R.id.tv_address);
        }
    }
}
