package com.satween.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * https://github.com/satween/utils
 */
public class SimpleUI {

    Context context;

    public SimpleUI(Context context) {
        this.context = context;
    }

    public void showShortToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    public void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    private void showToast(String message, int duration) {
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
