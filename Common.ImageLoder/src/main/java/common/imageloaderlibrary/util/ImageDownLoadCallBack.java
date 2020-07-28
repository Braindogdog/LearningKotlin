package common.imageloaderlibrary.util;

import android.graphics.Bitmap;

/**
 * Created by chasen on 2018/3/12.
 * 图片下载结果回调
 */

public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
