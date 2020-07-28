package common.baselibrary.baseutil;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by chasen on 2018/3/30.
 * 权限获取工具类
 */

public class PermissionUtils {

    public Activity mActivity;
    public static final int MY_PERMISSIONS_REQUEST_CODE = 200;

    public PermissionUtils(Activity actiity) {
        this.mActivity = actiity;
    }

    /**
     * 检查是否拥有某个权限
     *
     * @param permission
     * @return
     */
    public boolean checkPermission(String permission) {
        if (ContextCompat.checkSelfPermission(mActivity,
                permission)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否需要获取权限
     *
     * @return
     */
    public static boolean needPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否需要权限，没有的话获取全部
     *
     * @param requestCode
     */
    public void needPermissionAndReqest(int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        requestAllPermissions(requestCode);
    }

    /**
     * 获取所有危险权限
     * 分别是：联系人，电话，日历，相机，传感器，位置，文件，麦克风，短信
     * 这是所有需要动态申请权限的危险权限组
     *
     * @param requestCode
     */
    public void requestAllPermissions(int requestCode) {
        if (!needPermission())
            return;
        ActivityCompat.requestPermissions(mActivity,
                new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_CALENDAR, Manifest.permission.CAMERA,
                        Manifest.permission.BODY_SENSORS, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.SEND_SMS},
                MY_PERMISSIONS_REQUEST_CODE);

    }

    /**
     * 获取多个权限
     *
     * @param requestCode
     * @param permissions
     */
    public void requestPermissions(int requestCode, String[] permissions) {
        if (!needPermission())
            return;
        ActivityCompat.requestPermissions(mActivity, permissions,
                MY_PERMISSIONS_REQUEST_CODE);

    }

    /**
     * 获取联系人权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestContactPermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取电话权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestPhonePermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取日历权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestCalendarPermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取相机权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestCameraPermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取传感器权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestSensorsPermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取位置权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestLocationPermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取文件权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestStoragePermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取麦克风权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestAudioPermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取短信权限组权限
     *
     * @param requestCode
     * @return
     */
    public boolean requestSmsPermissions(int requestCode) {
        if (!needPermission())
            return true;
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }
}
