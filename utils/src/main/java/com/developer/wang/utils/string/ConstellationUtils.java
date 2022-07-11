package com.developer.wang.utils.string;

import android.content.Context;

import com.developer.wang.utils.R;


/**
 * Date:2019/1/17
 * Time:14:43
 * Author:Loren
 * Desc: 星座工具类
 * <p>
 * https://blog.csdn.net/qq_35180983/article/details/82973972
 */
public class ConstellationUtils {
    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22};
    private static String[] constellationArr;


    private static ConstellationUtils instance;
    private Context context;

    private ConstellationUtils(Context context) {
        this.context = context;
    }

    private void setConstellationArray(Context context) {
        constellationArr = context.getResources().getStringArray(R.array.constellation);
    }

    public static ConstellationUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (ConstellationUtils.class) {
                if (instance == null) {
                    instance = new ConstellationUtils(context);
                }
            }
        }
        return instance;
    }


    /**
     * Java通过生日计算星座
     *
     * @param month
     * @param day
     * @return
     */
    public String getConstellation(int month, int day) {
        setConstellationArray(context);
        return day < dayArr[month - 1] ? constellationArr[month - 1]
                : constellationArr[month];
    }

    /**
     * 通过生日计算属相
     *
     * @param year
     * @return
     */
    public String getYear(int year) {
        if (year < 1900) {
            return "未知";
        }
        int start = 1900;
        String[] years = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪"};
        return years[(year - start) % years.length];
    }
}
