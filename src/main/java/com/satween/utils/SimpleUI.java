package com.satween.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * https://github.com/satween/utils
 */
public class SimpleUI {

    public static void showShortToast(String message, Context context) {
        showToast(message, context, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(String message, Context context) {
        showToast(message, context, Toast.LENGTH_LONG);
    }

    private static void showToast(String message, Context context, int duration) {
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public static void hideKeyboard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
