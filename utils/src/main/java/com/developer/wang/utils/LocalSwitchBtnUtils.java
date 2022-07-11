package com.developer.wang.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Date:2018/6/15
 * Time:15:35
 * Author:Loren
 * Desc:
 */
public class LocalSwitchBtnUtils {

    public static String TABLE_SETTING = "userSetting";
    public static String KEY_SETTING_ONLINE = "online";
    public static String KEY_SETTING_VOICE_CHAT = "voiceChat";
    public static String KEY_SETTING_VIDEO_CHAT = "videoChat";
    public static String KEY_SETTING_TURN_CAMERA = "turnCamera";
    public static String KEY_SETTING_IMPRESSION_EVALUATION = "impressionEvaluation";
    public static String KEY_SETTING_MESSAGE_VOIDE = "messageVoice";
    public static String KEY_SETTING_MESSAGE_VIBRATE = "messageVibrate";

    public static String KEY_SETTING_LANGUAGE = "language";

    /**
     * 本地保存用户选择开关状态
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean saveSettingSwitch(Context context, String key, boolean value) {
        try {
            SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取用户在线开关状态
     *
     * @param context
     * @return
     */
    public static boolean getSettingOnline(Context context) {
        SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_SETTING_ONLINE, true);
    }


    /**
     * 获取用户打开摄像头开关状态
     *
     * @param context
     * @return
     */
//    public static boolean getSettingTurnCamera(Context context) {
//        SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
//        return sp.getBoolean(KEY_SETTING_TURN_CAMERA, true);
//    }

    /**
     * 获取用户印象评分开关状态
     *
     * @param context
     * @return
     */
    public static boolean getSettingImpressionEvaluation(Context context) {
        SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_SETTING_IMPRESSION_EVALUATION, true);
    }

    /**
     * 获取用户声音设置
     *
     * @param context
     * @return
     */
    public static boolean getSettingMessageVoice(Context context) {
        SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_SETTING_MESSAGE_VOIDE, true);
    }

    /**
     * 获取用户震动设置
     *
     * @param context
     * @return
     */
    public static boolean getSettingMessageVibrate(Context context) {
        SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_SETTING_MESSAGE_VIBRATE, true);
    }

    /**
     * 保存App内语言选择
     *
     * @param context
     * @param language
     * @return
     */
    public static boolean saveLanguageChoice(Context context, String language) {
        try {
            SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(KEY_SETTING_LANGUAGE, language);
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取App内语言选择
     *
     * @param context
     * @return
     */
    public static String getLanguageChoice(Context context) {
        SharedPreferences sp = context.getSharedPreferences(TABLE_SETTING, Context.MODE_PRIVATE);
        return sp.getString(KEY_SETTING_LANGUAGE, "");

    }

}
