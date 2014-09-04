package com.satween.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Wrapper for default android's shared preferences tool.
 * <p/>
 * https://github.com/satween/utils
 */

public class SharedSettingsUtil {
    private String name;
    private Context context;


    public static SharedSettingsUtil get(String settingsFileName, Context context) {
        return new SharedSettingsUtil(settingsFileName, context);
    }

    public SharedSettingsUtil(String name, Context context) {
        this.name = name;
        this.context = context;
    }

    public String getString(String key, String defaultValue) {
        return getSharedPreferences(context).getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor e = getSharedPreferences(context).edit();
        e.putString(key, value);
        e.commit();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor e = getSharedPreferences(context).edit();
        e.putBoolean(key, value);
        e.commit();
    }

    protected SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);

    }
}
