package com.satween.utils;

import android.content.res.AssetManager;

import com.google.common.io.ByteStreams;
import com.google.common.io.Closer;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


/**
 * Encapsulates routine stuff for more often tasks with assets mechanism.<br />
 * <p/>
 * https://github.com/satween/utils
 */
public class AssetsUtil {

    AssetManager assetManager;

    /**
     * @param assetManager - Assets manager that can provide form call Context's getAssets method
     */
    public AssetsUtil(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    /**
     * Extract assetName file form assets folder to given path
     *
     * @param assetName - name of file in assets folder root<br/>
     * @param path      - path to store extracted file<br/>
     * @return
     * @throws java.io.IOException when
     */
    public boolean extractAssetToPath(String assetName, String path) throws IOException {

        Closer closer = Closer.create();
        try {
            InputStream in = closer.register(assetManager.open(assetName));
            OutputStream out = closer.register(new FileOutputStream(path));
            ByteStreams.copy(in, out);
            return true;

        } catch (Throwable e) {
            throw closer.rethrow(e);

        } finally {
            closer.close();
        }
    }

    public String extractToString(String assetName) throws IOException {
        Closer closer = Closer.create();
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = assetManager.open(assetName);
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            return buf.toString();

        } catch (Throwable e) {
            throw closer.rethrow(e);

        } finally {
            closer.close();
        }

    }
}
