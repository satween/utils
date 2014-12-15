package com.satween.utils;

import android.util.Log;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;

/**
 * Little log helper
 * <p/>
 * https://github.com/satween/utils
 */

public class LogUtil {
    public static String TAG = "UTILS";
    String defaultClassName;

    private LogUtil(Object o) {
        this.defaultClassName = o.getClass().getName();
    }


    private LogUtil(Class clazz) {
        this.defaultClassName = clazz.getName();
    }

    public static LogUtil get(Object o) {
        return new LogUtil(o);
    }

    public static LogUtil get(Class clazz) {
        return new LogUtil(clazz);
    }

    public void logError(String message) {
        logError(defaultClassName, message);
    }

    public void logError(String className, String message) {
        Log.e(TAG, format(className, message));
    }

    public void logError(Exception e) {
        logError(defaultClassName, e);
    }

    public void logError(String className, Exception e) {
        Log.e(TAG, format(className, e.getMessage()));
        e.printStackTrace();
    }

    public void logInfo(String message) {
        logInfo(defaultClassName, message);
    }

    public void logInfo(String className, String message) {
        Log.i(TAG, format(className, message));
    }

    public void logDebug(String message) {
        logDebug(defaultClassName, message);
    }

    public void logDebug(String className, String message) {
        Log.d(TAG, format(className, message));
    }

    private String format(String className, String message) {
        return Joiner.on(" ").join("[", "at", className, "]", "\n", Optional.fromNullable(message).or(" unknown "));
    }

    public String getDefaultClassName() {
        return defaultClassName;
    }
}
