package com.developer.wang.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;

    public static boolean requestVideo(Activity activity) {
        return requestVideo(activity, true);
    }
    public static boolean requestAudio(Activity activity) {
        return requestAudio(activity,true);
    }


    public static boolean requestAudio(Activity activity, boolean isNeedTip) {
        if (afterM()) {
            int hasPermission = activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                if (isNeedTip)
                    activity.requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},
                            REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }




    public static boolean requestVideo(Activity activity, boolean isNeedTip) {
        if (afterM()) {
            final List<String> permissionsList = new ArrayList<>();
            if ((activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.CAMERA);
            if ((activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.RECORD_AUDIO);
            if (permissionsList.size() != 0) {
                if (isNeedTip)
                    activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                            REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
            int hasPermission = activity.checkSelfPermission(Manifest.permission.CAMERA);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                if (isNeedTip)
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                            REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
        }
        return true;
    }


    public static boolean afterM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean requestWindowManager(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(activity)) {
                return false;
            }
        }
        return true;
    }
}
