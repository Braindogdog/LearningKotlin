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

public class CommomThreeDialog extends Dialog {
    private TextView tv_content;
    private TextView tv_title;
    private TextView tv_submit;
    private TextView tv_cancel;
    private TextView tv_cancel2;

    private Context mContext;
    private OnSubmitListener onSubmitListener;
    private OnCancleListener onCancleListener;
    private OnCancleListener onCancle2Listener;
    private String content;
    private String cancelName;
    private String cancel2Name;
    private String submitName;
    private String title;
    private int gravity;

    public CommomThreeDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }

    public CommomThreeDialog setTitle(String title) {
        this.title = title;
        if (tv_title != null)
            tv_title.setText(title);
        return this;
    }

    public CommomThreeDialog setContent(String content) {
        this.content = content;
        if (tv_content != null)
            tv_content.setText(content);
        return this;
    }

    public CommomThreeDialog setContentGravity(int gravity) {
        this.gravity = gravity;
        if (tv_content != null)
            tv_content.setGravity(gravity);
        return this;
    }

    public CommomThreeDialog setSubmitName(String name) {
        this.submitName = name;
        if (tv_submit != null)
            tv_submit.setText(name);
        return this;
    }

    public CommomThreeDialog setCancelName(String name) {
        this.cancelName = name;
        if (tv_cancel != null)
            tv_cancel.setText(name);
        return this;
    }
    public CommomThreeDialog setCancel2Name(String name) {
        this.cancel2Name = name;
        if (tv_cancel2 != null)
            tv_cancel2.setText(name);
        return this;
    }

    public CommomThreeDialog setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public CommomThreeDialog setOnCancleListener(OnCancleListener onCancleListener) {
        this.onCancleListener = onCancleListener;
        return this;
    }

    public CommomThreeDialog setOnCancle2Listener(OnCancleListener onCancle2Listener) {
        this.onCancle2Listener = onCancle2Listener;
        return this;
    }

    /**
     * 弹窗是否可以取消
     *
     * @param cancelable
     * @return
     */
    public CommomThreeDialog setCanDismiss(boolean cancelable) {
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom_three);
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
        tv_cancel2 = (TextView) findViewById(R.id.tv_cancel2);
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
        tv_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCancle2Listener != null) {
                    onCancle2Listener.onClick();
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
        if (!TextUtils.isEmpty(cancel2Name)) {
            tv_cancel2.setText(cancel2Name);
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
