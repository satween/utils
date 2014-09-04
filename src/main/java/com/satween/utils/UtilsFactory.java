package com.satween.utils;

import android.content.Context;

/**
 * Factory for utils
 * <p/>
 * https://github.com/satween/utils
 */

public class UtilsFactory {
    private Context context;

    public UtilsFactory(Context context) {
        this.context = context;
    }

    /**
     * @return new {@link com.satween.utils.AssetsUtil} object
     */
    public AssetsUtil createAssetsUtil() {
        return new AssetsUtil(context.getAssets());
    }

    /**
     * @return new {@link com.satween.utils.DateUtil} object
     */
    public DateUtil createDateUtil() {
        return new DateUtil();
    }

    /**
     * @return new {@link com.satween.utils.HashUtil} object
     */
    public HashUtil createHashUtil() {
        return new HashUtil();
    }

    /**
     * @return new {@link com.satween.utils.IOUtil} object
     */
    public IOUtil createIoUtil() {
        return new IOUtil();
    }

    /**
     * @return new {@link com.satween.utils.PackageUtil} object
     */
    public PackageUtil createPackageUtil() {
        return new PackageUtil(context, context.getPackageManager());
    }

    /**
     * @param settingsName - settings file name
     * @return new {@link com.satween.utils.SharedSettingsUtil} object
     */
    public SharedSettingsUtil createSharedSettingsUtil(String settingsName) {
        return new SharedSettingsUtil(settingsName, context);
    }

    /**
     * @return new {@link com.satween.utils.SimpleUI} object
     */
    public SimpleUI createSimpleUI() {
        return new SimpleUI();
    }

    /**
     * @return new {@link com.satween.utils.ConnectivityUtil} object
     */
    public ConnectivityUtil createConnectivityUtil() {
        return new ConnectivityUtil(context);
    }

    /**
     * @return given {@link android.content.Context}
     */
    public Context getContext() {
        return context;
    }
}
