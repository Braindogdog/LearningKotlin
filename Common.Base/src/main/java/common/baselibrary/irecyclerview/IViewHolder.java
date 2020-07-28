package common.baselibrary.irecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chasen on 2018/3/21.
 */

public abstract class IViewHolder extends RecyclerView.ViewHolder {

    public IViewHolder(View itemView) {
        super(itemView);
    }

    @Deprecated
    public final int getIPosition() {
        return getPosition() - 2;
    }

    public final int getILayoutPosition() {
        return getLayoutPosition() - 2;
    }

    public final int getIAdapterPosition() {
        return getAdapterPosition() - 2;
    }

    public final int getIOldPosition() {
        return getOldPosition() - 2;
    }

    public final long getIItemId() {
        return getItemId();
    }

    public final int getIItemViewType() {
        return getItemViewType();
    }
}