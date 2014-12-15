package com.satween.utils;

import java.io.File;

public class Checkers {
    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static String checkNotEmpty(String value, String message) {
        if ("".equals(value)) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static String checkPathAccessible(String path, String message) {
        File file = new File(path);

        if (!file.isDirectory()) {
            file = file.getParentFile();
        }

        if (file == null || !file.exists()) {
            throw new IllegalArgumentException(message);
        }

        return path;
    }
}
