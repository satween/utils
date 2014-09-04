package com.satween.utils;

import android.util.Log;

import com.google.common.base.Joiner;

/**
 * Little log helper
 * <p/>
 * https://github.com/satween/utils
 */

public class LogUtil {

    public static LogUtil get() {
        return new LogUtil();
    }

    public static String TAG = "UTILS_LIB";

    public void logError(Object o, String message) {
        Log.e(TAG, format(o, message));
    }

    public void logInfo(Object o, String message) {
        Log.i(TAG, format(o, message));
    }

    public void logError(Object o, Exception e) {
        Log.e(TAG, format(o, e.getMessage()));
    }

    public void logError(Class clazz, String message) {
        Log.e(TAG, format(clazz.getName(), message));
    }

    public void logInfo(Class clazz, String message) {
        Log.i(TAG, format(clazz.getName(), message));
    }

    private String format(Object o, String message) {
        return format(o.getClass().getName(), message);
    }

    private String format(String className, String message) {
        return Joiner.on(" ").join(message, "[", "at", className, "]");
    }

}
