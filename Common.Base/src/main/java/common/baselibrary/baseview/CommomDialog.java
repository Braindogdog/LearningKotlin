package common.baselibrary.baseview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import common.baselibrary.R;

/**
 * Created by chasen on 2018/3/22.
 */

public class CommomDialog extends Dialog {
    private TextView tv_content;
    private TextView tv_title;
    private TextView tv_submit;
    private TextView tv_cancel;

    private Context mContext;
    private OnSubmitListener onSubmitListener;
    private OnCancleListener onCancleListener;
    private String content;
    private String cancelName;
    private String submitName;
    private String title;
    private int gravity;

    public CommomDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }

    public CommomDialog setTitle(String title) {
        this.title = title;
        if (tv_title != null)
            tv_title.setText(title);
        return this;
    }

    public CommomDialog setContent(String content) {
        this.content = content;
        if (tv_content != null)
            tv_content.setText(content);
        return this;
    }

    public CommomDialog setContentGravity(int gravity) {
        this.gravity = gravity;
        if (tv_content != null)
            tv_content.setGravity(gravity);
        return this;
    }

    public CommomDialog setSubmitName(String name) {
        this.submitName = name;
        if (tv_submit != null)
            tv_submit.setText(name);
        return this;
    }

    public CommomDialog setCancelName(String name) {
        this.cancelName = name;
        if (tv_cancel != null)
            tv_cancel.setText(name);
        return this;
    }

    public CommomDialog setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public CommomDialog setOnCancleListener(OnCancleListener onCancleListener) {
        this.onCancleListener = onCancleListener;
        return this;
    }

    /**
     * 弹窗是否可以取消
     *
     * @param cancelable
     * @return
     */
    public CommomDialog setCanDismiss(boolean cancelable) {
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom);
        initView();
    }

    private void initView() {
        tv_content = (TextView) findViewById(R.id.content);
        tv_title = (TextView) findViewById(R.id.title);
        tv_submit = (TextView) findViewById(R.id.submit);
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
        if (gravity != 0) {
            tv_content.setGravity(gravity);
        }

    }

    public interface OnCancleListener {
        void onClick();
    }

    public interface OnSubmitListener {
        void onClick();
    }
}
