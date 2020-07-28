package common.mediaselectlibrary.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.baselibrary.baseutil.AppUtil;
import common.baselibrary.baseutil.EmptyUtil;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.mymatisse.Matisse;
import common.mediaselectlibrary.mymatisse.MimeType;
import common.mediaselectlibrary.mymatisse.engine.impl.GlideEngine;
import common.mediaselectlibrary.model.SelectImageParameter;
import common.mediaselectlibrary.util.CompressPhotoUtils;
import common.mediaselectlibrary.view.SelectImageActivity;

/**
 * Created by chasen on 2018/3/13.
 */

public class SelectImagePresenterImpl implements SelectImagePresenter {

    private SelectImageActivity activity;
    private SelectImageParameter parameter;
    private int actionTag;
    private int macNumber;
    private boolean needDeleteTemp;//剪裁后是否需要删除老照片

    private String oldImagePath;
    private String newImagePath;

    public SelectImagePresenterImpl(SelectImageActivity activity, SelectImageParameter parameter, int maxNumber) {
        this.activity = activity;
        this.parameter = parameter;
        this.macNumber = maxNumber;
    }

    @Override
    public void takePhoto() {
        oldImagePath = getImagePath();
        File imgFile = new File(oldImagePath);
        if (!imgFile.getParentFile().exists()) {
            imgFile.getParentFile().mkdirs();
        }
        Uri imgUri = getImageContentUri(activity, imgFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        activity.startActivityForResult(intent, SelectImageActivity.TAKE_PHOTO);
    }

    private String getImagePath() {
        String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "image" + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg";
        return imagePath;
    }

    @Override
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, SelectImageActivity.SELECT_IMAGE);
    }

    @Override
    public void selectImages(int maxNumber) {
        Matisse.from(activity)
                .choose(MimeType.ofImage())
                .theme(R.style.Matisse_Zhihu)
                .countable(false)
                .maxSelectable(maxNumber)
                .imageEngine(new GlideEngine())
                .forResult(SelectImageActivity.SELECT_IMAGES);
    }

    @Override
    public void checkPermissions(int actionTag) {
        this.actionTag = actionTag;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (actionTag == SelectImageActivity.ACTION_TAKE_PHOTO) {
                if (activity.checkSelfPermission(Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA}, SelectImageActivity.GET_PREMISSION);
                } else {
                    openOtherActivity();
                }
            } else {
                if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SelectImageActivity.GET_PREMISSION);
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
            case SelectImageActivity.ACTION_TAKE_PHOTO:
                takePhoto();
                break;
            case SelectImageActivity.ACTION_SELECT_IMAGE:
                selectImage();
                break;
            case SelectImageActivity.ACTION_SELECT_IMAGES:
                selectImages(macNumber);
                break;
        }
    }

    @Override
    public void zoomPhoto(File inputFile, File outputFile) {
        File parentFile = outputFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(getImageContentUri(activity, inputFile), "image/*");
        intent.putExtra("crop", "true");

        //设置剪裁图片宽高比
        intent.putExtra("mAspectX", parameter.getmAspectX());
        intent.putExtra("mAspectY", parameter.getmAspectY());

        //设置剪裁图片大小
        intent.putExtra("mOutputX", parameter.getmOutputX());
        intent.putExtra("mOutputY", parameter.getmOutputY());

        // 是否返回uri
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        activity.startActivityForResult(intent, SelectImageActivity.ZOOM_PHOTO);
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
                    values.put(MediaStore.Images.Media.DATA, filePath);
                    return context.getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                } else {
                    return FileProvider.getUriForFile(context, AppUtil.getAppInfo(context).packageName + ".fileprovider", imageFile);
                }
            }
        } else {
            return Uri.fromFile(imageFile);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case SelectImageActivity.GET_PREMISSION:
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
            if (requestCode == SelectImageActivity.TAKE_PHOTO) {
                if (EmptyUtil.isEmpty(oldImagePath))
                    return;
                File mInputFile = new File(oldImagePath);
                if (parameter.isNeedCrop()) {
                    needDeleteTemp = true;
                    newImagePath = getImagePath();
                    File mOutputFile = new File(newImagePath);
                    zoomPhoto(mInputFile, mOutputFile);
                } else {
                    List<String> listPath = new ArrayList<>();
                    listPath.add(oldImagePath);
                    new CompressPhotoUtils().CompressPhoto(activity, listPath, new CompressPhotoUtils.CompressCallBack() {
                        @Override
                        public void success(List<String> list) {
                            Intent data = new Intent();
                            ArrayList<Uri> listUri = new ArrayList<>();
                            for (String path : list) {
                                listUri.add(getImageContentUri(activity, new File(path)));
                            }
                            data.putParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT_URI, listUri);
                            data.putStringArrayListExtra(SelectImageActivity.EXTRA_RESULT_PATH, (ArrayList<String>) list);
                            activity.setResult(Activity.RESULT_OK, data);
                            activity.finish();
                        }
                    });
                }
            } else if (requestCode == SelectImageActivity.SELECT_IMAGE) {
                if (intent != null) {
                    Uri sourceUri = intent.getData();
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = activity.managedQuery(sourceUri, proj, null, null, null);
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    oldImagePath = cursor.getString(columnIndex);
                    File mInputFile = new File(oldImagePath);
                    if (parameter.isNeedCrop()) {
                        needDeleteTemp = false;
                        newImagePath = getImagePath();
                        File mOutputFile = new File(newImagePath);
                        zoomPhoto(mInputFile, mOutputFile);
                    } else {
                        List<String> listPath = new ArrayList<>();
                        listPath.add(oldImagePath);
                        new CompressPhotoUtils().CompressPhoto(activity, listPath, new CompressPhotoUtils.CompressCallBack() {
                            @Override
                            public void success(List<String> list) {
                                Intent data = new Intent();
                                ArrayList<Uri> listUri = new ArrayList<>();
                                for (String path : list) {
                                    listUri.add(getImageContentUri(activity, new File(path)));
                                }
                                data.putParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT_URI, listUri);
                                data.putStringArrayListExtra(SelectImageActivity.EXTRA_RESULT_PATH, (ArrayList<String>) list);
                                activity.setResult(Activity.RESULT_OK, data);
                                activity.finish();
                            }
                        });
                    }
                }
            } else if (requestCode == SelectImageActivity.SELECT_IMAGES) {
                ArrayList<String> listPath = Matisse.obtainPathResult(intent);
                new CompressPhotoUtils().CompressPhoto(activity, listPath, new CompressPhotoUtils.CompressCallBack() {
                    @Override
                    public void success(List<String> list) {
                        Intent data = new Intent();
                        ArrayList<Uri> listUri = new ArrayList<>();
                        for (String path : list) {
                            listUri.add(getImageContentUri(activity, new File(path)));
                        }
                        data.putParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT_URI, listUri);
                        data.putStringArrayListExtra(SelectImageActivity.EXTRA_RESULT_PATH, (ArrayList<String>) list);
                        activity.setResult(Activity.RESULT_OK, data);
                        activity.finish();
                    }
                });
            } else if (requestCode == SelectImageActivity.ZOOM_PHOTO) {
                if (needDeleteTemp) {
                    //删除拍照的临时照片
                    File tmpFile = new File(oldImagePath);
                    if (tmpFile.exists())
                        tmpFile.delete();
                }
                List<String> listPath = new ArrayList<>();
                listPath.add(newImagePath);
                new CompressPhotoUtils().CompressPhoto(activity, listPath, new CompressPhotoUtils.CompressCallBack() {
                    @Override
                    public void success(List<String> list) {
                        Intent data = new Intent();
                        ArrayList<Uri> listUri = new ArrayList<>();
                        for (String path : list) {
                            listUri.add(getImageContentUri(activity, new File(path)));
                        }
                        data.putParcelableArrayListExtra(SelectImageActivity.EXTRA_RESULT_URI, listUri);
                        data.putStringArrayListExtra(SelectImageActivity.EXTRA_RESULT_PATH, (ArrayList<String>) list);
                        activity.setResult(Activity.RESULT_OK, data);
                        activity.finish();
                    }
                });
            }
        }
    }
}
