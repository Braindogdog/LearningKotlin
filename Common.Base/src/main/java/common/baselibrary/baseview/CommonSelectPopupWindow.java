package common.baselibrary.baseview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import common.baselibrary.R;
import common.baselibrary.irecyclerview.IRecyclerView;

/**
 * Created by chasen on 2018/4/18.
 */

public class CommonSelectPopupWindow<T> extends PopupWindow {
    private Context mContext;
    private String title;
    private OnCancelClickListener onCancelClickListener;
    private OnItemClickListener onItemClickListener;

    private TextView tv_cancel;
    private TextView tv_title;
    private IRecyclerView recyclerview;
    private SelectAdapter selectAdapter;

    private List<T> list;

    /**
     * 构造函数，list中的对象要有getPickerViewText()方法返回字符串，会根据此方法反射出要显示的内容
     * 否则会直接显示这个对象toString的内容
     *
     * @param context
     * @param list
     */
    public CommonSelectPopupWindow(@NonNull Context context, List<T> list) {
        super(context);
        this.mContext = context;
        this.list = list;
        initPopupWindow();
    }

    public CommonSelectPopupWindow setList(List<T> list) {
        this.list = list;
        selectAdapter.setList(list);
        return this;
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_select, null);
        tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_title = view.findViewById(R.id.tv_title);
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        selectAdapter = new SelectAdapter();
        recyclerview.setIAdapter(selectAdapter);
        selectAdapter.setList(list);
        selectAdapter.setOnItemClickListener(new common.baselibrary.irecyclerview.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v, Object object) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClickItem(object);
                } else {
                    dismiss();
                }
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCancelClickListener != null) {
                    onCancelClickListener.onClick();
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

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public CommonSelectPopupWindow setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
        return this;
    }

    public CommonSelectPopupWindow setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

    public interface OnCancelClickListener {
        void onClick();
    }

    public interface OnItemClickListener {
        void onClickItem(Object object);
    }

}
