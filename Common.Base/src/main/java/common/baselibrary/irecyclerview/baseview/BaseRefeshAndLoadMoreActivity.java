package common.baselibrary.irecyclerview.baseview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseview.BaseActivity;
import common.baselibrary.baseview.BaseAdapter;
import common.baselibrary.irecyclerview.IRecyclerView;
import common.baselibrary.irecyclerview.LoadMoreFooterView;
import common.baselibrary.irecyclerview.OnItemClickListener;
import common.baselibrary.irecyclerview.OnLoadMoreListener;
import common.baselibrary.irecyclerview.OnRefreshListener;

/**
 * Created by chasen on 2018/3/30.
 * 封装IRecyclerView功能的activity
 * iRecyclerView需要在子类中初始化，如果不初始化此控件则此类无意义
 * pageNumber需要自己设置，默认为10
 * loadingDatas方法为加载数据的方法，刷新和加载更多都使用此方法，在此方法的实现中直接使用pageIndex即可，词类会处理它的逻辑
 */

public abstract class BaseRefeshAndLoadMoreActivity<T> extends BaseActivity {

    protected List<T> listDatas;
    protected IRecyclerView iRecyclerView;
    protected BaseAdapter mAdapter;
    private OnRefreshListener onRefreshListener;
    private OnLoadMoreListener onLoadMoreListener;
    private int pageIndex = 1;//分页加载页码
    private int pageNumber = 10;//每页数据数量，当返回数量小于这个数量之后就不再加载更多
    protected boolean canLoadMore = true;
    protected boolean isRefresh = false;

    /**
     * 子类再重写时必须调用super
     */
    @Override
    protected void init() {
        inits();
        initRecyclerView();
        if (iRecyclerView != null) {
            initDatas();
            addListener();
        }
    }

    /**
     * recyclerview加载数据
     */
    public void initDatas() {
        listDatas = new ArrayList<>();
        mAdapter = getMyAdapter();
        iRecyclerView.setIAdapter(mAdapter);
    }

    /**
     * recyclerview添加事件
     */
    public void addListener() {
        onRefreshListener = getRefreshListener();
        onLoadMoreListener = getLoadMoreListener();
        if (onRefreshListener == null) {
            onRefreshListener = new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refresh();
                }
            };
        }
        iRecyclerView.setOnRefreshListener(onRefreshListener);
        if (onLoadMoreListener == null) {
            onLoadMoreListener = new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    loadMore();
                }
            };
        }
        iRecyclerView.setOnLoadMoreListener(onLoadMoreListener);
        iRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(true);
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v, Object object) {
                onRecyclerViewItemClick(position, v, object);
            }
        });
        LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
        loadMoreFooterView.setOnRetryListener(new LoadMoreFooterView.OnRetryListener() {
            @Override
            public void onRetry(LoadMoreFooterView view) {
                retry();
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    protected abstract void initRecyclerView();

    /**
     * 加载adapter，返回BaseAdapter
     *
     * @return
     */
    protected abstract @NonNull
    BaseAdapter getMyAdapter();

    /**
     * 加载数据的方法,直接使用父类pageIndex加载即可
     * 数据加载成功后调用onDataLoadedListener的loaded方法把数据传回来
     */
    protected abstract void loadingDatas(OnDataLoadedListener<T> onDataLoadedListener);

    /**
     * onCreate中最后调用的初始化方法
     */
    protected abstract void inits();

    /**
     * item的点击事件
     *
     * @param position
     * @param v
     */
    protected abstract void onRecyclerViewItemClick(int position, View v, Object object);

    /**
     * 设置刷新监听,可以为空调用默认监听
     *
     * @return
     */
    protected abstract @Nullable
    OnRefreshListener getRefreshListener();

    /**
     * 设置加载更多监听,可以为空调用默认监听
     *
     * @return
     */
    protected abstract @Nullable
    OnLoadMoreListener getLoadMoreListener();

    public void retry() {
        onLoadMoreListener.onLoadMore();
    }

    public void loadMore() {
        isRefresh = false;
        iRecyclerView.setLoadMoreStatus(IRecyclerView.LoadingMoreStatus.LOADING);
        loadingDatas(new OnDataLoadedListener<T>() {
            @Override
            public void loaded(List<T> list) {
                loadMoreLoaded(list);
            }
        });
    }

    protected void loadMoreLoaded(List<T> list) {
        if (!EmptyUtil.isEmpty(list)) {
            pageIndex++;
            if (list.size() < pageNumber) {
                iRecyclerView.setLoadMoreEnabled(false);
                iRecyclerView.setLoadMoreStatus(IRecyclerView.LoadingMoreStatus.THE_END);
            } else {
                iRecyclerView.setLoadMoreStatus(IRecyclerView.LoadingMoreStatus.COMPLETE);
            }
            listDatas.addAll(list);
            mAdapter.append(list);
        } else {
            if (list == null)
                iRecyclerView.setLoadMoreStatus(IRecyclerView.LoadingMoreStatus.ERROR);
            else
                iRecyclerView.setLoadMoreStatus(IRecyclerView.LoadingMoreStatus.THE_END);
        }
    }

    public void refresh() {
        isRefresh = true;
        loadingDatas(new OnDataLoadedListener<T>() {
            @Override
            public void loaded(List<T> list) {
                refreshLoadedData(list);
            }
        });
    }

    protected void refreshLoadedData(List<T> list) {
        iRecyclerView.setRefreshing(false);
        if (!EmptyUtil.isEmpty(list)) {
            pageIndex = 2;
            if (canLoadMore)
                iRecyclerView.setLoadMoreEnabled(true);
            if (list.size() < pageNumber) {
                iRecyclerView.setLoadMoreEnabled(false);
            }
            listDatas.clear();
            listDatas = list;
            mAdapter.setList(listDatas);
        } else if (list != null) {
            listDatas.clear();
            listDatas = list;
            mAdapter.setList(listDatas);
        }
    }

    public void setRefreshEabled(boolean refreshable) {
        if (iRecyclerView != null)
            iRecyclerView.setRefreshEnabled(refreshable);
    }

    public void setLoadMoreEabled(boolean loadmoreable) {
        iRecyclerView.setLoadMoreEnabled(loadmoreable);
        this.canLoadMore = loadmoreable;
    }

    public interface OnDataLoadedListener<T> {
        void loaded(List<T> list);
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public int getPageIndex() {
        if (isRefresh)
            return 1;
        else
            return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
