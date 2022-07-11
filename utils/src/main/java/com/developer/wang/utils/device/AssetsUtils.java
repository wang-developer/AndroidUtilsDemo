package com.developer.wang.utils.device;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Date:2018/7/16
 * Time:20:06
 * Author:Loren
 * Desc: 获取Assets中的文件
 */
public class AssetsUtils {
    /**
     * 获取运营商编号对应的区号
     * @param context
     * @return
     */
    public static String getOperatorJson(Context context) {
        return getJson(context, "operator.json");
    }

    public static String getLoadingJson(Context context) {
        return getJson(context, "aloading.json");
    }

    private static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
