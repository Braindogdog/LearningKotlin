package common.baselibrary.baseview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import common.baselibrary.R;
import common.baselibrary.baseutil.ActivityManager;

/**
 * Created by chasen on 2018/3/29.
 * CommonTitleBar一定要配合BaseActivity使用，这样返回按钮默认的返回事件才有意义
 */

public class CommonTitleBar extends FrameLayout {

    private TextView tv_title, tv_title_right,tv_title_right2,tv_title_right3;
    private ImageView iv_back, iv_right1, iv_right2, iv_right1_red;
    private FrameLayout fl_back, fl_right1;
    private View v_space;

    private Activity currentActivity;//当前页面所对应的activity，需要手动传入

    private onBackClickListener onBackClickListener;
    private onTitleClickListener onTitleClickListener;
    private onTitleRightClickListener onTitleRightClickListener;
    private onTitleRight2ClickListener onTitleRight2ClickListener;
    private onTitleRight3ClickListener onTitleRight3ClickListener;
    private onRight1ImageClickListener onRight1ImageClickListener;
    private onRight2ImageClickListener onRight2ImageClickListener;


    public CommonTitleBar(Context context) {
        this(context, null);
    }

    public CommonTitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.view_common_titlebar, this);
        tv_title = view.findViewById(R.id.tv_title);
        tv_title_right = view.findViewById(R.id.tv_title_right);
        tv_title_right2 = view.findViewById(R.id.tv_title_right2);
        tv_title_right3 = view.findViewById(R.id.tv_title_right3);
        iv_back = view.findViewById(R.id.iv_back);
        fl_back = view.findViewById(R.id.fl_back);
        fl_right1 = view.findViewById(R.id.fl_right1);
        v_space = view.findViewById(R.id.v_space);
        iv_right1 = view.findViewById(R.id.iv_right1);
        iv_right2 = view.findViewById(R.id.iv_right2);
        iv_right1_red = view.findViewById(R.id.iv_right1_red);
        if (ActivityManager.getInstance().getSize() <= 1) {
            fl_back.setVisibility(GONE);
            v_space.setVisibility(VISIBLE);
        } else {
            fl_back.setVisibility(VISIBLE);
            v_space.setVisibility(GONE);
        }
        fl_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBackClickListener != null) {
                    onBackClickListener.onClick();
                } else {
                    if (currentActivity != null) {
                        currentActivity.finish();
                    } else {
                        Activity activity = ActivityManager.getInstance().pop();
                        if (activity != null) {
                            activity.finish();
                        }
                    }
                }
            }
        });
        tv_title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTitleClickListener != null) {
                    onTitleClickListener.onClick();
                }
            }
        });
        tv_title_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTitleRightClickListener != null) {
                    onTitleRightClickListener.onClick();
                }
            }
        });
        tv_title_right2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTitleRight2ClickListener != null) {
                    onTitleRight2ClickListener.onClick();
                }
            }
        });
        tv_title_right3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTitleRight3ClickListener != null) {
                    onTitleRight3ClickListener.onClick();
                }
            }
        });
        iv_right1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRight1ImageClickListener != null) {
                    onRight1ImageClickListener.onClick();
                }
            }
        });
        iv_right2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRight2ImageClickListener != null) {
                    onRight2ImageClickListener.onClick();
                }
            }
        });
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public void setTitle(@NonNull String message) {
        tv_title.setText(message);
    }

    public void setRightTitle(@NonNull String message) {
        tv_title_right.setVisibility(VISIBLE);
        tv_title_right.setText(message);
    }

    public void setRight2Title(@NonNull String message) {
        tv_title_right2.setVisibility(VISIBLE);
        tv_title_right2.setText(message);
    }

    public void setRight3Title(@NonNull String message) {
        tv_title_right3.setVisibility(VISIBLE);
        tv_title_right3.setText(message);
    }

    public void setBackResorce(int resId) {
        iv_back.setBackgroundResource(resId);
    }

    public void setRight1ImageResorce(int resId) {
        fl_right1.setVisibility(VISIBLE);
        iv_right1.setBackgroundResource(resId);
    }

    public void setRight1ImageRed(int visibility) {
        iv_right1_red.setVisibility(visibility);
        fl_right1.setVisibility(visibility);
    }

    public void setRight2ImageResorce(int resId) {
        iv_right2.setVisibility(VISIBLE);
        iv_right2.setBackgroundResource(resId);
    }

    public void setOnBackClickListener(CommonTitleBar.onBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
    }

    public void setOnTitleClickListener(CommonTitleBar.onTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public void setOnTitleRightClickListener(CommonTitleBar.onTitleRightClickListener onTitleRightClickListener) {
        this.onTitleRightClickListener = onTitleRightClickListener;
    }

    public void setOnTitleRight2ClickListener(CommonTitleBar.onTitleRight2ClickListener onTitleRight2ClickListener) {
        this.onTitleRight2ClickListener = onTitleRight2ClickListener;
    }

    public void setOnTitleRight3ClickListener(CommonTitleBar.onTitleRight3ClickListener onTitleRight3ClickListener) {
        this.onTitleRight3ClickListener = onTitleRight3ClickListener;
    }

    public void setOnRight1ImageClickListener(CommonTitleBar.onRight1ImageClickListener onRight1ImageClickListener) {
        this.onRight1ImageClickListener = onRight1ImageClickListener;
    }

    public void setOnRight2ImageClickListener(CommonTitleBar.onRight2ImageClickListener onRight2ImageClickListener) {
        this.onRight2ImageClickListener = onRight2ImageClickListener;
    }

    public interface onBackClickListener {
        void onClick();
    }

    public interface onTitleClickListener {
        void onClick();
    }

    public interface onTitleRightClickListener {
        void onClick();
    }

    public interface onTitleRight2ClickListener {
        void onClick();
    }

    public interface onTitleRight3ClickListener {
        void onClick();
    }

    public interface onRight1ImageClickListener {
        void onClick();
    }

    public interface onRight2ImageClickListener {
        void onClick();
    }
}
