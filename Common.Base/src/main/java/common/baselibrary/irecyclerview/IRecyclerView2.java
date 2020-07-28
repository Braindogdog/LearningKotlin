package common.baselibrary.irecyclerview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import common.baselibrary.R;

/**
 * Created by chasen on 2018/3/21.
 */

public class IRecyclerView2 extends RecyclerView {
    private static final String TAG = IRecyclerView2.class.getSimpleName();


    /**
     * 定义刷新的四种状态
     * 通过状态码来设置HeaderView的UI效果  详见RefreshHeaderView -- 通过回调传过去不同的状态 RefreshHeaderViewTrigger
     */
    private static final int STATUS_DEFAULT = 0;
    private static final int STATUS_SWIPING_TO_REFRESH = 1;
    private static final int STATUS_RELEASE_TO_REFRESH = 2;
    private static final int STATUS_REFRESHING = 3;

    private static final boolean DEBUG = false;

    private int mStatus;

    //是否自动刷新
    private boolean mIsAutoRefreshing;

    private boolean mRefreshEnabled;
    //加载更多
    private boolean mLoadMoreEnabled;

    private int mRefreshFinalMoveOffset;

    /**
     * 刷新和加载更多的监听方法
     */
    private OnRefreshListener mOnRefreshListener;
    private OnLoadMoreListener mOnLoadMoreListener;


    /**
     * 自定义View刷新头部的布局
     */
    private RefreshHeaderLayout mRefreshHeaderContainer;
    //加载更多尾部布局
    private FrameLayout mLoadMoreFooterContainer;

    private LinearLayout mHeaderViewContainer;

    private LinearLayout mFooterViewContainer;

    private View mRefreshHeaderView;

    private View mLoadMoreFooterView;

    private boolean isLoadingMore;

    public IRecyclerView2(Context context) {
        this(context, null);
    }

    public IRecyclerView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IRecyclerView2(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IRecyclerView, defStyle, 0);
        //@LayoutRes 表明该参数、变量或者函数返回值应该是一个 layout 布局文件类型的资源
        @LayoutRes int refreshHeaderLayoutRes = -1;
        @LayoutRes int loadMoreFooterLayoutRes = -1;
        int refreshFinalMoveOffset = -1;
        boolean refreshEnabled;
        boolean loadMoreEnabled;

        //从xml文件中获得用户设定的集中属性
        try {
            refreshEnabled = a.getBoolean(R.styleable.IRecyclerView_refreshEnabled, false);
            loadMoreEnabled = a.getBoolean(R.styleable.IRecyclerView_loadMoreEnabled, false);
            refreshHeaderLayoutRes = a.getResourceId(R.styleable.IRecyclerView_refreshHeaderLayout, -1);
            loadMoreFooterLayoutRes = a.getResourceId(R.styleable.IRecyclerView_loadMoreFooterLayout, -1);
            refreshFinalMoveOffset = a.getDimensionPixelOffset(R.styleable.IRecyclerView_refreshFinalMoveOffset, -1);
        } finally {
            a.recycle();
        }

        //根据用户指定,设置是否可以刷新
        setRefreshEnabled(refreshEnabled);
        //根据用户指定,设置是否可以加载更多
        setLoadMoreEnabled(loadMoreEnabled);

        //如果用户指定了头部的布局样式,就用用户的,否则就默认用自己的
        if (refreshHeaderLayoutRes != -1) {
            setRefreshHeaderView(refreshHeaderLayoutRes);
        } else {
            setRefreshHeaderView(R.layout.layout_irecyclerview_refresh_header);
        }
        //如果用户指定了尾部布局,就用用户的,否则就用默认用自己的
        if (loadMoreFooterLayoutRes != -1) {
            setLoadMoreFooterView(loadMoreFooterLayoutRes);
        } else {
            setLoadMoreFooterView(R.layout.layout_irecyclerview_load_more_footer);
        }
        //设置刷新最终移动的偏移量
        if (refreshFinalMoveOffset != -1) {
            setRefreshFinalMoveOffset(refreshFinalMoveOffset);
        }
        //设置刷新状态为           默认状态
        setStatus(STATUS_DEFAULT);
    }

    /**
     * TODO 重写测量方法,判断头部刷新View是否存在,如果存在
     * 获取头部刷新布局VIEW的测量高度,如果大于刷新最终移动的偏移量,那么偏移量的值设置为0
     * @param widthSpec
     * @param heightSpec
     */
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (mRefreshHeaderView != null) {
            if (mRefreshHeaderView.getMeasuredHeight() > mRefreshFinalMoveOffset) {
                mRefreshFinalMoveOffset = 0;
            }
        }
    }

    //通过构造方法,在xml文件中传进来,经过此方法,成为成员变量,在整个类中调用
    public void setRefreshEnabled(boolean enabled) {
        this.mRefreshEnabled = enabled;
    }

    public void setLoadMoreEnabled(boolean enabled) {
        this.mLoadMoreEnabled = enabled;
        //是否允许上拉加载更多
        if (mLoadMoreEnabled) {
            //TODO 此处调用的这两个方法为父类的方法  添加和移除对RecyclerView的ScrollChanged的监听
            removeOnScrollListener(mOnLoadMoreScrollListener);
            addOnScrollListener(mOnLoadMoreScrollListener);
        } else {
            removeOnScrollListener(mOnLoadMoreScrollListener);
        }
    }

    //对外部提供的方法, 获取到是否可以上拉加载更多
    public boolean ismLoadMoreEnabled() {
        return mLoadMoreEnabled;
    }

    //对外提供方法,获取刷新的监听接口
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
    }

    ////对外提供方法,获取上拉加载更多的监听接口
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

    /**
     * 设置刷新状态
     *  这个方法的作用是    改变切换代码中刷新的状态码,并且设置对应的动画和UI
     *  并非数据上的改变
     * @param refreshing
     */
    public void setRefreshing(boolean refreshing) {
        //当前为默认状态且刷新为true  设置自动刷新为true 并这只状态为 STATUS_SWIPING_TO_REFRESH
        if (mStatus == STATUS_DEFAULT && refreshing) {
            this.mIsAutoRefreshing = true;
            setStatus(STATUS_SWIPING_TO_REFRESH);
            startScrollDefaultStatusToRefreshingStatus();
            //正在刷新的状态,用户设置不让刷新
        } else if (mStatus == STATUS_REFRESHING && !refreshing) {
            setStatus(STATUS_DEFAULT);
            //停止刷新
            this.mIsAutoRefreshing = false;
            startScrollRefreshingStatusToDefaultStatus();
        } else {
            this.mIsAutoRefreshing = false;
        }
    }

    /**
     * 设置加载更多状态
     *
     * @param status
     */
    public void setLoadMoreStatus(LoadingMoreStatus status) {
        if (mLoadMoreFooterView != null && mLoadMoreFooterView instanceof LoadMoreFooterViewTrigger) {
            LoadMoreFooterViewTrigger trigger = (LoadMoreFooterViewTrigger) mLoadMoreFooterView;
            switch (status) {
                case COMPLETE:
                    isLoadingMore = false;
                    trigger.onComplete();
                    break;
                case LOADING:
                    isLoadingMore = true;
                    trigger.onLoading();
                    break;
                case ERROR:
                    isLoadingMore = false;
                    trigger.onError();
                    break;
                case THE_END:
                    isLoadingMore = false;
                    trigger.onTheEnd();
                    break;
            }
        }
    }

    public void setRefreshFinalMoveOffset(int refreshFinalMoveOffset) {
        this.mRefreshFinalMoveOffset = refreshFinalMoveOffset;
    }

    public void setRefreshHeaderView(View refreshHeaderView) {
        //TODO 这里有疑问,新创建的headerView和RefreshHeaderViewTrigger,是没有联系的,这里岂不是要一直抛异常?
        if (!(refreshHeaderView instanceof RefreshHeaderViewTrigger)) {
            throw new ClassCastException("Refresh header view must be an implement of RefreshHeaderViewTrigger");
        }

        //如果mRefreshHeaderView不为null,说明这个View还有值,先将这个View移除,然后再设置新的HeaderView
        if (mRefreshHeaderView != null) {
            removeRefreshHeaderView();
        }
        //新创建的refreshHeaderView 和 mRefreshHeaderView 没有关系
        if (mRefreshHeaderView != refreshHeaderView) {
            //将传过来的值赋到成员变量中
            this.mRefreshHeaderView = refreshHeaderView;
            //再次确认头部布局的容器不为空
            ensureRefreshHeaderContainer();
            //将头部布局添加到容器中
            mRefreshHeaderContainer.addView(refreshHeaderView);
        }
    }

    public void setRefreshHeaderView(@LayoutRes int refreshHeaderLayoutRes) {
        //先确保创建一个头部布局的容器,并设置宽高
        ensureRefreshHeaderContainer();
        //通过传过来的布局文件,使用inflate()转换为一个View,并依附在创建好的头部布局容器中
        final View refreshHeader = LayoutInflater.from(getContext()).inflate(refreshHeaderLayoutRes, mRefreshHeaderContainer, false);
        if (refreshHeader != null) {
            //设置刷新头部的View
            setRefreshHeaderView(refreshHeader);
        }
    }

    public void setLoadMoreFooterView(View loadMoreFooterView) {
        if (!(loadMoreFooterView instanceof LoadMoreFooterViewTrigger)) {
            throw new ClassCastException("Refresh header view must be an implement of LoadMoreFooterViewTrigger");
        }
        if (mLoadMoreFooterView != null) {
            removeLoadMoreFooterView();
        }
        if (mLoadMoreFooterView != loadMoreFooterView) {
            this.mLoadMoreFooterView = loadMoreFooterView;
            ensureLoadMoreFooterContainer();
            mLoadMoreFooterContainer.addView(loadMoreFooterView);
        }
    }

    public void setLoadMoreFooterView(@LayoutRes int loadMoreFooterLayoutRes) {
        ensureLoadMoreFooterContainer();
        final View loadMoreFooter = LayoutInflater.from(getContext()).inflate(loadMoreFooterLayoutRes, mLoadMoreFooterContainer, false);
        if (loadMoreFooter != null) {
            setLoadMoreFooterView(loadMoreFooter);
        }
    }

    public View getRefreshHeaderView() {
        return mRefreshHeaderView;
    }

    public View getLoadMoreFooterView() {
        return mLoadMoreFooterView;
    }

    public LinearLayout getHeaderContainer() {
        ensureHeaderViewContainer();
        return mHeaderViewContainer;
    }

    public LinearLayout getFooterContainer() {
        ensureFooterViewContainer();
        return mFooterViewContainer;
    }

    public void addHeaderView(View headerView) {
        ensureHeaderViewContainer();
        mHeaderViewContainer.addView(headerView);
        Adapter adapter = getAdapter();
        if (adapter != null) {
            adapter.notifyItemChanged(1);
        }
    }

    public void addFooterView(View footerView) {
        ensureFooterViewContainer();
        mFooterViewContainer.addView(footerView);
        Adapter adapter = getAdapter();
        if (adapter != null) {
            adapter.notifyItemChanged(adapter.getItemCount() - 2);
        }
    }

    public Adapter getIAdapter() {
        final IRecyclerViewAdapter IRecyclerViewAdapter = (IRecyclerViewAdapter) getAdapter();
        return IRecyclerViewAdapter.getAdapter();
    }

    public void setIAdapter(Adapter adapter) {
        ensureRefreshHeaderContainer();
        ensureHeaderViewContainer();
        ensureFooterViewContainer();
        ensureLoadMoreFooterContainer();
        //TODO  IRecyclerViewAdapter
        setAdapter(new IRecyclerViewAdapter(adapter, mRefreshHeaderContainer, mHeaderViewContainer, mFooterViewContainer, mLoadMoreFooterContainer));
    }

    private void ensureRefreshHeaderContainer() {
        if (mRefreshHeaderContainer == null) {
            mRefreshHeaderContainer = new RefreshHeaderLayout(getContext());
            mRefreshHeaderContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }
    }

    private void ensureLoadMoreFooterContainer() {
        if (mLoadMoreFooterContainer == null) {
            mLoadMoreFooterContainer = new FrameLayout(getContext());
            mLoadMoreFooterContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void ensureHeaderViewContainer() {
        if (mHeaderViewContainer == null) {
            mHeaderViewContainer = new LinearLayout(getContext());
            mHeaderViewContainer.setOrientation(LinearLayout.VERTICAL);
            mHeaderViewContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void ensureFooterViewContainer() {
        if (mFooterViewContainer == null) {
            mFooterViewContainer = new LinearLayout(getContext());
            mFooterViewContainer.setOrientation(LinearLayout.VERTICAL);
            mFooterViewContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void removeRefreshHeaderView() {
        if (mRefreshHeaderContainer != null) {
            mRefreshHeaderContainer.removeView(mRefreshHeaderView);
        }
    }

    private void removeLoadMoreFooterView() {
        if (mLoadMoreFooterContainer != null) {
            mLoadMoreFooterContainer.removeView(mLoadMoreFooterView);
        }
    }

    private int mActivePointerId = -1;
    private int mLastTouchX = 0;
    private int mLastTouchY = 0;

    //ViewGroup事件拦截方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        final int action =  e.getActionMasked();
        final int actionIndex = e.getActionIndex();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {

                mActivePointerId =  e.getPointerId(0);

                mLastTouchX = (int) (e.getX(actionIndex) + 0.5f);
                mLastTouchY = (int) (e.getY(actionIndex) + 0.5f);
            }
            break;

            case MotionEvent.ACTION_POINTER_DOWN: {
                mActivePointerId = e.getPointerId(actionIndex);
                mLastTouchX = (int) (e.getX(actionIndex) + 0.5f);
                mLastTouchY = (int) (e.getY(actionIndex) + 0.5f);
            }
            break;

            case MotionEvent.ACTION_POINTER_UP: {
                onPointerUp(e);
            }
            break;
        }

        return super.onInterceptTouchEvent(e);
    }

    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        final int action = MotionEventCompat.getActionMasked(e);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int index = MotionEventCompat.getActionIndex(e);
                mActivePointerId = MotionEventCompat.getPointerId(e, 0);
                mLastTouchX = getMotionEventX(e, index);
                mLastTouchY = getMotionEventY(e, index);
            }
            break;

            case MotionEvent.ACTION_MOVE: {
                final int index = MotionEventCompat.findPointerIndex(e, mActivePointerId);
                if (index < 0) {
                    Log.e(TAG, "Error processing scroll; pointer index for id " + index + " not found. Did any MotionEvents get skipped?");
                    return false;
                }

                final int x = getMotionEventX(e, index);
                final int y = getMotionEventY(e, index);

                final int dx = x - mLastTouchX;
                final int dy = y - mLastTouchY;

                mLastTouchX = x;
                mLastTouchY = y;

                final boolean triggerCondition = isEnabled() && mRefreshEnabled && mRefreshHeaderView != null && isFingerDragging() && canTriggerRefresh();
                if (DEBUG) {
                    Log.i(TAG, "triggerCondition = " + triggerCondition + "; mStatus = " + mStatus + "; dy = " + dy);
                }
                if (triggerCondition) {

                    final int refreshHeaderContainerHeight = mRefreshHeaderContainer.getMeasuredHeight();
                    final int refreshHeaderViewHeight = mRefreshHeaderView.getMeasuredHeight();

                    if (dy > 0 && mStatus == STATUS_DEFAULT) {
                        setStatus(STATUS_SWIPING_TO_REFRESH);
                        mRefreshHeaderViewTrigger.onStart(false, refreshHeaderViewHeight, mRefreshFinalMoveOffset);
                    } else if (dy < 0) {
                        if (mStatus == STATUS_SWIPING_TO_REFRESH && refreshHeaderContainerHeight <= 0) {
                            setStatus(STATUS_DEFAULT);
                        }
                        if (mStatus == STATUS_DEFAULT) {
                            break;
                        }
                    }

                    if (mStatus == STATUS_SWIPING_TO_REFRESH || mStatus == STATUS_RELEASE_TO_REFRESH) {
                        if (refreshHeaderContainerHeight >= refreshHeaderViewHeight) {
                            setStatus(STATUS_RELEASE_TO_REFRESH);
                        } else {
                            setStatus(STATUS_SWIPING_TO_REFRESH);
                        }
                        fingerMove(dy);
                        return true;
                    }
                }
            }
            break;

            case MotionEvent.ACTION_POINTER_DOWN: {
                final int index = MotionEventCompat.getActionIndex(e);
                mActivePointerId = MotionEventCompat.getPointerId(e, index);
                mLastTouchX = getMotionEventX(e, index);
                mLastTouchY = getMotionEventY(e, index);
            }
            break;

            case MotionEvent.ACTION_POINTER_UP: {
                onPointerUp(e);
            }
            break;

            case MotionEvent.ACTION_UP: {
                onFingerUpStartAnimating();
            }
            break;

            case MotionEvent.ACTION_CANCEL: {
                onFingerUpStartAnimating();
            }
            break;
        }
        return super.onTouchEvent(e);
    }

    private boolean isFingerDragging() {
        return getScrollState() == SCROLL_STATE_DRAGGING;
    }

    public boolean canTriggerRefresh() {
        final Adapter adapter = getAdapter();
        if (adapter == null || adapter.getItemCount() <= 0) {
            return true;
        }
        View firstChild = getChildAt(0);
        int position = getChildLayoutPosition(firstChild);
        if (position == 0) {
            if (firstChild.getTop() == mRefreshHeaderContainer.getTop()) {
                return true;
            }
        }
        return false;
    }

    private int getMotionEventX(MotionEvent e, int pointerIndex) {
        return (int) (MotionEventCompat.getX(e, pointerIndex) + 0.5f);
    }

    private int getMotionEventY(MotionEvent e, int pointerIndex) {
        return (int) (MotionEventCompat.getY(e, pointerIndex) + 0.5f);
    }

    private void onFingerUpStartAnimating() {
        if (mStatus == STATUS_RELEASE_TO_REFRESH) {
            startScrollReleaseStatusToRefreshingStatus();
        } else if (mStatus == STATUS_SWIPING_TO_REFRESH) {
            startScrollSwipingToRefreshStatusToDefaultStatus();
        }
    }

    private void onPointerUp(MotionEvent e) {
        final int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == mActivePointerId) {
            // Pick a new pointer to pick up the slack.
            final int newIndex = actionIndex == 0 ? 1 : 0;
            mActivePointerId = e.getPointerId(newIndex);
            mLastTouchX = getMotionEventX(e, newIndex);
            mLastTouchY = getMotionEventY(e, newIndex);
        }
    }

    private void fingerMove(int dy) {
        int ratioDy = (int) (dy * 0.5f + 0.5f);
        int offset = mRefreshHeaderContainer.getMeasuredHeight();
        int finalDragOffset = mRefreshFinalMoveOffset;

        int nextOffset = offset + ratioDy;
        if (finalDragOffset > 0) {
            if (nextOffset > finalDragOffset) {
                ratioDy = finalDragOffset - offset;
            }
        }

        if (nextOffset < 0) {
            ratioDy = -offset;
        }
        move(ratioDy);
    }

    private void move(int dy) {
        if (dy != 0) {
            int height = mRefreshHeaderContainer.getMeasuredHeight() + dy;
            setRefreshHeaderContainerHeight(height);
            mRefreshHeaderViewTrigger.onMove(false, false, height);
        }
    }

    private void setRefreshHeaderContainerHeight(int height) {
        mRefreshHeaderContainer.getLayoutParams().height = height;
        mRefreshHeaderContainer.requestLayout();
    }

    /**
     * 从默认状态TO刷新状态
     */
    private void startScrollDefaultStatusToRefreshingStatus() {
        //这个接口已经实例化,调用此onStart()方法传值,将传递到实例化的接口中去
        mRefreshHeaderViewTrigger.onStart(true, mRefreshHeaderView.getMeasuredHeight(), mRefreshFinalMoveOffset);
        //获取headerView的测量高度
        int targetHeight = mRefreshHeaderView.getMeasuredHeight();
        //获取头部View容器的测量高度
        int currentHeight = mRefreshHeaderContainer.getMeasuredHeight();
        //开启动画
        startScrollAnimation(400, new AccelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollSwipingToRefreshStatusToDefaultStatus() {
        final int targetHeight = 0;
        final int currentHeight = mRefreshHeaderContainer.getMeasuredHeight();
        startScrollAnimation(300, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollReleaseStatusToRefreshingStatus() {
        mRefreshHeaderViewTrigger.onRelease();

        final int targetHeight = mRefreshHeaderView.getMeasuredHeight();
        final int currentHeight = mRefreshHeaderContainer.getMeasuredHeight();
        startScrollAnimation(300, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    /**
     * 从刷新状态TO默认状态
     */
    private void startScrollRefreshingStatusToDefaultStatus() {
        mRefreshHeaderViewTrigger.onComplete();

        final int targetHeight = 0;
        final int currentHeight = mRefreshHeaderContainer.getMeasuredHeight();
        startScrollAnimation(400, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    private ValueAnimator mScrollAnimator;

    private void startScrollAnimation(final int time, final Interpolator interpolator, int value, int toValue) {
        //判空,是为了防止多次创建对象
        if (mScrollAnimator == null) {
            mScrollAnimator = new ValueAnimator();
        }
        //cancel
        mScrollAnimator.removeAllUpdateListeners();
        mScrollAnimator.removeAllListeners();
        mScrollAnimator.cancel();

        //reset new value
        mScrollAnimator.setIntValues(value, toValue);
        mScrollAnimator.setDuration(time);
        mScrollAnimator.setInterpolator(interpolator);
        //TODO 这两个动画监听没看懂
        mScrollAnimator.addUpdateListener(mAnimatorUpdateListener);
        mScrollAnimator.addListener(mAnimationListener);
        mScrollAnimator.start();
    }

    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            final int height = (Integer) animation.getAnimatedValue();
            setRefreshHeaderContainerHeight(height);
            //通过状态码来设置HeaderView的UI效果
            switch (mStatus) {
                case STATUS_SWIPING_TO_REFRESH: {
                    mRefreshHeaderViewTrigger.onMove(false, true, height);
                }
                break;

                case STATUS_RELEASE_TO_REFRESH: {
                    mRefreshHeaderViewTrigger.onMove(false, true, height);
                }
                break;

                case STATUS_REFRESHING: {
                    mRefreshHeaderViewTrigger.onMove(true, true, height);
                }
                break;
            }

        }
    };

    private Animator.AnimatorListener mAnimationListener = new IAnimatorListener() {
        @Override
        public void onAnimationEnd(Animator animation) {
            int lastStatus = mStatus;
            switch (mStatus) {
                case STATUS_SWIPING_TO_REFRESH: {
                    if (mIsAutoRefreshing) {
                        mRefreshHeaderContainer.getLayoutParams().height = mRefreshHeaderView.getMeasuredHeight();
                        mRefreshHeaderContainer.requestLayout();
                        setStatus(STATUS_REFRESHING);
                        if (mOnRefreshListener != null) {
                            mOnRefreshListener.onRefresh();
                            mRefreshHeaderViewTrigger.onRefresh();
                        }
                    } else {
                        mRefreshHeaderContainer.getLayoutParams().height = 0;
                        mRefreshHeaderContainer.requestLayout();
                        setStatus(STATUS_DEFAULT);
                    }
                }
                break;

                case STATUS_RELEASE_TO_REFRESH: {
                    mRefreshHeaderContainer.getLayoutParams().height = mRefreshHeaderView.getMeasuredHeight();
                    mRefreshHeaderContainer.requestLayout();
                    setStatus(STATUS_REFRESHING);
                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onRefresh();
                        mRefreshHeaderViewTrigger.onRefresh();
                    }
                }
                break;

                case STATUS_REFRESHING: {
                    mIsAutoRefreshing = false;
                    mRefreshHeaderContainer.getLayoutParams().height = 0;
                    mRefreshHeaderContainer.requestLayout();
                    setStatus(STATUS_DEFAULT);
                    mRefreshHeaderViewTrigger.onReset();
                }
                break;
            }
            if (DEBUG) {
                Log.i(TAG, "onAnimationEnd " + getStatusLog(lastStatus) + " -> " + getStatusLog(mStatus) + " ;refresh view height:" + mRefreshHeaderContainer.getMeasuredHeight());
            }
        }
    };
    /**
     * 实例化这个接口
     * 调用这个接口的地方可以将值传到这里
     */
    private RefreshHeaderViewTrigger mRefreshHeaderViewTrigger = new RefreshHeaderViewTrigger() {
        @Override
        public void onStart(boolean automatic, int headerHeight, int finalHeight) {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshHeaderViewTrigger) {
                //当前的headerView如果实例化于 RefreshHeaderViewTrigger 那么他就是 赋值给RefreshHeaderView的那个我们创建的View
                RefreshHeaderViewTrigger trigger = (RefreshHeaderViewTrigger) mRefreshHeaderView;
                //再次使用接口回调,将值传走
                //将值传递到RefreshHeaderView中
                trigger.onStart(automatic, headerHeight, finalHeight);
            }
        }

        @Override
        public void onMove(boolean finished, boolean automatic, int moved) {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshHeaderViewTrigger) {
                RefreshHeaderViewTrigger trigger = (RefreshHeaderViewTrigger) mRefreshHeaderView;
                trigger.onMove(finished, automatic, moved);
            }
        }

        @Override
        public void onRefresh() {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshHeaderViewTrigger) {
                RefreshHeaderViewTrigger trigger = (RefreshHeaderViewTrigger) mRefreshHeaderView;
                trigger.onRefresh();
            }
        }

        @Override
        public void onRelease() {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshHeaderViewTrigger) {
                RefreshHeaderViewTrigger trigger = (RefreshHeaderViewTrigger) mRefreshHeaderView;
                trigger.onRelease();
            }
        }

        @Override
        public void onComplete() {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshHeaderViewTrigger) {
                RefreshHeaderViewTrigger trigger = (RefreshHeaderViewTrigger) mRefreshHeaderView;
                trigger.onComplete();
            }
        }

        @Override
        public void onReset() {
            if (mRefreshHeaderView != null && mRefreshHeaderView instanceof RefreshHeaderViewTrigger) {
                RefreshHeaderViewTrigger trigger = (RefreshHeaderViewTrigger) mRefreshHeaderView;
                trigger.onReset();
            }
        }
    };

    /**
     * 自定义一个 抽象类 OnLoadMoreScrollListener  并继承自RecyclerView.OnScrollListener(抽象的静态内部类)
     * 在其中监听滚动改变的方法中,调用抽象方法onLoadMore()
     * 在本类中创建这个抽象类的监听方法,并重写onLoadMore(),当RecyclerView滚送发生改变时,就会调用我们写的onLoadMore方法
     * 在这个方法中,我们再次使用接口回调的方法,将滚动的状态传递出去
     * 当外面的类监听到Recycler滚动loadMore时,就可以写下一步的逻辑了
     * 本类中的   setOnLoadMoreListener()   为外部类提供此接口
     */
    private OnLoadMoreScrollListener mOnLoadMoreScrollListener = new OnLoadMoreScrollListener() {
        @Override
        public void onLoadMore(RecyclerView recyclerView) {
            if (mOnLoadMoreListener != null && mStatus == STATUS_DEFAULT && !isLoadingMore) {
                mOnLoadMoreListener.onLoadMore();
            }
        }
    };

    private void setStatus(int status) {
        this.mStatus = status;
        if (DEBUG) {
            printStatusLog();
        }
    }

    private void printStatusLog() {
        Log.i(TAG, getStatusLog(mStatus));
    }

    private String getStatusLog(int status) {
        final String statusLog;
        switch (status) {
            case STATUS_DEFAULT:
                statusLog = "status_default";
                break;

            case STATUS_SWIPING_TO_REFRESH:
                statusLog = "status_swiping_to_refresh";
                break;

            case STATUS_RELEASE_TO_REFRESH:
                statusLog = "status_release_to_refresh";
                break;

            case STATUS_REFRESHING:
                statusLog = "status_refreshing";
                break;
            default:
                statusLog = "status_illegal!";
                break;
        }
        return statusLog;
    }

    //加载完成，正在加载，加载失败，加载到底
    public enum LoadingMoreStatus {
        COMPLETE, LOADING, ERROR, THE_END
    }
}
