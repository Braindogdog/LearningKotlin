package common.baselibrary.baseview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import common.baselibrary.R;

/**
 * Created by chasen on 2018/3/26.
 */

public class CommonSharePopupWindow extends PopupWindow {
    private Context mContext;
    private String title;
    private OnItemClickListener mListener;
    private TextView tv_title;

    public CommonSharePopupWindow(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initPopupWindow();
    }

    public CommonSharePopupWindow setTitle(@Nullable String title) {
        this.title = title;
        tv_title.setText(title);
        return this;
    }

    public CommonSharePopupWindow setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
        return this;
    }

    protected void initPopupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_share, null);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        view.findViewById(R.id.target1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClick(1);
                } else {
                    dismiss();
                }
            }
        });
        view.findViewById(R.id.target2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClick(2);
                } else {
                    dismiss();
                }
            }
        });
        view.findViewById(R.id.target3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClick(3);
                } else {
                    dismiss();
                }
            }
        });
        view.findViewById(R.id.target4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClick(4);
                } else {
                    dismiss();
                }
            }
        });
        view.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int top = view.findViewById(R.id.ll_container).getTop();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    int y = (int) motionEvent.getY();
                    if (y < top) {
                        dismiss();
                    }
                }
                return true;
            }
        });
        tv_title = view.findViewById(R.id.tv_title);

        setContentView(view);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void show() {
        if (!isShowing())
            showAtLocation(((ViewGroup) ((Activity) mContext).findViewById(android.R.id.content)).getChildAt(0), Gravity.CENTER, 0, 0);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (!isShowing())
            super.showAtLocation(parent, gravity, x, y);
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
