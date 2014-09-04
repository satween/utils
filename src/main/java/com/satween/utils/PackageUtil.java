package com.satween.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * This class helps with common package operations
 * <p/>
 * https://github.com/satween/utils
 */
public class PackageUtil {

    private static final int NO_FLAGS = 0;
    private static final String PACKAGE_SCHEME = "package";
    public static final String ARCHIVE = "application/vnd.android.package-archive";

    private Context context;
    private PackageManager packageManager;
    private LogUtil logger = new LogUtil();

    public PackageUtil(Context context, PackageManager packageManager) {
        this.context = context;
        this.packageManager = checkNotNull(packageManager);
    }

    /**
     * @param packageName
     * @return
     */
    public boolean removePackageByName(String packageName) {
        boolean isSuccess = false;
        try {

            PackageInfo packageInfo = checkNotNull(packageManager.getPackageInfo(packageName, NO_FLAGS));
            Intent intent = checkNotNull(new Intent(Intent.ACTION_DELETE,
                    Uri.fromParts(PACKAGE_SCHEME, packageInfo.packageName, null)));

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            isSuccess = true;

        } catch (PackageManager.NameNotFoundException e) {
            logger.logError(this, e);
        } catch (NullPointerException e) {
            logger.logError(this, e);
        }
        return isSuccess;
    }


    /**
     * @param file
     */
    public void installPackageFromFile(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), ARCHIVE);
        context.startActivity(intent);
    }


    /**
     * @param packageName
     * @return
     */
    public boolean isPackageInstalled(String packageName) {
        boolean isPackageExist = false;
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            isPackageExist = true;
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        return isPackageExist;
    }


    /**
     * @param packageName
     */
    public void deletePackageIfExist(String packageName) {
        if (isPackageInstalled(packageName))
            removePackageByName(packageName);
    }


    /**
     * @param packageName
     * @param defaultFilePath
     * @return
     */
    public String getPackagePath(String packageName, String defaultFilePath) {

        for (ApplicationInfo app : packageManager.getInstalledApplications(0)) {
            if (app.packageName.equals(packageName))
                return app.sourceDir;
        }
        return defaultFilePath;
    }

    public PackageManager getPackageManager() {
        return packageManager;
    }
}
