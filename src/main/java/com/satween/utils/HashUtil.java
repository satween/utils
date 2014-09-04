package com.satween.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This util helps with working with calculating hash sums.<br />
 * Current version supports only sha1 format<br />
 * <p/>
 * https://github.com/satween/utils
 */

public class HashUtil {

    /**
     * Calculates sha1 hashsum of file which located by given path
     * @param path
     * @return
     * @throws java.io.IOException
     */
    public String getShaOfFile(String path) throws IOException {
        return getShaOfFile(new File(path));
    }

    /**
     * Calculates sha1 hashsum of given file
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public String getShaOfFile(File file) throws IOException {
        checkNotNull(file);
        IOUtil.checkFileExist(file);
        return Files.asByteSource(file).hash(Hashing.sha1()).toString();
    }

    /**
     * Calculates sha1 hashsum of given string
     * @param content
     * @return
     */
    public String getShaOfString(String content) {
        HashFunction sha1 = Hashing.sha1();
        return sha1.newHasher().putString(content, Charsets.UTF_8).hash().toString();
    }
}
