package com.satween.utils;

import android.os.Environment;
import android.test.InstrumentationTestCase;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.satween.utils.IOUtil.checkIsDirectory;

/**
 * Clearview project<br />
 * http://www.clear--view.com<br />
 * Created by satween <br />
 */

public class IOUtilTest extends InstrumentationTestCase {
    final String nonexistentPath = Environment.getExternalStorageDirectory() + "nonexistentPath";
    final String locationToExtract = Environment.getExternalStorageDirectory() + "/extracted.txt";

    public void testCheckPathExistWhenFileNotExistThrowsFIFE() {
        try {
            IOUtil.checkFileExist(nonexistentPath);
            fail("FileNotFoundException wasn't throwed");
        } catch (FileNotFoundException ignored) {
        }
    }

    public void testCheckFileExistWhenFileNotExistThrowsFIFE() {
        try {
            IOUtil.checkFileExist(new File(nonexistentPath));
            fail("FileNotFoundException wasn't throwed");
        } catch (FileNotFoundException ignored) {
        }
    }

    public void testValidDeleting() throws IOException {
        File file = new File(locationToExtract);
        IOUtil ioUtil = new IOUtil();
        dumpAssetFile();

        assertTrue(file.exists());
        assertTrue(ioUtil.deleteFile(locationToExtract));
        assertFalse(file.exists());
    }

    public void testDeleteIfExistNotThrowFIFEWhenFileNotExist() {
        assertFalse(new IOUtil().deleteIfExists(nonexistentPath));
    }

    public void testValidReadToString() throws IOException {
        final String expectedStringContent = "line1\nline2\nline3";
        IOUtil ioUtil = new IOUtil();
        dumpAssetFile();

        assertEquals("Content is invalid", expectedStringContent, ioUtil.readToString(locationToExtract));

        ioUtil.deleteFile(locationToExtract);
    }

    public void testGetFileExtension() throws IOException {
        dumpAssetFile();
        assertEquals("txt", new IOUtil().getFileExtension(locationToExtract));
    }

    public void testCheckIsDirectoryOnFileShouldThrowIAE() throws IOException {
        dumpAssetFile();
        try {
            checkIsDirectory(locationToExtract);
            fail("IllegalArgumentException wasn't throwed");
        } catch (IllegalArgumentException ignored) {
        }
    }

    public void testCheckIsDirectoryOnDirectoryShouldntThrowAnyException() throws IOException {
        dumpAssetFile();
        checkIsDirectory(Environment.getExternalStorageDirectory());
    }

    public void testReadContentToList() throws IOException {
        dumpAssetFile();

        List<String> strings = new IOUtil().readContentToList(locationToExtract);

        assertTrue(strings.contains("line1"));
        assertTrue(strings.contains("line2"));
        assertTrue(strings.contains("line3"));
    }

    public void testGetFilesFromDirectory() throws IOException {
        dumpAssetFile();

        ArrayList<String> expectedFilePaths = new ArrayList<String>();
        expectedFilePaths.add(locationToExtract);
        expectedFilePaths.add(Environment.getExternalStorageDirectory() + "/Download");
        expectedFilePaths.add(Environment.getExternalStorageDirectory() + "/Pictures");
        expectedFilePaths.add(Environment.getExternalStorageDirectory() + "/Alarms");


        List<File> files = new IOUtil().getFilesFromDirectory(Environment.getExternalStorageDirectory().getAbsolutePath());

        List<String> actualPaths = Lists.transform(files, new Function<File, String>() {
            @Override
            public String apply(File input) {
                return input.getAbsoluteFile().getAbsolutePath();
            }
        });

        for (String path : expectedFilePaths) {
            assertTrue("Not contains " + path, actualPaths.contains(path));
        }

    }

    public void testDeleteRecursively() {

        File dir1 = new File(Environment.getExternalStorageDirectory() + "dir1");
        File dir2 = new File(Environment.getExternalStorageDirectory() + "dir1/dir2");

        if (dir2.exists()) {
            dir2.delete();
        }
        if (dir1.exists()) {
            dir1.delete();
        }

        new IOUtil().deleteRecursively(dir2.getAbsolutePath());

        assertFalse(dir2.exists());
        assertFalse(dir1.exists());
    }


    public void testWriteToFile() throws IOException {
        IOUtil ioUtil = new IOUtil();
        final String content = "test content";
        ioUtil.writeToFile(content, new File(locationToExtract));

        assertEquals(content, ioUtil.readToString(new File(locationToExtract)));
    }

    public void testPathCreation() {
        String dirs = Environment.getExternalStorageDirectory() + "/1/2/3/4/";
        IOUtil ioUtil = new IOUtil();
        ioUtil.createFullPathOfFile(dirs);

        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1").exists());
        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1/2").exists());
        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1/2/3").exists());
        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1/2/3/4").exists());

        ioUtil.deleteRecursively(new File(Environment.getExternalStorageDirectory() + "/1"));

        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1").exists());
        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1/2").exists());
        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1/2/3").exists());
        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1/2/3/4").exists());
    }

    public void testPathCreationWhenPathContainDoubleSlashes() {
        String dirs = Environment.getExternalStorageDirectory() + "/1///2/////////3////4/";
        IOUtil ioUtil = new IOUtil();
        ioUtil.createFullPathOfFile(dirs);

        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1").exists());
        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1/2").exists());
        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1/2/3").exists());
        assertTrue(new File(Environment.getExternalStorageDirectory() + "/1/2/3/4").exists());

        ioUtil.deleteRecursively(new File(Environment.getExternalStorageDirectory() + "/1"));

        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1").exists());
        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1/2").exists());
        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1/2/3").exists());
        assertFalse(new File(Environment.getExternalStorageDirectory() + "/1/2/3/4").exists());
    }

    public void testClearPath(){
        IOUtil ioUtil = new IOUtil();

        assertEquals("one/two/three/file", ioUtil.clearPath("one/two//three///file"));
    }

    public void testGetBaseDir(){
        IOUtil ioUtil = new IOUtil();
        assertEquals("base/dir/this", ioUtil.getBaseDir("base/dir/this/file"));
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        File f = new File(locationToExtract);
        if (f.exists()) {
            f.delete();
        }
    }

    private void dumpAssetFile() throws IOException {
        AssetsUtil assetsUtil = new AssetsUtil(getInstrumentation().getContext().getAssets());
        final String assetName = "test_asset.txt";
        assetsUtil.extractAssetToPath(assetName, locationToExtract);
    }
}
