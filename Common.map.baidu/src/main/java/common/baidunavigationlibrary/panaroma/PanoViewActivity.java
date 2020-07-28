//package common.baidunavigationlibrary.panaroma;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.SurfaceView;
//import android.view.Window;
//import android.widget.Toast;
//
//import com.baidu.lbsapi.BMapManager;
//import com.baidu.lbsapi.MKGeneralListener;
//import com.baidu.lbsapi.panoramaview.PanoramaView;
//import com.baidu.lbsapi.panoramaview.PanoramaViewListener;
//import com.baidu.lbsapi.tools.CoordinateConverter;
//import com.baidu.lbsapi.tools.Point;
//
//import common.baidunavigationlibrary.R;
//import common.baselibrary.baseutil.GsonUtil;
//
///**
// * 全景Demo主Activity
// */
//public class PanoViewActivity extends Activity {
//
//    private static final String LTAG = "BaiduPanoSDKDemo";
//
//    private PanoramaView mPanoView;
//    private double latitude = 39.963175;
//    private double longitude = 116.400244;
//    //定义操作动作
//    private final int ACTION_DRAG = 0;
//    private final int ACTION_CLICK = 1;
//    private MyHandler handler;
//    private String name, pid;
//    public BMapManager mBMapManager = null;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        // 先初始化BMapManager
//        initEngineManager(getApplicationContext());
//        setContentView(R.layout.panodemo_main);
//
//        if (getIntent() != null) {
//            latitude = getIntent().getDoubleExtra("latitude", 0);
//            longitude = getIntent().getDoubleExtra("longitude", 0);
//            name = "name";
//            pid = "4373943294879844934";
//        }
//
//        initView();
//
//        testPano();
//    }
//
//    public void initEngineManager(Context context) {
//        if (mBMapManager == null) {
//            mBMapManager = new BMapManager(context);
//        }
//
//        if (!mBMapManager.init(new MyGeneralListener())) {
//            Toast.makeText(this, "BMapManager  初始化错误!",
//                    Toast.LENGTH_LONG).show();
//        }
//    }
//
//    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
//    class MyGeneralListener implements MKGeneralListener {
//
//        @Override
//        public void onGetPermissionState(int iError) {
//            // 非零值表示key验证未通过
//            if (iError != 0) {
//                // 授权Key错误：
//                Toast.makeText(PanoViewActivity.this,
//                        "请在AndoridManifest.xml中输入正确的授权Key,并检查您的网络连接是否正常！error: " + iError, Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(PanoViewActivity.this, "key认证成功", Toast.LENGTH_LONG)
//                        .show();
//            }
//        }
//    }
//
//    private void initView() {
//        mPanoView = (PanoramaView) findViewById(R.id.panorama);
//        mPanoView.setPanoramaImageLevel(PanoramaView.ImageDefinition.ImageDefinitionHigh);
//
//        if (mPanoView != null && mPanoView.getChildCount() > 0) {
//            if (mPanoView.getChildAt(0) instanceof SurfaceView) {
//            }
//        }
//    }
//
//    private void testPano() {
//        handler = new MyHandler();
//
//        mPanoView.setShowTopoLink(true);
//
//
//        mPanoView.setPanorama(longitude, latitude);
//
//        // 测试回调函数,需要注意的是回调函数要在setPanorama()之前调用，否则回调函数可能执行异常
//        mPanoView.setPanoramaViewListener(new PanoramaViewListener() {
//
//            @Override
//            public void onLoadPanoramaBegin() {
//                Log.i(LTAG, "onLoadPanoramaStart...");
//            }
//
//            @Override
//            public void onLoadPanoramaEnd(String json) {
////                Log.e(LTAG, "onLoadPanoramaEnd : " + json);
//            }
//
//            @Override
//            public void onLoadPanoramaError(String error) {
////                Log.e(LTAG, "onLoadPanoramaError : " + error);
//
//            }
//
//            @Override
//            public void onDescriptionLoadEnd(String json) {
//
//                PanoramaBean panoramaBean = GsonUtil.json2Bean(json, PanoramaBean.class);
//
//                Point pointll = CoordinateConverter.MCConverter2LL(panoramaBean.getX(), panoramaBean.getY());
//
//                HotCityPanoBean hotCityPanoBean = new HotCityPanoBean();
//                hotCityPanoBean.setPid(pid);
//                hotCityPanoBean.setName(name);
//                hotCityPanoBean.setLatitude(pointll.y);
//                hotCityPanoBean.setLongitude(pointll.x);
//
//
//                Log.e(LTAG, "onDescriptionLoadEnd : " + hotCityPanoBean.getLatitude() + ", "
//                        + hotCityPanoBean.getLongitude() + "\n"
//                        + hotCityPanoBean.getName() + ", "
//                        + hotCityPanoBean.getPid());
//
//
//            }
//
//            @Override
//            public void onMessage(String msgName, int msgType) {
//                switch (msgType) {
//                    case 8213:
//                        //旋转
//                        Message message = new Message();
//                        message.what = ACTION_DRAG;
//                        message.arg1 = (int) mPanoView.getPanoramaHeading();
//                        handler.sendMessage(message);
//                        break;
//                    case 12302:
//                        //点击
//                        Message msg = new Message();
//                        msg.what = ACTION_CLICK;
//                        handler.sendMessage(msg);
//                        break;
//                    default:
//                        break;
//                }
//
//
//            }
//
//            @Override
//            public void onCustomMarkerClick(String key) {
//
//            }
//
//            @Override
//            public void onMoveStart() {
//
//            }
//
//            @Override
//            public void onMoveEnd() {
//
//            }
//        });
//
//
//    }
//
//
//    private class MyHandler extends Handler {
//
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what) {
//                case ACTION_CLICK:
//
//                    break;
//                case ACTION_DRAG:
//                    float heading = (float) msg.arg1;
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mPanoView.destroy();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mPanoView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mPanoView.onPause();
//    }
//}
