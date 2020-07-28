package common.baselibrary.irecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import common.baselibrary.R;

/**
 * Created by aspsine on 16/3/14.
 */
public class LoadMoreFooterView extends FrameLayout implements LoadMoreFooterViewTrigger {

    private View mLoadingView;

    private View mErrorView;

    private View mTheEndView;

    private OnRetryListener mOnRetryListener;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_load_more_footer_view, this, true);

        mLoadingView = findViewById(R.id.loadingView);
        mErrorView = findViewById(R.id.errorView);
        mTheEndView = findViewById(R.id.theEndView);

        mErrorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry(LoadMoreFooterView.this);
                }
            }
        });
        setVisibility(GONE);
    }

    /**
     * 设置点击重试按钮监听
     *
     * @param listener
     */
    public void setOnRetryListener(OnRetryListener listener) {
        this.mOnRetryListener = listener;
    }

    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        mLoadingView.setVisibility(VISIBLE);
        mErrorView.setVisibility(GONE);
        mTheEndView.setVisibility(GONE);
    }

    @Override
    public void onComplete() {
        setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mTheEndView.setVisibility(GONE);
    }

    @Override
    public void onError() {
        setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(VISIBLE);
        mTheEndView.setVisibility(GONE);
    }

    @Override
    public void onTheEnd() {
        setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mTheEndView.setVisibility(VISIBLE);
    }

    public interface OnRetryListener {
        void onRetry(LoadMoreFooterView view);
    }

}
