package common.baselibrary.baseview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import common.baselibrary.R;
import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseview.TickView.TickAnimatorListener;
import common.baselibrary.baseview.TickView.TickView;

/**
 * Created by chasen on 2018/3/28.
 */

public class CommonProgressWithSuccessDialog extends Dialog {
    private Context mContext;
    private String loading_message;
    private String success_message;
    private String error_message;
    private boolean cancelable;
    private TickView mLoadingView;
    private TextView tv_message;

    public CommonProgressWithSuccessDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.mContext = context;
    }

    public CommonProgressWithSuccessDialog setLoadingMessage(String msg) {
        this.loading_message = msg;
        if (tv_message != null)
            tv_message.setText(msg);
        return this;
    }

    public CommonProgressWithSuccessDialog setSuccessMessage(String msg) {
        this.success_message = msg;
        return this;
    }

    public CommonProgressWithSuccessDialog setErrorMessage(String msg) {
        this.error_message = msg;
        return this;
    }

    public CommonProgressWithSuccessDialog setCanDismiss(boolean canDismiss) {
        this.cancelable = canDismiss;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_with_success);
        tv_message = (TextView) findViewById(R.id.tv_message);
        if (!TextUtils.isEmpty(loading_message)) {
            tv_message.setText(loading_message);
        }
        mLoadingView = (TickView) findViewById(R.id.tick_view);
        mLoadingView.startAnim();
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
    }

    @Override
    public void dismiss() {
        if (mLoadingView != null)
            mLoadingView.stopAnim();
        mLoadingView.clear();
        super.dismiss();
    }

    @Override
    public void show() {
        if (tv_message != null) {
            tv_message.setText(EmptyUtil.isEmpty(loading_message) ? "加载中..." : loading_message);
        }
        if (mLoadingView != null)
            mLoadingView.startAnim();
        super.show();
    }

    /**
     * 调用加载成功动画方法
     */
    public void success(final OnAnimalEndListener listener) {
        if (!TextUtils.isEmpty(success_message)) {
            tv_message.setText(success_message);
        } else {
            tv_message.setText("加载成功");
        }
        mLoadingView.getConfig().setTickAnimatorListener(new TickAnimatorListener.TickAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(TickView tickView) {
                super.onAnimationEnd(tickView);
                dismiss();
                listener.end();
            }
        });
        if (mLoadingView != null)
            mLoadingView.stopAnim();
        mLoadingView.success();
    }

    public void error(final OnAnimalEndListener listener) {
        if (!TextUtils.isEmpty(error_message)) {
            tv_message.setText(error_message);
        } else {
            tv_message.setText("加载失败");
        }
        mLoadingView.getConfig().setTickAnimatorListener(new TickAnimatorListener.TickAnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(TickView tickView) {
                super.onAnimationEnd(tickView);
                dismiss();
                listener.end();
            }
        });
        if (mLoadingView != null)
            mLoadingView.stopAnim();
        mLoadingView.error();
    }

    public interface OnAnimalEndListener {
        void end();
    }
}
