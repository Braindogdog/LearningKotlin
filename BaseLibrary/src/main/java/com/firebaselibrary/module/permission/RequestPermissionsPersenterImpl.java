package com.firebaselibrary.module.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by MHshachang on 2017/6/1.
 */

public class RequestPermissionsPersenterImpl implements RequestPermissionsPersenter {
    private Context context;
    private String[] PERMISSIONS_CONTACT;
    private PermissionsView permissionsView;
    private Activity activity;
    private int REQUEST_CONTACTS = 1;
    private AlertDialog dialog;

    public RequestPermissionsPersenterImpl(Activity activity, PermissionsView permissionsView, String[]PERMISSIONS_CONTACT) {
        this.activity = activity;
        this.context = activity;
        this.permissionsView = permissionsView;
        this.PERMISSIONS_CONTACT = PERMISSIONS_CONTACT;
    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CheckPermission();
        } else {
            permissionsView.doAfterRequestPermissions();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void CheckPermission() {
        List<String> permissionsList = new ArrayList<String>();
        for (int i = 0; i < PERMISSIONS_CONTACT.length; i++) {
            addPermission(permissionsList, PERMISSIONS_CONTACT[i]);
        }
        if (permissionsList.size() > 0) {
            activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CONTACTS);
        } else {
            permissionsView.doAfterRequestPermissions();
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                if (!activity.shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {
            for (int i : grantResults) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    if (dialog == null) {
                        showDialog("禁止了必须的权限，系统无法启动，请在系统设置的权限管理中修改权限后重新启动");
                        return;
                    }
                }
            }
        }
    }

    /**
     * 创建对话框
     */
    public void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_menu_info_details)
                .setTitle("提示")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startPremissionSetting();
                        if (dialog != null) {
                            dialog.dismiss();
                            dialog = null;
                        }
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    /**
     * 打开权限设置界面
     */
    private void startPremissionSetting() {
        Uri selfPackageUri = Uri.parse("package:"
                + context.getApplicationContext().getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                selfPackageUri);
        context.startActivity(intent);
    }


}
