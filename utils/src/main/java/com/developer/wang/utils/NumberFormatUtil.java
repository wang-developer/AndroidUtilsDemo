package com.developer.wang.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date:2018/6/5
 * Time:19:47
 * Author:Loren
 * Desc:数字转化
 *  例如：int 保留两位转化为Stirng
 */
public class NumberFormatUtil {
    /**
     *
     * @param number
     * @return 保留小数点后两位
     */
    public static String getNumberFormat(float number){
        //#表示没有则为空，0表示如果没有则该位补0.
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return decimalFormat.format(number);
    }

    /**
     *
     * @param number
     * @param pattern 小数点几位
     * @return  保留小数点多位
     */
    public static String getNumberFormat(float number,String pattern){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);

    }

    /**
     * 两个数相除，保留小数点后两位
     * @param a 除数
     * @param b 被除数
     * @return  字符串类型
     */
    public static String getNumberFormat(int a,int b){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//格式化小数
        return decimalFormat.format((float)a/b);//返回的是String类型
    }

    /**
     * 两个数相除，保留小数点后两位
     * @param a 除数
     * @param b 被除数
     * @return float
     */
    public static float getNumberFormatFloat(int a,int b){
        float value = new BigDecimal((float)a/b).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return value;
    }



    /**
     * 判断当前字符串是否为数字
     * @param number
     * @return
     */
    public static boolean isNumber(String number){
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(number);
        return m.matches();
    }
}
