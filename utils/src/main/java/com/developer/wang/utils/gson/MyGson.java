package com.developer.wang.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Date:2018/7/6
 * Time:11:07
 * Author:Loren
 * Desc: 对服务器返回非字符串字段为""或者null做处理(理论上不会返回null)
 */
public class MyGson {

    private static Gson gson;

    /**
     * 增加后台返回""和"null"的处理
     * 1.int=>0
     * 2.double=>0.00
     * 3.long=>0L
     *
     * @return
     */

    public static Gson buildGson() {
        if (gson == null) {
            synchronized (MyGson.class) {
                gson = new GsonBuilder()
                        .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                        .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                        .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                        .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                        .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                        .registerTypeAdapter(long.class, new LongDefault0Adapter())
                        .registerTypeAdapterFactory(new StringNullAdapterFactory())
                        .create();
            }
        }
        return gson;
    }
}
