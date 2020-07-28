/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package common.baidunavigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;


import common.baidunavigation.listener.OnActivityShowingListener;

/**
 * 诱导界面
 */
public class GuideTTSActivity extends Activity {
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        View view = NaviManager.getInstance().getView(this);

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            setContentView(view);
        }

        NaviManager.getInstance().setOnActivityShowingListener(new OnActivityShowingListener() {
            @Override
            public void onActivityDestory() {
                finish();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!NaviManager.getInstance().isGuideing()) {
            NaviManager.getInstance().onActivityStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        NaviManager.getInstance().onActivityResume();
    }

    protected void onPause() {
        super.onPause();
//        NaviManager.getInstance().onActivityPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        NaviManager.getInstance().onActivityStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        NaviManager.getInstance().onActivityDestroy();
        NaviManager.getInstance().setOnActivityShowingListener(null);
    }

    @Override
    public void onBackPressed() {
        NaviManager.getInstance().onActivityBackPressed();
    }

    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        NaviManager.getInstance().onActivityConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (!NaviManager.getInstance().onActivityKeyDown(keyCode, event)) {
            return super.onKeyDown(keyCode, event);
        }
        return true;

    }

}
