package common.baidunavigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNaviCommonParams;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.baidu.navisdk.adapter.IBNRouteGuideManager;
import com.baidu.navisdk.adapter.IBNRoutePlanManager;
import com.baidu.navisdk.adapter.IBaiduNaviManager;
import com.baidu.navisdk.comapi.routeguide.RouteGuideParams;
import common.baidunavigation.entity.NaviPoint;
import common.baidunavigation.listener.OnActivityShowingListener;
import common.baidunavigation.listener.OnNaviInitListener;
import common.baidunavigation.listener.OnRouteUpdateListener;
import common.baidunavigation.listener.OnRouteplanListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmh on 2018/5/22.
 * <p>
 * 导航监听
 */

public class NaviManager {
    private Context appContext;
    //本app的导航数据存放路径,文件夹，TTS的appId
    private String path, appFolderName, ttsAppID;
    private OnNaviInitListener onNaviInitListener;
    private OnRouteUpdateListener onRouteUpdateListener;
    private OnActivityShowingListener onActivityShowingListener;
    private Bundle lastTureIcon;
    private String lastTureDistance;
    private IBNRouteGuideManager mRouteGuideManager;
    private IBNRouteGuideManager.OnNavigationListener mOnNavigationListener;
    private View view;
    //正在导航
    private boolean isGuideing;

    private static class SingletonHolder {
        private static NaviManager instance = new NaviManager();
    }

    private NaviManager() {
    }

    public static NaviManager getInstance() {
        return SingletonHolder.instance;
    }

    public NaviManager init(Context appContext, String path, String appFolderName, String ttsAppID) {
        this.appContext = appContext.getApplicationContext();
        this.path = path;
        this.appFolderName = appFolderName;
        this.ttsAppID = ttsAppID;
        initNavigationListener();
        return this;
    }

    private void initNavigationListener() {
        mOnNavigationListener = new IBNRouteGuideManager.OnNavigationListener() {

            @Override
            public void onNaviGuideEnd() {
                // 退出导航
                stopNavi();
            }

            @Override
            public void notifyOtherAction(int actionType, int arg1, int arg2, Object obj) {
                if (actionType == 0) {
                    // 导航到达目的地 自动退出
//                        Log.i(TAG, "notifyOtherAction actionType = " + actionType + ",导航到达目的地！");
                    stopNavi();
                }
            }
        };
    }


    /**
     * 初始化导航
     *
     * @return
     */
    public NaviManager initNavi(Activity activity, OnNaviInitListener listener) {
        onNaviInitListener = listener;
        BaiduNaviManagerFactory.getBaiduNaviManager().init(activity,
                path, appFolderName, new IBaiduNaviManager.INaviInitListener() {

                    @Override
                    public void onAuthResult(int status, String msg) {
                        // status==0 key校验成功；否则 key校验失败，失败说明为 msg
                        if (onNaviInitListener != null) {
                            onNaviInitListener.onAuthResult(status, msg);
                        }
                    }

                    @Override
                    public void initStart() {
                        //百度导航引擎初始化开始
                        if (onNaviInitListener != null) {
                            onNaviInitListener.initStart();
                        }
                    }

                    @Override
                    public void initSuccess() {
                        //百度导航引擎初始化成功
                        if (onNaviInitListener != null) {
                            onNaviInitListener.initSuccess();
                        }
                        // 初始化tts
                        initTTS();
                    }

                    @Override
                    public void initFailed() {
                        //百度导航引擎初始化失败
                        if (onNaviInitListener != null) {
                            onNaviInitListener.initFailed();
                        }
                    }
                });
        return this;
    }

    /**
     * 初始化百度TTS
     */
    private void initTTS() {
        BaiduNaviManagerFactory.getTTSManager().initTTS(appContext, path, appFolderName, ttsAppID);
    }


    /**
     * 开始算路
     *
     * @param activityContext
     * @param naviPointList
     * @param listener
     */
    public void routeplanToNavi(@NonNull final Context activityContext, @NonNull List<NaviPoint> naviPointList, final OnRouteplanListener listener) {
        List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
        BNRoutePlanNode mStartNode = null;
        for (NaviPoint naviPoint : naviPointList) {
            if (naviPoint != null) {
                if (mStartNode == null) {
                    mStartNode = new BNRoutePlanNode(naviPoint.getLongitude(), naviPoint.getLatitude(), naviPoint.getName(), naviPoint.getDescription(), naviPoint.getCoType());
                    list.add(mStartNode);
                } else {
                    list.add(new BNRoutePlanNode(naviPoint.getLongitude(), naviPoint.getLatitude(), naviPoint.getName(), naviPoint.getDescription(), naviPoint.getCoType()));
                }
            }
        }
        final BNRoutePlanNode finalMStartNode = mStartNode;
        BaiduNaviManagerFactory.getRoutePlanManager().routeplanToNavi(list,
                IBNRoutePlanManager.RoutePlanPreference.ROUTE_PLAN_PREFERENCE_DEFAULT,
                null,
                new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_START:
                                if (listener != null) {
                                    listener.onRoutePlanStart();
                                }
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_SUCCESS:
                                if (listener != null) {
                                    listener.onRoutePlanSuccess();
                                }
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_FAILED:
                                if (listener != null) {
                                    listener.onRoutePlanFailed();
                                }
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_TO_NAVI:
//                                setMapMini();
                                boolean isOpenGuide = false;
                                mRouteGuideManager = BaiduNaviManagerFactory.getRouteGuideManager();
                                if (listener != null) {
                                    //跳转导航界面
                                    isOpenGuide = listener.onRoutePlanToNavi();
                                }
                                //如果没有处理跳转导航页面的
                                if (!isOpenGuide) {
                                    if (activityContext == null || finalMStartNode == null) {
                                        break;
                                    }
                                    Intent intent = new Intent(activityContext, GuideTTSActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(NaiConstants.ROUTE_PLAN_NODE, finalMStartNode);
                                    intent.putExtras(bundle);
                                    activityContext.startActivity(intent);
                                }
                                routeGuideEvent();
                                isGuideing = true;
                                break;
                            default:
                                // nothing
                                break;
                        }
                    }
                });
    }

    /**
     * 导航界面地图右下角小窗口
     * 分为两种模式——小窗口和路况条
     * 现在设置为小窗口模式
     *
     * @return
     */
    private void setMapMini() {
        BaiduNaviManagerFactory.getProfessionalNaviSettingManager()
                .setFullViewMode(RouteGuideParams.PreViewRoadCondition.MapMini);
    }

    public Bitmap getLastTureIcon() {
        return bundleToBitmap(lastTureIcon);
    }

    public Bitmap bundleToBitmap(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        byte[] byteArray = bundle.getByteArray(BNaviCommonParams.BNGuideKey.ROAD_TURN_ICON);
        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        //图片背景默认是黑色，
//        Bitmap tureIcon = replaceBitmapColor(bm, oldColor, newColor);
        return bm;

    }

    /**
     * 将bitmap中的某种颜色值替换成新的颜色
     *
     * @param oldBitmap
     * @param oldColor
     * @param newColor
     * @return
     */
    public static Bitmap replaceBitmapColor(Bitmap oldBitmap, int oldColor, int newColor) {
        //相关说明可参考 http://xys289187120.blog.51cto.com/3361352/657590/
        Bitmap mBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true);
        //循环获得bitmap所有像素点
        int mBitmapWidth = mBitmap.getWidth();
        int mBitmapHeight = mBitmap.getHeight();
        int mArrayColorLengh = mBitmapWidth * mBitmapHeight;
        int[] mArrayColor = new int[mArrayColorLengh];
        int count = 0;
        for (int i = 0; i < mBitmapHeight; i++) {
            for (int j = 0; j < mBitmapWidth; j++) {
                int color = mBitmap.getPixel(j, i);
                if (color == oldColor) {
                    mBitmap.setPixel(j, i, newColor);
                }

            }
        }
        return mBitmap;
    }

    /**
     * 导航过程事件监听
     */
    private void routeGuideEvent() {
        BaiduNaviManagerFactory.getRouteGuideManager().setRouteGuideEventListener(
                new IBNRouteGuideManager.IRouteGuideEventListener() {
                    @Override
                    public void onCommonEventCall(int what, int arg1, int arg2, Bundle bundle) {
//                        Log.i("onCommonEventCall", String.format("%d,%d,%d,%s", what, arg1, arg2,
//                                (bundle == null ? "" : bundle.toString())));
                        if (what == BNaviCommonParams.MessageType.EVENT_ROAD_TURN_ICON_UPDATE) {
                            if (onRouteUpdateListener != null) {
                                //转向图标改变
                                onRouteUpdateListener.onRoadTurnIconUpdate(bundleToBitmap(bundle));
                            } else {
                                setLastTureIcon(bundle);
                            }
                        } else if (what == BNaviCommonParams.MessageType.EVENT_ROAD_TURN_DISTANCE_UPDATE) {
                            //tureDistance = 多少米后转向，例如：150米
                            String tureDistance = bundle.getString(BNaviCommonParams.BNGuideKey.TROAD_TURN_DISTANCE);
                            if (onRouteUpdateListener != null) {
                                onRouteUpdateListener.onRoadTurnDistanceUpdate(tureDistance);
                            } else {
                                setLastTureDistance(tureDistance);
                            }
                        }
                    }
                }
        );
    }

    /**
     * 是否正在导航
     */
    public boolean isGuideing() {
        return isGuideing;
    }


    public IBNRouteGuideManager getmRouteGuideManager() {
        return mRouteGuideManager;
    }

    public IBNRouteGuideManager.OnNavigationListener getmOnNavigationListener() {
        return mOnNavigationListener;
    }

    public void setLastTureIcon(Bundle lastTureIcon) {
        this.lastTureIcon = lastTureIcon;
    }

    public String getLastTureDistance() {
        return lastTureDistance;
    }

    public void setLastTureDistance(String lastTureDistance) {
        this.lastTureDistance = lastTureDistance;
    }

    public void setOnRouteUpdateListener(OnRouteUpdateListener onRouteUpdateListener) {
        this.onRouteUpdateListener = onRouteUpdateListener;
    }

    public void setOnActivityShowingListener(OnActivityShowingListener onActivityShowingListener) {
        this.onActivityShowingListener = onActivityShowingListener;
    }

    /**
     * 获取导航界面
     *
     * @param activity
     * @return
     */
    public View getView(Activity activity) {
        if (view != null && isGuideing()) {
            return view;
        } else {
            if (mRouteGuideManager == null) {
                mRouteGuideManager = BaiduNaviManagerFactory.getRouteGuideManager();
            }
            view = mRouteGuideManager.onCreate(activity, mOnNavigationListener);
            return view;
        }
    }

    //activity生命周期调用
    public void onActivityStart() {
        if (mRouteGuideManager != null) {
            mRouteGuideManager.onStart();
        }
    }

    //activity生命周期调用
    public void onActivityResume() {
        if (mRouteGuideManager != null) {
            mRouteGuideManager.onResume();
        }
    }

    //activity生命周期调用
    public void onActivityPause() {
        if (mRouteGuideManager != null) {
            mRouteGuideManager.onPause();
        }
    }

    //activity生命周期调用
    public void onActivityStop() {
        if (mRouteGuideManager != null) {
            mRouteGuideManager.onStop();
        }
    }

    //activity生命周期调用
    public void onActivityDestroy() {
        if (mRouteGuideManager != null) {
            mRouteGuideManager.onDestroy(false);
        }
    }

    //按下返回键
    public void onActivityBackPressed() {
        if (mRouteGuideManager != null) {
            mRouteGuideManager.onBackPressed(false, true);
        }
    }

    //按键
    public boolean onActivityKeyDown(int keyCode, android.view.KeyEvent event) {
        if (mRouteGuideManager != null) {
            return mRouteGuideManager.onKeyDown(keyCode, event);
        } else {
            return false;
        }
    }

    //activity状态变化，比如屏幕旋转等
    public void onActivityConfigurationChanged(android.content.res.Configuration newConfig) {
        if (mRouteGuideManager != null) {
            mRouteGuideManager.onConfigurationChanged(newConfig);
        }
    }

    /**
     * 退出导航
     */
    public void stopNavi() {
        if (mRouteGuideManager != null) {
            try{
                mRouteGuideManager.forceQuitNaviWithoutDialog();
            }catch (Exception e){
                //按下导航界面上的“退出”按钮后
                //这里会报错  android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views
            }
        }
        if (onRouteUpdateListener != null) {
            onRouteUpdateListener.onRoadNaviGuideEnd();
        }
        if(onActivityShowingListener != null){
            onActivityShowingListener.onActivityDestory();
        }
        isGuideing = false;
        view = null;
        setLastTureDistance("");
        setLastTureIcon(null);
    }


}
