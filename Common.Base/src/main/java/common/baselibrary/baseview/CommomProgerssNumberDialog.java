package common.baselibrary.baseview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import common.baselibrary.R;
import common.baselibrary.baseview.numberprogressbar.NumberProgressBar;

/**
 * Created by chasen on 2018/4/18.
 */

public class CommomProgerssNumberDialog extends Dialog {
    private TextView tv_content;
    private TextView tv_title;
    private TextView tv_submit;
    private TextView tv_cancel;
    private NumberProgressBar number_progressbar;

    private Context mContext;
    private OnSubmitListener onSubmitListener;
    private OnCancleListener onCancleListener;
    private String content;
    private String cancelName;
    private String submitName;
    private String title;

    public CommomProgerssNumberDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public CommomProgerssNumberDialog setTitle(String title) {
        this.title = title;
        if (tv_title != null)
            tv_title.setText(title);
        return this;
    }

    public CommomProgerssNumberDialog setContent(String content) {
        this.content = content;
        if (tv_content != null)
            tv_content.setText(content);
        return this;
    }

    public CommomProgerssNumberDialog setSubmitName(String name) {
        this.submitName = name;
        if (tv_submit != null)
            tv_submit.setText(name);
        return this;
    }

    public CommomProgerssNumberDialog setCancelName(String name) {
        this.cancelName = name;
        if (tv_cancel != null)
            tv_cancel.setText(name);
        return this;
    }

    public CommomProgerssNumberDialog setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public CommomProgerssNumberDialog setOnCancleListener(OnCancleListener onCancleListener) {
        this.onCancleListener = onCancleListener;
        return this;
    }

    /**
     * 弹窗是否可以取消
     *
     * @param cancelable
     * @return
     */
    public CommomProgerssNumberDialog setCanDismiss(boolean cancelable) {
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom_progressnumber);
        initView();
    }

    private void initView() {
        tv_content = (TextView) findViewById(R.id.content);
        tv_title = (TextView) findViewById(R.id.title);
        tv_submit = (TextView) findViewById(R.id.submit);
        number_progressbar = (NumberProgressBar) findViewById(R.id.number_progressbar);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSubmitListener != null) {
                    onSubmitListener.onClick();
                } else {
                    dismiss();
                }
            }
        });
        tv_cancel = (TextView) findViewById(R.id.cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCancleListener != null) {
                    onCancleListener.onClick();
                } else {
                    dismiss();
                }
            }
        });

        if (!TextUtils.isEmpty(content)) {
            tv_content.setText(content);
        }
        if (!TextUtils.isEmpty(submitName)) {
            tv_submit.setText(submitName);
        }

        if (!TextUtils.isEmpty(cancelName)) {
            tv_cancel.setText(cancelName);
        }

        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        }
    }

    public interface OnCancleListener {
        void onClick();
    }

    public interface OnSubmitListener {
        void onClick();
    }

    public void setProgress(int progress) {
        number_progressbar.setProgress(progress);
    }
}
