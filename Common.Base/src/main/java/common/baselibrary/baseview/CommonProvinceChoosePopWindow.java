package common.baselibrary.baseview;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import common.baselibrary.baseview.wheelview.OnItemSelectedListener;
import common.baselibrary.baseview.wheelview.WheelView;
import common.baselibrary.choosecity.AddressDBManager;
import common.baselibrary.choosecity.AreaModel;
import common.baselibrary.choosecity.CityModel;
import common.baselibrary.choosecity.ProvinceModel;

/**
 * Created by chasen on 2018/3/23.
 */

public class CommonProvinceChoosePopWindow extends PopupWindow {

    private Context mContext;
    private OnSubmitClickListener onSubmitClickListener;
    private OnCancelClickListener onCancelClickListener;
    private TextView tv_title;
    private Button btn_cancel;
    private Button btn_submit;
    private WheelView wv_province;
    private SQLiteDatabase database;
    private AddressDBManager manager;

    private ArrayList<ProvinceModel> listProvince;

    public CommonProvinceChoosePopWindow(Context context) {
        super(context);
        this.mContext = context;
        initPopupWindow();
    }

    public CommonProvinceChoosePopWindow setTitle(@NonNull String title) {
        tv_title.setText(title);
        return this;
    }

    public void initPopupWindow() {
        listProvince = new ArrayList<>();
        View view = LayoutInflater.from(mContext).inflate(R.layout.pickerview_province, null);
        tv_title = view.findViewById(R.id.tv_title);
        btn_cancel = view.findViewById(R.id.btn_Cancel);
        btn_submit = view.findViewById(R.id.btn_Submit);
        wv_province = view.findViewById(R.id.wv_province);
        wv_province.setCyclic(false);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSubmitClickListener != null) {
                    int indexProvince = wv_province.getCurrentItem();
                    ProvinceModel provinceModel = null;
                    if (listProvince != null && listProvince.size() > indexProvince)
                        provinceModel = listProvince.get(indexProvince);
                    onSubmitClickListener.onClick(provinceModel);
                } else {
                    dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCancelClickListener != null) {
                    int indexProvince = wv_province.getCurrentItem();
                    ProvinceModel provinceModel = null;
                    if (listProvince != null && listProvince.size() > indexProvince)
                        provinceModel = listProvince.get(indexProvince);
                    onCancelClickListener.onClick(provinceModel);
                } else {
                    dismiss();
                }
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int top = v.findViewById(R.id.ll_container).getTop();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int y = (int) event.getY();
                    if (y < top) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        //设置显示内容
        manager = new AddressDBManager(mContext);
        database = manager.getAddressDatabase();
        listProvince = manager.getProvinces(database);
        //省
        if (listProvince != null && listProvince.size() > 0) {
            listProvince.remove(0);
            wv_province.setAdapter(new ArrayWheelAdapter(listProvince));
            wv_province.setCurrentItem(0);
        }
        setContentView(view);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public CommonProvinceChoosePopWindow setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
        return this;
    }

    public CommonProvinceChoosePopWindow setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
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
        void onClick(ProvinceModel provinceModel);
    }

    public interface OnCancelClickListener {
        void onClick(ProvinceModel provinceModel);
    }
}
