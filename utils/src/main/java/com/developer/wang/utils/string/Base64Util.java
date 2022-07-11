package com.developer.wang.utils.string;

/**
 * Created by Administrator on 2018/2/8.
 * Desc:
 */

public class Base64Util {

    public static String encodeBase64Url(String resouse) {
        String resouseEncode = Base64.encode(resouse);
        return resouseEncode.replaceAll("/", "_").replaceAll("=", "").replaceAll("\\+", "-");

    }
}
