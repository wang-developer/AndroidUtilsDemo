package com.developer.wang.utils.device;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.developer.wang.utils.string.StringUtil;


/**
 * Date:2018/6/4
 * Time:10:47
 * Author:Loren
 * Desc: App版本更新工具
 */
public class VersionUtils {
    private static final String TAG = VersionUtils.class.getSimpleName();
    //进率
    private static final long[] bits = new long[]{1000000, 10000, 100, 1};

    /**
     * 获取版本号
     *
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return packInfo == null ? "" : packInfo.versionName;

    }

    /**
     * 检查是否更新
     *
     * @param newVersion
     * @param curVersion
     * @return
     */
    public static boolean canVersionUpdate(String newVersion, String curVersion) {
        //如果有一个为空则返回false
        if (StringUtil.isEmpty(newVersion) || StringUtil.isEmpty(curVersion)) return false;
        Log.d(TAG, "newVersion=" + newVersion + " curVersion=" + curVersion);
        String[] newVals = newVersion.split("\\.");
        String[] curVals = curVersion.split("\\.");
        long newCode = 0;
        long curCode = 0;
        for (int i = 0; i < newVals.length; i++) {
            if (i < newVals.length && newVals[i] != null && newVals[i].length() > 0) {
                newCode += Long.parseLong(newVals[i]) * bits[i];
            }
            if (i < curVals.length && curVals[i] != null && curVals[i].length() > 0) {
                curCode += Long.parseLong(curVals[i]) * bits[i];
            }
        }
        //新版本和当前版本对比两个相等则提示升级这种方式兼容比较好，可以兼容回滚版本到当前版本之前的稳定版本，而newCode > curCode不能达到版本回滚
        return (newCode > curCode);
    }


//    public static boolean isGoogleVersion(){
//        return  BuildConfig.FLAVOR.equals("google");
//    }
//    public static boolean isChinaVersion(){
//        return  BuildConfig.FLAVOR.equals("china");
//    }

}
