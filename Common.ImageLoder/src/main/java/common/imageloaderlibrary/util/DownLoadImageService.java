package common.imageloaderlibrary.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chasen on 2018/3/12.
 * 图片下载
 */

public class DownLoadImageService implements Runnable {
    private String url;
    private Context context;
    private ImageDownLoadCallBack callBack;
    private File currentFile;
    private String fileName;

    private boolean isSetMediaStore;

    /**
     * 下载图片
     * @param context
     * @param url
     * @param isSetMediaStore 是否保存图片到系统相册
     * @param fileName 可为null
     * @param callBack 保存结果回调
     */
    public DownLoadImageService(Context context, String url, boolean isSetMediaStore, String fileName, ImageDownLoadCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
        this.context = context;
        this.isSetMediaStore = isSetMediaStore;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Bitmap b = null;
        FutureTarget<Bitmap> bitmap = Glide.with(context)
                .asBitmap()
                .load(url)
                .submit();
        try{
            b = bitmap.get();
            saveImageToLocal(context, b);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bitmap != null && currentFile.exists()) {
                callBack.onDownLoadSuccess(b);
            } else {
                callBack.onDownLoadFailed();
            }
        }
    }

    public void saveImageToLocal(Context context, Bitmap bmp) {
        // 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();

        File appDir = new File(file, fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        fileName = System.currentTimeMillis() + ".jpg";
        currentFile = new File(appDir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (isSetMediaStore) {
            setMediaDtore(fileName);
        }

        //最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }

    /**
     * 加入到系统图库
     *
     * @param fileName
     */
    public void setMediaDtore(String fileName) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    currentFile.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
