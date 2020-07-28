package common.baselibrary.baseview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import common.baselibrary.R;
import common.baselibrary.baseutil.EmptyUtil;

public class CommonWebViewActivity extends BaseActivity {

    private ProgressBar pb_webview_progress;
    private WebView webView;
    public static final String LOAD_URL = "loadurl";
    public static final String RIGHT_TITLE = "right_title";
    public static final String RIGHT_URL = "right_url";
    private String loadurl;
    private String rightTitle;
    private String rightUrl;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_common_web_view;
    }

    @Override
    protected void getIntentData(Intent intent) {
        loadurl = intent.getStringExtra(LOAD_URL);
        rightTitle = intent.getStringExtra(RIGHT_TITLE);
        rightUrl = intent.getStringExtra(RIGHT_URL);
    }

    @Override
    protected void initWidget() {
        pb_webview_progress = findViewById(R.id.pb_webview_progress);
        webView = findViewById(R.id.webview);
    }

    @Override
    protected void setListener() {
        titleBar.setOnBackClickListener(new CommonTitleBar.onBackClickListener() {
            @Override
            public void onClick() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
        titleBar.setOnTitleRightClickListener(new CommonTitleBar.onTitleRightClickListener() {
            @Override
            public void onClick() {
                CommonWebViewActivity.startActivity(context, rightUrl);
            }
        });
    }

    @Override
    protected void init() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initWebView();
        if(!EmptyUtil.isEmpty(rightTitle)){
            titleBar.setRightTitle(rightTitle);
        }
    }

    private void initWebView() {
        pb_webview_progress.setMax(100);//设置加载进度最大值
        if (EmptyUtil.isEmpty(loadurl)) {
            loadurl = "http://www.baidu.com";
        }
        WebSettings webSettings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//加载缓存否则网络
        }

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解码
        }
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);//硬件解码

        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setSupportZoom(true);// 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false

        webSettings.setDisplayZoomControls(false);//隐藏缩放工具
        webSettings.setUseWideViewPort(true);// 扩大比例的缩放

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDatabaseEnabled(true);//
        webSettings.setSavePassword(true);//保存密码
        webSettings.setDomStorageEnabled(false);//是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webView.setSaveEnabled(true);
        webView.setKeepScreenOn(true);
        webView.setDownloadListener(new MyWebViewDownLoadListener());

        // 设置setWebChromeClient对象
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleBar.setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb_webview_progress.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        //设置此方法可在WebView中打开链接，反之用浏览器打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!webView.getSettings().getLoadsImagesAutomatically()) {
                    webView.getSettings().setLoadsImagesAutomatically(true);
                }
                pb_webview_progress.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb_webview_progress.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                if (url.startsWith("http:") || url.startsWith("https:")) {
//                    view.loadUrl(url);
//                    return false;
//                }
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return true;
//            }
        });
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(paramAnonymousString1));
                startActivity(intent);
            }
        });

        webView.loadUrl(loadurl);
    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    public static void startActivity(Context context, String loadurl) {
        Intent intent = new Intent(context, CommonWebViewActivity.class);
        intent.putExtra(LOAD_URL, loadurl);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String loadurl,String rightTitle,String rightUrl) {
        Intent intent = new Intent(context, CommonWebViewActivity.class);
        intent.putExtra(LOAD_URL, loadurl);
        intent.putExtra(RIGHT_TITLE, rightTitle);
        intent.putExtra(RIGHT_URL, rightUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        paramBundle.putString("url", webView.getUrl());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
