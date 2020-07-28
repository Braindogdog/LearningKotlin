package common.baselibrary.baseview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import common.baselibrary.R;
import common.baselibrary.baseview.wheelview.WheelView;

/**
 * Created by chasen on 2018/3/22.
 */

public class CommonDoublePickerPopupWindow extends PopupWindow {
    private Context mContext;
    private TextView tv_title;
    private Button btn_cancel;
    private Button btn_submit;
    private WheelView wv_option1;
    private WheelView wv_option2;

    private OnSubmitClickListener onSubmitClickListener;
    private OnCancelClickListener onCancelClickListener;

    private ArrayList<?> list1;
    private ArrayList<?> list2;

    /**
     * 构造函数，list中的对象要有getPickerViewText()方法返回字符串，会根据此方法反射出要显示的内容
     * 否则会直接显示这个对象toString的内容
     *
     * @param context
     * @param list1,list2
     */
    public CommonDoublePickerPopupWindow(@NonNull Context context, ArrayList<?> list1, ArrayList<?> list2) {
        super(context);
        this.mContext = context;
        this.list1 = list1;
        this.list2 = list2;
        initPopupWindow();
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_wheel_double_option, null);
        tv_title = view.findViewById(R.id.tv_title);
        btn_cancel = view.findViewById(R.id.btn_Cancel);
        btn_submit = view.findViewById(R.id.btn_Submit);
        wv_option1 = view.findViewById(R.id.wv_option1);
        wv_option1.setAdapter(new ArrayWheelAdapter(list1));
        wv_option1.setCyclic(false);
        wv_option2 = view.findViewById(R.id.wv_option2);
        wv_option2.setAdapter(new ArrayWheelAdapter(list2));
        wv_option2.setCyclic(false);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSubmitClickListener != null) {
                    onSubmitClickListener.onClick(wv_option1.getCurrentItem(), wv_option2.getCurrentItem());
                } else {
                    dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCancelClickListener != null) {
                    onCancelClickListener.onClick(wv_option1.getCurrentItem(), wv_option2.getCurrentItem());
                } else {
                    dismiss();
                }
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
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
        setContentView(view);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public CommonDoublePickerPopupWindow setTitle(@NonNull String title) {
        tv_title.setText(title);
        return this;
    }

    public CommonDoublePickerPopupWindow setLable(String lable1, String lable2) {
        wv_option1.setLabel(lable1);
        wv_option2.setLabel(lable2);
        return this;
    }

    /**
     * 设置是否可以循环滚动
     *
     * @param cyclic1
     * @param cyclic2
     * @return
     */
    public CommonDoublePickerPopupWindow setCyclic(boolean cyclic1, boolean cyclic2) {
        wv_option1.setCyclic(cyclic1);
        wv_option2.setCyclic(cyclic2);
        return this;
    }

    public CommonDoublePickerPopupWindow setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
        return this;
    }

    public CommonDoublePickerPopupWindow setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
        return this;
    }

    /**
     * 设置显示位置
     *
     * @param currentItem1
     * @param currentItem2
     */
    public void setCurrentItem(int currentItem1, int currentItem2) {
        wv_option1.setCurrentItem(currentItem1);
        wv_option2.setCurrentItem(currentItem2);
    }

    /**
     * 显示的方法
     */
    public void show() {
        if (!isShowing())
            showAtLocation(((ViewGroup) ((Activity) mContext).findViewById(android.R.id.content)).getChildAt(0), Gravity.CENTER, 0, 0);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (!isShowing())
            super.showAtLocation(((ViewGroup) ((Activity) mContext).findViewById(android.R.id.content)).getChildAt(0), Gravity.CENTER, 0, 0);
    }

    public interface OnSubmitClickListener {
        void onClick(int postion1, int postion2);
    }

    public interface OnCancelClickListener {
        void onClick(int postion1, int postion2);
    }

}
