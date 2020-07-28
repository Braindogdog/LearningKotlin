package common.baselibrary.baseview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import common.baselibrary.R;

/**
 * Created by chasen on 2018/3/22.
 */

public class CommonProgressDialog extends Dialog {

    private Context mContext;
    private String loading_message;
    private boolean cancelable;
    CircularRing mLoadingView;
    private TextView tv_loading_message;

    public CommonProgressDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.mContext = context;
    }

    public CommonProgressDialog setMessage(String msg) {
        this.loading_message = msg;
        if (tv_loading_message != null)
            tv_loading_message.setText(msg);
        return this;
    }

    public CommonProgressDialog setCanDismiss(boolean canDismiss) {
        this.cancelable = canDismiss;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_view);
        tv_loading_message = (TextView) findViewById(R.id.tv_loading_message);
        if (!TextUtils.isEmpty(loading_message)) {
            tv_loading_message.setText(loading_message);
        }
        mLoadingView = (CircularRing) findViewById(R.id.circularring);
        mLoadingView.startAnim();
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
    }

    @Override
    public void dismiss() {
        if (mLoadingView != null)
            mLoadingView.stopAnim();
        super.dismiss();
    }

    @Override
    public void show() {
        if (mLoadingView != null)
            mLoadingView.startAnim();
        super.show();
    }
}
