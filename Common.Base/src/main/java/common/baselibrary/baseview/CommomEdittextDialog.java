package common.baselibrary.baseview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import common.baselibrary.R;

/**
 * Created by chasen on 2018/3/22.
 */

public class CommomEdittextDialog extends Dialog {
    private EditText et_content;
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

    public CommomEdittextDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }

    public CommomEdittextDialog setTitle(String title) {
        this.title = title;
        if (tv_title != null)
            tv_title.setText(title);
        return this;
    }

    public CommomEdittextDialog setHint(String content) {
        this.content = content;
        if (et_content != null)
            et_content.setHint(content);
        return this;
    }

    public CommomEdittextDialog setSubmitName(String name) {
        this.submitName = name;
        if (tv_submit != null)
            tv_submit.setText(name);
        return this;
    }

    public CommomEdittextDialog setCancelName(String name) {
        this.cancelName = name;
        if (tv_cancel != null)
            tv_cancel.setText(name);
        return this;
    }

    public CommomEdittextDialog setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public CommomEdittextDialog setOnCancleListener(OnCancleListener onCancleListener) {
        this.onCancleListener = onCancleListener;
        return this;
    }

    /**
     * 弹窗是否可以取消
     *
     * @param cancelable
     * @return
     */
    public CommomEdittextDialog setCanDismiss(boolean cancelable) {
        setCanceledOnTouchOutside(cancelable);
        setCancelable(cancelable);
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom_edittext);
        initView();
    }

    private void initView() {
        et_content = (EditText) findViewById(R.id.et_content);
        tv_title = (TextView) findViewById(R.id.title);
        tv_submit = (TextView) findViewById(R.id.submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSubmitListener != null) {
                    onSubmitListener.onClick(et_content.getText().toString());
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
                    onCancleListener.onClick(et_content.getText().toString());
                } else {
                    dismiss();
                }
            }
        });

        et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null)
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    if (onSubmitListener != null) {
                        onSubmitListener.onClick(et_content.getText().toString());
                    } else {
                        dismiss();
                    }
                }
                return false;
            }
        });

        if (!TextUtils.isEmpty(content)) {
            et_content.setHint(content);
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
        void onClick(String content);
    }

    public interface OnSubmitListener {
        void onClick(String content);
    }
}
