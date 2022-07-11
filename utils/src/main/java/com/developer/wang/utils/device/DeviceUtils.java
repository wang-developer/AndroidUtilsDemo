package com.developer.wang.utils.device;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import android.telephony.TelephonyManager;
import android.util.Log;


import androidx.core.app.NotificationManagerCompat;

import com.developer.wang.utils.R;
import com.developer.wang.utils.SharedPreferencesUtils;
import com.developer.wang.utils.bean.OperatorBean;
import com.developer.wang.utils.gson.MyGson;
import com.developer.wang.utils.string.StringUtil;
import com.google.gson.reflect.TypeToken;


import java.util.Iterator;
import java.util.List;

/**
 * Created by developer on 2018/4/23.
 */

public class DeviceUtils {

    /**
     * SD卡判断
     *
     * @return
     */
    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 返回版本名字
     * 对应build.gradle中的versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static String getVersionNameNoPoint(Context context) {
        String versionName = getVersionName(context);
        if(!StringUtil.isEmpty(versionName)){
            return versionName.replace(".","");
        }
        return "";
    }

    /**
     * 返回版本号
     * 对应build.gradle中的versionCode
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        String versionCode = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = String.valueOf(packInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取设备的唯一标识，deviceId
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = tm.getDeviceId();
            if (deviceId == null) {
                return "no-null";
            } else {
                return deviceId;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "no-null";
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取当前App进程的id
     *
     * @return
     */
    public static int getAppProcessId() {
        return android.os.Process.myPid();
    }


    /**
     * 获取当前App进程的Name
     *
     * @param context
     * @param processId
     * @return
     */
    public static String getAppProcessName(Context context, int processId) {
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取所有运行App的进程集合
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = context.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == processId) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));

                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return processName;
    }


    /**
     * 获取AndroidManifest.xml里 的值
     *
     * @param context
     * @param name
     * @return
     */
    public static String getMetaData(Context context, String name) {
        String value = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取当前的运营商
     *
     * @param context
     * @return 运营商名字
     */
    public static String getOperator(Context context) {


        String ProvidersName = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String IMSI = telephonyManager.getSubscriberId();
        if (IMSI != null) {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
                ProvidersName = "中国移动";
            } else if (IMSI.startsWith("46001") || IMSI.startsWith("46006")) {
                ProvidersName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                ProvidersName = "中国电信";
            } else {
                ProvidersName = IMSI;
            }
            return ProvidersName;
        } else {
            return "没有获取到sim卡信息";
        }
    }


    /**
     * 获取手机运营商编码
     *
     * @param context
     * @param object  调用此方法的类，此处在请求权限处requestPermissions区分是Activity还是Fragment
     * @return
     */
    public static String getOperatorCode(Context context, Object object,boolean requestPermission) {
        try {
            //需要请求权限则请求：例如注册传area不需要动态权限则传递imsi为null
            if(requestPermission){
                if (requestPhone(context, object)) {
                    return getPhoneImsi(context);
                }
            }else {
                if(isHavePhonePermission(context,object)){
                    return getPhoneImsi(context);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String getPhoneImsi(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String imsi = mTelephonyMgr.getSubscriberId();
        return imsi;
    }

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 100;

    private static boolean requestPhone(Context context, Object object) {
        if (AndroidVersionUtils.isMOrLater()) {
            int hasPermission = context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                if (object instanceof Activity) {
                    Activity activity = (Activity) object;
                    activity.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                } else if (object instanceof Fragment) {
                    Fragment fragment = (Fragment) object;
                    fragment.requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                }
                return false;
            }
        }

        return true;
    }

    /**
     * 是否有READ_PHONE_STATE权限
     * @param context
     * @param object
     * @return
     */
    private static boolean isHavePhonePermission(Context context, Object object) {
        if (AndroidVersionUtils.isMOrLater()) {
            int hasPermission = context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据运营商获取国家区号：例如返回+86代表中国
     *
     * @param context
     * @param object  调用此方法的类，此处在请求权限处requestPermissions区分是Activity还是Fragment
     * @return
     */
    public static String getCountryCode(Context context, Object object,boolean requestPermission) {
        OperatorBean operatorBean = getOperatorItem(context,object,requestPermission);
        if(operatorBean != null){
            return operatorBean.getCountryCode();
        }
        return null;
    }

    /**
     * 根据运营商获取国家名：例如中国返回China
     * @param context
     * @param object
     * @return
     */
    public static String getCountry(Context context,Object object,boolean requestPermission){
        OperatorBean operatorBean = getOperatorItem(context,object,requestPermission);
        if(operatorBean != null){
            return operatorBean.getCountry();
        }
        return null;
    }

    public static String getISO(Context context,Object object){
       return getISO(context,object,true);
    }
    /**
     * 根据运营商获取国家对应ISO：例如中国返回cn
     * @param context
     * @param object
     * @return
     */
    public static String getISO(Context context,Object object,boolean requestPermission){
        OperatorBean operatorBean = getOperatorItem(context,object,requestPermission);
        if(operatorBean != null){
            return operatorBean.getISO();
        }
        return null;
    }

    private static OperatorBean getOperatorItem(Context context,Object object,boolean requestPermission){
        String operatorCode = getOperatorCode(context, object,requestPermission);
        String operatorStr = AssetsUtils.getOperatorJson(context);
        List<OperatorBean> operatorList = MyGson.buildGson().fromJson(operatorStr, new TypeToken<List<OperatorBean>>() {
        }.getType());
        if (operatorCode != null && operatorList != null) {
            for (OperatorBean operatorBean : operatorList) {
                if (operatorCode.startsWith(operatorBean.getMCC())) {
                    return operatorBean;
                }
            }
        }
        return null;
    }

    /**
     * 获取国家区号默认没插卡返回 United States
     * @param context
     * @param object
     * @return
     */
    public static String getCountryWithDefault(Context context, Object object,boolean requestPermission) {
        String country = getCountry(context, object,requestPermission);
        if(StringUtil.isEmpty(country)){
            String ios = SharedPreferencesUtils.getLanguageISO(context);
            if(!StringUtil.isEmpty(ios)){
                country = getCountryFromIos(context,ios);
            }
        }
        Log.i("DeviceUtils","getCountryWithDefault : " + country);
        return StringUtil.isEmpty(country)?"United States":country;
    }

    /**
     * 根据国家 ios获取国家中文
     * @param ios cn
     * @return    China
     */
    public static String getCountryFromIos(Context context,String ios){
        String operatorStr = AssetsUtils.getOperatorJson(context);
        List<OperatorBean> operatorList = MyGson.buildGson().fromJson(operatorStr, new TypeToken<List<OperatorBean>>() {
        }.getType());
        if(operatorList != null){
            for (OperatorBean operatorBean : operatorList) {
                if (ios.equalsIgnoreCase(operatorBean.getISO())) {
                    return operatorBean.getCountry();
                }
            }
        }
        return null;
    }

    /**
     * 根据国家 ios获取国家区号
     * @param ios cn
     * @return    China
     */
    public static String getCountryCodeFromIos(Context context,String ios){
        String operatorStr = AssetsUtils.getOperatorJson(context);
        List<OperatorBean> operatorList = MyGson.buildGson().fromJson(operatorStr, new TypeToken<List<OperatorBean>>() {
        }.getType());
        if(operatorList != null){
            for (OperatorBean operatorBean : operatorList) {
                if (ios.equalsIgnoreCase(operatorBean.getISO())) {
                    return operatorBean.getCountryCode();
                }
            }
        }
        return null;
    }
    /**
     * 获取国家区号默认没插卡返回 886
     *
     * @param context
     * @param object  调用此方法的类，此处在请求权限处requestPermissions区分是Activity还是Fragment
     * @return
     */
    public static String getCountryCodeWithDefault(Context context, Object object) {
        return getCountryCodeWithDefault(context,object,true);
    }

    /**
     * 如果没有插卡则默认返回+886
     * @param context
     * @param object
     * @param requestPermission
     * @return
     */
    public static String getCountryCodeWithDefault(Context context, Object object,boolean requestPermission) {
        String countryCode = getCountryCode(context, object,requestPermission);
        Log.i("DeviceUtils","getCountryCodeWithDefault countryCode : " + countryCode);
        return StringUtil.isEmpty(countryCode)?"886":countryCode;
    }

//    public static String getCountryCodeWithDefault(Context context, Object object,boolean requestPermission) {
//        String countryCode = getCountryCode(context, object,requestPermission);
//        if(StringUtil.isEmpty(countryCode)){
//            String iso = SharedPreferencesUtils.getLanguageISO(context);
//            LogUtils.INSTANCE.i("DeviceUtils","getCountryCodeWithDefault iso : " + iso);
//            if(!StringUtil.isEmpty(iso)){
//                countryCode = getCountryCodeFromIos(context,iso);
//            }
//        }
//        LogUtils.INSTANCE.i("DeviceUtils","getCountryCodeWithDefault countryCode : " + countryCode);
//        return StringUtil.isEmpty(countryCode)?"886":countryCode;
//    }

    public static String getISOWithDefaultNull(Context context, Object object){
        String iso = getISO(context,object,true);
        if(StringUtil.isEmpty(iso)){
            iso = SharedPreferencesUtils.getLanguageISO(context);
        }
        return iso.toUpperCase();
    }

    /**
     * 获取国际ISO,默认unknown
     * @param context
     * @param object
     * @return
     */
    public static String getISOWithDefault(Context context, Object object){
      return getISOWithDefault(context,object,true);
    }

    public static String getISOWithDefault(Context context, Object object,boolean requestPermission){
        String iso = getISO(context,object,requestPermission);
        if(StringUtil.isEmpty(iso)){
            iso = SharedPreferencesUtils.getLanguageISO(context);
        }
        iso = StringUtil.isEmpty(iso)?"unknown":iso.toUpperCase();
        return iso;
    }

    /**
     * 通过Iso获取国家
     * @param iso
     * @return
     */
    public static String getCountryListFromIso(Context context,String iso){
        String[] countryArrays = context.getResources().getStringArray(R.array.area_code_all);
        for (int i = 0; i < countryArrays.length; i++) {
            String[] info = countryArrays[i].split("\\=");
            if(info.length > 1){
                if(info[1].trim().equals(iso.trim())){
                    return info[0].trim();
                }
            }
        }
        return "";
    }

    /**
     * 获取默认ISO不返回unknown
     * @param context
     * @param object
     * @param requestPermission
     * @return
     */
    public static String getISONotWithDefault(Context context, Object object,boolean requestPermission){
        String iso = getISO(context,object,requestPermission);
        if(StringUtil.isEmpty(iso)){
            iso = SharedPreferencesUtils.getLanguageISO(context);
        }
        iso = StringUtil.isEmpty(iso)?"":iso.toUpperCase();
        return iso;
    }

    /**
     *iso是否在总表中
     * @param context
     * @param iso
     * @return
     */
    public static boolean isFromIsoAll(Context context,String iso){
        String[] countryArrays = context.getResources().getStringArray(R.array.area_code_all);
        for (int i = 0; i < countryArrays.length; i++) {
            String[] info = countryArrays[i].split("\\=");
            if(info.length > 1){
                if(info[1].trim().equals(iso.toUpperCase().trim())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 通过国家获取到ISO: 因为老版本可能存在应为和繁体所以适配总表area_all
     * @param context
     * @param country 包含英文和繁体：例如China,中國
     * @return
     */
    public static String getIsoByCountryFromIsoAll(Context context,String country){
        String[] countryArrays = context.getResources().getStringArray(R.array.area_all);
        for (int i = 0; i < countryArrays.length; i++) {
            String[] info = countryArrays[i].split("\\=");
            if(info.length > 1){
                if(info[0].trim().equals(country.trim())){
                    return info[1].trim();
                }
            }
        }
        return "";
    }


    /**
     * 获取系统通知开关状态
     * @param context
     * @return
     */
    public static boolean isNotificationOpened(Context context){
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        return manager.areNotificationsEnabled();
    }

    public static void jumpAppSettingView(Activity context) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
            context.startActivity(localIntent);
        } catch (Exception e) {
            e.printStackTrace();
//            ToastUtil.showToast(context,context.getResources().getString(R.string.setting_open_notify_view_failed),true,false);

        }
    }


    /**
     * 例如：通过臺灣&臺北市 获取 臺灣；
     * 兼容老版本：臺灣 獲取 臺灣
     * @param location  臺灣&臺北市 或者 臺灣
     * @return  臺灣
     */
    public static String getCountryFromLocation(String location,String regular){
        if(location == null)return "";
        String country = location;
        if(location.contains(regular)){
            String[] locations = location.split(regular);
            if(locations.length > 0){
                country = locations[0];
            }
        }
        return country;
    }

    /**
     *
     * @param location  臺灣&臺北市 或者 臺灣
     * @return  臺北市
     */
    public static String getProvinceFromLocation(String location,String regular){
        if(location == null)return "";
        String province = "";
        if(location.contains(regular)){
            String[] locations = location.split(regular);
            if(locations.length > 1){
                province = locations[1];
            }
        }
        return province;
    }


    /**
     * 当前所选择的省转化为英文
     * @param context
     * @param province
     * @return
     */
    public static String getProvinceEn(Context context, String province){
        String[] provinceEns = context.getResources().getStringArray(R.array.area_second_upload);
        for (int i = 0; i < provinceEns.length; i++) {
            String[] info = provinceEns[i].split("\\=");
            if(info.length > 1){
                if(info[0].trim().equals(province.trim())){
                    return info[1];
                }
            }
        }
        return "";
    }

    /**
     * 根据当省的英文得到当前语言环境的语言
     * @param context
     * @param province
     * @return
     */
    public static String getProvinceShow(Context context, String province){
        String[] provinceShows = context.getResources().getStringArray(R.array.area_second_show);
        for (int i = 0; i < provinceShows.length; i++) {
            String[] info = provinceShows[i].split("\\=");
            if(info.length > 1){
                if(info[1].trim().equals(province.trim())){
                    return info[0];
                }
            }
        }
        return "";
    }

}
