package com.developer.wang.utils.device;
import android.os.Build.VERSION;

/**
 * Date:2018/6/26
 * Time:10:20
 * Author:Loren
 * Desc: Android 版本工具
 */
public class AndroidVersionUtils {
    public static boolean isFroyoOrLater() {
        return VERSION.SDK_INT >= 8;
    }

    public static boolean isGingerbreadOrLater() {
        return VERSION.SDK_INT >= 9;
    }

    public static boolean isHoneycombOrLater() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean isHoneycombMr1OrLater() {
        return VERSION.SDK_INT >= 12;
    }

    public static boolean isICSOrLater() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean isJellyBeanOrLater() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean isJellyBeanMR1OrLater() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean isJellyBeanMR2OrLater() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean isKitKatOrLater() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isKitKatWatchOrLater(){
        return VERSION.SDK_INT >= 20;
    }

    public static boolean isLolliPopOrLater() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean isLollipopmr1OrLater(){
        return VERSION.SDK_INT >= 22;
    }

    public static boolean isMOrLater(){
        return VERSION.SDK_INT >= 23;
    }

    public static boolean isNOrLater(){
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isNMr1OrLater(){
        return VERSION.SDK_INT >= 25;
    }

    public static boolean isOOrLater(){
        return VERSION.SDK_INT >= 26;
    }
}
