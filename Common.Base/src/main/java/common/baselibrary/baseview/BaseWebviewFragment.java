package common.baselibrary.baseview;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import common.baselibrary.R;

/**
 * 调取webview fragment基类
 */
public class BaseWebviewFragment extends BaseFragment {
    protected WebView webView;
    protected AppCompatSeekBar vProgress;

    /**
     * @param url
     *          需要打开的url
     * @param hasProgress
     *          是否需要顶部的进度条
     * @return
     */
    public static BaseWebviewFragment getBaseWebviewFragment(String url, boolean hasProgress) {
        BaseWebviewFragment baseWebviewFragment = new BaseWebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putBoolean("progress", hasProgress);
        baseWebviewFragment.setArguments(bundle);
        return baseWebviewFragment;
    }

    protected String getUrl() {
        return getArguments().getString("url");
    }

    protected boolean hasProgress() {
        return getArguments().getBoolean("progress", true);
    }

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_base_webview;
    }

    @Override
    protected void initWidget(View view) {
        webView = view.findViewById(R.id.webView);
        vProgress = view.findViewById(R.id.vProgress);
    }

    @Override
    protected void setListener() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                onWebviewProgressChanged(newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                webView.loadUrl("file:///android_asset/web_error/error.html");
            }
        });

        webView.loadUrl(getUrl());
    }

    protected void onWebviewProgressChanged(int newProgress) {
        if (hasProgress()) {
            if (newProgress != 100) {
                vProgress.setProgress(newProgress);
                vProgress.setVisibility(View.VISIBLE);
            } else {
                vProgress.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void init() {
        WebSettings settings = webView.getSettings();
        //不使用缓存
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        if (!hasProgress()) {
            vProgress.setVisibility(View.GONE);
        }
    }

}
