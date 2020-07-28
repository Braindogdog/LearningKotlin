package com.firebaselibrary.module.permission;

import android.support.annotation.NonNull;

/**
 * Created by MHshachang on 2017/6/1.
 */

public interface RequestPermissionsPersenter {


    /**
     * 初始化权限（申请权限）
     */
    void initPermission();


    /**
     * 申请权限后返回的结果
     * @param requestCode
     * @param grantResults
     */
    void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults);


}
