package common.mediaselectlibrary.view;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.krx.ydzh.commoncore.constants.ARouterConfig;

import java.io.File;
import java.util.ArrayList;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseview.BaseActivity;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.ImageMessage;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class ShowVideoActivity extends BaseActivity {

    private VideoView vv_video;
//    private JZVideoPlayerStandard videoplayer;
    private ImageView iv_play;
    private ArrayList<ImageMessage> listImages;
    private int index;
    private String url;
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener jcAutoFullscreenListener;
    private JCVideoPlayerStandard jcVideoPlayerStandard;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_show_video;
    }

    @Override
    protected void getIntentData(Intent intent) {
        listImages = intent.getParcelableArrayListExtra("listPath");
        url = intent.getStringExtra("url");
        if (EmptyUtil.isEmpty(listImages))
            listImages = new ArrayList<>();
    }

    @Override
    protected void initWidget() {
//        videoplayer = findViewById(R.id.videoplayer);
        iv_play = findViewById(R.id.iv_play);
        vv_video = findViewById(R.id.vv_video);


//        jcVideoPlayerStandard = findViewById(R.id.jiecao_Player);
//        添加视频缩略图
//         ImageView thumbImageView = jcVideoPlayerStandard.thumbImageView;
//         使用Glide添加
//         Glide.with(this).load(imageUrl).into(thumbImageView );



    }

    @Override
    protected void setListener() {

//        videoplayer.setUp(url, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
//        videoplayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
//        iv_play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                vv_video.start();
//                vv_video.requestFocus();
//                iv_play.setVisibility(View.GONE);
//            }
//        });
    }

    @Override
    protected void init() {
        vv_video.setMediaController(new MediaController(this));

        if(TextUtils.isEmpty(url)) {
            if (!EmptyUtil.isEmpty(listImages.get(0))) {
                ImageMessage imageMessage = listImages.get(0);
                if (imageMessage.getType() == ImageMessage.PATH) {
                    File file = new File(imageMessage.getPath());
                    vv_video.setVideoPath(file.getPath());

                } else {
                    vv_video.setVideoPath(imageMessage.getPath());
                }
            }
        }else {
            vv_video.setVideoPath(url);
        }


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        sensorManager.registerListener(jcAutoFullscreenListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
//
//    }

    @Override
    public void onBackPressed() {
//        if (JCVideoPlayer.backPress()) {
//            return;
//        }
//        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void startActivity(Activity context, ArrayList<ImageMessage> listPath) {
        Intent intent = new Intent(context, ShowVideoActivity.class);
        intent.putParcelableArrayListExtra("listPath", listPath);
        context.startActivity(intent);
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void startActivity(Activity context, String url) {
        Intent intent = new Intent(context, ShowVideoActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }



//    @Override
//    protected void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(jcAutoFullscreenListener);
//        JCVideoPlayer.releaseAllVideos();
//    }
}
