package common.baselibrary.baseutil;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * assets 工具类
 */
public final class AssetUtil {

    /**
     * 获取 assets 目录下的文件
     *
     * @param path 文件在 assets 文件夹中的路径
     * @return 文件内容
     */
    public static String getFileFromAssets(Context context, @NonNull String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(path)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 获取 assets 目录下的图片
     *
     * @param path 图片在 assets 文件夹中的路径
     * @return
     */
    public static Bitmap getImageFromAssets(Context context, @NonNull String path) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(path);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 把assets下的文件拷贝到指定文件
     *
     * @param context
     * @param path
     * @param file
     * @throws IOException
     */
    public static void copyAssetFile(Context context, String path, File file) throws IOException {

        AssetManager am = context.getAssets();

        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = am.open(path);
            out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }

        } catch (IOException e) {
            e.printStackTrace();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ignored) {

                }
            }
        }
    }
}
