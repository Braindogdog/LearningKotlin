package common.mediaselectlibrary.presenter;

import android.content.Intent;

import java.io.File;

/**
 * Created by chasen on 2018/3/13.
 */

public interface SelectVideoPresenter {
    void takeVideo();//拍照

    void selectVideo(int maxNumber);//选择视频

    void checkPermissions(int actionTag);//检查权限

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);//权限设置结果返回

    void onActivityResult(int requestCode, int resultCode, Intent intent);//页面返回数据
}
