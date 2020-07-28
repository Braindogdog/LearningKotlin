package common.mediaselectlibrary.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import common.baselibrary.baseutil.AppUtil;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.mymatisse.Matisse;
import common.mediaselectlibrary.mymatisse.MimeType;
import common.mediaselectlibrary.mymatisse.engine.impl.GlideEngine;
import common.mediaselectlibrary.view.SelectVideoActivity;

/**
 * Created by chasen on 2018/8/31.
 */

public class SelectVideoPresenterImpl implements SelectVideoPresenter {
    private SelectVideoActivity activity;
    private int actionTag;
    private int macNumber;
    private String videoPath;

    public SelectVideoPresenterImpl(SelectVideoActivity activity, int macNumber) {
        this.activity = activity;
        this.macNumber = macNumber;
    }

    @Override
    public void takeVideo() {
        videoPath = getImagePath();
        File imgFile = new File(videoPath);
        if (!imgFile.getParentFile().exists()) {
            imgFile.getParentFile().mkdirs();
        }
        Uri imgUri = getImageContentUri(activity, imgFile);
        Intent intent = new Intent(
                MediaStore.ACTION_VIDEO_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60);
        activity.startActivityForResult(intent, SelectVideoActivity.TAKE_VIDEO);
    }

    private String getImagePath() {
        String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "video" + File.separator + String.valueOf(System.currentTimeMillis()) + ".mp4";
        return imagePath;
    }

    @Override
    public void selectVideo(int maxNumber) {
        Matisse.from(activity)
                .choose(MimeType.ofVideo())
                .theme(R.style.Matisse_Zhihu)
                .countable(false)
                .maxSelectable(maxNumber)
                .imageEngine(new GlideEngine())
                .forResult(SelectVideoActivity.SELECT_VIDEO);
    }

    @Override
    public void checkPermissions(int actionTag) {
        this.actionTag = actionTag;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (actionTag == SelectVideoActivity.ACTION_TAKE_VIDEO) {
                if (activity.checkSelfPermission(Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, SelectVideoActivity.GET_PREMISSION);
                } else {
                    openOtherActivity();
                }
            } else {
                if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SelectVideoActivity.GET_PREMISSION);
                } else {
                    openOtherActivity();
                }
            }
        } else {
            openOtherActivity();
        }
    }

    private void openOtherActivity() {
        switch (actionTag) {
            case SelectVideoActivity.ACTION_TAKE_VIDEO:
                takeVideo();
                break;
            case SelectVideoActivity.ACTION_SELECT_VIDEO:
                selectVideo(macNumber);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case SelectVideoActivity.GET_PREMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openOtherActivity();
                } else {
                    Toast.makeText(activity, "授权失败，无法进行下一步，请允许授权", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SelectVideoActivity.TAKE_VIDEO) {
                File mInputFile = new File(videoPath);
                Intent data = new Intent();
                ArrayList<String> listPath = new ArrayList<>();
                ArrayList<Uri> listUri = new ArrayList<>();
                listPath.add(videoPath);
                listUri.add(getImageContentUri(activity, mInputFile));
                data.putParcelableArrayListExtra(SelectVideoActivity.EXTRA_RESULT_URI, listUri);
                data.putStringArrayListExtra(SelectVideoActivity.EXTRA_RESULT_PATH, listPath);
                activity.setResult(Activity.RESULT_OK, data);
                activity.finish();
            } else if (requestCode == SelectVideoActivity.SELECT_VIDEO) {
                Intent data = new Intent();
                ArrayList<String> listPath = Matisse.obtainPathResult(intent);
                ArrayList<Uri> listUri = Matisse.obtainResult(intent);
                data.putParcelableArrayListExtra(SelectVideoActivity.EXTRA_RESULT_URI, listUri);
                data.putStringArrayListExtra(SelectVideoActivity.EXTRA_RESULT_PATH, listPath);
                activity.setResult(Activity.RESULT_OK, data);
                activity.finish();
            }
        }
    }

    /**
     * 根据文件路径获取uri,适配安卓7.0
     */
    private Uri getImageContentUri(Context context, File imageFile) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String filePath = imageFile.getAbsolutePath();
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Images.Media._ID},
                    MediaStore.Images.Media.DATA + "=? ",
                    new String[]{filePath}, null);

            if (cursor != null && cursor.moveToFirst()) {
                int id = cursor.getInt(cursor
                        .getColumnIndex(MediaStore.MediaColumns._ID));
                Uri baseUri = Uri.parse("content://media/external/images/media");
                return Uri.withAppendedPath(baseUri, "" + id);
            } else {
                if (imageFile.exists()) {
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Video.Media.DATA, filePath);
                    return context.getContentResolver().insert(
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
                } else {
                    return FileProvider.getUriForFile(context, AppUtil.getAppInfo(context).packageName + ".fileprovider", imageFile);
                }
            }
        } else {
            return Uri.fromFile(imageFile);
        }
    }

}
