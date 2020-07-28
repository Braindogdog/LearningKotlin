package common.networkrequestlibrary.util;

import android.content.Context;

import java.io.File;
import java.util.Date;

import okhttp3.ResponseBody;

/**
 * Created by chasen on 2018/3/20.
 */

public class CommonUtil {
    public static boolean stringIsEmpty(String str) {
        if (str == null || str.trim().equals("") || str.trim().equals("NULL") || str.trim().equals("null")) {
            return true;
        }
        return false;
    }

    public static String getFilePathNomal(Context context, ResponseBody responseBody) {
        return context.getExternalFilesDir(null) + File.separator + new Date().getTime() + getFileType(responseBody);
    }

    public static String getFilePathNomal(Context context, String fileName) {
        return context.getExternalFilesDir(null) + File.separator + "video" + File.separator + fileName;
    }

    public static String getFileType(ResponseBody responseBody) {
        if (responseBody.contentType() == null) {
            return ".apk";
        }
        String type = responseBody.contentType().toString();
        if (type.equals("application/vnd.android.package-archive"))
            return ".apk";
        if (type.equals("text/html"))
            return ".html";
        if (type.equals("text/plain"))
            return ".txt";
        if (type.equals("text/xml"))
            return ".xml";
        if (type.equals("image/gif"))
            return ".gif";
        if (type.equals("image/jpeg"))
            return ".jpg";
        if (type.equals("image/png"))
            return ".png";
        if (type.equals("application/pdf"))
            return ".pdf";
        if (type.equals("application/msword"))
            return ".doc";
        if (type.equals("video/mpeg4"))
            return ".mp4";
        if (type.equals(" \taudio/mp3"))
            return ".mp3";
        if (type.equals("application/vnd.rn-realmedia-vbr"))
            return ".rmvb";
        if (type.equals("video/avi"))
            return ".avi";
        return ".apk";
    }
}
