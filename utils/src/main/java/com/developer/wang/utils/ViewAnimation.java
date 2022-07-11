package com.developer.wang.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by developer on 2018/9/27.
 */

public class ViewAnimation {

    /**
     * 设置位移动画效果，来回移动
     */
    public static void setTransAni(View view, float x0) {

        TranslateAnimation transAni = new TranslateAnimation(x0, 0, 0, 0);
        final int ANITIME = 500;
        transAni.setDuration(ANITIME);
        transAni.setRepeatMode(Animation.REVERSE);
        transAni.setRepeatCount(0);
        view.startAnimation(transAni);
    }

    /**
     * 为控件添加扩散光效
     */
    public static void setLightExpendAni(View V) {
        AnimationSet aniSet = new AnimationSet(true);
        final int ANITIME = 800;
        // 尺寸变化动画，设置尺寸变化
        ScaleAnimation scaleAni = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAni.setDuration(ANITIME);                // 设置动画效果时间
        scaleAni.setRepeatMode(Animation.RESTART);    // 重新播放
        scaleAni.setRepeatCount(0);// 循环播放
        aniSet.addAnimation(scaleAni);    // 将动画效果添加到动画集中
        // 透明度变化
        AlphaAnimation alphaAni = new AlphaAnimation(0f, 1f);
        alphaAni.setDuration(ANITIME);                // 设置动画效果时间
        alphaAni.setRepeatMode(Animation.RESTART);    // 重新播放
        alphaAni.setRepeatCount(0);// 循环播放Animation.INFINITE
        aniSet.addAnimation(alphaAni);    // 将动画效果添加到动画集中
        V.startAnimation(aniSet);        // 添加光效动画到控件
    }

    /**
     * 为View添加透明度变换效果
     */
    public static void AddAlphaAni(View view) {
        AddAlphaAni(view, 0, 1, 1000, Animation.REVERSE, Animation.INFINITE);
    }

    /**
     * 为View添加透明度变换效果，透明度从fromAlpha变化到toAlpha，变化持续时间durationMillis，重复模式repeatMode
     */
    public static void AddAlphaAni(View view, float fromAlpha, float toAlpha, long durationMillis, int repeatMode, int repeatCount) {
        AlphaAnimation alphaAni = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAni.setDuration(durationMillis);    // 设置动画效果时间
        alphaAni.setRepeatMode(repeatMode);        // 重新播放
        alphaAni.setRepeatCount(repeatCount);    // 循环播放
        view.startAnimation(alphaAni);
    }

    /**
     * 设置位移动画效果，来回移动
     */
    public static void setTransAni(View view, int x0, int x1, int y0, int y1, long durationMillis) {
        TranslateAnimation transAni = new TranslateAnimation(x0, x1, y0, y1);
        transAni.setDuration(durationMillis);
        transAni.setRepeatMode(Animation.REVERSE);
        transAni.setRepeatCount(Animation.INFINITE);
        view.startAnimation(transAni);
    }

    /**
     * 设置旋转动画
     */
    public static void setRotateAni(View view, float fromDegrees, float toDegrees, long time) {
        setRotateAni(view, fromDegrees, toDegrees, time, Animation.REVERSE, Animation.INFINITE, true);
    }

    /**
     * 设置旋转动画, 从角度fromDegrees旋转到toDegrees，旋转速度durationMillis毫秒，重启模式repeatModes，重启次数repeatCount，是否匀速旋转linear。	 *  示例：AnimationEffect.setRotateAni(view, 0, 360, 1000, Animation.RESTART, Animation.INFINITE, true); // 控制view每秒旋转360度
     */
    public static void setRotateAni(View view, float fromDegrees, float toDegrees, long durationMillis, int repeatModes, int repeatCount, boolean linear) {
        RotateAnimation rotateAni = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAni.setDuration(durationMillis);
        rotateAni.setRepeatMode(repeatModes);
        rotateAni.setRepeatCount(repeatCount);
        if (linear)
            rotateAni.setInterpolator(new LinearInterpolator());    // 匀速旋转
        view.startAnimation(rotateAni);
    }

    /**
     * 为控件添加尺寸渐变动画
     */
    public static void setScaleAni(View V, float fromScale, float toScale, long ANITIME) {
        AnimationSet aniSet = new AnimationSet(true);        //
//         final int ANITIME = 500;// 尺寸变化动画，设置尺寸变化
        ScaleAnimation scaleAni = new ScaleAnimation(fromScale, toScale, fromScale, toScale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAni.setDuration(ANITIME);    // 设置动画效果时间
        aniSet.addAnimation(scaleAni);    // 将动画效果添加到动画集中
        V.startAnimation(aniSet);        // 添加光效动画到控件
    }


    /**
     * 设置背景旋转光效动画
     */
    public static void setLightAni(final View view) {
        Animation.AnimationListener listenser = new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                setRotateAni(view, 36, 3996, 110000, Animation.RESTART, Animation.INFINITE, true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        };
        AnimationSet set = new AnimationSet(true);
        // 渐现
        AlphaAnimation alphaAni = new AlphaAnimation(0.0f, 1f);
        alphaAni.setDuration(1000);    // 设置动画效果时间
        set.addAnimation(alphaAni);                // 旋转
        RotateAnimation rotateAni = new RotateAnimation(0, 36, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAni.setDuration(1000);
        set.addAnimation(rotateAni);                // 尺寸由小变大
        ScaleAnimation scaleAni = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAni.setDuration(1000);
        scaleAni.setAnimationListener(listenser);
        set.addAnimation(scaleAni);
        view.startAnimation(set);
    }

}
