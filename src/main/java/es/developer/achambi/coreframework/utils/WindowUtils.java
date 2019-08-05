package es.developer.achambi.coreframework.utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class WindowUtils {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View currentFocus = activity.getCurrentFocus();
            if(currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(
                        currentFocus.getWindowToken(), 0);
            }
        }
    }
}
