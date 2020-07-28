package common.baselibrary.baseview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import common.baselibrary.irecyclerview.IViewHolder;
import common.baselibrary.irecyclerview.OnItemClickListener;
import common.baselibrary.irecyclerview.OnItemLongClickListener;

/**
 * Created by chasen on 2018/3/21.
 */

public abstract class BaseMultiAdapter<T, iVH extends IViewHolder> extends RecyclerView.Adapter<iVH>{

    public List<T> listDatas;
    public OnItemClickListener mOnItemClickListener;
    public OnItemLongClickListener mOnItemLongClickListener;

    /**
     * 添加点击事件监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 添加长按点击时间监听
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 添加数据
     *
     * @param datas
     */

    public void setList(List<T> datas) {
        if (null == listDatas)
            listDatas = new ArrayList<>();
        listDatas.clear();
        append(datas);
    }

    /**
     * 追加数据
     *
     * @param datas
     */
    public void append(List<T> datas) {
        if (null == listDatas)
            listDatas = new ArrayList<>();
        int positionStart = listDatas.size();
        int itemCount = 0;
        if (datas != null) {
            itemCount = datas.size();
            listDatas.addAll(datas);
        }
        if (positionStart > 0 && itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * 删除某一个数据
     *
     * @param position
     */
    public void remove(int position) {
        if (null == listDatas)
            listDatas = new ArrayList<>();
        listDatas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        if (null == listDatas)
            listDatas = new ArrayList<>();
        listDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取所有数据
     *
     * @return
     */
    public List<T> getAllData() {
        if (null == listDatas)
            listDatas = new ArrayList<>();
        return listDatas;
    }

    @NonNull
    @Override
    public iVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (getItemResID(viewType) == 0) {
            return null;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemResID(viewType), parent, false);
        final iVH iViewHolder = mCreateViewHolder(view,viewType);
        if (mOnItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = iViewHolder.getIAdapterPosition();
                    mOnItemClickListener.onItemClick(position, view, listDatas.get(position));
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = iViewHolder.getIAdapterPosition();
                    mOnItemLongClickListener.onItemLongClick(position, view, listDatas.get(position));
                    return true;
                }
            });
        }
        return iViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull iVH holder, int position) {
        if (listDatas == null || listDatas.size() < position + 1)
            return;
        mBindViewHolder(holder, listDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (null == listDatas)
            listDatas = new ArrayList<>();
        return listDatas.size();
    }

    /**
     * 获取布局id
     *
     * @return
     */
    @NonNull
    protected abstract int getItemResID(int viewType);

    /**
     * 加载item内容
     */
    protected abstract void mBindViewHolder(iVH holder, T data, int position);

    /**
     * 创建继承于IViewHolder的viewHolder
     *
     * @param view
     * @return
     */
    protected abstract iVH mCreateViewHolder(View view, int viewType);
}
