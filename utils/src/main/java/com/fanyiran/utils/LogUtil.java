package com.fanyiran.utils;

import android.util.Log;

public class LogUtil {
    private static final String DEFAULT_SPLIT = ":";

    public static void v(String tag, String msgPrefix, String msg) {
        LogUtil.v(tag, msgPrefix, DEFAULT_SPLIT, msg);
    }

    public static void v(String tag, String msgPrefix, String split, String msg) {
        LogUtil.v(tag, msgPrefix + split + msg);
    }

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }
}
