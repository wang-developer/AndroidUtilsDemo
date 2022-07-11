package com.developer.wang.utils.gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Date:2018/12/17
 * Time:14:30
 * Author:Loren
 * Desc:
 */
public class GsonUtils {

    /**
     * 数据是否为jsonobject
     * @param jsonStr
     * @return
     */
    public static boolean isJsonObject(String jsonStr) {
        try {
            Object json = new JSONTokener(jsonStr).nextValue();
            if (json instanceof JSONObject) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 数据是否为jsonarray
     * @param jsonStr
     * @return
     */
    public static boolean isJsonArray(String jsonStr) {
        try {
            Object json = new JSONTokener(jsonStr).nextValue();
            if (json instanceof JSONArray) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

}
