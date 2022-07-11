package com.developer.wang.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;


import com.developer.wang.utils.string.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Date:2018/5/30
 * Time:12:01
 * Author:Loren
 * Desc: 设置App内部语言切换,暂时先设置两种：英文，繁体中文（繁体中文string中还没有添加）
 * https://www.jianshu.com/p/a6d090234d25
 */
public class LanguageUtils {
    private static final String TAG = LanguageUtils.class.getSimpleName();
    public static final String LANGUAGE_UK = "English";
    public static final String LANGUAGE_TRADITIONAL_CHINESE = "繁體中文";

    public static List<String> getLanguageList() {
        List<String> languageList = new ArrayList<>();
//        languageList.add(LANGUAGE_UK);
        languageList.add(LANGUAGE_TRADITIONAL_CHINESE);
        return languageList;
    }

    public static List<String> getLanguageLocalList(){
        List<String> languageLocalList = new ArrayList<>();
        List<String> languagelList = getLanguageList();
        for(String language : languagelList){
            String languageLocal = getCurrentLocale(language).toString();
            languageLocalList.add(languageLocal);
        }
        return languageLocalList;
    }

    private static Locale getCurrentLocale(String language) {
        if (language.equals(LANGUAGE_UK)) return Locale.UK;
        if (language.equals(LANGUAGE_TRADITIONAL_CHINESE)) return Locale.TRADITIONAL_CHINESE;
        //return Locale.getDefault();
        //默认不随系统，默认为繁体
        return Locale.TRADITIONAL_CHINESE;
    }

    public static String getDefaultLanguage(Context context) {
        if(StringUtil.isEmpty(LocalSwitchBtnUtils.getLanguageChoice(context))){
            //如果没有设置过则跟随系统，此时如果系统设置的语言如果当前app内部没有则显示英文
            List<String> languageLocalList = getLanguageLocalList();
            String localLanguage = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
            Log.i(TAG,"getDefaultLanguage languageLocalList : " + languageLocalList.toString());
            Log.i(TAG,"getDefaultLanguage localLanguage 11 : " + localLanguage);
            //繁体香港在设置中不可见，但是如果没有设置过则系统设置为繁体香港则不默认显示英语，应该显示繁体语言
            //后续设置中添加繁体香港在做相应处理
            if(localLanguage.contains("zh"))localLanguage = "zh_TW";
            if(localLanguage.contains("en"))localLanguage = "en_GB";
            Log.i(TAG,"getDefaultLanguage localLanguage 22 : " + localLanguage);
            if(languageLocalList.contains(localLanguage)){
                return getLanguageFromLocal(localLanguage);
            }
            return LANGUAGE_TRADITIONAL_CHINESE;
        }
        return LocalSwitchBtnUtils.getLanguageChoice(context);
    }

    /**
     * 获取当前App语言
     * @param context
     * @return
     */
    public static String getCurrentShowLanguage(Context context){
        String currentLanguage = LocalSwitchBtnUtils.getLanguageChoice(context);
        return  StringUtil.isEmpty(currentLanguage)? LanguageUtils.getDefaultLanguage(context):currentLanguage;
    }

    private static String getLanguageFromLocal(String localLanguage){
        for(String language : getLanguageList()){
            String languageLocal = getCurrentLocale(language).toString();
            if(localLanguage.equals(languageLocal)){
                return language;
            }
        }
        //异常返回繁体
        return LANGUAGE_TRADITIONAL_CHINESE;
    }

    /**
     * 是否和系统设置的语言相同
     * @return
     */
    public static boolean isSameLanguageWithSystem(Context context){
        Locale current = context.getResources().getConfiguration().locale;
        Locale system = getCurrentLocale(LocalSwitchBtnUtils.getLanguageChoice(context));
        return current.equals(system);
    }

    public static boolean isEnglishLanguageWithCurrentLanguage(Context context){
        return getCurrentShowLanguage(context).equals(LANGUAGE_UK);
    }

    public static void updateLanguageConfiguration(Context context, String language) {
        Log.i(TAG, "updateLanguageConfiguration language : " + language + " local language : " + getCurrentLocale(language));
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        Locale locale = getCurrentLocale(language);// getSetLocale方法是获取新设置的语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        Log.i(TAG,"updateLanguageConfiguration to set language : " + locale);
        resources.updateConfiguration(config, dm);


    }


    /**
     * 以H5要求格式传递相应国际化值
     * @param context
     * @return
     */
//    public static String getCurrentH5ChoiceLanguage(Context context){
//        String localChoice = LocalSwitchBtnUtils.getLanguageChoice(context);
//        localChoice = StringUtil.isEmpty(localChoice)? LanguageUtils.getDefaultLanguage(context):localChoice;
//        if (localChoice.equals(LANGUAGE_UK)) return H5Constants.LANGUAGE_EN;
//        if (localChoice.equals(LANGUAGE_TRADITIONAL_CHINESE)) return H5Constants.LANGUAGE_TW;
//        return H5Constants.LANGUAGE_TW;
//    }
}
