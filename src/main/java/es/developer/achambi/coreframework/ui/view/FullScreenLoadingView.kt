package es.developer.achambi.coreframework.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import es.developer.achambi.coreframework.R
import kotlinx.android.synthetic.main.full_screen_loading_layout.view.*

class FullScreenLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.full_screen_loading_layout, this, true)
    }
    fun showProgress() {
        progress_background.visibility = View.VISIBLE
    }

    fun showProgressSuccess() {
        progress_background.visibility = View.GONE
    }

    fun showError(errorMessage: String) {
        progress_background.visibility = View.VISIBLE
        base_request_progress_bar.visibility = View.GONE
        base_request_error_message.visibility = View.VISIBLE
        base_request_error_message.text = errorMessage
    }
}