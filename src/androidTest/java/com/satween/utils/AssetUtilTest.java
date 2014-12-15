package com.satween.utils;

import android.os.Environment;
import android.test.InstrumentationTestCase;

import java.io.File;
import java.io.IOException;

/**
 * Clearview project<br />
 * http://www.clear--view.com<br />
 * Created by satween <br />
 */

public class AssetUtilTest extends InstrumentationTestCase {

    public void testValidAssetToFileExtraction() throws IOException {
        final String assetName = "test_asset.txt";
        final String pathToExtract = Environment.getExternalStorageDirectory().getAbsolutePath() + "/extracted.txt";
        AssetsUtil assetsUtil = new AssetsUtil(getInstrumentation().getContext().getAssets());


        assetsUtil.extractAssetToPath(assetName, pathToExtract);

        assertTrue(new File(pathToExtract).exists());
    }

    public void testValidAssetToStringExtraction() throws IOException {
        final String assetName = "test_asset.txt";

        AssetsUtil assetsUtil = new AssetsUtil(getInstrumentation().getContext().getAssets());


        String extractedContent = assetsUtil.extractToString(assetName);

        assertEquals("line1line2line3", extractedContent);

    }
}
