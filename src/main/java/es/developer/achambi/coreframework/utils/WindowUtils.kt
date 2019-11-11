package es.developer.achambi.coreframework.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

object WindowUtils {
    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager != null) {
            val currentFocus = activity.currentFocus
            if (currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(
                    currentFocus.windowToken, 0
                )
            }
        }
    }
}
