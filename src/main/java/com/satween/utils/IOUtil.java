package com.satween.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * IOUtils helps with commons operations with filesystem<br />
 * <p/>
 * https://github.com/satween/utils
 */

public class IOUtil {

    /**
     * @param file
     * @throws java.io.FileNotFoundException
     */
    public static void checkFileExist(File file) throws FileNotFoundException {
        checkNotNull(file);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
    }

    /**
     * @param path
     * @throws java.io.FileNotFoundException
     */
    public static void checkFileExist(String path) throws FileNotFoundException {
        checkFileExist(new File(path));
    }

    /**
     * @param dirName
     * @throws IllegalArgumentException      if given path is not directory
     * @throws java.io.FileNotFoundException if given path is not exist
     */
    public static void checkIsDirectory(String dirName) throws IllegalArgumentException, FileNotFoundException {
        checkIsDirectory(new File(dirName));
    }

    /**
     * @param dir
     * @throws IllegalArgumentException      if given path is not directory
     * @throws java.io.FileNotFoundException if given path is not exist
     */
    public static void checkIsDirectory(File dir) throws IllegalArgumentException, FileNotFoundException {
        checkFileExist(dir);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @param path
     * @return
     * @throws java.io.FileNotFoundException
     */
    public boolean deleteFile(String path) throws FileNotFoundException {
        return deleteFile(new File(path));
    }

    /**
     * @param file
     * @return
     * @throws java.io.FileNotFoundException
     */
    public boolean deleteFile(File file) throws FileNotFoundException {
        checkFileExist(file);
        return file.delete();
    }

    /**
     * Delete file. If file not exists FileNotFoundException not throwed.
     *
     * @param path
     * @return
     */
    public boolean deleteIfExists(String path) {
        return deleteIfExists(new File(path));
    }

    /**
     * Delete file. If file not exists FileNotFoundException not throwed.
     *
     * @param file
     * @return
     */
    public boolean deleteIfExists(File file) {
        checkNotNull(file);
        try {
            return deleteFile(file);
        } catch (FileNotFoundException ignored) {
            return false;
        }
    }

    /**
     * Read file content and return them as string
     *
     * @param pathOfTextFile
     * @return
     * @throws java.io.IOException
     */
    public String readToString(String pathOfTextFile) throws IOException {
        return readToString(new File(pathOfTextFile));
    }

    /**
     * Read file content and return them as string
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public String readToString(File file) throws IOException {
        checkFileExist(file);
        return Files.toString(file, Charsets.UTF_8);
    }

    public String getFileExtension(String path) throws FileNotFoundException {
        return Files.getFileExtension(path);
    }

    /**
     * Read file content and return array of them, separated by lines
     *
     * @param filePath
     * @return
     * @throws java.io.IOException
     */
    public List<String> readContentToList(String filePath) throws IOException {
        return readContentToList(new File(filePath));
    }

    /**
     * Read file content and return array of them, separated by lines
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public List<String> readContentToList(File file) throws IOException {
        checkFileExist(file);
        return Files.readLines(file, Charsets.UTF_8);
    }

    /**
     * Returns list of files objects in given directory
     *
     * @param dirName
     * @return
     * @throws java.io.FileNotFoundException if given file not exist
     * @throws IllegalArgumentException      if given file is not directory
     */
    public List<File> getFilesFromDirectory(String dirName) throws FileNotFoundException {
        return getFilesFromDirectory(new File(dirName));
    }

    public List<File> getFilesFromDirectory(File dir) throws FileNotFoundException {
        checkIsDirectory(dir);
        return Files.fileTreeTraverser().breadthFirstTraversal(dir).toList();
    }

    /**
     * Delete file tree from given root. If root is file just deletes given file
     *
     * @param rootPath
     * @return
     */
    public boolean deleteRecursively(String rootPath) {
        checkNotNull(rootPath);
        return deleteRecursively(new File(rootPath));
    }

    /**
     * Delete file tree from given root. If root is file just deletes given file
     *
     * @param root
     * @return
     */
    public boolean deleteRecursively(File root) {
        boolean result = true;
        if (root.isDirectory()) {
            for (File file : root.listFiles()) {
                result &= deleteRecursively(file);
            }
        }
        result &= root.delete();

        return result;
    }


    /**
     * @param data
     * @param file
     * @throws java.io.IOException
     */
    public void writeToFile(String data, File file) throws IOException {
        Files.write(data, file, Charsets.UTF_8);
    }


    public InputStream streamFromFile(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public InputStream streamFromString(String string) {
        return new ByteArrayInputStream(string.getBytes(Charsets.UTF_8));
    }


    /**
     * Wrapper for check path existing
     *
     * @param path
     * @return
     */
    public boolean isFileExist(String path) {
        checkNotNull(path);
        return new File(path).exists();
    }


    /**
     * Checks that directory exist
     *
     * @param path
     * @return
     */
    public boolean isDirectoryExist(String path) {
        checkNotNull(path);
        return new File(path).isDirectory();
    }

    /**
     * Clearing all double and more slashes in path string
     *
     * @param path
     * @return
     */
    public String clearPath(String path) {
        return path.replaceAll("[//]+", "/");
    }

    /**
     * Returns a base directory name of given path
     *
     * @param path
     * @return
     */
    public String getBaseDir(String path) {
        return path.substring(0, path.lastIndexOf("/"));
    }

    /**
     * Creates all needle dirs in given path
     *
     * @param path
     * @return
     */
    public boolean createFullPath(String path) {
        checkNotNull(path);
        path = clearPath(path);

        return new File(getBaseDir(clearPath(path))).mkdirs();
    }

}
