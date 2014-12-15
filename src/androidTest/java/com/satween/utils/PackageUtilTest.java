package com.satween.utils;

import android.content.Context;
import android.test.InstrumentationTestCase;

/**
 * Clearview project<br />
 * http://www.clear--view.com<br />
 * Created by satween <br />
 */

public class PackageUtilTest extends InstrumentationTestCase {

    public void testIsPackageInstalledReturnTrueOnInstalledPackageName() {
        final String installedPackageName = "com.android.contacts";
        Context context = getInstrumentation().getContext();
        PackageUtil packageUtil = new PackageUtil(context, context.getPackageManager());

        assertTrue(packageUtil.isPackageInstalled(installedPackageName));
    }

    public void testIsPackageInstalledReturnFalseOnNotInstalledPackageName() {
        final String notInstalledPackageName = "not.installed.package";
        Context context = getInstrumentation().getContext();
        PackageUtil packageUtil = new PackageUtil(context, context.getPackageManager());

        assertFalse(packageUtil.isPackageInstalled(notInstalledPackageName));
    }
}
