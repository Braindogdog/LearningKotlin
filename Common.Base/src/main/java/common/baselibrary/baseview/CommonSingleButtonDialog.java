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

public class CommonSingleButtonDialog extends Dialog {
    private TextView tv_content;
    private TextView tv_title;
    private TextView tv_submit;

    private Context mContext;
    private OnSubmitListener onSubmitListener;
    private String content;
    private String submitName;
    private String title;
    private boolean cancelable;

    public CommonSingleButtonDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }

    public CommonSingleButtonDialog setTitle(String title) {
        this.title = title;
        if (tv_title != null)
            tv_title.setText(title);
        return this;
    }

    public CommonSingleButtonDialog setContent(String content) {
        this.content = content;
        if (tv_content != null)
            tv_content.setText(content);
        return this;
    }

    public CommonSingleButtonDialog setSubmitName(String name) {
        this.submitName = name;
        if (tv_submit != null)
            tv_submit.setText(name);
        return this;
    }

    public CommonSingleButtonDialog setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    /**
     * 弹窗是否可以取消
     *
     * @param cancelable
     * @return
     */
    public CommonSingleButtonDialog setCanDismiss(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom_singlebtn);
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
                    dismiss();
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

        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        }
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
    }

    public interface OnSubmitListener {
        void onClick();
    }
}
