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

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import common.baselibrary.R;
import common.baselibrary.baseview.wheelview.WheelTime;

/**
 * Created by chasen on 2018/3/22.
 */

public class CommonDatePickerPopupWindow extends PopupWindow {

    public enum Type {
        ALL, YEAR_MONTH_DAY, YEAR_MONTH_DAY_HOUR, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
    }// 选择模式，年月日时分，年月日，年月日时 , 时分，月日时分 ,年月

    WheelTime wheelTime;
    private Context mContext;
    private OnSubmitClickListener onSubmitClickListener;
    private OnCancelClickListener onCancelClickListener;
    private TextView tv_title;
    private Button btn_cancel;
    private Button btn_submit;

    public CommonDatePickerPopupWindow(Context context, Type type) {
        super(context);
        this.mContext = context;
        initPopupWindow(type);
    }

    public CommonDatePickerPopupWindow setTitle(@NonNull String title) {
        tv_title.setText(title);
        return this;
    }

    private void initPopupWindow(Type type) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pickerview_times, null);
        tv_title = view.findViewById(R.id.tv_title);
        btn_cancel = view.findViewById(R.id.btn_Cancel);
        btn_submit = view.findViewById(R.id.btn_Submit);
        View timepickerview = view.findViewById(R.id.timepicker);
        wheelTime = new WheelTime(timepickerview, type);

        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSubmitClickListener != null) {
                    try {
                        Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
                        onSubmitClickListener.onClick(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCancelClickListener != null) {
                    try {
                        Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
                        onSubmitClickListener.onClick(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
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
        wheelTime.setPicker(year, month, day, hours, minute);
        setContentView(view);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public CommonDatePickerPopupWindow setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
        return this;
    }

    public CommonDatePickerPopupWindow setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
        return this;
    }

    /**
     * 设置可以选择的时间范围
     *
     * @param startYear
     * @param endYear
     */
    public CommonDatePickerPopupWindow setRange(int startYear, int endYear) {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);
        return this;
    }

    /**
     * 设置选中时间
     *
     * @param date
     */
    public CommonDatePickerPopupWindow setTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null)
            calendar.setTimeInMillis(System.currentTimeMillis());
        else
            calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPicker(year, month, day, hours, minute);
        return this;
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public CommonDatePickerPopupWindow setCyclic(boolean cyclic) {
        wheelTime.setCyclic(cyclic);
        return this;
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
        void onClick(Date date);
    }

    public interface OnCancelClickListener {
        void onClick(Date date);
    }
}
