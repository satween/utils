package com.satween.utils;

/**
 * Common functions for simplifying code when working with {@code null} object
 * <p/>
 * https://github.com/satween/utils
 */

public class NullUtil {

    /**
     * Returns true if object is {@code null}
     *
     * @param object the object to check
     * @param <T>    type of object
     * @return true if object is {@code null}
     */
    public static <T> boolean isNull(T object) {
        return object == null;
    }


    /**
     * Returns true if object is not {@code null}
     *
     * @param object the object to check
     * @param <T>    type of object
     * @return true if object is {@code null}
     */
    public static <T> boolean isNotNull(T object) {
        return object != null;
    }

    /**
     * Returns true if any of elements is {@code null}
     *
     * @param elements the elements to check
     * @param <E>      the type of the elements
     * @return true if at least one of element is {@code null}
     */
    public static <E> boolean isAnyNull(E... elements) {
        for (E e : elements) {
            if (isNull(e)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Executes runnable code, if given object is not {@code null} <br />
     * Code in runnable object executes in current thread. <br />
     *
     * @param object   te object check to {@code null}
     * @param runnable the runnable that runs if object is not {@code null}
     * @param <T>      the element of object
     */
    public static <T> void runIfNotNull(T object, Runnable runnable) {
        if (isNull(object)) {
            runnable.run();
        }
    }




}
