package common.baselibrary.baseview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

//import com.umeng.message.PushAgent;

import common.baselibrary.R;
import common.baselibrary.baseutil.ActivityManager;
import common.baselibrary.baseutil.SystemBarUtil;

/**
 * Created by chasen on 2018/3/21.
 * 所有activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context context = this;
    protected CommonTitleBar titleBar;
    public View superView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTransparentTopBar(this);
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().pushActivity(this);
//        PushAgent.getInstance(context).onAppStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarUpperAPI21();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setStatusBarUpperAPI19();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ActivityManager.getInstance().pushActivity(this);
        int layouId = getLayoutResID();
        if (layouId > 0) {
            View view = LayoutInflater.from(context).inflate(layouId, null);
            superView = view;
            setContentView(view);
            initTitleBar();
            getIntentData(getIntent());
            initWidget();
            init();
            setListener();
            setStatusBar();
        } else {
            Log.e("BaseActivity", "请重写getLayoutResID()");
            finish();
        }
    }

    public static void setTransparentTopBar(Activity act) {
        act.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    // 5.0版本以上
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setStatusBarUpperAPI21() {
        Window window = getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    // 4.4 - 5.0版本
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setStatusBarUpperAPI19() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View statusBarView = mContentView.getChildAt(0);
        //移除假的 View
        if (statusBarView != null && statusBarView.getLayoutParams() != null &&
                statusBarView.getLayoutParams().height == getStatusBarHeight()) {
            mContentView.removeView(statusBarView);
        }
        //不预留空间
        if (mContentView.getChildAt(0) != null) {
            ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), false);
        }
    }


    private int getStatusBarHeight() {
        int result = 0;
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = getResources().getDimensionPixelSize(resId);
        }
        return result;
    }


    public void setStatusBar() {
//        SystemBarUtil.setColor(this, getResources().getColor(R.color.main_color), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().popActivity(this);
    }

    public void initTitleBar() {
        titleBar = findViewById(R.id.titlebar);
        if (titleBar != null) {
            titleBar.setCurrentActivity(this);
            titleBar.setTitle(getTitle().toString());
        }
    }

    @NonNull
    protected abstract int getLayoutResID();

    protected abstract void getIntentData(Intent intent);

    protected abstract void initWidget();

    protected abstract void init();

    protected abstract void setListener();
}
