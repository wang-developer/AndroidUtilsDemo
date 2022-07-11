package com.developer.wang.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by developer on 2017/6/20.
 */

public class SharedPreferencesUtils {


    /**********************************  SharedPreferences 中使用的文件名字 统计 ************************************/
    private final static String FILE_NAME_USER_INFO = "userInfo"; //用户信息 token perfect activeToken BeautyWhite sourceJson
    private final static String FILE_NAME_IS_FIRST = "isfirst";//是否是第一次登陆
    private final static String FILE_NAME_SYSTEM_CONFIG = "sysConfig"; //系统config配置项
    private final static String FILE_NAME_ZIP = "zip"; //压缩文件的路径
    private final static String FILE_NAME_DISTANCE_INFO = "distanceInfo";
    private final static String FILE_NAME_ME_TAY_LIST = "meTagsListTable";//个人标签
    private final static String FILE_NAME_BASE_URL = "baseUrl";//加载服务器使用的url
    private final static String FILE_NAME_VIDEO_TIP = "videoTip";//videoTip
    private final static String FILE_NAME_AUTH = "verifyTable";//最后认证状态；生成认证模型
    private final static String FILE_NAME_USER_CONFIG = "userConfig";//用户设置信息
    private final static String FILE_NAME_VIDEO_SETTING = "videoSetting";//视频设置
    private final static String FILE_NAME_OVER_EIGHTEEN = "overEighteen";//满18岁

    /**
     * 保存系统语言ISO
     * 因为语言设置在进入app后做了相关处理
     *
     * @param context
     * @param countryIso
     * @return
     */
    public static boolean saveLanguageISO(Context context, String countryIso) {
        try {
            SharedPreferences sp = context.getSharedPreferences("countryIsoTable", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("countryIso", countryIso);
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取系统语言ISO
     *
     * @param context
     * @return
     */
    public static String getLanguageISO(Context context) {
        SharedPreferences sp = context.getSharedPreferences("countryIsoTable", Context.MODE_PRIVATE);
        String countryIso = sp.getString("countryIso", "");
        return countryIso;
    }



    /**
     * 保存注册18+状态值,用户点击过则保存未true
     *
     * @param context
     * @param isOverEighteen
     * @return
     */
    public static boolean saveOverEighteen(Context context, boolean isOverEighteen) {
        try {
            SharedPreferences sp = context.getSharedPreferences(FILE_NAME_OVER_EIGHTEEN, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("overEighteen", isOverEighteen);
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 获取注册18+状态值
     *
     * @param context
     * @return
     */
    public static boolean getOverEighteen(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME_OVER_EIGHTEEN, Context.MODE_PRIVATE);
        return sp.getBoolean("overEighteen", false);
    }



}
