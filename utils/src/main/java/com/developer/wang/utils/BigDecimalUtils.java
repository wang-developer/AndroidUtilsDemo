package com.developer.wang.utils;

import java.math.BigDecimal;

/**
 * Date:2019/3/25
 * Time:14:50
 * Author:Loren
 * Desc: 保留小数点后几位
 */
public class BigDecimalUtils {

    /**
     * 保留小数点后两位
     * @param value
     * @return
     */
    public static float get2Digit(float value){
        BigDecimal decimal = new BigDecimal(value);
        return decimal.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 保留小数点后四位
     * @param value
     * @return
     */
    public static float get4Digit(float value){
        BigDecimal decimal = new BigDecimal(value);
        return decimal.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
