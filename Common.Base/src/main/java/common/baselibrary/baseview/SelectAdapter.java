package common.baselibrary.baseview;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;

import common.baselibrary.R;
import common.baselibrary.irecyclerview.IViewHolder;

/**
 * Created by chasen on 2018/4/18.
 */

public class SelectAdapter extends BaseAdapter {
    @NonNull
    @Override
    protected int getItemResID() {
        return R.layout.item_select;
    }

    @Override
    protected void mBindViewHolder(IViewHolder holder, Object object, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tv_content.setText(getContentText(object));
    }

    @Override
    protected IViewHolder mCreateViewHolder(View view) {
        return new MyViewHolder(view);
    }

    class MyViewHolder extends IViewHolder {
        TextView tv_content;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }

    private String getContentText(Object item) {
        String contentText = item.toString();
        try {
            Class<?> clz = item.getClass();
            Method m = clz.getMethod("getPickerViewText");
            contentText = m.invoke(item, new Object[0]).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentText;
    }
}
