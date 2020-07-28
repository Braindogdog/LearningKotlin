package common.mediaselectlibrary.presenter;

import android.content.Intent;

import java.io.File;

/**
 * Created by chasen on 2018/3/13.
 */

public interface SelectImagePresenter {
    void takePhoto();//拍照

    void selectImage();//选择单张照片

    void selectImages(int maxNumber);//选择多张照片

    void checkPermissions(int actionTag);//检查权限

    void zoomPhoto(File inputFile, File outputFile);//剪裁照片

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);//权限设置结果返回

    void onActivityResult(int requestCode, int resultCode, Intent intent);//页面返回数据
}
