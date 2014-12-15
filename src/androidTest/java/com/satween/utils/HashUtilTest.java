package com.satween.utils;

import android.os.Environment;
import android.test.InstrumentationTestCase;

import java.io.IOException;

/**
 * Clearview project<br />
 * http://www.clear--view.com<br />
 * Created by satween <br />
 */

public class HashUtilTest extends InstrumentationTestCase {

    public void testValidShaOfString() {
        final String content = "Test string";
        final String expectedHashSum = "18af819125b70879d36378431c4e8d9bfa6a2599";
        HashUtil hashUtil = new HashUtil();

        assertEquals(expectedHashSum, hashUtil.getShaOfString(content));
    }

    public void testValidCountOfFile() throws IOException {
        final String expectedHashSum = "0ab7283988e8f49022d126054947f222cbdf0a52";
        final String locationToExtract = Environment.getExternalStorageDirectory() + "/extracted.txt";
        final String assetName = "test_asset.txt";
        HashUtil hashUtil = new HashUtil();

        new AssetsUtil(getInstrumentation().getContext().getAssets()).extractAssetToPath(assetName, locationToExtract);
        assertEquals(expectedHashSum, hashUtil.getShaOfFile(locationToExtract));

        new IOUtil().deleteIfExists(locationToExtract);
    }
}
